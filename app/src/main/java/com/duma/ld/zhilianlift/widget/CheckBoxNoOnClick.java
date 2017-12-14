package com.duma.ld.zhilianlift.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liudong on 2017/12/14.
 */

public class CheckBoxNoOnClick extends android.support.v7.widget.AppCompatCheckBox {
    public CheckBoxNoOnClick(Context context) {
        super(context);
    }

    public CheckBoxNoOnClick(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckBoxNoOnClick(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public interface OnNewClickListener {
        void onClick();
    }

    public void setOnNewClickListener(final OnNewClickListener onClick) {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (onClick != null) {
                            onClick.onClick();
                        }
                        break;
                }
                return true;
            }
        });
    }
}
