package com.duma.ld.zhilianlift.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.blankj.utilcode.util.StringUtils;

/**
 * settext 为空 有个默认值
 * Created by liudong on 2018/1/22.
 */

public class TvNoNullText extends AppCompatTextView {
    public TvNoNullText(Context context) {
        super(context);
    }

    public TvNoNullText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TvNoNullText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNewText(CharSequence text) {
        if (StringUtils.isEmpty(text)) {
            setText("未填");
        } else {
            setText(text);
        }

    }

    public void setNewText(CharSequence text, String add) {
        if (StringUtils.isEmpty(text)) {
            setText("未填");
        } else {
            setText(text + add);
        }

    }
}
