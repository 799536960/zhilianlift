package com.duma.ld.zhilianlift.view.main.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.MyViewPagerAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.util.ImageLoader;

import butterknife.BindView;

/**
 * 金融
 * Created by liudong on 2017/11/29.
 */

public class FinanceFragment extends BaseMyFragment {
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.layout_tablayout)
    TabLayout layoutTablayout;
    @BindView(R.id.viewPager_content)
    ViewPager viewPagerContent;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_finance, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        ImageLoader.with_noCache("/public/upload/carousel/xinjiaju.png", imgIcon);
        // 0  新居贷  1 按揭
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(FinanceListFragment.newInstance(0), "兴居贷");
        viewPagerAdapter.addFragment(FinanceListFragment.newInstance(1), "按揭贷");
        viewPagerContent.setAdapter(viewPagerAdapter);
        layoutTablayout.setupWithViewPager(viewPagerContent);
    }

}
