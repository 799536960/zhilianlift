package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.view.login.CodeBarActivity;

/**
 * 发送短信
 * 如果没有电话号码输入框 就是对自己号码发送短信
 * Created by liudong on 2017/12/7.
 */

public class SendCodeUtil {
    private TextView textView;
    private String defaultString = "获取验证码";
    private CountDownTimer countDownTimer;
    private Activity mActivity;
    private EditText mEditText;

    private View.OnClickListener onClickListener;

    public SendCodeUtil(final TextView codeText, EditText editTextPhone, Activity activity) {
        this.mEditText = editTextPhone;
        ininData(codeText, activity);
    }

    public SendCodeUtil(final TextView codeText, EditText editTextPhone, Activity activity, View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.mEditText = editTextPhone;
        ininData(codeText, activity);

    }

    public SendCodeUtil(final TextView codeText, final Activity activity, View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        ininData(codeText, activity);
    }

    public SendCodeUtil(final TextView codeText, final Activity activity) {
        ininData(codeText, activity);
    }

    private void ininData(TextView codeText, Activity activity) {
        this.textView = codeText;
        this.mActivity = activity;
        initTextView();
        countDownTimer = new CountDownTimer(10 * 1000, 1 + 1000) {
            @Override
            public void onTick(long l) {
                textView.setText("重新发送(" + (int) (l / 1000) + "s)");
                textView.setTextColor(ZhuanHuanUtil.getColor(R.color.hui2));
            }

            @Override
            public void onFinish() {
                initTextView();
            }
        };
        if (onClickListener == null) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendCode();
                }
            });
        } else {
            textView.setOnClickListener(onClickListener);
        }

    }


    private void initTextView() {
        textView.setText(defaultString);
        textView.setTextColor(ZhuanHuanUtil.getColor(R.color.primary_hong));
    }

    public void sendCode() {
        String editPhone;
        if (mEditText != null) {
            editPhone = mEditText.getText().toString();
        } else {
            editPhone = SpDataUtil.getUser().getMobile();
        }
        if (!RegexUtils.isMobileExact(editPhone)) {
            TsUtils.show("请输入正确的手机号码!");
            return;
        }
        if (textView.getText().toString().equals(defaultString)) {
            mActivity.startActivity(new Intent(mActivity, CodeBarActivity.class));
        } else {
            TsUtils.show("请稍后再试!");
        }
    }

    public void starTime() {
        initTextView();
        countDownTimer.start();
    }

    public void destroy() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}
