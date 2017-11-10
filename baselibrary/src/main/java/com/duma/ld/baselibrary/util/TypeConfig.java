package com.duma.ld.baselibrary.util;

import android.app.Activity;

/**
 * Created by liudong on 2017/11/10.
 */

public class TypeConfig {
    private Activity mActivity;
    //activity的类型
    @ConfigConstants.ActivityType
    private int mActivityType;

    public TypeConfig(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public ActivityConfig setmActivityType(@ConfigConstants.ActivityType int activityType) {
        this.mActivityType = activityType;
        return ActivityConfig.init(mActivity, activityType);
    }
}
