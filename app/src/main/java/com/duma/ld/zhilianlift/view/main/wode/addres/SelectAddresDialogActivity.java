package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.MyViewPagerStateAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.PCDAddresModel;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.event_addres_refresh;

/**
 * 选择地址dialog
 * 由添加和编辑地址页面初始化
 * Created by liudong on 2017/12/12.
 */

public class SelectAddresDialogActivity extends BaseMyActivity {
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private PCDAddresModel pcdAddresModel;//省市区model
    private SelectAddresFragment provinceFragment, cityFragment, districtFragment;
    private MyViewPagerStateAdapter viewPagerAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.dialog_select_addres, false);
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
                finish();
            } else {
                refresh();
            }
        }
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        viewPagerAdapter = new MyViewPagerStateAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        provinceFragment = SelectAddresFragment.newInstance(pcdAddresModel);
        cityFragment = SelectAddresFragment.newInstance(pcdAddresModel);
        districtFragment = SelectAddresFragment.newInstance(pcdAddresModel);

        refresh();
    }

    private void refresh() {
        if (pcdAddresModel == null) {
            pcdAddresModel = new PCDAddresModel();
        }
        if (pcdAddresModel.getProvinceModel() == null) {
            //还没有选择省 表示还没选择
            provinceFragment.setModel(pcdAddresModel);
            viewPagerAdapter.addFragment(provinceFragment, "请选择");
            viewPagerAdapter.notifyDataSetChanged();
        } else if (pcdAddresModel.getProvinceModel() != null && pcdAddresModel.getCityModel() == null) {
            //还没有选择市 已经选择了省
            viewPagerAdapter.setTitle(0, pcdAddresModel.getProvinceModel().getName());
            cityFragment.setModel(pcdAddresModel);
            if (viewPagerAdapter.mFragments.size() < 2) {
                viewPagerAdapter.addFragment(cityFragment, "请选择");
            } else {
                if (viewPagerAdapter.mFragments.size() == 3) {
                    viewPagerAdapter.mFragments.remove(2);
                }
                cityFragment.onClickLoadingRefresh();
            }
            viewPagerAdapter.notifyDataSetChanged();
            viewPager.setCurrentItem(2);
        } else if (pcdAddresModel.getProvinceModel() != null && pcdAddresModel.getCityModel() != null && pcdAddresModel.getDistrictModel() == null) {
            //还没有选择区 已经选择了省 市
            viewPagerAdapter.setTitle(1, pcdAddresModel.getCityModel().getName());
            districtFragment.setModel(pcdAddresModel);
            if (viewPagerAdapter.mFragments.size() < 3) {
                viewPagerAdapter.addFragment(districtFragment, "请选择");
            } else {
                cityFragment.onClickLoadingRefresh();
            }
            viewPagerAdapter.notifyDataSetChanged();
            viewPager.setCurrentItem(3);
        }


//        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
//        if (pcdAddresModel.getProvinceModel() == null) {
//            //还没有选择省 表示还没选择
//            provinceFragment = SelectAddresFragment.newInstance(pcdAddresModel);
//            viewPagerAdapter.addFragment(provinceFragment, "请选择");
//            viewPager.setOffscreenPageLimit(3);
//            viewPager.setAdapter(viewPagerAdapter);
//            tabLayout.setupWithViewPager(viewPager);
//            return;
//        }
//        if (pcdAddresModel.getProvinceModel() != null && pcdAddresModel.getCityModel() == null) {
//            //还没有选择市 已经选择了省
//            viewPagerAdapter.addFragment(provinceFragment, pcdAddresModel.getProvinceModel().getName());
//            cityFragment = SelectAddresFragment.newInstance(pcdAddresModel);
//            viewPagerAdapter.addFragment(cityFragment, "请选择");
//            viewPager.setOffscreenPageLimit(3);
//            viewPager.setAdapter(viewPagerAdapter);
//            tabLayout.setupWithViewPager(viewPager);
//            viewPager.setCurrentItem(2);
//            return;
//        }
//        if (pcdAddresModel.getProvinceModel() != null && pcdAddresModel.getCityModel() != null && pcdAddresModel.getDistrictModel() == null) {
//            //还没有选择区 已经选择了省 市
//            viewPagerAdapter.addFragment(provinceFragment, pcdAddresModel.getProvinceModel().getName());
//            viewPagerAdapter.addFragment(cityFragment, pcdAddresModel.getCityModel().getName());
//            districtFragment = SelectAddresFragment.newInstance(pcdAddresModel);
//            viewPagerAdapter.addFragment(districtFragment, "请选择");
//            viewPager.setOffscreenPageLimit(3);
//            viewPager.setAdapter(viewPagerAdapter);
//            tabLayout.setupWithViewPager(viewPager);
//            viewPager.setCurrentItem(3);
//            return;
//        }


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.v_back_enter, R.anim.v_back_exit);
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

}
