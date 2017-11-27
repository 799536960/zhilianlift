package com.duma.ld.baselibrary.base;


import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author liudong
 */
public abstract class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        /**
         * 常用工具
         */
        Utils.init(this);

        /**
         * 日志
         */
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                //是否打印线程信息
                .showThreadInfo(false)
                //要显示多少个方法行。默认2
                .methodCount(0)
                //隐藏内部方法调用直到偏移。默认5
//                .methodOffset(7)
                //更改日志策略以打印输出。默认logcat的
//                .logStrategy(customLog)
                .tag("liudong")
                .build();
        AndroidLogAdapter adapter = new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return isDebug();
            }
        };
        Logger.addLogAdapter(adapter);
    }

    protected abstract boolean isDebug();

}
