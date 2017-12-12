package com.duma.ld.baselibrary.util.config;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.R;
import com.duma.ld.baselibrary.base.BaseActivity;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;

/**
 * Created by liudong on 2017/11/10.
 */

public class ActivityConfig extends PublicConfig {
    //绑定的activity
    private BaseActivity mActivity;
    //本体布局视图
    private View mViewContent;


    public ActivityConfig(BaseActivity activity, @LayoutRes int layoutResID, boolean isOpen, OnViewConfigListener onViewConfigListener) {
        super(onViewConfigListener, activity, isOpen);
        this.mActivity = activity;
        //设置根布局
        mActivity.setContentView(R.layout.activity_root);
        //设置本体布局
        mViewContent = LayoutInflater.from(mActivity).inflate(layoutResID, (FrameLayout) mActivity.findViewById(R.id.layout_boot_content));
    }

    public ActivityConfig setTopBar_A(String name) {
        setTopBar(name);
        return this;
    }

    public ActivityConfig setTopBar_A(String name, OnTopBarLeftListener onTopBarLeftListener) {
        setTopBar(name, onTopBarLeftListener);
        return this;
    }

    @Override
    protected ViewGroup getBootErrorViewGroup() {
        return (FrameLayout) mActivity.findViewById(R.id.layout_boot_error);
    }

    @Override
    protected ViewGroup getBootLoadingViewGroup() {
        return (FrameLayout) mActivity.findViewById(R.id.layout_boot_loading);
    }

    @Override
    protected ViewGroup getBootTopbarViewGroup() {
        return (FrameLayout) mActivity.findViewById(R.id.layout_toot_topbar);
    }

    @Override
    protected View getBootContentViewGroup() {
        return mViewContent;
    }
}
