package com.duma.ld.zhilianlift.view.main.wode.userSecuryty;

import android.os.Bundle;
import android.widget.TextView;

import com.arron.passwordview.PasswordView;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.Constants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置和更改支付密码
 * Created by liudong on 2017/12/18.
 */

public class PayPasswordActivity extends BaseMyActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.passwordView)
    PasswordView passwordView;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    private String type;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_pay_password, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        type = getIntent().getStringExtra(Constants.Type);
        if (type == null) {
            finish();
        }
        switch (type) {
            case Constants.type_new:
                //第一次设置支付密码
                mActivityConfig.setTopBar_A("设置6位支付密码");
                tvTitle.setText("设置6位支付密码");
                break;
            case Constants.type_verify:
                /**
                 * 验证原支付密码
                 * 就是修改密码的第一步
                 */
                mActivityConfig.setTopBar_A("验证原6位支付密码");
                tvTitle.setText("验证原6位支付密码");
                break;
            case Constants.type_verify_new:
                /**
                 * 验证后设置支付密码
                 * 就是修改密码的第二步
                 */
                mActivityConfig.setTopBar_A("设置新6位支付密码");
                tvTitle.setText("设置新6位支付密码");
                break;
            case Constants.type_forget_verify_new:
                /**
                 * 设置新支付密码
                 * 就是忘记支付密码 验证手机后的页面
                 */
                mActivityConfig.setTopBar_A("设置新6位支付密码 ");
                tvTitle.setText("设置新6位支付密码");
                break;
        }
        passwordView.setPasswordListener(new PasswordView.PasswordListener() {
            @Override
            public void passwordChange(String changeText) {

            }

            @Override
            public void passwordComplete() {

            }

            @Override
            public void keyEnterPress(String password, boolean isComplete) {

            }
        });
    }


    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
    }
}
