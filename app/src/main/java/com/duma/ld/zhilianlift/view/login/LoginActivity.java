package com.duma.ld.zhilianlift.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.model.UserModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.login;

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
                startActivity(new Intent(mActivity, ForgetPasswordActivity.class));
                break;
            case R.id.tv_login:
                if (!RegexUtils.isMobileExact(editPhone.getText().toString())) {
                    TsUtils.show("请输入正确的手机号码!");
                    return;
                }
                if (editPassword.getText().toString().isEmpty()) {
                    TsUtils.show("请输入密码!");
                    return;
                }
                DialogUtil.getInstance().show_noBack(mActivity, "登陆中");
                OkGo.<HttpResModel<UserModel>>post(login)
                        .params("username", editPhone.getText().toString())
                        .params("password", editPassword.getText().toString())
                        .execute(new MyJsonCallback<HttpResModel<UserModel>>() {
                            @Override
                            protected void onJsonSuccess(Response<HttpResModel<UserModel>> respons, HttpResModel<UserModel> stringHttpResModel) {
                                DialogUtil.getInstance().hide();
                                SpDataUtil.setUser(stringHttpResModel.getResult());
                                finish();
                            }
                        });
                break;
        }
    }
}
