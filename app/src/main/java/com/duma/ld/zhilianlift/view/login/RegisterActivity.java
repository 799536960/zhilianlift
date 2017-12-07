package com.duma.ld.zhilianlift.view.login;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.SendCodeUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.reg;
import static com.duma.ld.zhilianlift.util.HttpUrl.send_validate_code;

/**
 * Created by liudong on 2017/12/7.
 */

public class RegisterActivity extends BaseMyActivity {
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.tv_sendCode)
    TextView tvSendCode;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.edit_password2)
    EditText editPassword2;
    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;
    @BindView(R.id.tv_xieyi_btn1)
    TextView tvXieyiBtn1;
    @BindView(R.id.tv_xieyi_btn2)
    TextView tvXieyiBtn2;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    private SendCodeUtil sendCodeUtil;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_register, false).setTopBar("注册");
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == Constants.event_codeMax_success) {
            //发送验证码
            sendCode();
        }
    }

    private void sendCode() {
        DialogUtil.getInstance().show_noBack(mActivity, "发送验证码中");
        OkGo.<HttpResModel<String>>get(send_validate_code)
                .params("mobile", editPhone.getText().toString())
                .params("type", "1")
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        sendCodeUtil.starTime();
                    }
                });
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        SpanUtils spanUtils = new SpanUtils();
        SpannableStringBuilder spannableStringBuilder = spanUtils.append("已阅读并同意以下协议,")
                .append("接受免除或者限制责任,诉讼管辖约定")
                .setUnderline()
                .setBold()
                .append("等粗体下划线标示条款.")
                .create();
        tvXieyi.setText(spannableStringBuilder);
        sendCodeUtil = new SendCodeUtil(tvSendCode, editPhone, mActivity);
    }

    @OnClick({R.id.tv_xieyi_btn1, R.id.tv_xieyi_btn2, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_xieyi_btn1:
                TsUtils.show("协议1");
                break;
            case R.id.tv_xieyi_btn2:
                TsUtils.show("协议2");
                break;
            case R.id.tv_register:
                if (!RegexUtils.isMobileExact(editPhone.getText().toString())) {
                    TsUtils.show("请输入正确的手机号码!");
                    return;
                }
                if (editCode.getText().toString().isEmpty()) {
                    TsUtils.show("请输入正确的验证码!");
                    return;
                }
                if (editPassword.getText().toString().isEmpty()) {
                    TsUtils.show("请输入密码!");
                    return;
                }
                if (editPassword2.getText().toString().isEmpty()) {
                    TsUtils.show("请输入确认密码!");
                    return;
                }
                if (!editPassword2.getText().toString().equals(editPassword.getText().toString())) {
                    TsUtils.show("两次密码输入不同!");
                    return;
                }
                DialogUtil.getInstance().show_noBack(mActivity);
                OkGo.<HttpResModel<String>>post(reg)
                        .params("username", editPhone.getText().toString())
                        .params("password", editPassword.getText().toString())
                        .params("password2", editPassword2.getText().toString())
                        .params("code", editCode.getText().toString())
                        .execute(new MyJsonCallback<HttpResModel<String>>() {
                            @Override
                            protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                                DialogUtil.getInstance().hide();
                            }
                        });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sendCodeUtil.destroy();
    }
}
