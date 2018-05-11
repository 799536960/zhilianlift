package com.duma.ld.zhilianlift.view.start;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HouseMapModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.PinPaiMenDianModel;
import com.duma.ld.zhilianlift.util.ArithUtil;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.StoreByBrand;

/**
 * 品牌门店列表
 * Created by liudong on 2018/1/25.
 */

public class PinPaiMenDIanActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;

    private String id;
    private BaseAdapter<PinPaiMenDianModel.StoreListBean> mAdapter;
    private boolean isJuli = true;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_pinpai_mendian).setTopBar_A("品牌门店").setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        id = getIntent().getStringExtra(Constants.id);
        mAdapter = new BaseAdapter.Builder<PinPaiMenDianModel.StoreListBean>(rvList, mActivity, R.layout.adapter_pinpai_mendian)
                .buildLoad(new OnBaseLoadAdapterListener<PinPaiMenDianModel.StoreListBean>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<PinPaiMenDianModel>>get(StoreByBrand)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .params("brand_id", id)
                                .params("latitude", SpDataUtil.getLocation().getmLatitude())
                                .params("longitude", SpDataUtil.getLocation().getmLongitude())
                                .execute(new MyJsonCallback<HttpResModel<PinPaiMenDianModel>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<PinPaiMenDianModel>> respons, HttpResModel<PinPaiMenDianModel> listHttpResModel) {
                                        if (listHttpResModel.getResult().getType() == 2) {
                                            isJuli = false;
                                        }
                                        mAdapter.setLoadData(listHttpResModel.getResult().getStore_list());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, PinPaiMenDianModel.StoreListBean item) {
                        ImageLoader.with(item.getStore_logo(), (ImageView) helper.getView(R.id.img_icon));
                        helper.setText(R.id.tv_name, item.getStore_name() + "")
                                .setText(R.id.tv_address, item.getStore_address() + "");
                        TextView tv_jili = helper.getView(R.id.tv_jili);
                        if (isJuli) {
                            try {
                                tv_jili.setText(ArithUtil.div(item.getDistance(), "1000", 2) + "km");
                            } catch (IllegalAccessException e) {
                            }
                        } else {
                            tv_jili.setText("定位失败");
                        }
                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PinPaiMenDianModel.StoreListBean storeListBean = mAdapter.getData().get(position);
                IntentUtil.goHouseMap(mActivity, new HouseMapModel(storeListBean.getStore_name(), storeListBean.getStore_address(), storeListBean.getLatitude(), storeListBean.getLongitude()));
            }
        });
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }
}
