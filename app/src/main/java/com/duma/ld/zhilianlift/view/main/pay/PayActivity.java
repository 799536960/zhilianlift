package com.duma.ld.zhilianlift.view.main.pay;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.CommitOrderModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.WeiXinModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.pay.PayUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.get_code;
import static com.duma.ld.zhilianlift.util.HttpUrl.get_code2;

/**
 * 收银台
 * Created by liudong on 2018/1/8.
 */

public class PayActivity extends BaseMyActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.radio_weiXin)
    RadioButton radioWeiXin;
    @BindView(R.id.radio_zhiFuBao)
    RadioButton radioZhiFuBao;
    @BindView(R.id.radio_yinlian)
    RadioButton radioYinlian;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.tv_Type_money)
    TextView tvTypeMoney;
    private CommitOrderModel model;
    //0 微信 1 支付宝 2 英联
    private int type;
    private PayUtil payUtil;


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        super.onReceiveEvent(eventModel);
        switch (eventModel.getCode()) {
            case Constants.event_weixin_success:
            case Constants.event_zhifuBao_success:
                IntentUtil.goPaySuccess(mActivity);
                finish();
                break;
        }
    }

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_pay, false).setTopBar_A("智联生活收银台", new OnTopBarLeftListener() {
            @Override
            public void onClick() {
                onBack();
            }
        });
    }

    @Override
    protected void onBack() {
        AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "收银台", "确定要退出支付嘛?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        IntentUtil.goOrderList(mActivity);
                        finish();
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false);
        builder.show();
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        payUtil = new PayUtil(mActivity);
        model = (CommitOrderModel) getIntent().getSerializableExtra(Constants.Model);
        tvMoney.setText("¥" + model.getOrder_amount() + "元");
        radioGroup.setOnCheckedChangeListener(this);
        radioWeiXin.setChecked(true);
    }

    private void setType(int i) {
        type = i;
        switch (i) {
            case 0:
                tvTypeMoney.setText("微信支付" + model.getOrder_amount() + "元");
                break;
            case 1:
                tvTypeMoney.setText("支付宝支付" + model.getOrder_amount() + "元");
                break;
            case 2:
                tvTypeMoney.setText("银联支付" + model.getOrder_amount() + "元");
                break;
        }
    }

    @OnClick({R.id.tv_Type_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_Type_money:
                switch (type) {
                    case 0:
                        weiXinHttp();
                        break;
                    case 1:
                        zhiFuBaoHttp();
                        break;
                    case 2:
                        TsUtils.show("银联支付");
                        break;
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_weiXin:
                setType(0);
                break;
            case R.id.radio_zhiFuBao:
                setType(1);
                break;
            case R.id.radio_yinlian:
                setType(2);
                break;
        }
    }

    private void zhiFuBaoHttp() {
        GetRequest<HttpResModel<String>> request = OkGo.<HttpResModel<String>>get(get_code)
                .tag(httpTag);
        pulicRequest(request);
        request.execute(new MyJsonCallback<HttpResModel<String>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                payUtil.starZhiFuBao(stringHttpResModel.getResult());
            }
        }.isDialog(mActivity));
    }

    private void weiXinHttp() {
        GetRequest<HttpResModel<WeiXinModel>> request = OkGo.<HttpResModel<WeiXinModel>>get(get_code2)
                .tag(httpTag);
        pulicRequest(request);
        request.execute(new MyJsonCallback<HttpResModel<WeiXinModel>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<WeiXinModel>> respons, HttpResModel<WeiXinModel> stringHttpResModel) {
                payUtil.starWeiXin(stringHttpResModel.getResult());
            }
        }.isDialog(mActivity));
    }

    private void pulicRequest(GetRequest request) {
        if (StringUtils.isEmpty(model.getMaster_order_sn())) {
            request.params("order_id", model.getOrderId());
        } else {
            request.params("master_order_sn", model.getMaster_order_sn());
        }
    }
}


