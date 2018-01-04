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
 * 用于筛选的chenkbox
 * isMode false 文字加设定的图片  只有这种model的时候设置的图片才有效  0
 * isMode true 二个箭头 1
 * Created by liudong on 2017/12/20.
 */

public class RedioLayout extends LinearLayout {
    private Context context;

    private TextView tv_redio;
    private ImageView image_redio;


    //是否能点击
    private boolean isClick;
    //点击
    private boolean checked;


    public RedioLayout(Context context) {
        this(context, null);
    }

    public RedioLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedioLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View.inflate(context, R.layout.view_redio_layout, this);
        tv_redio = findViewById(R.id.tv_redio);
        image_redio = findViewById(R.id.image_redio);
        String text = "";
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RedioLayout);
            text = a.getString(R.styleable.RedioLayout_redio_text);
            a.recycle();
        }
        if (text == null || text.isEmpty()) {
            text = "请填写名字";
        }
        setText(text);
        isClick = true;
        checked = true;
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setChecked(!checked);
//            }
//        });
    }

    public void setText(String text) {
        tv_redio.setText(text);
    }


    public boolean isClick() {
        return isClick;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setClick(boolean isClick) {
        this.isClick = isClick;
        if (!isClick) {
            tv_redio.setTextColor(ContextCompat.getColor(context, R.color.hui2));
        } else {
            tv_redio.setTextColor(ContextCompat.getColor(context, R.color.hei1));
        }
    }

    public void setChecked(boolean b) {
        if (!isClick) {
            return;
        }
        this.checked = b;
        tv_redio.setTextColor(ContextCompat.getColor(context, R.color.hei1));
        if (b) {
            image_redio.setVisibility(VISIBLE);
        } else {
            image_redio.setVisibility(GONE);
        }
    }

}
