package com.duma.ld.zhilianlift.base.baseJsonHttp;

import android.app.Activity;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.PublicConfig;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;

/**
 * Created by liudong on 2017/6/5.
 */

public abstract class MyJsonCallback<T> extends JsonCallback<T> {
    private PublicConfig config;
    private boolean successIsHideLoading;
    private boolean isReadCache;//是否读取缓存

    //分页的适配器
    private BaseAdapter adapter;

    //是否开启dialog
    private boolean isDialog = false;
    private Activity mActivity;

    //是否开启缓存更新
    private boolean isCacheRefresh = false;


    @Override
    public void onStart(Request<T, ? extends Request> request) {
        loadingShow();
        HttpParams params = request.getParams();
        params.put("token", SpDataUtil.getToken());
        params.put("user_id", SpDataUtil.getUserId());
    }

    public MyJsonCallback() {
    }

    public MyJsonCallback(PublicConfig config) {
        this(config, true);
    }

    public MyJsonCallback(PublicConfig config, boolean successIsHideLoading) {
        this.config = config;
        this.successIsHideLoading = successIsHideLoading;
        isReadCache = false;
    }

    public MyJsonCallback<T> setLoadAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public MyJsonCallback<T> isDialog(Activity activity) {
        isDialog = true;
        mActivity = activity;
        return this;
    }

    public MyJsonCallback<T> openCacheRefresh() {
        isCacheRefresh = true;
        return this;
    }

    @Override
    public void onSuccess(Response<T> response) {
        if (isReadCache && !isCacheRefresh) {
            //如果已经读过缓存 就不加载了 除非isCacheRefresh==true
            return;
        }
        httpSuccess(response);
    }

    /**
     * 缓存只读一次
     *
     * @param response
     */
    @Override
    public void onCacheSuccess(Response<T> response) {
        super.onCacheSuccess(response);
        //没读过缓存同时页面没数据 才会读缓存
        if (!isReadCache) {
            if (config != null) {
                if (!config.isOneSuccess()) {
                    //页面没数据
                    readCache(response);
                }
            } else {
                Logger.e("请传入PublicConfig或者自己重写缓存逻辑");
            }
        }
    }

    public void readCache(Response<T> response) {
        Logger.e("读取了缓存");
        // 已经读取缓存
        isReadCache = true;
        httpSuccess(response);
        if (isCacheRefresh) {
            //现在show小圆圈
            if (config.getSw_loading() != null) {
                config.showLoadingView();
            }
        }
    }

    private void httpSuccess(Response<T> response) {
        Logger.json(new Gson().toJson(response.body()));
        if (config != null) {
            config.setOneSuccess(true);
        }
        if (successIsHideLoading || isDialog) {
            loadingHide();
        }
        onJsonSuccess(response, response.body());
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        loadingHide();
        showErrorView();
        if (response.code() == 200) {
            //前面返回的请求成功后 code==500 要读取message
            TsUtils.show(response.getException().getMessage());
            switch (response.getException().getMessage()) {
                case "100":
                    TsUtils.show("登录已失效!请重新登录");
                    SpDataUtil.removeUser();
                    break;
                default:
                    TsUtils.show("" + response.getException().getMessage());
                    break;
            }
        } else {
            TsUtils.show("网络请求错误!" + response.code() + " msg: " + response.message());
        }
    }

    private void showErrorView() {
        if (config == null) {
            return;
        }
        if (adapter != null) {
            adapter.loadMoreFail();
        }
        config.showErrorView();

    }

    private void loadingHide() {
        DialogUtil.getInstance().hide();
        if (config == null) {
            return;
        }
        config.hideLoadingView();
    }

    private void loadingShow() {
        if (isDialog) {
            DialogUtil.getInstance().show(mActivity);
            return;
        }
        if (config == null) {
            return;
        }
        if (adapter != null && config.isOneSuccess() && adapter.getPage() != 1) {
            //在分页中 如果有数据了 分页会有自己的loading page==1的时候 说明是在次加载

        } else {
            config.showLoadingView();
        }

    }

    protected abstract void onJsonSuccess(Response<T> respons, T t);
}
