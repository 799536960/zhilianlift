package com.duma.ld.zhilianlift.view.dialog;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.util.Log;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.MyViewPagerAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.view.main.wode.addres.SelectAddresFragment;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.getprovince;

/**
 * Created by liudong on 2017/12/12.
 */

public class SelectAddresDialog extends BaseMyFragment {
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.dialog_select_addres, false);
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(mActivity.getSupportFragmentManager());
        viewPagerAdapter.addFragment(SelectAddresFragment.newInstance(getprovince), "请选择1");
        viewPagerAdapter.addFragment(SelectAddresFragment.newInstance(getprovince), "请选择2");
        viewPagerAdapter.addFragment(SelectAddresFragment.newInstance(getprovince), "请选择3");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Log.e("SelectAddresDialog");
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(this.getChildFragmentManager());
        viewPagerAdapter.addFragment(SelectAddresFragment.newInstance(getprovince), "请选择");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
