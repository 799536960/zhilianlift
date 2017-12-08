package com.duma.ld.zhilianlift.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;

import static com.blankj.utilcode.util.ConvertUtils.dp2px;

/**
 * Created by liudong on 2017/6/14.
 */

public class DragSeekBarView extends LinearLayout {
    private onMaxProgress onMaxProgress;

    public interface onMaxProgress {
        void onMax();
    }

    private View view_loading, view_isCenter;
    private LinearLayout layout_drag, layout_bg;
    private TextView tv_res;
    private ImageView img_drag;

    private int DragProgressBg, DragViewBg, DragSbBg, DragTextColor;
    private float DragViewWidth;
    private Drawable DragViewDrawable;
    private String DragText;
    private boolean isCenter;
    private boolean isAnimation;

    private float mProgress;
    private float maxProgress;

    private float onClickX;
    private boolean isOnClick;
    private ViewGroup.LayoutParams layoutParams;

    private boolean isRun;//是否滑动中

    public DragSeekBarView(Context context) {
        this(context, null);
    }

    public DragSeekBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragSeekBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        isOnClick = false;
        View inflate = View.inflate(context, R.layout.view_dragseekbar, this);
        inflate.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.button_blue_shape));
        view_loading = findViewById(R.id.view_loading);
        layout_drag = (LinearLayout) findViewById(R.id.layout_drag);
        layout_bg = (LinearLayout) findViewById(R.id.layout_bg);
        tv_res = (TextView) findViewById(R.id.tv_res);
        img_drag = (ImageView) findViewById(R.id.img_drag);
        view_isCenter = findViewById(R.id.view_isCenter);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DragSeekBarView);
        DragProgressBg = a.getColor(R.styleable.DragSeekBarView_DragProgressBg, Color.rgb(144, 194, 49));
        DragViewBg = a.getColor(R.styleable.DragSeekBarView_DragViewBg, Color.rgb(255, 255, 255));
        DragSbBg = a.getColor(R.styleable.DragSeekBarView_DragSbBg, Color.rgb(230, 230, 230));
        DragTextColor = a.getColor(R.styleable.DragSeekBarView_DragTextColor, Color.rgb(153, 153, 153));
        DragViewWidth = a.getDimension(R.styleable.DragSeekBarView_DragViewWidth, dp2px(50));
        DragViewDrawable = a.getDrawable(R.styleable.DragSeekBarView_DragViewDrawable);
        DragText = a.getString(R.styleable.DragSeekBarView_DragText);
        isCenter = a.getBoolean(R.styleable.DragSeekBarView_isCenter, true);
        isAnimation = a.getBoolean(R.styleable.DragSeekBarView_isAnimation, false);
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        layout_drag.setBackgroundColor(DragViewBg);
        layout_bg.setBackgroundColor(DragSbBg);
        view_loading.setBackgroundColor(DragProgressBg);
        tv_res.setText(DragText);
        tv_res.setTextColor(DragTextColor);

        ViewGroup.LayoutParams layoutParams = layout_drag.getLayoutParams();
        layoutParams.width = (int) DragViewWidth;
        layout_drag.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParams1 = view_isCenter.getLayoutParams();
        if (isCenter) {
            layoutParams1.width = (int) DragViewWidth;
            view_isCenter.setLayoutParams(layoutParams1);
        } else {
            layoutParams1.width = 0;
            view_isCenter.setLayoutParams(layoutParams1);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initData();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        Logger.e("w:" + w);
//        Logger.e("h:" + h);
//        Logger.e("oldw:" + oldw);
//        Logger.e("oldh:" + oldh);
    }

    private void initData() {
        if (DragViewDrawable != null) {
            img_drag.setImageDrawable(DragViewDrawable);
        }
        if (isAnimation) {
            try {
                AnimationDrawable drawable = (AnimationDrawable) DragViewDrawable;
                drawable.start();
            } catch (Exception e) {
                TsUtils.show("请设置动画Drawable");
            }
        }

        layoutParams = view_loading.getLayoutParams();
        mProgress = 0;
        maxProgress = getWidth() - layout_drag.getWidth();
        layout_drag.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        onClickX = event.getRawX();
                        isRun = true;
                        isOnClick = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mProgress = event.getRawX() - onClickX;
                        startDrag();
                        break;
                    case MotionEvent.ACTION_UP:
                        initDrag();
                        break;
                }
                return true;
            }
        });

    }

    private void initDrag() {
        mProgress = 0;
        isOnClick = false;
        startDrag();
    }


    private void startDrag() {
        if (!isRun) {
            return;
        }
        if (mProgress <= 0) {
            layoutParams.width = 0;
        } else if (mProgress <= maxProgress || (int) maxProgress <= 0) {
            layoutParams.width = (int) mProgress;
        } else {
            layoutParams.width = (int) maxProgress;
            if (onMaxProgress != null) {
                if (isOnClick) {
                    isOnClick = false;
                    onMaxProgress.onMax();
                    initDrag();
                    isRun = false;
                }

            }
        }
        view_loading.setLayoutParams(layoutParams);
    }

    public void setOnMaxProgress(DragSeekBarView.onMaxProgress onMaxProgress) {
        this.onMaxProgress = onMaxProgress;
    }
}
