package com.duma.ld.zhilianlift.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.zhilianlift.R;

/**
 * 用于筛选的chenkbox
 * isMode false 文字加设定的图片  只有这种model的时候设置的图片才有效  0
 * isMode true 二个箭头 1
 * Created by liudong on 2017/12/20.
 */

public class CheckBoxGoodsList extends LinearLayout {
    private boolean typeTop;

    private TextView tv_name;
    private ImageView img_icon;
    private boolean checked;

    //要显示的文字
    private String cbName;
    //文字颜色
    private int cbTrueTvColor, cbFalseTvColor;
    //设置图片
    private Drawable cbTrueIcon, cbFalseIcon;
    //mode
    private boolean isMode;

    public CheckBoxGoodsList(Context context) {
        this(context, null);
    }

    public CheckBoxGoodsList(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckBoxGoodsList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_check_box_goodslist, this);
        tv_name = findViewById(R.id.tv_name);
        img_icon = findViewById(R.id.img_icon);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CheckBoxGoodsList);
            cbName = a.getString(R.styleable.CheckBoxGoodsList_CbName);
            cbTrueIcon = a.getDrawable(R.styleable.CheckBoxGoodsList_CbTrueIcon);
            cbFalseIcon = a.getDrawable(R.styleable.CheckBoxGoodsList_CbFalseIcon);
            isMode = a.getBoolean(R.styleable.CheckBoxGoodsList_CbIsMode, false);
            cbTrueTvColor = a.getColor(R.styleable.CheckBoxGoodsList_CbTrueTvColor, ContextCompat.getColor(context, R.color.hong));
            cbFalseTvColor = a.getColor(R.styleable.CheckBoxGoodsList_CbFalseTvColor, ContextCompat.getColor(context, R.color.hei1));
            a.recycle();
        }
        if (cbName == null) {
            cbName = "设置文字";
        }
        initData();
    }

    private void initData() {
        setText(cbName);
        setChecked(false);
    }

    public void setChecked(boolean checked) {
        setChecked(checked, typeTop);
    }

    public void setChecked(boolean checked, boolean isTop) {
        this.checked = checked;
        this.typeTop = isTop;
        if (checked) {
            tv_name.setTextColor(cbTrueTvColor);
            if (isMode) {
                if (isTop) {
                    img_icon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.zhuangtai2));
                } else {
                    img_icon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.zhuangtai3));
                }
            } else {
                if (cbTrueIcon == null) {
                    img_icon.setVisibility(GONE);
                } else {
                    img_icon.setImageDrawable(cbTrueIcon);
                }
            }
        } else {
            tv_name.setTextColor(cbFalseTvColor);
            if (isMode) {
                img_icon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.zhuangtai1));
            } else {
                if (cbTrueIcon == null) {
                    img_icon.setVisibility(GONE);
                } else {
                    img_icon.setImageDrawable(cbFalseIcon);
                }
            }
        }
    }

    public void setText(String res) {
        tv_name.setText(res);
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isTypeTop() {
        return typeTop;
    }
}
