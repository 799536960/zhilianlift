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
    private boolean isOpenCache;

    //分页的适配器
    private BaseAdapter adapter;

    //是否开启dialog
    private boolean isDialog = false;
    private Activity mActivity;
    //是否tag
    private boolean isTag = true;
    public static int httpTag = 200;

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        loadingShow();
        if (isTag) {
            request.tag(httpTag);
        }
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
        isOpenCache = false;
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

    public MyJsonCallback<T> noTag() {
        isTag = false;
        return this;
    }

    public MyJsonCallback<T> setTag(int tag) {
        isTag = true;
        httpTag = tag;
        return this;
    }

    @Override
    public void onSuccess(Response<T> response) {
        if (isOpenCache) {
            /**
             * 如果缓存开启的话 网络请求回来的就不回调了
             * 因为缓存已经回调一次了
             * 网络请求就负责缓存就好了
             */
            return;
        }
        httpSuccess(response);
    }

    private void httpSuccess(Response<T> response) {
        Logger.json(new Gson().toJson(response.body()));
        if (config != null) {
            config.setOneSuccess(true);
        }
        if (successIsHideLoading) {
            loadingHide();
        }
        if (isDialog) {
            loadingHide();
        }
        onJsonSuccess(response, response.body());
    }

    @Override
    public void onCacheSuccess(Response<T> response) {
        super.onCacheSuccess(response);
        //回调了这里 说明启动了缓存
        Logger.e("读取的缓存");
        isOpenCache = true;
        httpSuccess(response);
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
