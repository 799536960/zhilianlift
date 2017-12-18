package com.duma.ld.zhilianlift.view.main.wode.userSecuryty;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
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
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.SendCodeUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.mobile;
import static com.duma.ld.zhilianlift.util.HttpUrl.send_validate_code;

/**
 * 修改手机号
 * Created by liudong on 2017/12/18.
 */

public class ChangePhoneActivity extends BaseMyActivity {
    @BindView(R.id.tv_old_sendCode)
    TextView tvOldSendCode;
    @BindView(R.id.edit_old_code)
    EditText editOldCode;
    @BindView(R.id.edit_new_phone)
    EditText editNewPhone;
    @BindView(R.id.tv_new_sendCode)
    TextView tvNewSendCode;
    @BindView(R.id.edit_new_code)
    EditText editNewCode;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_old_phone)
    TextView tvOldPhone;

    private SendCodeUtil sendCodeUtil_Old, sendCodeUtil_New;
    private boolean isOld;

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
        GetRequest<HttpResModel<String>> httpResModelGetRequest = OkGo.<HttpResModel<String>>get(send_validate_code);
        if (isOld) {
            //旧手机号的验证码
            httpResModelGetRequest
                    .params("mobile", SpDataUtil.getUser().getMobile())
                    .params("type", "2");
        } else {
            httpResModelGetRequest
                    .params("mobile", editNewPhone.getText().toString())
                    .params("type", "1");
        }
        httpResModelGetRequest.execute(new MyJsonCallback<HttpResModel<String>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                DialogUtil.getInstance().hide();
                if (isOld) {
                    //旧手机号的验证码
                    sendCodeUtil_Old.starTime();
                } else {
                    sendCodeUtil_New.starTime();
                }
            }
        });
    }

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_change_phone, false).setTopBar_A("修改手机号码");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        tvOldPhone.setText(SpDataUtil.getUser().getMobile_xx());
        sendCodeUtil_Old = new SendCodeUtil(tvOldSendCode, mActivity, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOld = true;
                sendCodeUtil_Old.sendCode();
            }
        });
        sendCodeUtil_New = new SendCodeUtil(tvNewSendCode, editNewPhone, mActivity, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOld = false;
                sendCodeUtil_New.sendCode();
            }
        });
    }

    @OnClick({R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                if (editOldCode.getText().toString().isEmpty() || editOldCode.getText().toString().length() < 4) {
                    TsUtils.show("请输入旧手机号码正确的验证码!");
                    return;
                }
                if (editNewCode.getText().toString().isEmpty() || editNewCode.getText().toString().length() < 4) {
                    TsUtils.show("请输入新手机号码正确的验证码!");
                    return;
                }
                if (RegexUtils.isMobileSimple(editNewPhone.getText().toString())) {
                    TsUtils.show("请输入正确的新手机号码!");
                    return;
                }
                DialogUtil.getInstance().show_noBack(mActivity);
                OkGo.<HttpResModel<String>>post(mobile)
                        .params("old_mobile", SpDataUtil.getUser().getMobile())
                        .params("old_code", editOldCode.getText().toString())
                        .params("new_mobile", editNewPhone.getText().toString())
                        .params("new_code", editNewCode.getText().toString())
                        .execute(new MyJsonCallback<HttpResModel<String>>() {
                            @Override
                            protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                                DialogUtil.getInstance().hide();
                                TsUtils.show("修改成功!请使用号码重新登录~");
                                SpDataUtil.removeUser();
                                IntentUtil.goMain(mActivity);
                                IntentUtil.goLogin(mActivity);
                            }
                        });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sendCodeUtil_Old.destroy();
        sendCodeUtil_New.destroy();
    }


}
