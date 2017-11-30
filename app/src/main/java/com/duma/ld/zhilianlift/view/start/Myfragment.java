package com.duma.ld.zhilianlift.view.start;

import android.os.Bundle;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.BaseMyFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author liudong
 * @date 2017/11/28
 */

public class Myfragment extends BaseMyFragment {
    @BindView(R.id.tv_btn)
    TextView tvBtn;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.activity_main);
    }


    @OnClick(R.id.tv_btn)
    public void onViewClicked() {
    }
}
