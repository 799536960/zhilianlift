package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;

/**
 * 房屋信息
 * Created by liudong on 2018/1/9.
 */

public class HouseImageFragment extends BaseMyFragment {
    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_house_info);
    }
}
