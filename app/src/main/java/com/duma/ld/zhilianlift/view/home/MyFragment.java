package com.duma.ld.zhilianlift.view.home;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.BaseMyFragment;

/**
 * Created by liudong on 2017/11/29.
 */

public class MyFragment extends BaseMyFragment {
    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_my);
    }
}
