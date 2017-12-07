package com.duma.ld.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duma.ld.baselibrary.R;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.baselibrary.util.config.OnViewConfigListener;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 目前的功能
 * 配置一些公共ui
 * 封装了eventbus
 *
 * @author liudong
 * @date 2017/11/10
 */

public abstract class BaseFragment extends SupportFragment implements OnViewConfigListener {
    protected BaseActivity mActivity;
    protected FragmentConfig mFragmentConfig;
    public static final int httpTag = 200;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.activity_root, container, false);
        this.mFragmentConfig = setFragmentConfig(savedInstanceState, new InitConfig(mActivity, mRootView, this));
        mFragmentConfig.end();
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        return mRootView;
    }


    /**
     * 设置fragment配置
     *
     * @param savedInstanceState 总会有用的
     * @param initConfig         初始化配置 要初始化fragment的配置
     * @return 配置
     */
    protected abstract FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig);


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
        OkGo.getInstance().cancelTag(httpTag);
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

}
