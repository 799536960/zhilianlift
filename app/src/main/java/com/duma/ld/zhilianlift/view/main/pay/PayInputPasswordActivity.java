package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.jaeger.library.StatusBarUtil;

/**
 * 支付密码输入
 * Created by liudong on 2018/1/5.
 */

public class PayInputPasswordActivity extends BaseMyActivity {
    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_paypassword, false);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.v_back_enter, R.anim.v_back_exit);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(mActivity);
    }
}
