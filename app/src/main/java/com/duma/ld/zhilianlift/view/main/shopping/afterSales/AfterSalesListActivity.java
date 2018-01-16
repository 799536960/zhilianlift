package com.duma.ld.zhilianlift.view.main.shopping.afterSales;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.AfterSalesListModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.return_list;

/**
 * 售后详情列表
 * Created by liudong on 2018/1/12.
 */

public class AfterSalesListActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    FrameLayout layoutRoot;
    private BaseAdapter<AfterSalesListModel> mAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_after_sales_list).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading).setTopBar_A("退款/售后");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mAdapter = new BaseAdapter.Builder<AfterSalesListModel>(rvList, mActivity, R.layout.adapter_after_sales_list)
                .buildLoad(new OnBaseLoadAdapterListener<AfterSalesListModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<List<AfterSalesListModel>>>get(return_list)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .execute(new MyJsonCallback<HttpResModel<List<AfterSalesListModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<AfterSalesListModel>>> respons, HttpResModel<List<AfterSalesListModel>> myRecordModel) {
                                        mAdapter.setLoadData(myRecordModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, AfterSalesListModel item) {
                        PublicUtil.getViewOrderGoods(helper, item, mActivity);
                        helper.setText(R.id.tv_String_type, item.getContext());
                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtil.goAfterSalesInfo(mActivity, mAdapter.getData().get(position).getId() + "");
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
