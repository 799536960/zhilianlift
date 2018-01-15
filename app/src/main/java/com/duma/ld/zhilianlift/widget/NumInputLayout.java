package com.duma.ld.zhilianlift.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duma.ld.zhilianlift.R;

/**
 * 能输入数字 加减 编辑
 * Created by liudong on 2017/12/8.
 */

public class NumInputLayout extends LinearLayout implements TextWatcher {
    private FrameLayout layout_jian, layout_jia;
    private ImageView img_jian, img_jia;
    private EditText edit_num;
    private int num;
    private int maxNum;

    private Context mContext;
    private OnInputListener onInputListener;
    private OnTextClickListener onTextClickListener;

    public void setOnTextClickListener(OnTextClickListener onTextClickListener) {
        this.onTextClickListener = onTextClickListener;
    }

    public void setOnInputListener(OnInputListener onInputListener) {
        this.onInputListener = onInputListener;
    }

    public interface OnInputListener {
        void onInput(int num);
    }

    public interface OnTextClickListener {
        void onClick(int num);
    }

    public int getNum() {
        return num;
    }

    public NumInputLayout(Context context) {
        this(context, null);
    }

    public NumInputLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View.inflate(context, R.layout.view_num_input, this);
        img_jian = findViewById(R.id.img_jian);
        img_jia = findViewById(R.id.img_jia);
        edit_num = findViewById(R.id.edit_num);
        layout_jian = findViewById(R.id.layout_jian);
        layout_jia = findViewById(R.id.layout_jia);
        layout_jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTextClickListener != null) {
                    int readNum = getReadNum(num - 1);
                    if (readNum != num) {
                        onTextClickListener.onClick(readNum);
                    }
                } else {
                    setNum(num - 1);
                }

            }
        });
        layout_jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTextClickListener != null) {
                    int readNum = getReadNum(num + 1);
                    if (readNum != num) {
                        onTextClickListener.onClick(readNum);
                    }
                } else {
                    setNum(num + 1);
                }
            }
        });
        edit_num.addTextChangedListener(this);
        maxNum = 999;
        setNum(1);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NumInputLayout);
            float mWidth = a.getDimension(R.styleable.NumInputLayout_Nun_Width, dp2px(25));
            a.recycle();
            refreH((int) mWidth);
        }
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        refreH();
//    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    private int dp2px(final float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setNoInput() {
        edit_num.setFocusable(false);
        edit_num.setFocusableInTouchMode(false);
    }


    public void setMaxNum(int maxNum) {
        if (maxNum < 1) {
            this.maxNum = 1;
        } else if (maxNum > 999) {
            this.maxNum = 999;
        } else {
            this.maxNum = maxNum;
        }
        setNum(num);
    }

    public void setNum(int num) {
        this.num = getReadNum(num);
        if (this.num == 1) {
            img_jian.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.jian_hui));
            img_jia.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.jia_hei));
        } else if (this.num == maxNum) {
            img_jian.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.jian_hei));
            img_jia.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.jia_hui));
        } else {
            img_jian.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.jian_hei));
            img_jia.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.jia_hei));
        }
        if (!edit_num.getText().toString().equals(this.num + "")) {
            edit_num.setText(this.num + "");
            edit_num.setSelection(edit_num.getText().toString().length());
            if (onInputListener != null) {
                onInputListener.onInput(this.num);
            }
        }
    }

    public int getReadNum(int num) {
        if (num < 1) {
            return 1;
        } else if (num > maxNum) {
            return maxNum;
        } else {
            return num;
        }
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        refreH();
//    }

    private void refreH(int width) {
        LayoutParams layoutParams = new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        layout_jian.setLayoutParams(layoutParams);
        layout_jia.setLayoutParams(layoutParams);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String s1 = s.toString();
        if (s1.isEmpty()) {
            s1 = "1";
        }
        if (!s1.equals(num + "") || edit_num.getText().toString().isEmpty()) {
            setNum(Integer.parseInt(s1));
        }
    }

    public EditText getEdit_num() {
        return edit_num;
    }
}
