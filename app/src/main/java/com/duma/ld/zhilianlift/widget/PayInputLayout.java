package com.duma.ld.zhilianlift.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.zhilianlift.R;

/**
 * 支付密码输入法
 * Created by liudong on 2017/12/8.
 */

public class PayInputLayout extends LinearLayout implements View.OnClickListener {
    private TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, tv_8, tv_9, tv_0, tv_ok;
    private FrameLayout layout_delete;
    private OnInputClickListener onInputClickListener;

    public void setOnInputClickListener(OnInputClickListener onInputClickListener) {
        this.onInputClickListener = onInputClickListener;
    }

    public interface OnInputClickListener {
        void onClick(String res);
    }

    public PayInputLayout(Context context) {
        this(context, null);
    }

    public PayInputLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_layout_pay_input, this);

        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_4 = findViewById(R.id.tv_4);
        tv_5 = findViewById(R.id.tv_5);
        tv_6 = findViewById(R.id.tv_6);
        tv_7 = findViewById(R.id.tv_7);
        tv_8 = findViewById(R.id.tv_8);
        tv_9 = findViewById(R.id.tv_9);
        tv_0 = findViewById(R.id.tv_0);
        tv_ok = findViewById(R.id.tv_ok);
        layout_delete = findViewById(R.id.layout_delete);

        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
        tv_5.setOnClickListener(this);
        tv_6.setOnClickListener(this);
        tv_7.setOnClickListener(this);
        tv_8.setOnClickListener(this);
        tv_9.setOnClickListener(this);
        tv_0.setOnClickListener(this);
        tv_ok.setOnClickListener(this);
        layout_delete.setOnClickListener(this);

        setOkGone();
    }

    public void setOkGone() {
        tv_ok.setVisibility(INVISIBLE);
    }


    @Override
    public void onClick(View v) {
        if (onInputClickListener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.tv_ok:
                onInputClickListener.onClick("ok");
                break;
            case R.id.layout_delete:
                onInputClickListener.onClick("-1");
                break;
            default:
                onInputClickListener.onClick(((TextView) v).getText().toString());
        }
    }
}
