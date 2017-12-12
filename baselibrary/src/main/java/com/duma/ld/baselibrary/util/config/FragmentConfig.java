package com.duma.ld.baselibrary.util.config;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.R;
import com.duma.ld.baselibrary.base.BaseActivity;

/**
 * Created by liudong on 2017/11/10.
 */

public class FragmentConfig extends PublicConfig {
    //根布局
    private View mRootView;
    //本体布局视图
    private View mViewContent;


    public FragmentConfig(BaseActivity activity, View rootView, @LayoutRes int layoutResID, boolean isOpen, OnViewConfigListener onViewConfigListener) {
        super(onViewConfigListener, activity, isOpen);
        this.mRootView = rootView;
        //设置本体布局
        mViewContent = LayoutInflater.from(activity).inflate(layoutResID, (FrameLayout) rootView.findViewById(R.id.layout_boot_content));
    }

    public FragmentConfig setRefresh_f(int id, int rootId, int contentId) {
        setRefresh(id, rootId, contentId);
        return this;
    }

    public FragmentConfig setLoadingOrErrorView_f(int rootId, int contentId) {
        setLoadingOrErrorViewByLinearlayout(rootId, contentId);
        return this;
    }

    public FragmentConfig setTopBar_f(String name) {
        setTopBar(name);
        return this;
    }


    @Override
    protected ViewGroup getBootErrorViewGroup() {
        return (FrameLayout) mRootView.findViewById(R.id.layout_boot_error);
    }

    @Override
    protected ViewGroup getBootLoadingViewGroup() {
        return (FrameLayout) mRootView.findViewById(R.id.layout_boot_loading);
    }

    @Override
    protected ViewGroup getBootTopbarViewGroup() {
        return (FrameLayout) mRootView.findViewById(R.id.layout_toot_topbar);
    }

    @Override
    protected View getBootContentViewGroup() {
        return mViewContent;
    }
}
