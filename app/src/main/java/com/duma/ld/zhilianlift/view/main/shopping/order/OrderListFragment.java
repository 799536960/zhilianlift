package com.duma.ld.zhilianlift.view.main.shopping.order;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.OrderEventModel;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.Constants.Order_Text_ChaKanWuLiu;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_PinJia;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_QuXiaoDinDan;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_QuZhiFu;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_QueRenShouHuo;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_ShanChuDinDan;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_ZaiCiGouMai;
import static com.duma.ld.zhilianlift.util.HttpUrl.getOrderList;
import static com.duma.ld.zhilianlift.util.HttpUrl.logistics;

/**
 * 订单列表
 * 待付款->取消订单 去支付
 * 待发货->  --     查看详情
 * 待收货->查看物流 确认收货
 * 待评价->再次购买 评价
 * 已取消->删除订单 再次购买
 * 已完成->删除订单 再次购买
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
    private OrderListActivity orderListActivity;

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
        orderListActivity = (OrderListActivity) mActivity;
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
    public void onSupportVisible() {
        super.onSupportVisible();
        //tab变化时才加载 如果加载一次后 tab没变化就不加载
        if (orderListActivity.isRefresh()) {
            orderListActivity.setRefresh(false);
            onClickLoadingRefresh();
        }
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<OrderModel>(rvList, mActivity, R.layout.adapter_order_list)
                .setTitleOrDrawableId("没有当前类型的订单!", R.drawable.ld2)
                .buildLoad(new OnBaseLoadAdapterListener<OrderModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
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
                    public void convert(final BaseViewHolder helper, final OrderModel item) {
                        PublicUtil.getViewOrder(mActivity, helper, item, false, new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                // 跳转到订单详情
                                IntentUtil.goOrderInfo(mActivity, helper.getLayoutPosition(), item.getOrder_id() + "", type);
                            }
                        });
                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtil.goOrderInfo(mActivity, position, mAdapter.getData().get(position).getOrder_id() + "", type);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView textView = (TextView) view;
                OrderModel orderModel = mAdapter.getData().get(position);
                switch (textView.getText().toString()) {
                    case Order_Text_QuXiaoDinDan:
                        cancelOrder(position, orderModel.getOrder_id());
                        break;
                    case Order_Text_QuZhiFu:
                        IntentUtil.goPay(mActivity, orderModel.getOrder_id(), orderModel.getDaFuKuan());
                        break;
                    case Order_Text_ChaKanWuLiu:
                        //跳转
                        IntentUtil.goWebView(mActivity, logistics + orderModel.getOrder_id(), "订单跟踪");
                        break;
                    case Order_Text_QueRenShouHuo:
                        quRenShouHuo(position, orderModel.getOrder_id());
                        break;
                    case Order_Text_ZaiCiGouMai:
                        zaiCiGouMai(orderModel.getMaster_order_sn());
                        break;
                    case Order_Text_PinJia:
                        //跳转
                        IntentUtil.goAddComment(mActivity, position, orderModel.getOrder_id() + "", type);
                        break;
                    case Order_Text_ShanChuDinDan:
                        deleteOrder(position, orderModel.getOrder_id());
                        break;
                    default:
                        IntentUtil.goOrderInfo(mActivity, position, orderModel.getOrder_id() + "", type);
                        break;
                }
            }
        });
    }


    /**
     * 删除订单
     */
    private void deleteOrder(final int position, final int order_id) {
        PublicUtil.HttpDeleteOrder(mActivity, position, order_id, new MyJsonCallback<HttpResModel<OrderModel>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<OrderModel>> respons, HttpResModel<OrderModel> orderModelHttpResModel) {
                mAdapter.remove(position);
            }
        }.isDialog(mActivity));
    }

    /**
     * 再次购买
     */
    private void zaiCiGouMai(String sn) {
        PublicUtil.HttpZaiCiGouMai(sn, mActivity);
    }

    /**
     * 确认收货
     */
    private void quRenShouHuo(final int position, final int order_id) {
        PublicUtil.HttpQuRenShouHuo(mActivity, position, order_id, new MyJsonCallback<HttpResModel<OrderModel>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<OrderModel>> respons, HttpResModel<OrderModel> orderModelHttpResModel) {
                refreshModel(orderModelHttpResModel.getResult(), position);
            }
        }.isDialog(mActivity));
    }

    /**
     * 取消订单
     */
    private void cancelOrder(final int position, final int order_id) {
        PublicUtil.HttpCancelOrder(mActivity, position, order_id, new MyJsonCallback<HttpResModel<OrderModel>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<OrderModel>> respons, HttpResModel<OrderModel> orderModelHttpResModel) {
                refreshModel(orderModelHttpResModel.getResult(), position);
            }
        }.isDialog(mActivity));
    }

    private void refreshModel(OrderModel model, int position) {
        if (mAdapter.getData().get(position).getOrder_id() != model.getOrder_id()) {
            //说明不是该订单 不做任何动作
            return;
        }
        if (type.isEmpty()) {
            mAdapter.setData(position, model);
        } else {
            mAdapter.remove(position);
        }
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == Constants.event_refresh_order || eventModel.getCode() == Constants.event_refresh_order_comment) {
            OrderEventModel model = (OrderEventModel) eventModel.getData();
            if (model.getType().equals(type)) {
                if (model.isDelete()) {
                    //删除
                    mAdapter.remove(model.getPosition());
                } else {
                    refreshModel(model.getModel(), model.getPosition());
                }
            }
        }
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }
}
