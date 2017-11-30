package com.duma.ld.zhilianlift.base;

import com.baidu.mapapi.SDKInitializer;
import com.duma.ld.baselibrary.base.BaseApplication;

/**
 * Created by liudong on 2017/11/27.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }

    /**
     * 是否是debug
     *
     * @return ture 是
     */
    @Override
    protected boolean isDebug() {
        return true;
    }
}
