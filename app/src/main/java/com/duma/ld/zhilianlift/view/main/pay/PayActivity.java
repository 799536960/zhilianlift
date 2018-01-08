package com.duma.ld.zhilianlift.view.main.pay;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.CommitOrderModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 收银台
 * Created by liudong on 2018/1/8.
 */

public class PayActivity extends BaseMyActivity {
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.rdoBtn_weixin)
    RadioButton rdoBtnWeixin;
    @BindView(R.id.rdoBtn_ZhiFuBao)
    RadioButton rdoBtnZhiFuBao;
    @BindView(R.id.rdoBtn_yinLian)
    RadioButton rdoBtnYinLian;
    @BindView(R.id.tv_Type_money)
    TextView tvTypeMoney;

    private CommitOrderModel model;
    //0 微信 1 支付宝 2 英联
    private int type;

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
        model = (CommitOrderModel) getIntent().getSerializableExtra(Constants.Model);
        tvMoney.setText("¥" + model.getOrder_amount() + "元");
        setType(0);
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

    @OnClick({R.id.rdoBtn_weixin, R.id.rdoBtn_ZhiFuBao, R.id.rdoBtn_yinLian, R.id.tv_Type_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rdoBtn_weixin:
                setType(0);
                rdoBtnZhiFuBao.setChecked(false);
                rdoBtnYinLian.setChecked(false);
                break;
            case R.id.rdoBtn_ZhiFuBao:
                setType(1);
                rdoBtnWeixin.setChecked(false);
                rdoBtnYinLian.setChecked(false);
                break;
            case R.id.rdoBtn_yinLian:
                setType(2);
                rdoBtnZhiFuBao.setChecked(false);
                rdoBtnWeixin.setChecked(false);
                break;
            case R.id.tv_Type_money:
                switch (type) {
                    case 0:
                        TsUtils.show("微信支付");
                        break;
                    case 1:
                        TsUtils.show("支付宝支付");
                        break;
                    case 2:
                        TsUtils.show("银联支付");
                        break;
                }
                break;
        }
    }
}
