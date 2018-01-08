package com.duma.ld.baselibrary.util.config;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.R;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.base.OnTopBarRightListener;

/**
 * 负责activity上面一些公共的ui控制
 * 控制显示loading error页面
 * 控制显示常见的topbar功能
 * <p>
 * Created by liudong on 2017/11/28.
 */

public abstract class PublicConfig {
    //topbar 左边图片的默认值
    private final int LiftImg_Default = -1;
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
    private View loadingView, errorView;

    //topbar 阴影效果
    private View yinYinView;


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
     * rootId 必须是ViewGroup
     *
     * @param id        下拉刷新view资源id
     * @param rootId    根布局的id 会自动在这个布局后面添加loadingview 和 errorview
     * @param contentId 是内容的view 如果是第一次还没加载数据的话 这个view会隐藏 然后显示错误页面 有数据后就会显示
     *                  只负责显示隐藏
     */
    protected void setRefresh(int id, int rootId, int contentId) {
        isRefresh = true;
        sw_loading = getBootContentViewGroup().findViewById(id);
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
     * rootId 必须是ViewGroup
     *
     * @param rootId    根布局的id 会自动在这个布局后面添加loadingview 和 errorview
     * @param contentId 是内容的view 如果是第一次还没加载数据的话 这个view会隐藏 然后显示错误页面 有数据后就会显示
     *                  只负责显示隐藏
     */
    protected void setLoadingOrErrorViewByLinearlayout(int rootId, int contentId) {
        contentView = getBootContentViewGroup().findViewById(contentId);
        ViewGroup viewGroup = getBootContentViewGroup().findViewById(rootId);
        loadingView = LayoutInflater.from(mActivity).inflate(R.layout.include_loading, viewGroup);
        errorView = LayoutInflater.from(mActivity).inflate(R.layout.include_error, viewGroup);
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

    protected abstract ViewGroup getBootTopbarViewGroup();

    protected abstract View getBootContentViewGroup();

    /**
     * 设置tobbar
     */
    /**
     * 设置topbar
     * 默认带有阴影效果
     *
     * @param name                  topbar的标题
     * @param rightText             右边的文字
     * @param liftImg               左边的图片
     * @param rightImg              右边的图片
     * @param onTopBarLeftListener  监听
     * @param onTopBarRightListener 监听
     */
    protected void setTopBar(String name, String rightText, @DrawableRes int liftImg, @DrawableRes int rightImg
            , final OnTopBarLeftListener onTopBarLeftListener, final OnTopBarRightListener onTopBarRightListener) {
        FrameLayout viewById = (FrameLayout) getBootTopbarViewGroup();
        if (name == null) {
            viewById.setVisibility(View.GONE);
            return;
        }
        //有topbar
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.activity_topbar, viewById);
        yinYinView = inflate.findViewById(R.id.base_view);
        setYinyin(true);
        if (liftImg == LiftImg_Default) {
            ((ImageView) inflate.findViewById(R.id.img_left)).setImageDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.back));
        } else if (liftImg != 0) {
            ((ImageView) inflate.findViewById(R.id.img_left)).setImageDrawable(ContextCompat.getDrawable(mActivity, liftImg));
        }
        if (rightImg != 0) {
            ((ImageView) inflate.findViewById(R.id.img_right)).setImageDrawable(ContextCompat.getDrawable(mActivity, rightImg));
        }
        if (rightText != null) {
            ((TextView) inflate.findViewById(R.id.tv_right)).setText(rightText);
        }
        ((TextView) inflate.findViewById(R.id.tv_title)).setText(name);
        //设置点击事件
        LinearLayout liftLayout = inflate.findViewById(R.id.layout_left);
        if (liftImg != 0) {
            liftLayout.setVisibility(View.VISIBLE);
            liftLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTopBarLeftListener != null) {
                        onTopBarLeftListener.onClick();
                    } else {
                        mActivity.finish();
                    }
                }
            });
        } else {
            liftLayout.setVisibility(View.GONE);
        }

        //如果没有
        LinearLayout rightLayout = inflate.findViewById(R.id.layout_right);
        if (rightText != null || rightImg != 0) {
            rightLayout.setVisibility(View.VISIBLE);
            rightLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTopBarRightListener != null) {
                        onTopBarRightListener.onClick();
                    }
                }
            });
        } else {
            rightLayout.setVisibility(View.GONE);
        }

    }

    /**
     * 默认的topbar 有返回键的
     *
     * @param name 标题
     * @return
     */
    @NonNull
    protected void setTopBar(String name) {
        setTopBar(name, null, LiftImg_Default, 0, null, null);
    }

    @NonNull
    protected void setTopBar(String name, OnTopBarLeftListener onTopBarLeftListener) {
        setTopBar(name, null, LiftImg_Default, 0, onTopBarLeftListener, null);
    }

    @NonNull
    protected void setTopBar(String name, OnTopBarLeftListener onTopBarLeftListener, String rightString, OnTopBarRightListener onTopBarRightListener) {
        setTopBar(name, rightString, LiftImg_Default, 0, onTopBarLeftListener, onTopBarRightListener);
    }

    protected void setYinyin(boolean isYinyin) {
        if (yinYinView != null) {
            if (isYinyin) {
                yinYinView.setVisibility(View.VISIBLE);
            } else {
                yinYinView.setVisibility(View.GONE);
            }
        }
    }

    public boolean isOneSuccess() {
        return isOneSuccess;
    }
}
