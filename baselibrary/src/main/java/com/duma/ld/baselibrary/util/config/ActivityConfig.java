package com.duma.ld.baselibrary.util.config;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.R;
import com.duma.ld.baselibrary.base.BaseActivity;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.base.OnTopBarRightListener;

/**
 * Created by liudong on 2017/11/10.
 */

public class ActivityConfig extends PublicConfig {
    //topbar 左边图片的默认值
    private final int LiftImg_Default = -1;
    //绑定的activity
    private BaseActivity mActivity;
    //本体布局视图
    private View mViewContent;


    public ActivityConfig(BaseActivity activity, @LayoutRes int layoutResID, boolean isOpen, OnViewConfigListener onViewConfigListener) {
        super(onViewConfigListener, activity, isOpen);
        this.mActivity = activity;
        //设置根布局
        mActivity.setContentView(R.layout.activity_root);
        //本体布局
        mViewContent = LayoutInflater.from(mActivity).inflate(layoutResID, (FrameLayout) mActivity.findViewById(R.id.layout_boot_content));
        //初始化load error 页面
        initLoadOrErrorView((FrameLayout) mActivity.findViewById(R.id.layout_boot_loading), (FrameLayout) mActivity.findViewById(R.id.layout_boot_error));
    }

    /**
     * 默认的topbar 有返回键的
     *
     * @param name 标题
     * @return
     */
    @NonNull
    public ActivityConfig setTopBar(String name) {
        setTopBar(name, null, LiftImg_Default, 0, null, null);
        return this;
    }

    @NonNull
    public ActivityConfig setTopBar(String name, OnTopBarLeftListener onTopBarLeftListener) {
        setTopBar(name, null, LiftImg_Default, 0, onTopBarLeftListener, null);
        return this;
    }

    /**
     * 设置topbar
     *
     * @param name                  topbar的标题
     * @param rightText             右边的文字
     * @param liftImg               左边的图片
     * @param rightImg              右边的图片
     * @param onTopBarLeftListener  监听
     * @param onTopBarRightListener 监听
     */
    public void setTopBar(String name, String rightText, @DrawableRes int liftImg, @DrawableRes int rightImg
            , final OnTopBarLeftListener onTopBarLeftListener, final OnTopBarRightListener onTopBarRightListener) {
        FrameLayout viewById = mActivity.findViewById(R.id.layout_topbar);
        if (name == null) {
            viewById.setVisibility(View.GONE);
            return;
        }
        //有topbar
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.activity_topbar, viewById);
        if (liftImg == LiftImg_Default) {
            ((ImageView) inflate.findViewById(R.id.img_left)).setImageDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.back));
        } else if (liftImg != 0) {
            ((ImageView) inflate.findViewById(R.id.img_left)).setImageDrawable(ContextCompat.getDrawable(mActivity, liftImg));
        }
        if (rightImg != 0) {
            ((ImageView) inflate.findViewById(R.id.img_right)).setImageDrawable(ContextCompat.getDrawable(mActivity, rightImg));
        }
        if (rightText != null) {
            ((TextView) inflate.findViewById(R.id.tv_right)).setText(rightText);
        }
        ((TextView) inflate.findViewById(R.id.tv_title)).setText(name);
        //设置点击事件
        LinearLayout liftLayout = inflate.findViewById(R.id.layout_left);
        if (liftImg != 0) {
            liftLayout.setVisibility(View.VISIBLE);
            liftLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTopBarLeftListener != null) {
                        onTopBarLeftListener.onClick();
                    } else {
                        mActivity.finish();
                    }
                }
            });
        } else {
            liftLayout.setVisibility(View.GONE);
        }

        //如果没有
        LinearLayout rightLayout = inflate.findViewById(R.id.layout_right);
        if (rightText != null || rightImg != 0) {
            rightLayout.setVisibility(View.VISIBLE);
            rightLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTopBarRightListener != null) {
                        onTopBarRightListener.onClick();
                    }
                }
            });
        } else {
            rightLayout.setVisibility(View.GONE);
        }


    }


}
