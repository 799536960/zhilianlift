package com.duma.ld.zhilianlift.view.main.shopping.order;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.CommitOrderModel;
import com.duma.ld.zhilianlift.model.ConfirmOrderModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.ShoppingSpacModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.widget.RedioLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.Cart2;
import static com.duma.ld.zhilianlift.util.HttpUrl.Cart3;

/**
 * 确认订单页面
 * 由购物车结算进入 或者 商品详情页直接购买进入
 * 直接购买进入会传入商品规格model
 * <p>
 * 默认没有
 * 修改是 创建一个吧原本的值给set进去 在修改后 提交 提交成功后 吧原本的值给还原到提交的值上去
 * Created by liudong on 2018/1/4.
 */

public class ConfirmOrderActivity extends BaseMyActivity {
    @BindView(R.id.tv_address_name)
    TextView tvAddressName;
    @BindView(R.id.tv_address_phone)
    TextView tvAddressPhone;
    @BindView(R.id.tv_address_info)
    TextView tvAddressInfo;
    @BindView(R.id.layout_address)
    ConstraintLayout layoutAddress;
    @BindView(R.id.tv_Null_address)
    TextView tvNullAddress;
    @BindView(R.id.layout_select_address)
    LinearLayout layoutSelectAddress;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.layout_time)
    LinearLayout layoutTime;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.tv_coupons)
    TextView tvCoupons;
    @BindView(R.id.layout_coupons)
    LinearLayout layoutCoupons;
    @BindView(R.id.switch_walletPay)
    Switch switchWalletPay;
    @BindView(R.id.redio_zhuangxiu)
    RedioLayout redioZhuangxiu;
    @BindView(R.id.redio_jifen)
    RedioLayout redioJifen;
    @BindView(R.id.redio_yuE)
    RedioLayout redioYuE;
    @BindView(R.id.tv_goods_money)
    TextView tvGoodsMoney;
    @BindView(R.id.tv_freight_money)
    TextView tvFreightMoney;
    @BindView(R.id.tv_address_info2)
    TextView tvAddressInfo2;
    @BindView(R.id.tv_pay_money)
    TextView tvPayMoney;
    @BindView(R.id.tv_send_order)
    TextView tvSendOrder;
    //传过来的 用于判断是不是直接购买
    private ShoppingSpacModel model;
    private BaseAdapter<ConfirmOrderModel.ListBean.CartListBean> adapter;

    private ConfirmOrderModel orderModel;
    private String addressId;
    private String conponsId;

    public final int ZhuangXiu = 1;
    private final int JiFen = 2;
    private final int YuE = 3;
    private typeModel mTypeModel;
    private long orderTime;//配送时间
    private TimePickerView timePickerView;

    private boolean isCommitOrder = false;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        switch (eventModel.getCode()) {
            case Constants.event_address:
                addressId = eventModel.getMessage();
                onClickLoadingRefresh();
                break;
            case Constants.event_conpons:
                conponsId = eventModel.getMessage();
                onClickLoadingRefresh();
                break;
            case Constants.event_pay_password:
                String password = eventModel.getMessage();
                commitOrderHttp(password);
                break;
            case Constants.event_pay_success_order:
                //设置支付密码成功的回调
                orderModel.getUserInfo().setPaypwd("111");
                isCommitOrder = true;
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isCommitOrder) {
            TsUtils.show("请输入支付密码!");
            isCommitOrder = false;
            commitOrder();
        }
    }

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_confirm_order).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.layout_content).setTopBar_A("确认订单", new OnTopBarLeftListener() {
            @Override
            public void onClick() {
                onBack();
            }
        });
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        model = (ShoppingSpacModel) getIntent().getSerializableExtra(Constants.Model);
        mTypeModel = new typeModel();
        adapter = new BaseAdapter.Builder<ConfirmOrderModel.ListBean.CartListBean>(rvGoods, mActivity, R.layout.adapter_order_goods)
                .isNested()
                .setNoEnpty()
                .build(new OnBaseAdapterListener<ConfirmOrderModel.ListBean.CartListBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, ConfirmOrderModel.ListBean.CartListBean item) {
                        helper.setText(R.id.tv_title, item.getGoods_name())
                                .setText(R.id.tv_spec, item.getSpec_key_name())
                                .setText(R.id.tv_price, "¥" + item.getGoods_price())
                                .setText(R.id.tv_num, "x" + item.getGoods_num());
                        ImageView img_icon = helper.getView(R.id.img_icon);
                        ImageLoader.with(mActivity, item.getOriginal_img(), img_icon);
                    }
                });
        //初始化按钮
        switchWalletPay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        setSwitch(!switchWalletPay.isChecked());
                        break;
                }
                return true;
            }
        });
        Calendar instance = Calendar.getInstance();
        instance.set(2018, 11, 31);
        timePickerView = new TimePickerView.Builder(mActivity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                orderTime = date.getTime();
                tvTime.setText(ZhuanHuanUtil.Time2nian(orderTime));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setSubmitColor(ZhuanHuanUtil.getColor(R.color.primary_hong))
                .setCancelColor(ZhuanHuanUtil.getColor(R.color.primary_hong))
                .setRangDate(Calendar.getInstance(), instance)
                .build();
        closeSwitch();
        onClickLoadingRefresh();
    }

    private void setSwitch(boolean b) {
        if (switchWalletPay.isChecked() == b) {
            return;
        }
        typeModel typeModel = new typeModel();
        if (b) {
            if (!isZhuangXiu()) {
                typeModel.setRedio(ZhuangXiu);
                refresh(typeModel);
                return;
            }
            if (!isJiFen()) {
                typeModel.setRedio(JiFen);
                refresh(typeModel);
                return;
            }
            if (!isYuE()) {
                typeModel.setRedio(YuE);
                refresh(typeModel);
                return;
            }
            TsUtils.show("钱包暂时没有可用方式!");
        } else {
            refresh(typeModel);
        }

    }


    public void refresh(final typeModel typeModel) {
        MyJsonCallback<HttpResModel<ConfirmOrderModel>> callback = new MyJsonCallback<HttpResModel<ConfirmOrderModel>>(mActivityConfig) {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<ConfirmOrderModel>> respons, HttpResModel<ConfirmOrderModel> confirmOrderModelHttpResModel) {
                initData(confirmOrderModelHttpResModel.getResult());
                mTypeModel.setRedio(typeModel.getRedio());
                setCheck(mTypeModel.getRedio());
            }

            @Override
            public void onError(Response<HttpResModel<ConfirmOrderModel>> response) {
                super.onError(response);
                if (orderModel == null) {
                    finish();
                }
            }
        };
        GetRequest<HttpResModel<ConfirmOrderModel>> params = OkGo.<HttpResModel<ConfirmOrderModel>>get(Cart2).tag(httpTag);
        if (!isType()) {
            params.params("goods_id", model.getGoods_id())
                    .params("action", "buy_now")
                    .params("goods_num", model.getGoods_num());
            //是否有规格
            if (model.getItem_id() != 0) {
                params.params("item_id", model.getItem_id());
            }
        }
        //是否选择地址
        if (addressId != null) {
            params.params("address_id", addressId);
        }
        //是否选择优惠券
        if (conponsId != null) {
            params.params("coupon_id", conponsId);
        }
        //钱包选择
        if (typeModel != null) {
            switch (typeModel.getRedio()) {
                case ZhuangXiu:
                    params.params("renovation_money", orderModel.getLast_order_amount());
                    break;
                case JiFen:
                    params.params("pay_points", orderModel.getLast_order_amount());
                    break;
                case YuE:
                    params.params("user_money", orderModel.getLast_order_amount());
                    break;
            }
        }
        params.execute(callback);
    }

    private void initData(ConfirmOrderModel result) {
        orderModel = result;
        /**
         * 判断收货地址
         */
        ConfirmOrderModel.AddressListBean addressList = result.getAddressList();
        if (addressList == null) {
            layoutAddress.setVisibility(View.GONE);
            tvNullAddress.setVisibility(View.VISIBLE);
            tvAddressInfo2.setText("请填写收货信息");
        } else {
            layoutAddress.setVisibility(View.VISIBLE);
            tvNullAddress.setVisibility(View.GONE);
            tvAddressInfo.setText(addressList.getTotal_address());
            tvAddressInfo2.setText(addressList.getTotal_address());
            tvAddressName.setText(addressList.getConsignee());
            tvAddressPhone.setText(addressList.getMobile());
            addressId = addressList.getAddress_id() + "";
        }
        //优惠券
        if (result.getCouponNum() == null || result.getCouponNum().size() == 0) {
            tvCoupons.setText("请选择");
        } else {
            tvCoupons.setText("减" + result.getCouponNum().get(0).getMoney());
        }

        //加载商品列表
        adapter.setNewData(result.getList().getCartList());
        //加载价格
        tvGoodsMoney.setText("¥" + result.getStoreCartTotalPrice());
        tvFreightMoney.setText("¥" + result.getShipping_price());
        tvPayMoney.setText("实付款:¥" + result.getOrder_amount());
        //钱包
        redioZhuangxiu.setText("装修资金(" + result.getUserInfo().getRenovation_money() + "元)");
        redioJifen.setText("补贴积分(" + result.getUserInfo().getPay_points() + ")");
        redioYuE.setText("余额(" + result.getUserInfo().getUser_money() + "元)");
    }

    private void setCheck(int i) {
        if (i == 0) {
            //关闭switch
            closeSwitch();
        } else {
            if (!switchWalletPay.isChecked()) {
                //说明是第一次打开
                switchWalletPay.setChecked(true);
                if (!isZhuangXiu()) {
                    redioZhuangxiu.setClick(true);
                }
                if (!isJiFen()) {
                    redioJifen.setClick(true);
                }
                if (!isYuE()) {
                    redioYuE.setClick(true);
                }
            }
            redioZhuangxiu.setChecked(false);
            redioJifen.setChecked(false);
            redioYuE.setChecked(false);
            switch (i) {
                case ZhuangXiu:
                    redioZhuangxiu.setChecked(true);
                    break;
                case JiFen:
                    redioJifen.setChecked(true);
                    break;
                case YuE:
                    redioYuE.setChecked(true);
                    break;
            }
        }

    }

    private void closeSwitch() {
        if (switchWalletPay.isChecked()) {
            switchWalletPay.setChecked(false);
        }
        redioZhuangxiu.setChecked(false);
        redioJifen.setChecked(false);
        redioYuE.setChecked(false);
        redioZhuangxiu.setClick(false);
        redioJifen.setClick(false);
        redioYuE.setClick(false);
    }


    @Override
    protected void onBack() {
        AlertDialog.Builder builder = PublicUtil.getAlertDialog_nessage(mActivity, "便宜不等人，请三思而行")
                .setPositiveButton("去意已决", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("我再想想", null)
                .setCancelable(false);
        builder.show();
    }

    private boolean isType() {
        return model == null;
    }


    @OnClick({R.id.layout_select_address, R.id.layout_time, R.id.layout_coupons, R.id.switch_walletPay, R.id.redio_zhuangxiu, R.id.redio_jifen, R.id.redio_yuE, R.id.tv_send_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_select_address:
                //选择收货地址
                IntentUtil.goAddresListById(mActivity);
                break;
            case R.id.layout_time:
                //选择配送时间
                timePickerView.show();
                break;
            case R.id.layout_coupons:
                //选择优惠券
                IntentUtil.goCoupons(mActivity, orderModel.getStoreCartTotalPrice());
                break;
            case R.id.switch_walletPay:
                switchWalletPay.setChecked(!switchWalletPay.isChecked());
                break;
            case R.id.redio_zhuangxiu:
                if (redioZhuangxiu.isClick()) {
                    refresh(new typeModel(ZhuangXiu));
                }
                break;
            case R.id.redio_jifen:
                if (redioJifen.isClick()) {
                    refresh(new typeModel(JiFen));
                }
                break;
            case R.id.redio_yuE:
                if (redioYuE.isClick()) {
                    refresh(new typeModel(YuE));
                }
                break;
            case R.id.tv_send_order:
                //提交订单
                commitOrder();
                break;
        }
    }

    /**
     * 验证提交订单
     */
    private void commitOrder() {
        if (mTypeModel.getRedio() != 0) {
            //说明已经选择了钱包支付 需要输入支付密码
            if (orderModel.getUserInfo().getPaypwd() == null || orderModel.getUserInfo().getPaypwd().isEmpty()) {
                //没有设置支付密码 指引去设置
                //跳转设置支付密码
                TsUtils.show("钱包支付,请设置支付密码~");
                IntentUtil.goPaySetting_order(mActivity);
            } else {
                //已经设置了 指引去输入
                IntentUtil.goPayInputPassword(mActivity);
            }
        } else {
            //不需要密码
            commitOrderHttp("");
        }
    }

    //提交订单请求
    private void commitOrderHttp(String password) {
        PostRequest<HttpResModel<CommitOrderModel>> params = OkGo.<HttpResModel<CommitOrderModel>>post(Cart3)
                .tag(httpTag)
                .params("act", "submit_order")
                .params("user_note", editText.getText().toString());
        if (orderTime != 0) {
            params.params("dispatching", orderTime);
        }
        if (!password.isEmpty()) {
            params.params("paypwd", password);
        }
        //是否选择地址
        if (addressId != null) {
            params.params("address_id", addressId);
        }
        //是否选择优惠券
        if (conponsId != null) {
            params.params("coupon_id", conponsId);
        }
        //钱包选择
        if (mTypeModel != null) {
            switch (mTypeModel.getRedio()) {
                case ZhuangXiu:
                    params.params("renovation_money", orderModel.getLast_order_amount());
                    break;
                case JiFen:
                    params.params("pay_points", orderModel.getLast_order_amount());
                    break;
                case YuE:
                    params.params("user_money", orderModel.getLast_order_amount());
                    break;
            }
        }
        if (!isType()) {
            params.params("goods_id", model.getGoods_id())
                    .params("action", "buy_now")
                    .params("goods_num", model.getGoods_num());
            //是否有规格
            if (model.getItem_id() != 0) {
                params.params("item_id", model.getItem_id());
            }
        }
        params.execute(new MyJsonCallback<HttpResModel<CommitOrderModel>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<CommitOrderModel>> respons, HttpResModel<CommitOrderModel> stringHttpResModel) {
                if (stringHttpResModel.getResult().getOrder_amount() == 0) {
                    //跳转支付成功页面
                    IntentUtil.goPaySuccess(mActivity);
                    finish();
                } else {
                    //去收银台
                    IntentUtil.goPay(mActivity, stringHttpResModel.getResult());
                    finish();
                }
            }
        }.isDialog(mActivity));
    }

    private boolean isYuE() {
        return orderModel.getUserInfo().getUser_money().equals("0.00");
    }

    private boolean isJiFen() {
        return orderModel.getUserInfo().getPay_points().equals("0.00");
    }

    private boolean isZhuangXiu() {
        return orderModel.getUserInfo().getRenovation_money().equals("0.00");
    }


    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        refresh(mTypeModel);
    }

    class typeModel {
        private int redio;

        public typeModel() {
        }

        public typeModel(int redio) {
            this.redio = redio;
        }

        public int getRedio() {
            return redio;
        }

        public void setRedio(int redio) {
            this.redio = redio;
        }
    }
}
