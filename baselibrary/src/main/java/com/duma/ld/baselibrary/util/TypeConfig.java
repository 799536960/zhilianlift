package com.duma.ld.baselibrary.util;

import android.app.Activity;
import android.support.annotation.LayoutRes;

/**
 * Created by liudong on 2017/11/10.
 */

public class TypeConfig {
    private Activity mActivity;
    @LayoutRes
    private int layoutResID;

    public TypeConfig(Activity mActivity) {
        this.mActivity = mActivity;
    }


    public ActivityConfig setLayoutId(@LayoutRes int layoutResID) {
        return ActivityConfig.init(mActivity, layoutResID, true);
    }

    public ActivityConfig setLayoutId(@LayoutRes int layoutResID, boolean isLoadOrError) {
        return ActivityConfig.init(mActivity, layoutResID, isLoadOrError);
    }
}
