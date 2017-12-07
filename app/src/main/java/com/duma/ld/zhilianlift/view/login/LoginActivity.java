package com.duma.ld.zhilianlift.view.login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 2017/12/7.
 */

public class LoginActivity extends BaseMyActivity {
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.textInputLayout)
    TextInputLayout textInputLayout;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.textInputLayout2)
    TextInputLayout textInputLayout2;
    @BindView(R.id.tv_wangjimima)
    TextView tvWangjimima;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_login, false).setTopBar("登录");
    }


    @OnClick({R.id.tv_wangjimima, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wangjimima:
                break;
            case R.id.tv_login:
                break;
        }
    }
}
