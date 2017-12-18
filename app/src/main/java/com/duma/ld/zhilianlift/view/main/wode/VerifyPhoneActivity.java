package com.duma.ld.zhilianlift.view.main.wode;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.SendCodeUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.Type;
import static com.duma.ld.zhilianlift.util.Constants.Verify_RealName;
import static com.duma.ld.zhilianlift.util.Constants.Verify_payPassword;
import static com.duma.ld.zhilianlift.util.Constants.Verify_payPassword_forget;
import static com.duma.ld.zhilianlift.util.Constants.type_forget_verify_new;
import static com.duma.ld.zhilianlift.util.Constants.type_new;
import static com.duma.ld.zhilianlift.util.HttpUrl.check_sms;
import static com.duma.ld.zhilianlift.util.HttpUrl.send_validate_code;

/**
 * 验证绑定手机页面
 * 验证通过将根据type跳转某个页面 并发送验证成功
 * Created by liudong on 2017/12/14.
 */

public class VerifyPhoneActivity extends BaseMyActivity {
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_verify)
    TextView tvVerify;

    private TextView tv_send;
    private TextInputLayout textInputLayout;

    private String intentType;
    private AlertDialog alertDialog;
    private SendCodeUtil sendCodeUtil;
    private View.OnClickListener onClickListener;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == Constants.event_codeMax_success) {
            //发送验证码
            DialogUtil.getInstance().show_noBack(mActivity, "发送验证码中");
            OkGo.<HttpResModel<String>>get(send_validate_code)
                    .params("mobile", SpDataUtil.getUser().getMobile())
                    .execute(new MyJsonCallback<HttpResModel<String>>() {
                        @Override
                        protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                            DialogUtil.getInstance().hide();
                            sendCodeUtil.starTime();
                        }
                    });
        }
    }


    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_verify_phone, false).setTopBar_A("验证绑定手机");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        intentType = getIntent().getStringExtra(Type);
        tvPhone.setText(SpDataUtil.getUser().getMobile_xx());
        //初始化dialog
        AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "验证手机号");
        //初始化自定义dialogview
        View dialog = getLayoutInflater().inflate(R.layout.dialog_verify_phone, (ViewGroup) findViewById(R.id.layout_dialog));
        tv_send = dialog.findViewById(R.id.tv_send);
        textInputLayout = dialog.findViewById(R.id.textInputLayout);
        alertDialog = builder.setView(dialog)
                .setPositiveButton("验证", null)
                .setNegativeButton("取消", null)
                .setCancelable(false)
                .create();
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = textInputLayout.getEditText().getText().toString();
                if (s.isEmpty() || s.length() < 4) {
                    textInputLayout.setError("请输入正确的验证码");
                } else {
                    //验证
                    textInputLayout.setErrorEnabled(false);
                    alertDialog.dismiss();
                    verifyHttp(s);
                }
            }
        };

        sendCodeUtil = new SendCodeUtil(tv_send, mActivity);
    }

    private void verifyHttp(String str) {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<String>>post(check_sms)
                .params("mobile", SpDataUtil.getUser().getMobile())
                .params("check_code", str)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        finish();
                        switch (intentType) {
                            case Verify_RealName:
                                IntentUtil.goRealName_add(mActivity);
                                break;
                            case Verify_payPassword:
                                IntentUtil.goPayPassword(mActivity, type_new);
                                break;
                            case Verify_payPassword_forget:
                                IntentUtil.goPayPassword(mActivity, type_forget_verify_new);
                                break;
                        }
                    }
                });
    }

    @OnClick(R.id.tv_verify)
    public void onViewClicked() {
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(onClickListener);
        sendCodeUtil.sendCode();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sendCodeUtil.destroy();
    }
}
