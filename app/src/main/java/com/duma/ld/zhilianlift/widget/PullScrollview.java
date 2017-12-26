package com.duma.ld.zhilianlift.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.duma.ld.zhilianlift.R;

public class PullScrollview extends ScrollView {
    //要操作的布局
    private View innerView;
    private float y;
    private Rect normal = new Rect();
    private boolean animationFinish = true;
    private boolean isScroll = false;
    private boolean isSuccess = false;
    private OnScrollEnd onScrollEnd;
    private LinearLayout ScrollView;
    private LinearLayout.LayoutParams layoutParams;
    private int TopValue;

    public void setOnScrollEnd(OnScrollEnd onScrollEnd) {
        this.onScrollEnd = onScrollEnd;
    }

    public interface OnScrollEnd {
        void onEnd();

        void onSuccess();
    }

    public PullScrollview(Context context) {
        super(context, null);
    }

    public PullScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount > 0) {
            innerView = getChildAt(0);
        }
        ScrollView = findViewById(R.id.view_scrollView);
        layoutParams = (LinearLayout.LayoutParams) ScrollView.getLayoutParams();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (innerView == null) {
            return super.onTouchEvent(ev);
        } else {
            commonTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 自定义touch事件处理
     *
     * @param ev
     */
    private void commonTouchEvent(MotionEvent ev) {
        if (animationFinish) {
            int action = ev.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    y = ev.getY();
                    isScroll = false;
                    isSuccess = false;
                    TopValue = 0;
                    break;
                case MotionEvent.ACTION_MOVE:
                    float preY = y == 0 ? ev.getY() : y;
                    float nowY = ev.getY();
                    int detailY = (int) (preY - nowY);
                    y = nowY;
//                    Log.e("detailY:" + detailY);
                    //操作view进行拖动detailY的一半
                    if (isNeedMove() && detailY > 0) {
                        isScroll = true;
                        //布局改变位置之前，记录一下正常状态的位置
                        if (normal.isEmpty()) {
                            normal.set(innerView.getLeft(), innerView.getTop(), innerView.getRight(), innerView.getBottom());
                        }
                        TopValue = TopValue + detailY / 2;
                        layoutParams.setMargins(0, 0, 0, TopValue);
                        ScrollView.setLayoutParams(layoutParams);
//                        innerView.setPadding(innerView.getPaddingLeft(), innerView.getPaddingTop(), innerView.getPaddingRight(), innerView.getPaddingBottom() + detailY / 2);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    y = 0;
                    //布局回滚到原来的位置
//                    if (isNeedAnimation() && isScroll) {
//                        animation();
//                    }
                    break;
            }
        }
    }

    private void animation() {
//        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, normal.top - innerView.getTop());
//        ta.setDuration(200);
//        ta.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                animationFinish = false;
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                innerView.clearAnimation();
//                innerView.layout(normal.left, normal.top, normal.right, normal.bottom);
//                normal.setEmpty();
//                animationFinish = true;
////                if (onScrollEnd != null) {
////                    onScrollEnd.onEnd();
////                }
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        innerView.startAnimation(ta);

        animationFinish = false;
        final int value = 0 - innerView.getTop();
//        Log.e("value:" + value);
        final int t = innerView.getTop();
        final int b = innerView.getBottom();
        ValueAnimator animator = ValueAnimator.ofInt(innerView.getPaddingBottom(), 0);
        animator
                .setDuration(200)
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                        innerView.layout(normal.left, normal.top+value, normal.right, normal.bottom);
                        int num = (int) valueAnimator.getAnimatedValue();
                        innerView.setPadding(innerView.getPaddingLeft(), innerView.getPaddingTop(), innerView.getPaddingRight(), num);

////                        Log.e("num:" + num);
//                        innerView.layout(innerView.getLeft(), t + num, innerView.getRight(), b + num);
////                        Log.e("onAnimationUpdatet:" + innerView.getTop() + " onAnimationUpdateb:" + innerView.getBottom());
                        if (num == value) {
//                            innerView.layout(normal.left, normal.top, normal.right, normal.bottom);
                            normal.setEmpty();
                            animationFinish = true;
//                            if (onScrollEnd != null) {
//                                onScrollEnd.onEnd();
//                            }
                        }
                    }
                });
        animator.start();
    }

    /**
     * 判断是否需要回滚
     *
     * @return
     */
    private boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    /**
     * 判断是否需要移动
     *
     * @return
     */
    private boolean isNeedMove() {
        int offset = innerView.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
//        Log.e("getMeasuredHeight:" + innerView.getMeasuredHeight() + "----getHeight:" + getHeight());
//        Log.e("offset:" + offset + "----scrollY:" + scrollY);
        if (scrollY == offset) {
            return true;
        }
        return false;
    }
}