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

public abstract class PublicConfig {
    //传过来的根content
    private Activity mActivity;
    //load error 的view
    private LinearLayout mLayoutLoading, mLayoutError;
    private TextView mTvLoadingTitle;

    //是否已经有数据了
    private boolean isOneSuccess = false;
    /**
     * 配置的监听
     */
    private OnViewConfigListener onViewConfigListener;
    //是否加载loadingOrError布局
    private boolean isOpen;

    /**
     * 刷新的配置
     */
    //是否加入下拉swloading
    private boolean isRefresh = false;
    private SwipeRefreshLayout sw_loading;
    //下拉刷新包含的view
    private View contentView;
    //本体布局视图
    private View mViewContent;
    private View loadingView, errorView;

    public void setmViewContent(View mViewContent) {
        this.mViewContent = mViewContent;
    }

    public PublicConfig(OnViewConfigListener onViewConfigListener, Activity activity, boolean isOpen) {
        this.onViewConfigListener = onViewConfigListener;
        this.mActivity = activity;
        this.isOpen = isOpen;
    }

    public void initLoadOrErrorView() {
        mLayoutLoading = loadingView.findViewById(R.id.layout_loading);
        mTvLoadingTitle = loadingView.findViewById(R.id.tv_loadingTitle);
        mLayoutError = errorView.findViewById(R.id.layout_error);
        TextView mTvErrorBtn = errorView.findViewById(R.id.tv_refresh);

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
        if (contentView != null) {
            contentView.setVisibility(View.GONE);
        }
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
        /**
         * 有下拉刷新sw 同时 页面有数据了不是空白页面了
         * 就启用sw自己的loading
         */
        if (isRefresh && isOneSuccess) {
            if (!sw_loading.isRefreshing()) {
                sw_loading.setRefreshing(true);
            }
        } else {
            if (contentView != null) {
                contentView.setVisibility(View.GONE);
            }
            mLayoutLoading.setVisibility(View.VISIBLE);
            if (!title.isEmpty()) {
                mTvLoadingTitle.setText(title);
            }
        }
        mLayoutError.setVisibility(View.GONE);
    }


    /**
     * 是否加入下拉swloading 加入后就会替代原来的loading
     * rootId 必须是linearlayout
     *
     * @param id        下拉刷新view资源id
     * @param rootId    根布局的id 会自动在这个布局后面添加loadingview 和 errorview
     * @param contentId 是内容的view 如果是第一次还没加载数据的话 这个view会隐藏 然后显示错误页面 有数据后就会显示
     *                  只负责显示隐藏
     */
    protected void setRefresh(int id, int rootId, int contentId) {
        isRefresh = true;
        sw_loading = mViewContent.findViewById(id);
        sw_loading.setColorSchemeColors(ContextCompat.getColor(mActivity, R.color.accent));
        sw_loading.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onViewConfigListener.onClickLoadingRefresh();
            }
        });
        setLoadingOrErrorViewByLinearlayout(rootId, contentId);
    }

    /**
     * 在bootid 这个linearlayout的尾部添加 loading error
     * rootId 必须是linearlayout
     *
     * @param rootId    根布局的id 会自动在这个布局后面添加loadingview 和 errorview
     * @param contentId 是内容的view 如果是第一次还没加载数据的话 这个view会隐藏 然后显示错误页面 有数据后就会显示
     *                  只负责显示隐藏
     */
    protected void setLoadingOrErrorViewByLinearlayout(int rootId, int contentId) {
        contentView = mViewContent.findViewById(contentId);
        LinearLayout linearLayout = mViewContent.findViewById(rootId);
        loadingView = LayoutInflater.from(mActivity).inflate(R.layout.include_loading, linearLayout);
        errorView = LayoutInflater.from(mActivity).inflate(R.layout.include_error, linearLayout);
    }


    /**
     * 是否已经有数据了
     *
     * @param oneSuccess true 有数据
     */
    public void setOneSuccess(boolean oneSuccess) {
        isOneSuccess = oneSuccess;
    }

    /**
     * 结束
     */
    public void end() {
        if (!isOpen) {
            return;
        }
        if (loadingView == null) {
            loadingView = LayoutInflater.from(mActivity).inflate(R.layout.include_loading, getBootLoadingViewGroup());
        }
        if (errorView == null) {
            errorView = LayoutInflater.from(mActivity).inflate(R.layout.include_error, getBootErrorViewGroup());
        }
        initLoadOrErrorView();
    }

    protected abstract ViewGroup getBootErrorViewGroup();

    protected abstract ViewGroup getBootLoadingViewGroup();

}
