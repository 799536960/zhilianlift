package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.base.OnTopBarRightListener;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.IntentUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 支付成功
 * Created by liudong on 2018/1/8.
 */

public class PaySuccessActivity extends BaseMyActivity {
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_order_info)
    TextView tvOrderInfo;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_pay_success, false).setTopBar_A("支付成功", new OnTopBarLeftListener() {
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
    protected void onBack() {
        IntentUtil.goMain(mActivity);
    }


    @OnClick({R.id.tv_home, R.id.tv_order_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home:
                onBack();
                break;
            case R.id.tv_order_info:
                IntentUtil.goOrderList(mActivity);
                finish();
                break;
        }
    }
}
