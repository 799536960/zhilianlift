package com.duma.ld.zhilianlift.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.zhilianlift.R;

/**
 * 有小圆点的布局
 * Created by liudong on 2017/12/8.
 */

public class LinearImageLayout extends LinearLayout {
    private TextView view_tv_name, view_tv_number;
    private ImageView view_img_icon;
    private Drawable imgDrawable;
    private String tvString;
    private float padding;
    private int Num;

    public LinearImageLayout(Context context) {
        this(context, null);
    }

    public LinearImageLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearImageLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_layout_image, this);

        view_tv_name = findViewById(R.id.view_tv_name);
        view_tv_number = findViewById(R.id.view_tv_number);
        view_img_icon = findViewById(R.id.view_img_icon);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LinearImageLayout);
        imgDrawable = a.getDrawable(R.styleable.LinearImageLayout_LiIcon);
        tvString = a.getString(R.styleable.LinearImageLayout_LiName);
        padding = a.getDimension(R.styleable.LinearImageLayout_LiPadding, 0);
        Num = a.getInteger(R.styleable.LinearImageLayout_LiNum, 0);
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view_img_icon.setImageDrawable(imgDrawable);
        if (tvString == null || tvString.isEmpty()) {
            view_tv_name.setVisibility(GONE);
        }
        view_tv_name.setText(tvString);
        view_tv_name.setPadding(0, (int) padding, 0, 0);
        setNum(Num + "");
    }

    public void setNum(String num) {
        if (num == null || num.isEmpty() || num.equals("0")) {
            view_tv_number.setVisibility(GONE);
        } else {
            view_tv_number.setVisibility(VISIBLE);
            view_tv_number.setText(num);
        }
    }


}
