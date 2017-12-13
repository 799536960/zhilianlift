package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.duma.ld.zhilianlift.model.AddresModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.getAddressList;

/**
 * Created by liudong on 2017/12/11.
 */

public class AddresListActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.tv_addres)
    TextView tvAddres;
    private BaseAdapter<AddresModel> adapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_addres_list)
                .setTopBar_A("地址管理")
                .setRefresh_A(R.id.sw_loading, R.id.layout_content, R.id.rv_list);
    }

    @OnClick(R.id.tv_addres)
    public void onViewClicked() {
        IntentUtil.goAdd_Addres(mActivity);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        adapter = new BaseAdapter.Builder<AddresModel>(rvList, mActivity, R.layout.rv_dizhi_list)
                .buildLoad(new OnBaseLoadAdapterListener<AddresModel>() {
                    @Override
                    public void onLoadHttp(int page, int httpTag) {
                        OkGo.<HttpResModel<List<AddresModel>>>get(getAddressList)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .execute(new MyJsonCallback<HttpResModel<List<AddresModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<AddresModel>>> respons, HttpResModel<List<AddresModel>> listHttpResModel) {
                                        adapter.setLoadData(listHttpResModel.getResult());
                                    }
                                }.setLoadAdapter(adapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, AddresModel item) {
                        helper.setText(R.id.tv_name, item.getConsignee())
                                .setText(R.id.tv_dianhua, item.getMobile())
                                .setText(R.id.tv_xiangxidizhi, item.getProvince_city_district() + " " + item.getAddress());

                    }
                });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtil.goAddOrChange(mActivity, (AddresModel) adapter.getData().get(position));
            }
        });
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        adapter.onRefresh();
    }


}
