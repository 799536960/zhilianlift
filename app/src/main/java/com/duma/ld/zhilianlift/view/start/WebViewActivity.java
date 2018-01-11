package com.duma.ld.zhilianlift.view.start;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.StringUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.Constants;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;

import butterknife.BindView;

/**
 * \(^o^)/~
 * Created by liudong on 2018/1/11.
 */

public class WebViewActivity extends BaseMyActivity {
    @BindView(R.id.layout_frame)
    FrameLayout layoutFrame;
    private AgentWeb webView;
    private String url, mTitle;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_webview, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        url = getIntent().getStringExtra(Constants.Url);
        mTitle = getIntent().getStringExtra(Constants.Name);
        //只加载一次
        if (StringUtils.isEmpty(mTitle)) {
            mActivityConfig.setTopBar_A("加载中");
        } else {
            mActivityConfig.setTopBar_A(mTitle);
        }
        webView = AgentWeb.with(mActivity)
                .setAgentWebParent(layoutFrame, new FrameLayout.LayoutParams(-1, -1))//传入AgentWeb的父控件。
                .useDefaultIndicator()// 使用默认进度条
                .setIndicatorColorWithHeight(ZhuanHuanUtil.getColor(R.color.hong), 2)//设置进度条颜色与高度，-1为默认值，高度为2，单位为dp。
                .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        if (StringUtils.isEmpty(mTitle)) {
                            mActivityConfig.setTitle(title);
                        }
                    }
                })//标题回调。
                .createAgentWeb()//创建AgentWeb。
                .ready()//设置 WebSettings。
                .go(url);//WebView载入该url地址的页面并显示。
    }

    @Override
    protected void onBack() {
        if (!webView.back()) {// true表示AgentWeb处理了该事件
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        webView.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

}
