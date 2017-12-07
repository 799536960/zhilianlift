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
 * Created by liudong on 2017/12/7.
 */

public class SendCodeUtil {
    private TextView textView;
    private String defaultString = "获取验证码";
    private CountDownTimer countDownTimer;


    public SendCodeUtil(final TextView codeText, final EditText editTextPhone, final Activity activity) {
        this.textView = codeText;
        initTextView();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!RegexUtils.isMobileExact(editTextPhone.getText().toString())) {
                    TsUtils.show("请输入正确的手机号码!");
                    return;
                }
                if (textView.getText().toString().equals(defaultString)) {
                    activity.startActivity(new Intent(activity, CodeBarActivity.class));
                } else {
                    TsUtils.show("请稍后再试!");
                }
            }
        });
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
    }

    private void initTextView() {
        textView.setText(defaultString);
        textView.setTextColor(ZhuanHuanUtil.getColor(R.color.primary_hong));
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
