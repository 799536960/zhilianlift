package com.duma.ld.zhilianlift.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.zhilianlift.R;

/**
 * Created by liudong on 2017/12/25.
 */

public class CheckBoxComment extends LinearLayout {
    private ImageView img_view_icon;
    private TextView tv_view_name;
    private String text;
    private boolean checked;
    private Context context;

    public CheckBoxComment(Context context) {
        this(context, null);
    }

    public CheckBoxComment(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckBoxComment(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View.inflate(context, R.layout.view_chenk_box_comment, this);
        img_view_icon = findViewById(R.id.img_view_icon);
        tv_view_name = findViewById(R.id.tv_view_name);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CheckBoxComment);
            text = a.getString(R.styleable.CheckBoxComment_CbC_Name);
            a.recycle();
        }
        if (text == null || text.isEmpty()) {
            text = "默认";
        }
        setText(text);
        setChecked(false);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        if (checked) {
            tv_view_name.setTextColor(ContextCompat.getColor(context, R.color.hong));
            img_view_icon.setVisibility(VISIBLE);
        } else {
            img_view_icon.setVisibility(GONE);
            tv_view_name.setTextColor(ContextCompat.getColor(context, R.color.hei1));
        }
    }

    public void setText(String text) {
        this.text = text;
        tv_view_name.setText(text);
    }
}
