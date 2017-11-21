package com.duma.ld.baselibrary.util;

import android.support.annotation.LayoutRes;

import com.duma.ld.baselibrary.base.BaseActivity;

/**
 * Created by liudong on 2017/11/10.
 */

public class TypeConfig {
    private BaseActivity mActivity;
    @LayoutRes
    private int layoutResID;

    public TypeConfig(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }


    public ActivityConfig setLayoutId(@LayoutRes int layoutResID) {
        return ActivityConfig.init(mActivity, layoutResID, true);
    }

    public ActivityConfig setLayoutId(@LayoutRes int layoutResID, boolean isLoadOrError) {
        return ActivityConfig.init(mActivity, layoutResID, isLoadOrError);
    }
}
