package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.duma.ld.baselibrary.base.OnTopBarRightListener;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.MyViewPagerAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HouseHttpModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * 添加出租房或者二手房
 * 有值就是二手房
 * Created by liudong on 2018/1/9.
 */

public class AddHouseActivity extends BaseMyActivity implements OnTopBarRightListener {
    @BindView(R.id.layout_tablayout)
    TabLayout layoutTablayout;
    @BindView(R.id.viewPager_content)
    ViewPager viewPagerContent;
    //是不是二手房
    private boolean isSecondHouse;
    private HouseHttpModel model;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_house, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        isSecondHouse = false;
        if (getIntent().getStringExtra(Constants.key) != null) {
            isSecondHouse = true;
        }
        model = new HouseHttpModel();
        if (isSecondHouse) {
            mActivityConfig.setTopBar_A("我要出售", "提交", this);
            model.setRental(false);
        } else {
            mActivityConfig.setTopBar_A("我要出租", "提交", this);
            model.setRental(true);
        }
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(HouseInfoFragment.newInstance(model), "房屋信息");
        viewPagerAdapter.addFragment(HouseImageFragment.newInstance(model), "房屋图片");
        viewPagerAdapter.addFragment(HouseOtherFragment.newInstance(model), "配套补充");
        viewPagerContent.setAdapter(viewPagerAdapter);
        viewPagerContent.setOffscreenPageLimit(3);
        layoutTablayout.setupWithViewPager(viewPagerContent);
    }

    @Override
    public void onClick() {
        Logger.e(model.toString());
    }
}
