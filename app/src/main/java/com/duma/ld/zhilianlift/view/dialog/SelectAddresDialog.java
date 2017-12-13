package com.duma.ld.zhilianlift.view.dialog;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.MyViewPagerAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.PCDAddresModel;
import com.duma.ld.zhilianlift.view.main.wode.addres.SelectAddresFragment;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.event_addres_refresh;

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

    private PCDAddresModel pcdAddresModel;//省市区model
    private SelectAddresFragment provinceFragment, cityFragment, districtFragment;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.dialog_select_addres, false);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == event_addres_refresh) {
            pcdAddresModel = (PCDAddresModel) eventModel.getData();
            if (pcdAddresModel == null) {
                pop();
            } else {
                refresh();
            }
        }
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        pop();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        refresh();
    }

    private void refresh() {
        if (pcdAddresModel == null) {
            pcdAddresModel = new PCDAddresModel();
        }
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(this.getChildFragmentManager());
        if (pcdAddresModel.getProvinceModel() == null) {
            //还没有选择省 表示还没选择
            provinceFragment = SelectAddresFragment.newInstance(pcdAddresModel);
            viewPagerAdapter.addFragment(provinceFragment, "请选择");
            viewPager.setOffscreenPageLimit(3);
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            return;
        }
        if (pcdAddresModel.getProvinceModel() != null && pcdAddresModel.getCityModel() == null) {
            //还没有选择市 已经选择了省
            viewPagerAdapter.addFragment(provinceFragment, pcdAddresModel.getProvinceModel().getName());
            cityFragment = SelectAddresFragment.newInstance(pcdAddresModel);
            viewPagerAdapter.addFragment(cityFragment, "请选择");
            viewPager.setOffscreenPageLimit(3);
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            viewPager.setCurrentItem(2);
            return;
        }
        if (pcdAddresModel.getProvinceModel() != null && pcdAddresModel.getCityModel() != null && pcdAddresModel.getDistrictModel() == null) {
            //还没有选择区 已经选择了省 市
            viewPagerAdapter.addFragment(provinceFragment, pcdAddresModel.getProvinceModel().getName());
            viewPagerAdapter.addFragment(cityFragment, pcdAddresModel.getCityModel().getName());
            districtFragment = SelectAddresFragment.newInstance(pcdAddresModel);
            viewPagerAdapter.addFragment(districtFragment, "请选择");
            viewPager.setOffscreenPageLimit(3);
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            viewPager.setCurrentItem(3);
            return;
        }


    }
}
