package com.duma.ld.baselibrary.base;


import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

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

        /**
         * fragment
         */
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.NONE)
                // ture时，遇到异常："Can not perform this action after onSaveInstanceState!"时，会抛出
                // false时，不会抛出，会捕获，可以在handleException()里监听到
                .debug(isDebug())
                // 在debug=false时，即线上环境时，上述异常会被捕获并回调ExceptionHandler
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 建议在该回调处上传至我们的Crash监测服务器
                        // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

    protected abstract boolean isDebug();

}
