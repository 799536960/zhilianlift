package com.duma.ld.baselibrary.util;

import android.support.annotation.IntDef;

/**
 * @author liudong
 * @date 2017/11/10
 */

public class ConfigConstants {
    /**
     * activity type
     */
    @IntDef({ActivityType_null, ActivityType_topBar})
    @interface ActivityType {
    }

    public static final int ActivityType_null = 0;
    //只有一个topbar的页面
    public static final int ActivityType_topBar = 1;
}
