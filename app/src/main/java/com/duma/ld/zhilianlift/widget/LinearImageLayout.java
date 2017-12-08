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
 * Created by liudong on 2017/12/8.
 */

public class LinearImageLayout extends LinearLayout {
    private TextView tv_name, tv_number;
    private ImageView img_icon;
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

        tv_name = findViewById(R.id.tv_name);
        tv_number = findViewById(R.id.tv_number);
        img_icon = findViewById(R.id.img_icon);

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
        img_icon.setImageDrawable(imgDrawable);
        if (tvString == null || tvString.isEmpty()) {
            tv_name.setVisibility(GONE);
        }
        tv_name.setText(tvString);
        tv_name.setPadding(0, 0, 0, (int) padding);
        setNum(Num + "");
    }

    public void setNum(String num) {
        if (num == null || num.isEmpty() || num.equals("0")) {
            tv_number.setVisibility(GONE);
        } else {
            tv_number.setText(num);
        }
    }


}
