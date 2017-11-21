package com.duma.ld.baselibrary.base;


import android.app.Application;

/**
 * @author liudong
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
