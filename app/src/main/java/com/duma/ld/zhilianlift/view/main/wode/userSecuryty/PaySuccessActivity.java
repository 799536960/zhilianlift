package com.duma.ld.zhilianlift.view.main.wode.userSecuryty;

import android.os.Bundle;

import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;

import static com.duma.ld.zhilianlift.util.Constants.event_pay_success;

/**
 * Created by liudong on 2017/12/19.
 */

public class PaySuccessActivity extends BaseMyActivity {
    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_pay_success, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mActivityConfig.setTopBar_A("密码设置成功", new OnTopBarLeftListener() {
            @Override
            public void onClick() {
                back();
            }
        });
    }

    public void back() {
        String stringExtra = getIntent().getStringExtra(Constants.Type);
        if (stringExtra.equals(Constants.type_new)) {
            EventBusUtil.sendModel(event_pay_success);
        }
        IntentUtil.goPaySetting(mActivity);
    }

    @Override
    protected void onBack() {
        back();
    }
}
