package com.duma.ld.baselibrary.util;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by liudong on 2017/11/10.
 */

public class ActivityConfig {
    //最后返回的view
    private View mView;
    private Activity mActivity;

    //资源id
    @LayoutRes
    private int layoutResID;

    //activity的类型
    @ConfigConstants.ActivityType
    private int mActivityType;

    private ActivityConfig(Activity activity, int activityType) {
        this.mActivity = activity;
        this.mActivityType = activityType;
    }

    public static ActivityConfig init(Activity activity, int activityType) {
        return new ActivityConfig(activity, activityType);
    }

    public ActivityConfig end() {
        switch (mActivityType) {
            case ConfigConstants.ActivityType_null:
                break;
            case ConfigConstants.ActivityType_topBar:
                break;
            default:
                break;
        }
        Log.e("ld", "mActivity: " + mActivity + " layoutResID:" + layoutResID);
        mView = LayoutInflater.from(mActivity).inflate(layoutResID, null);
        return this;
    }

    /**
     * set and get
     */
    public ActivityConfig setLayoutResID(@LayoutRes int layoutResID) {
        this.layoutResID = layoutResID;
        return this;
    }

    public View getmView() {
        return mView;
    }
}
