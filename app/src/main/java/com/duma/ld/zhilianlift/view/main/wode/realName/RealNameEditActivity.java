package com.duma.ld.zhilianlift.view.main.wode.realName;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;

/**
 * Created by liudong on 2017/12/14.
 */

public class RealNameEditActivity extends BaseMyActivity {
    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_real_name_edit, false).setTopBar_A("实名认证");
    }

}
