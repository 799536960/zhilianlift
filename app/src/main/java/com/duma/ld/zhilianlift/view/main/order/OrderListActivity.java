package com.duma.ld.zhilianlift.view.main.order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.MyViewPagerAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.Constants;

import butterknife.BindView;

/**
 * 订单列表
 * Created by liudong on 2018/1/8.
 */

public class OrderListActivity extends BaseMyActivity {
    @BindView(R.id.layout_tablayout)
    TabLayout layoutTablayout;
    @BindView(R.id.viewPager_content)
    ViewPager viewPagerContent;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_order_list, false).setTopBar_A("我的订单");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(OrderListFragment.newInstance(""), "全部");
        viewPagerAdapter.addFragment(OrderListFragment.newInstance("WAITPAY"), "待支付");
        viewPagerAdapter.addFragment(OrderListFragment.newInstance("WAITSEND"), "待发货");
        viewPagerAdapter.addFragment(OrderListFragment.newInstance("WAITRECEIVE"), "待收货");
        viewPagerAdapter.addFragment(OrderListFragment.newInstance("WAITCCOMMENT"), "待评价");
        viewPagerContent.setAdapter(viewPagerAdapter);
        layoutTablayout.setupWithViewPager(viewPagerContent);
        viewPagerContent.setCurrentItem(getIntent().getIntExtra(Constants.key, 0));
    }
}
