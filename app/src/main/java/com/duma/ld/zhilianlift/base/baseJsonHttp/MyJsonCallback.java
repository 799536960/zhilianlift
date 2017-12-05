package com.duma.ld.zhilianlift.base.baseJsonHttp;

import com.duma.ld.baselibrary.util.Log;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.PublicConfig;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;

/**
 * Created by liudong on 2017/6/5.
 */

public abstract class MyJsonCallback<T> extends JsonCallback<T> {

    private PublicConfig config;

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
        Logger.json(new Gson().toJson(response.body()));
        if (config != null) {
            config.setOneSuccess(true);
        }
        loadingHide();
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
        if (config == null) {
            return;
        }
        config.showErrorView();
    }

    private void loadingHide() {
        Log.e("loadingHide");
        if (config == null) {
            return;
        }
        config.hideLoadingView();
        DialogUtil.getInstance().hide();
    }

    private void loadingShow() {
        if (config == null) {
            return;
        }
        config.showLoadingView();
    }

    protected abstract void onJsonSuccess(Response<T> respons, T t);
}
