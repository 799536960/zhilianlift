package com.duma.ld.baselibrary.util.config;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
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
        //本体布局
        mViewContent = LayoutInflater.from(activity).inflate(layoutResID, (FrameLayout) rootView.findViewById(R.id.layout_boot_content));
        setmViewContent(mViewContent);
        //初始化load error 页面
        initLoadOrErrorView((FrameLayout) mRootView.findViewById(R.id.layout_boot_loading), (FrameLayout) mRootView.findViewById(R.id.layout_boot_error));
    }

    public FragmentConfig setRefreshByFragment(int swId, int contentId) {
        setRefresh(swId, contentId);
        return this;
    }
}
