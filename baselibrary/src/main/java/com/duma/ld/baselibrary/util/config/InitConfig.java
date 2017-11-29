package com.duma.ld.baselibrary.util.config;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.duma.ld.baselibrary.base.BaseActivity;

/**
 * Created by liudong on 2017/11/10.
 */

public class InitConfig {
    private BaseActivity mActivity;
    private View mRootView;

    private OnViewConfigListener onViewConfigListener;

    public InitConfig(BaseActivity mActivity, OnViewConfigListener onViewConfigListener) {
        this.mActivity = mActivity;
        this.onViewConfigListener = onViewConfigListener;
    }

    public InitConfig(BaseActivity activity, View rootView, OnViewConfigListener onViewConfigListener) {
        this.mActivity = activity;
        this.mRootView = rootView;
        this.onViewConfigListener = onViewConfigListener;
    }

    public ActivityConfig setLayoutIdByActivity(@LayoutRes int layoutResID) {
        return setLayoutIdByActivity(layoutResID, true);
    }

    /**
     * 初始化布局
     *
     * @param layoutResID   布局
     * @param isLoadOrError 是否需要错误和load页面 默认true
     * @return 布局配置
     */
    public ActivityConfig setLayoutIdByActivity(@LayoutRes int layoutResID, boolean isLoadOrError) {
        return new ActivityConfig(mActivity, layoutResID, isLoadOrError, onViewConfigListener);
    }

    public FragmentConfig setLayoutIdByFragment(@LayoutRes int layoutResID) {
        return setLayoutIdByFragment(layoutResID, true);
    }

    /**
     * 初始化布局
     *
     * @param layoutResID   布局
     * @param isLoadOrError 是否需要错误和load页面 默认true
     * @return 布局配置
     */
    public FragmentConfig setLayoutIdByFragment(@LayoutRes int layoutResID, boolean isLoadOrError) {
        return new FragmentConfig(mActivity, mRootView, layoutResID, isLoadOrError, onViewConfigListener);
    }
}
