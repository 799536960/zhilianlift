package com.duma.ld.zhilianlift.view.main.wode.userSecuryty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.SpDataUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 2017/12/15.
 */
public class UserSecurityActivity extends BaseMyActivity {
    @BindView(R.id.layout_phone)
    LinearLayout layoutPhone;
    @BindView(R.id.layout_login_password)
    LinearLayout layoutLoginPassword;
    @BindView(R.id.layout_pay_password)
    LinearLayout layoutPayPassword;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.layout_user_securyty, false).setTopBar_A("账户与安全");
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        tvPhone.setText(SpDataUtil.getUser().getMobile_xx());
    }

    @OnClick({R.id.layout_phone, R.id.layout_login_password, R.id.layout_pay_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_phone:
                startActivity(new Intent(mActivity, ChangePhoneActivity.class));
                break;
            case R.id.layout_login_password:
                startActivity(new Intent(mActivity, ChangeLoginPasswordActivity.class));
                break;
            case R.id.layout_pay_password:
                startActivity(new Intent(mActivity, PaySettingActivity.class));
                break;
        }
    }
}
