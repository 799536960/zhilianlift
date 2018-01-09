package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.Constants;

/**
 * 添加出租房或者二手房
 * 有值就是二手房
 * Created by liudong on 2018/1/9.
 */

public class AddHouseActivity extends BaseMyActivity {
    //是不是二手房
    private boolean isSecondHouse;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_house, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        isSecondHouse = false;
        if (getIntent().getStringExtra(Constants.key) != null) {
            isSecondHouse = true;
        }
        if (isSecondHouse) {
            mActivityConfig.setTopBar_A("我要出售");
        } else {
            mActivityConfig.setTopBar_A("我要出租");
        }
    }
}
