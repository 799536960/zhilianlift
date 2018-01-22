package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;

/**
 * 添加报备
 * Created by liudong on 2018/1/22.
 */

public class AddBaoBeiActivity extends BaseMyActivity {
    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_baobei, false).setTopBar_A("推荐朋友买房");
    }
}
