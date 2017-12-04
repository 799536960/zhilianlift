package com.duma.ld.baselibrary.util.config;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private TextView mTvLoadingTitle, mTvErrorBtn;

    //是否已经有数据了
    private boolean isOneSuccess = false;
    /**
     * 配置的监听
     */
    private OnViewConfigListener onViewConfigListener;
    //是否加载布局
    private boolean isOpen;

    /**
     * 刷新的配置
     */
    private boolean isRefresh = false;
    private SwipeRefreshLayout sw_loading;
    //下拉刷新包含的view
    private View contentView;
    //本体布局视图
    private View mViewContent;

    public void setmViewContent(View mViewContent) {
        this.mViewContent = mViewContent;
    }

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
        mLayoutError.setVisibility(View.GONE);
        if (isRefresh) {
            sw_loading.setRefreshing(false);
        }
        if (contentView != null) {
            contentView.setVisibility(View.VISIBLE);
        }
    }

    public void showErrorView() {
        if (!isOpen) {
            return;
        }
        if (isOneSuccess) {
            //已经请求成功一次了 说明页面有数据了 不是空白页面的 不用显示error了 而且只可能是下拉刷新
            return;
        }
        mLayoutError.setVisibility(View.VISIBLE);
        mLayoutLoading.setVisibility(View.GONE);
        if (isRefresh) {
            sw_loading.setRefreshing(false);
        }
    }


    public void showLoadingView() {
        showLoadingView("");
    }

    public void showLoadingView(String title) {
        if (!isOpen) {
            return;
        }
        if (isRefresh) {
            if (!sw_loading.isRefreshing()) {
                sw_loading.setRefreshing(true);
            }
        } else {
            mLayoutLoading.setVisibility(View.VISIBLE);
            if (!title.isEmpty()) {
                mTvLoadingTitle.setText(title);
            }
        }
        mLayoutError.setVisibility(View.GONE);
        //是否显示下拉刷新包含的view
        if (contentView != null) {
            if (isOneSuccess) {
                contentView.setVisibility(View.VISIBLE);
            } else {
                contentView.setVisibility(View.GONE);
            }
        }

    }


    /**
     * 是否加入下拉swloading 加入后就会替代原来的loading
     *
     * @param id 资源id
     *           contentid 是内容的view 如果是第一次还没加载数据的话 这个view会隐藏 有数据后就会显示
     */
    public void setRefresh(int id, int contentId) {
        isRefresh = true;
        sw_loading = mViewContent.findViewById(id);
        sw_loading.setColorSchemeColors(ContextCompat.getColor(mActivity, R.color.accent));
        sw_loading.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onViewConfigListener.onClickLoadingRefresh();
            }
        });

        contentView = mViewContent.findViewById(contentId);
    }

    public void setOneSuccess(boolean oneSuccess) {
        isOneSuccess = oneSuccess;
    }
}
