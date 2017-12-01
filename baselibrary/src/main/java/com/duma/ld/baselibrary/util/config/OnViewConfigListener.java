package com.duma.ld.baselibrary.util.config;

/**
 * @author liudong
 * @date 2017/11/28
 */

public interface OnViewConfigListener {
    /**
     * 点击404页面的刷新事件
     * 如果有swloading的话 就是下拉刷新的事件
     */
    void onClickLoadingRefresh();
}
