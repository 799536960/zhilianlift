package com.duma.ld.zhilianlift;

import android.os.Bundle;

import com.duma.ld.baselibrary.base.BaseActivity;
import com.duma.ld.baselibrary.util.ActivityConfig;
import com.duma.ld.baselibrary.util.ConfigConstants;
import com.duma.ld.baselibrary.util.TypeConfig;

public class MainActivity extends BaseActivity {

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, TypeConfig typeConfig) {
        return typeConfig
                .setmActivityType(ConfigConstants.ActivityType_null)
                .setLayoutResID(R.layout.activity_main)
                .end();
    }
}
