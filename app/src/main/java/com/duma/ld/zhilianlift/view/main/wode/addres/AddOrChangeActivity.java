package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;

/**
 * Created by liudong on 2017/12/12.
 */

public class AddOrChangeActivity extends BaseMyActivity {

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_change, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setDefaultFragmentBackground(R.color.transparent);
        AddOrChangeFragment fragment = findFragment(AddOrChangeFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.layout_fragment, new AddOrChangeFragment());
        }
    }
}
