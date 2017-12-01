package com.duma.ld.zhilianlift.base.baseJsonHttp;

import com.duma.ld.baselibrary.util.Log;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.PublicConfig;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * Created by liudong on 2017/6/5.
 */

public abstract class MyJsonCallback<T> extends JsonCallback<T> {

    private PublicConfig config;
    private boolean isOneSuccess = false;

    public MyJsonCallback() {
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        loadingShow();
    }


    public MyJsonCallback(PublicConfig config) {
        this.config = config;
    }

    @Override
    public void onSuccess(Response<T> response) {
        isOneSuccess = true;
        loadingHide();
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
                case "400":
                    Log.e("重新登陆400");
                    break;
                default:
                    TsUtils.show(response.getException().getMessage());
                    break;
            }
        } else {
            TsUtils.show("网络请求错误!" + response.code() + " msg: " + response.message());
        }
    }

    private void showErrorView() {
        if (!isOneSuccess) {
            //没有请求成功的话 就显示error页面
            config.showErrorView();
        } else {
            //已经请求成功一次了 说明页面有数据了 而且只可能是下拉刷新  所以啥都不做
        }

    }

    private void loadingHide() {
        config.hideLoadingView();
    }

    private void loadingShow() {
        config.showLoadingView(isOneSuccess);
    }
}
