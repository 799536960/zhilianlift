package com.duma.ld.baselibrary.util.config;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.R;
import com.duma.ld.baselibrary.base.BaseActivity;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.base.OnTopBarRightListener;

/**
 * Created by liudong on 2017/11/10.
 */

public class ActivityConfig extends PublicConfig {
    //绑定的activity
    private BaseActivity mActivity;
    //本体布局视图
    private View mViewContent;


    public ActivityConfig(BaseActivity activity, @LayoutRes int layoutResID, boolean isOpen, OnViewConfigListener onViewConfigListener) {
        super(onViewConfigListener, activity, isOpen);
        this.mActivity = activity;
        //设置根布局
        mActivity.setContentView(R.layout.activity_root);
        //设置本体布局
        mViewContent = LayoutInflater.from(mActivity).inflate(layoutResID, (FrameLayout) mActivity.findViewById(R.id.layout_boot_content));
    }

    //---------------------------------------------------------------------------------------------------------
    public ActivityConfig setTopBar_A(String name) {
        setTopBar(name);
        return this;
    }

    public ActivityConfig setTopBar_A(String name, OnTopBarLeftListener onTopBarLeftListener) {
        setTopBar(name, onTopBarLeftListener);
        return this;
    }

    public ActivityConfig setTopBar_A(String name, OnTopBarLeftListener onTopBarLeftListener, String rightString, OnTopBarRightListener onTopBarRightListener) {
        setTopBar(name, onTopBarLeftListener, rightString, onTopBarRightListener);
        return this;
    }

    /**
     * 是否加入下拉swloading 加入后就会替代原来的loading
     * rootId 必须是ViewGroup
     *
     * @param swId      下拉刷新view资源id
     * @param rootId    根布局的id 会自动在这个布局后面添加loadingview 和 errorview
     * @param contentId 是内容的view 如果是第一次还没加载数据的话 这个view会隐藏 然后显示错误页面 有数据后就会显示
     *                  只负责显示隐藏
     */
    public ActivityConfig setRefresh_A(int swId, int rootId, int contentId) {
        setRefresh(swId, rootId, contentId);
        return this;
    }

    public ActivityConfig setLoadingOrErrorView_A(int rootId, int contentId) {
        setLoadingOrErrorViewByLinearlayout(rootId, contentId);
        return this;
    }

    public ActivityConfig setNoYinyin() {
        setYinyin(false);
        return this;
    }

    //---------------------------------------------------------------------------------------------------------
    @Override
    protected ViewGroup getBootErrorViewGroup() {
        return (FrameLayout) mActivity.findViewById(R.id.layout_boot_error);
    }

    @Override
    protected ViewGroup getBootLoadingViewGroup() {
        return (FrameLayout) mActivity.findViewById(R.id.layout_boot_loading);
    }

    @Override
    protected ViewGroup getBootTopbarViewGroup() {
        return (FrameLayout) mActivity.findViewById(R.id.layout_toot_topbar);
    }

    @Override
    protected View getBootContentViewGroup() {
        return mViewContent;
    }
}
