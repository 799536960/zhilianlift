package com.duma.ld.zhilianlift.view.main.shopping.order;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.OrderEventModel;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.util.ClipboardUtils;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.Order_Text_ChaKanWuLiu;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_PinJia;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_QuXiaoDinDan;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_QuZhiFu;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_QueRenShouHuo;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_ShanChuDinDan;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_ZaiCiGouMai;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiFaHuo;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiFuKuan;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiPinJia;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiShouHuo;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_YiQuXiao;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_YiWanChen;
import static com.duma.ld.zhilianlift.util.HttpUrl.logistics;
import static com.duma.ld.zhilianlift.util.HttpUrl.order_detail;

/**
 * 订单详情
 * Created by liudong on 2018/1/10.
 */

public class OrderInfoActivity extends BaseMyActivity {
    @BindView(R.id.tv_courier_state)
    TextView tvCourierState;
    @BindView(R.id.tv_courier_type)
    TextView tvCourierType;
    @BindView(R.id.tv_address_name)
    TextView tvAddressName;
    @BindView(R.id.tv_address_phone)
    TextView tvAddressPhone;
    @BindView(R.id.tv_address_info)
    TextView tvAddressInfo;
    @BindView(R.id.layout_address)
    ConstraintLayout layoutAddress;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_sn_num)
    TextView tvSnNum;
    @BindView(R.id.tv_sn_copy)
    TextView tvSnCopy;
    @BindView(R.id.tv_sn_time)
    TextView tvSnTime;
    @BindView(R.id.tv_wallet_type)
    TextView tvWalletType;
    @BindView(R.id.tv_wallet_money)
    TextView tvWalletMoney;
    @BindView(R.id.layout_pay_wallet)
    LinearLayout layoutPayWallet;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_pay_money)
    TextView tvPayMoney;
    @BindView(R.id.tv_courier_type2)
    TextView tvCourierType2;
    @BindView(R.id.tv_goods_money)
    TextView tvGoodsMoney;
    @BindView(R.id.tv_courier_money)
    TextView tvCourierMoney;
    @BindView(R.id.tv_order_money)
    TextView tvOrderMoney;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.tv_shouhou)
    TextView tvShouhou;
    @BindView(R.id.tv_hui)
    TextView tvHui;
    @BindView(R.id.tv_hong)
    TextView tvHong;
    @BindView(R.id.layout_content)
    LinearLayout layoutContent;
    @BindView(R.id.layout_root)
    FrameLayout layoutRoot;
    @BindView(R.id.layout_pay)
    LinearLayout layoutPay;
    @BindView(R.id.img_courier_state)
    ImageView imgCourierState;
    private int position;
    private String sn;
    private String type;
    private BaseAdapter<OrderModel> mAdapter;
    private OrderModel model;
    private OrderEventModel eventModel;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_order_info).setTopBar_A("订单详情").setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.layout_content);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        position = getIntent().getIntExtra(Constants.position, 0);
        sn = getIntent().getStringExtra(Constants.key);
        type = getIntent().getStringExtra(Constants.Type);
        eventModel = new OrderEventModel(position, type);
        mAdapter = new BaseAdapter.Builder<OrderModel>(rvList, mActivity, R.layout.adapter_order_list)
                .isNested()
                .setNoEnpty()
                .build(new OnBaseAdapterListener<OrderModel>() {
                    @Override
                    public void convert(BaseViewHolder helper, OrderModel item) {
                        PublicUtil.getViewOrder(mActivity, helper, item, true, new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                //点击商品跳转商品详情页
                                OrderModel.OrderGoodsBean item = (OrderModel.OrderGoodsBean) adapter.getData().get(position);
                                IntentUtil.goGoodsDetails(mActivity, item.getGoods_id());
                            }
                        });
                    }
                });
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<OrderModel>>get(order_detail)
                .params("sn", sn)
                .execute(new MyJsonCallback<HttpResModel<OrderModel>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<OrderModel>> respons, HttpResModel<OrderModel> orderModelHttpResModel) {
                        initData(orderModelHttpResModel.getResult());
                    }
                });
    }

    private void initData(OrderModel result) {
        model = result;
        List<OrderModel> list = new ArrayList<>();
        list.add(result);
        mAdapter.setNewData(list);
        tvAddressName.setText(result.getConsignee());
        tvAddressInfo.setText(result.getTotal_address());
        tvAddressPhone.setText(ZhuanHuanUtil.setPhoneXX(result.getMobile()));
        tvSnNum.setText("订单编号：" + result.getMaster_order_sn());
        tvSnTime.setText("下单时间：" + ZhuanHuanUtil.Time2fen(result.getAdd_time() * 1000));
        tvGoodsMoney.setText("¥" + result.getGoods_price());
        tvCourierMoney.setText("¥" + result.getShipping_price());
        tvOrderMoney.setText("¥" + result.getDaFuKuan());
        //支付方式 钱包支付
        layoutPayWallet.setVisibility(View.VISIBLE);
        if (result.getUser_money() != 0) {
            tvWalletType.setText("余额支付");
            tvWalletMoney.setText("¥" + result.getUser_money());
        } else if (result.getIntegral_money() != 0) {
            tvWalletType.setText("积分支付");
            tvWalletMoney.setText("¥" + result.getIntegral_money());
        } else if (result.getRenovation_money() != 0) {
            tvWalletType.setText("装修资金支付");
            tvWalletMoney.setText("¥" + result.getRenovation_money());
        } else {
            layoutPayWallet.setVisibility(View.GONE);
        }
        if (StringUtils.isEmpty(result.getPay_name())) {
            layoutPay.setVisibility(View.GONE);
        } else {
            layoutPay.setVisibility(View.VISIBLE);
            tvPayType.setText(result.getPay_name());
            tvPayMoney.setText(result.getDaFuKuan() + "");
        }
        //订单状态
        tvCourierType.setText("普通快递");
        PublicUtil.refreshOrderBut(tvHui, tvHong, result.getOrder_status_code(), false);
        tvShouhou.setVisibility(View.GONE);
        switch (result.getOrder_status_code()) {
            case Order_Type_DaiFuKuan:
                tvCourierState.setText("等待付款");
                imgCourierState.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.pay));
                tvCourierType.setText("需付款：¥" + result.getDaFuKuan());
                break;
            case Order_Type_DaiFaHuo:
                tvCourierState.setText("正在出库");
                imgCourierState.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.out));
                tvShouhou.setVisibility(View.VISIBLE);
                break;
            case Order_Type_DaiShouHuo:
                tvCourierState.setText("已出库");
                imgCourierState.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.car));
                break;
            case Order_Type_DaiPinJia:
                tvCourierState.setText("完成");
                imgCourierState.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.complete));
                tvShouhou.setVisibility(View.VISIBLE);
                break;
            case Order_Type_YiQuXiao:
                tvCourierState.setText("已取消");
                imgCourierState.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.cancel));
                tvCourierType.setText("");
                break;
            case Order_Type_YiWanChen:
                tvCourierState.setText("完成");
                imgCourierState.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.complete));
                tvShouhou.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.tv_sn_copy, R.id.tv_shouhou, R.id.tv_hui, R.id.tv_hong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sn_copy:
                ClipboardUtils.copyText(model.getMaster_order_sn());
                TsUtils.show("复制订单编号成功!");
                break;
            case R.id.tv_shouhou:
                IntentUtil.goAddAfterSalesList(mActivity, model.getOrder_goods(), model.getOrder_id() + "");
                break;
            case R.id.tv_hui:
                clickBtn(tvHui);
                break;
            case R.id.tv_hong:
                clickBtn(tvHong);
                break;
        }
    }

    public void clickBtn(TextView textView) {
        switch (textView.getText().toString()) {
            case Order_Text_QuXiaoDinDan:
                cancelOrder();
                break;
            case Order_Text_QuZhiFu:
                IntentUtil.goPay(mActivity, model.getMaster_order_sn(), model.getDaFuKuan());
                break;
            case Order_Text_ChaKanWuLiu:
                IntentUtil.goWebView(mActivity, logistics + model.getOrder_id(), "订单跟踪");
                break;
            case Order_Text_QueRenShouHuo:
                quRenShouHuo();
                break;
            case Order_Text_ZaiCiGouMai:
                zaiCiGouMai();
                break;
            case Order_Text_PinJia:
                IntentUtil.goAddComment(mActivity, position, model.getOrder_id() + "", type);
                break;
            case Order_Text_ShanChuDinDan:
                deleteOrder();
                break;
            default:
                break;
        }
    }

    @Override
    public void finish() {
        if (eventModel.isSend()) {
            EventBusUtil.sendModel(Constants.event_refresh_order, eventModel);
        }
        super.finish();
    }

    /**
     * 删除订单
     */
    private void deleteOrder() {
        PublicUtil.HttpDeleteOrder(mActivity, position, model.getOrder_id(), new MyJsonCallback<HttpResModel<OrderModel>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<OrderModel>> respons, HttpResModel<OrderModel> orderModelHttpResModel) {
                eventModel.setDelete(true);
                finish();
            }
        }.isDialog(mActivity));
    }


    /**
     * 确认收货
     */
    private void quRenShouHuo() {
        PublicUtil.HttpQuRenShouHuo(mActivity, position, model.getOrder_id(), new MyJsonCallback<HttpResModel<OrderModel>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<OrderModel>> respons, HttpResModel<OrderModel> orderModelHttpResModel) {
                eventModel.setModel(orderModelHttpResModel.getResult());
                initData(orderModelHttpResModel.getResult());
            }
        }.isDialog(mActivity));
    }

    /**
     * 取消订单
     */
    private void cancelOrder() {
        PublicUtil.HttpCancelOrder(mActivity, position, model.getOrder_id(), new MyJsonCallback<HttpResModel<OrderModel>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<OrderModel>> respons, HttpResModel<OrderModel> orderModelHttpResModel) {
                eventModel.setModel(orderModelHttpResModel.getResult());
                initData(orderModelHttpResModel.getResult());
            }
        }.isDialog(mActivity));
    }

    /**
     * 再次购买
     */
    private void zaiCiGouMai() {
        PublicUtil.HttpZaiCiGouMai(model.getMaster_order_sn(), mActivity);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == Constants.event_refresh_order_comment) {
            OrderEventModel model = (OrderEventModel) eventModel.getData();
            OrderInfoActivity.this.eventModel.setModel(model.getModel());
            initData(model.getModel());
        }
    }
}
