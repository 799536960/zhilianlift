package com.duma.ld.baselibrary.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.duma.ld.baselibrary.R;

/**
 * Created by liudong on 2017/6/9.
 */

public class OkDialog extends Dialog {
    private String biaoti, leirong, left, right;
    private TextView tv_biaoti, tv_leirong, tv_left, tv_right;
    private Object object;

    private OnLeftClickListener onLeftClickListener;
    private OnRightClickListener onRightClickListener;

    public interface OnLeftClickListener {
        void onLeft(Object object);

    }

    public interface OnRightClickListener {
        void onRight(Object object);
    }

    public void setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
    }

    public void setOnRightClickListener(OnRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
    }


    public OkDialog(@NonNull Activity activity) {
        super(activity);
    }


    public OkDialog setBiaoti(String biaoti) {
        this.biaoti = biaoti;
        return this;
    }

    public OkDialog setLeirong(String leirong) {
        this.leirong = leirong;
        return this;
    }

    public OkDialog setLeft(String left) {
        this.left = left;
        return this;
    }

    public OkDialog setRight(String right) {
        this.right = right;
        return this;
    }

    public OkDialog setObj(Object obj) {
        this.object = obj;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setCancelable(false);
        setContentView(R.layout.dialog_base);
        tv_biaoti = (TextView) findViewById(R.id.tv_biaoti);
        tv_leirong = (TextView) findViewById(R.id.tv_leirong);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_right = (TextView) findViewById(R.id.tv_right);

        if (StringUtils.isEmpty(biaoti)) {
            tv_biaoti.setVisibility(View.GONE);
        } else {
            tv_biaoti.setText(biaoti);
        }
        if (StringUtils.isEmpty(leirong)) {
            tv_leirong.setVisibility(View.GONE);
        } else {
            tv_leirong.setText(leirong);
        }
        if (StringUtils.isEmpty(left)) {
            tv_left.setVisibility(View.GONE);
        } else {
            tv_left.setText(left);
        }
        if (StringUtils.isEmpty(right)) {
            tv_right.setVisibility(View.GONE);
        } else {
            tv_right.setText(right);
        }

        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onLeftClickListener != null) {
                    onLeftClickListener.onLeft(object);
                }
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onRightClickListener != null) {
                    onRightClickListener.onRight(object);
                }
            }
        });
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;   //宽度填满
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
    }
}
