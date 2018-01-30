package com.duma.ld.zhilianlift.base;

import android.content.Intent;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.SPUtils;
import com.duma.ld.baselibrary.base.BaseApplication;
import com.duma.ld.zhilianlift.util.HttpApiCacheInterceptor;
import com.duma.ld.zhilianlift.view.start.ApiService;
import com.lzy.okgo.OkGo;
import com.squareup.leakcanary.LeakCanary;

import org.litepal.LitePal;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

import static com.duma.ld.zhilianlift.util.Constants.sp_Api;


public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * okhttp
         */
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpApiCacheInterceptor loggingInterceptor = new HttpApiCacheInterceptor(Tag);
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpApiCacheInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.WARNING);
        builder.addInterceptor(loggingInterceptor);
        //全局的读取超时时间
        builder.readTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        OkGo.getInstance().init(this).setOkHttpClient(builder.build());

        //百度
        SDKInitializer.initialize(this);
        /**
         * 数据库
         */
        LitePal.initialize(this);

        //检查内存溢出
        LeakCanary.install(this);

        if (isDebug()) {
            SPUtils.getInstance().put(sp_Api, true);
            Intent startIntent = new Intent(this, ApiService.class);
            startService(startIntent);
        }
    }

    /**
     * 是否是debug
     *
     * @return ture 是
     */
    @Override
    public boolean isDebug() {
        return true;
    }
}
