package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.base.OnTopBarRightListener;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.PayStoreModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;

import butterknife.BindView;

/**
 * 扫码付款成功
 * Created by liudong on 2018/1/29.
 */

public class PayStoreSuccessActivity extends BaseMyActivity {
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.textView14)
    TextView textView14;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_money_type)
    TextView tvMoneyType;
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.layout_moneyType)
    LinearLayout layoutMoneyType;
    private PayStoreModel model;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_pay_store_success, false).setTopBar_A("支付成功", new OnTopBarLeftListener() {
            @Override
            public void onClick() {
                onBack();
            }
        }, "完成", new OnTopBarRightListener() {
            @Override
            public void onClick() {
                onBack();
            }
        });
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        model = (PayStoreModel) getIntent().getSerializableExtra(Constants.Model);
        tvMoney.setText("￥" + model.getMoney());
        tvName.setText(model.getName());
        tvMoneyType.setText(model.getNameType());
    }

    @Override
    protected void onBack() {
        IntentUtil.goMain(mActivity);
    }

}
