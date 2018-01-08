package com.duma.ld.zhilianlift.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.zhilianlift.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付密码输入框
 * Created by liudong on 2017/12/8.
 */

public class PayPasswordLayout extends LinearLayout {
    private TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6;
    private LinearLayout layout_bx;
    private PayInputLayout payInputLayout;
    private List<tvPassword> mList;
    private OnPasswordType onPasswordType;
    private static String CIPHER_TEXT = "●"; //密文符号

    public void setOnPasswordType(OnPasswordType onPasswordType) {
        this.onPasswordType = onPasswordType;
    }

    public interface OnPasswordType {
        void onEnd(String password);

        void onDelete();
    }

    public PayPasswordLayout(Context context) {
        this(context, null);
    }

    public PayPasswordLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayPasswordLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_layout_pay_password, this);

        layout_bx = findViewById(R.id.layout_bx);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_4 = findViewById(R.id.tv_4);
        tv_5 = findViewById(R.id.tv_5);
        tv_6 = findViewById(R.id.tv_6);
        mList = new ArrayList<>();
        mList.add(new tvPassword(tv_1));
        mList.add(new tvPassword(tv_2));
        mList.add(new tvPassword(tv_3));
        mList.add(new tvPassword(tv_4));
        mList.add(new tvPassword(tv_5));
        mList.add(new tvPassword(tv_6));

    }

    public void setPayInputLayout(PayInputLayout payInputLayout) {
        this.payInputLayout = payInputLayout;
        payInputLayout.setOnInputClickListener(new PayInputLayout.OnInputClickListener() {
            @Override
            public void onClick(String res) {
                switch (res) {
                    case "-1":
                        //删除
                        deleteNum();
                        onPasswordType.onDelete();
                        break;
                    case "ok":
                        //完成
                        isSuccess();
                        break;
                    default:
                        //添加
                        addNum(res);
                        break;
                }
            }
        });
    }

    private void deleteNum() {
        for (int i = mList.size() - 1; i >= 0; i--) {
            if (!mList.get(i).getPassword().isEmpty()) {
                mList.get(i).setPassword("");
                return;
            }
        }
    }

    private void isSuccess() {
        String password = getPassword();
        if (password == null) return;
        onPasswordType.onEnd(password);
    }

    @Nullable
    public String getPassword() {
        String password = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getPassword().isEmpty()) {
                TsUtils.show("请输入支付密码!");
                return null;
            }
            password = password + mList.get(i).getPassword();
        }
        return password;
    }

    private void addNum(String res) {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getPassword().isEmpty()) {
                mList.get(i).setPassword(res);
                if (i == mList.size() - 1) {
                    //结束 去验证
                    isSuccess();
                }
                return;
            }
        }
    }

    class tvPassword {
        private TextView textView;
        private String password;

        public tvPassword(TextView textView) {
            this.textView = textView;
            password = "";
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
            if (password.isEmpty()) {
                textView.setText("");
            } else {
                textView.setText(CIPHER_TEXT);
            }
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}
