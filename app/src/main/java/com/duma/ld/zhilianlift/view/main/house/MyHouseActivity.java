package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.MyHouseModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.getMyHouse;

/**
 * 我的二手房
 * Created by liudong on 2018/1/9.
 */

public class MyHouseActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    FrameLayout layoutRoot;
    @BindView(R.id.tv_sell)
    TextView tvSell;
    private boolean isChuZu;
    private BaseAdapter<MyHouseModel> mAdapter;
    private String httpType;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_my_second_house).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        if (StringUtils.isEmpty(getIntent().getStringExtra(Constants.key))) {
            isChuZu = false;
            mActivityConfig.setTopBar_A("我的二手房");
            tvSell.setText("我要出售");
            httpType = "2";
        } else {
            isChuZu = true;
            mActivityConfig.setTopBar_A("我的出租");
            tvSell.setText("我要出租");
            httpType = "3";
        }
        initAdapter();
        onClickLoadingRefresh();
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<MyHouseModel>(rvList, mActivity, R.layout.adapter_my_house_list)
                .buildLoad(new OnBaseLoadAdapterListener<MyHouseModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<List<MyHouseModel>>>get(getMyHouse)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .params("house_type", httpType)
                                .execute(new MyJsonCallback<HttpResModel<List<MyHouseModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<MyHouseModel>>> respons, HttpResModel<List<MyHouseModel>> listHttpResModel) {
                                        mAdapter.setLoadData(listHttpResModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, MyHouseModel item) {
                        helper.setText(R.id.tv_name, item.getHouse_name() + "")
                                .setText(R.id.tv_spec1, item.getDoor_door() + "室" + item.getOffice() + "厅" + item.getToilet() + "卫  " +
                                        item.getArchitecture() + "㎡  " + item.getOrientation())
                                .setText(R.id.tv_spec2, item.getHouse_name() + "")
                                .setText(R.id.tv_time, ZhuanHuanUtil.Time2fen(item.getOn_time() * 1000));
                        ImageLoader.with(item.getOriginal_img(), (ImageView) helper.getView(R.id.img_icon));

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }

    @OnClick(R.id.tv_sell)
    public void onViewClicked() {
        if (isChuZu) {
            IntentUtil.goAddRentalHouse(mActivity);
        } else {
            IntentUtil.goAddSecondHouse(mActivity);
        }

    }
}
