package com.duma.ld.zhilianlift.base;

import com.baidu.mapapi.SDKInitializer;
import com.duma.ld.baselibrary.base.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

import org.litepal.LitePal;

/**
 * Created by liudong on 2017/11/27.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //百度
        SDKInitializer.initialize(this);
        /**
         * 数据库
         */
        LitePal.initialize(this);

        //检查内存溢出
        LeakCanary.install(this);
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
