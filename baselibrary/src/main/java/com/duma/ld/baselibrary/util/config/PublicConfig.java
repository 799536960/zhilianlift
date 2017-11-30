package com.duma.ld.baselibrary.util.config;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.R;

/**
 * Created by liudong on 2017/11/28.
 */

public class PublicConfig {
    //传过来的根content
    private Activity mActivity;
    //load error 的view
    private LinearLayout mLayoutLoading, mLayoutError;
    private View mViewContent;
    private TextView mTvLoadingTitle, mTvErrorBtn;

    /**
     * 配置的监听
     */
    private OnViewConfigListener onViewConfigListener;
    //是否加载布局
    private boolean isOpen;

    public PublicConfig(OnViewConfigListener onViewConfigListener, Activity activity, boolean isOpen) {
        this.onViewConfigListener = onViewConfigListener;
        this.mActivity = activity;
        this.isOpen = isOpen;
    }

    public void initLoadOrErrorView(ViewGroup loadingViewGroup, ViewGroup errorViewGroup) {
        if (!isOpen) {
            return;
        }
        //loading
        View loading = LayoutInflater.from(mActivity).inflate(R.layout.include_loading, loadingViewGroup);
        //error
        View error = LayoutInflater.from(mActivity).inflate(R.layout.include_error, errorViewGroup);

        mLayoutLoading = loading.findViewById(R.id.layout_loading);
        mTvLoadingTitle = loading.findViewById(R.id.tv_loadingTitle);
        mLayoutError = error.findViewById(R.id.layout_error);
        mTvErrorBtn = error.findViewById(R.id.tv_refresh);

        //默认隐藏布局的
        hideErrorView();
        hideLoadingView();

        mTvErrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingView();
                onViewConfigListener.onClickLoadingRefresh();
            }
        });
    }

    public void hideLoadingView() {
        if (!isOpen) {
            return;
        }
        mLayoutLoading.setVisibility(View.GONE);
    }

    public void hideErrorView() {
        if (!isOpen) {
            return;
        }
        mLayoutError.setVisibility(View.GONE);
    }


    public void showLoadingView() {
        showLoadingView("");
    }

    public void showLoadingView(String title) {
        if (!isOpen) {
            return;
        }
        mLayoutLoading.setVisibility(View.VISIBLE);
        mLayoutError.setVisibility(View.GONE);
        if (!title.isEmpty()) {
            mTvLoadingTitle.setText(title);
        }
    }

    public void showErrorView() {
        if (!isOpen) {
            return;
        }
        mLayoutError.setVisibility(View.VISIBLE);
        mLayoutLoading.setVisibility(View.GONE);
    }
}
