package com.duma.ld.zhilianlift.view.main.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.OrderGoodsAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.getOrderList;

/**
 * 订单列表
 * Created by liudong on 2018/1/9.
 */

public class OrderListFragment extends BaseMyFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    FrameLayout layoutRoot;
    private String type;
    private BaseAdapter<OrderModel> mAdapter;

    public static OrderListFragment newInstance(String type) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle args = new Bundle();
        args.putString(Constants.Type, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_order_list).setRefresh_f(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            type = args.getString(Constants.Type);
        } else {
            TsUtils.show("type获取失败!");
            mActivity.finish();
        }
        initAdapter();
        mFragmentConfig.showLoadingView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        onClickLoadingRefresh();
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<OrderModel>(rvList, mActivity, R.layout.adapter_order_list)
                .buildLoad(new OnBaseLoadAdapterListener<OrderModel>() {
                    @Override
                    public void onLoadHttp(int page, int httpTag) {
                        OkGo.<HttpResModel<List<OrderModel>>>get(getOrderList)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .params("type", type)
                                .execute(new MyJsonCallback<HttpResModel<List<OrderModel>>>(mFragmentConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<OrderModel>>> respons, HttpResModel<List<OrderModel>> myRecordModel) {
                                        mAdapter.setLoadData(myRecordModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, OrderModel item) {
                        OrderGoodsAdapter adapter = new OrderGoodsAdapter((RecyclerView) helper.getView(R.id.rv_goodsList), mActivity);
                        adapter.getmAdapter().setNewData(item.getOrder_goods());
                        helper.setText(R.id.tv_orderType, item.getOrder_status_detail());
//                                .setText(R.id.tv_orderMoney, "共"++"件商品,合计付款:¥498.00(含运费¥0.00)");
                    }
                });
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }
}
