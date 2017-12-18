package com.duma.ld.zhilianlift.view.main.wode.userSecuryty;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.password;

/**
 * 修改登录密码
 * Created by liudong on 2017/12/18.
 */

public class ChangeLoginPasswordActivity extends BaseMyActivity {
    @BindView(R.id.edit_old_password)
    EditText editOldPassword;
    @BindView(R.id.edit_new_password1)
    EditText editNewPassword1;
    @BindView(R.id.edit_new_password2)
    EditText editNewPassword2;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_change_login_password, false).setTopBar_A("修改登录密码");
    }


    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
        if (editOldPassword.getText().toString().isEmpty()) {
            TsUtils.show("请输入正确的旧密码!");
            return;
        }
        if (editNewPassword1.getText().toString().isEmpty()) {
            TsUtils.show("请输入正确的新密码!");
            return;
        }
        if (editNewPassword2.getText().toString().isEmpty()) {
            TsUtils.show("请输入正确的新密码!");
            return;
        }
        if (!editNewPassword1.getText().toString().equals(editNewPassword2.getText().toString())) {
            TsUtils.show("请确认两次新密码输入相同!");
            return;
        }
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<String>>post(password)
                .params("old_password", editOldPassword.getText().toString())
                .params("new_password", editNewPassword1.getText().toString())
                .params("confirm_password", editNewPassword2.getText().toString())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        TsUtils.show("修改成功!请使用新密码重新登录~");
                        SpDataUtil.removeUser();
                        IntentUtil.goMain(mActivity);
                        IntentUtil.goLogin(mActivity);
                    }
                });
    }
}
