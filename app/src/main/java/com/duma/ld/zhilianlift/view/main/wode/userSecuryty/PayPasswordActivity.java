package com.duma.ld.zhilianlift.view.main.wode.userSecuryty;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
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
import com.duma.ld.zhilianlift.widget.PayInputLayout;
import com.duma.ld.zhilianlift.widget.PayPasswordLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.type_verify_new;
import static com.duma.ld.zhilianlift.util.HttpUrl.paypwd;
import static com.duma.ld.zhilianlift.util.HttpUrl.setpaypwd;

/**
 * 设置和更改支付密码
 * Created by liudong on 2017/12/18.
 */

public class PayPasswordActivity extends BaseMyActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.layout_password)
    PayPasswordLayout layoutPassword;
    @BindView(R.id.layout_input)
    PayInputLayout layoutInput;
    private String type;
    private boolean isOk;

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
            case type_verify_new:
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

        tvOk.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hui));
        layoutPassword.setPayInputLayout(layoutInput);
        layoutPassword.setOnPasswordType(new PayPasswordLayout.OnPasswordType() {
            @Override
            public void onEnd(String password) {
                isOk = true;
                tvOk.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hong));
            }

            @Override
            public void onDelete() {
                isOk = false;
                tvOk.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hui));
            }
        });
    }


    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
        if (isOk) {
            switch (type) {
                case Constants.type_new:
                    //第一次设置支付密码
                    settingPay();
                    break;
                case Constants.type_verify:
                    /**
                     * 验证原支付密码
                     * 就是修改密码的第一步
                     */
                    verifyPayPassword();
                    break;
                case type_verify_new:
                    /**
                     * 验证后设置支付密码
                     * 就是修改密码的第二步
                     */
                    settingPay();
                    break;
                case Constants.type_forget_verify_new:
                    /**
                     * 设置新支付密码
                     * 就是忘记支付密码 验证手机后的页面
                     */
                    settingPay();
                    break;
            }
        }
    }

    //验证支付密码
    private void verifyPayPassword() {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<String>>post(paypwd)
                .params("paypwd", layoutPassword.getPassword())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        IntentUtil.goPayPassword(mActivity, type_verify_new);
                    }
                });
    }

    public void settingPay() {
        AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "支付密码", "确定设置此支付密码?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setPay();
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false);
        builder.show();
    }

    //设置支付密码
    private void setPay() {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<String>>post(setpaypwd)
                .params("paypwd", layoutPassword.getPassword())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        IntentUtil.goPayPasswordSuccess(mActivity, type);
                    }
                });
    }
}
