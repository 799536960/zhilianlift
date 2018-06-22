package com.duma.ld.zhilianlift.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.duma.ld.baselibrary.base.BaseApplication;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.util.HttpApiCacheInterceptor;
import com.duma.ld.zhilianlift.view.start.ApiService;
import com.lzy.okgo.OkGo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.litepal.LitePal;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

import static com.duma.ld.zhilianlift.util.Constants.sp_Api;


public class MyApplication extends BaseApplication {

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);
            }
        });
    }

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
        if (isDebug()) {
            builder.addInterceptor(loggingInterceptor);
        }
        //全局的读取超时时间
        builder.readTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        OkGo.getInstance().init(this).setOkHttpClient(builder.build());
        //百度地图
        SDKInitializer.initialize(this);
        /**
         * 数据库
         */
        LitePal.initialize(this);

//        //检查内存溢出
//        LeakCanary.install(this);

        //api调试
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
        return AppUtils.isAppDebug();
    }
}
