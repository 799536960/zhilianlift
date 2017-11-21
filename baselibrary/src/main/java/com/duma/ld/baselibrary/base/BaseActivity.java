package com.duma.ld.baselibrary.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SupportActivity;

import com.duma.ld.baselibrary.util.ActivityConfig;
import com.duma.ld.baselibrary.util.TypeConfig;

/**
 * @author liudong
 * @date 2017/11/10
 */

public abstract class BaseActivity extends SupportActivity {
    protected Activity mActivity;
    protected ActivityConfig mActivityConfig;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        this.mActivityConfig = setActivityConfig(savedInstanceState, new TypeConfig(mActivity));
        init();
    }

    protected void init() {
    }

    protected abstract ActivityConfig setActivityConfig(Bundle savedInstanceState, TypeConfig typeConfig);
}
