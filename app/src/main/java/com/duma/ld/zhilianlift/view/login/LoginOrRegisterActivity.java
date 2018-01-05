package com.duma.ld.zhilianlift.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.SpDataUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 2017/12/7.
 */

public class LoginOrRegisterActivity extends BaseMyActivity {
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig
                .setLayoutIdByActivity(R.layout.activity_login_or_register, false)
                .setTopBar_A("").setNoYinyin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SpDataUtil.isLogin()) {
            finish();
        }
    }

    @OnClick({R.id.tv_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(mActivity, RegisterActivity.class));
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }
}
