package com.duma.ld.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.baselibrary.util.config.OnViewConfigListener;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 目前的功能
 * 配置一些公共ui
 * 封装了eventbus
 *
 * @author liudong
 * @date 2017/11/10
 */

public abstract class BaseActivity extends SupportActivity implements OnViewConfigListener {
    protected BaseActivity mActivity;
    protected ActivityConfig mActivityConfig;
    public static final int httpTag = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        this.mActivityConfig = setActivityConfig(savedInstanceState, new InitConfig(mActivity, this));
        mActivityConfig.end();
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(EventModel eventModel) {
        if (eventModel != null) {
            onReceiveEvent(eventModel);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param eventModel 事件
     */
    protected void onReceiveEvent(EventModel eventModel) {

    }


    protected abstract ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
        OkGo.getInstance().cancelTag(httpTag);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            onBack();
        }
    }

    protected void onBack() {
        finish();
    }

}
