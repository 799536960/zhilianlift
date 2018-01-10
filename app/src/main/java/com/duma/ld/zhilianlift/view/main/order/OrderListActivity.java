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

import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiFaHuo;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiFuKuan;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiPinJia;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiShouHuo;

/**
 * 订单列表
 * 待付款->取消订单 去支付
 * 待发货->  --     查看详情
 * 待收货->查看物流 确认收货
 * 待评价->再次购买 评价
 * 已取消->删除订单 再次购买
 * 已完成->删除订单 再次购买
 * Created by liudong on 2018/1/8.
 */

public class OrderListActivity extends BaseMyActivity {
    @BindView(R.id.layout_tablayout)
    TabLayout layoutTablayout;
    @BindView(R.id.viewPager_content)
    ViewPager viewPagerContent;

    private boolean isRefresh = true;

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_order_list, false).setTopBar_A("我的订单");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(OrderListFragment.newInstance(""), "全部");
        viewPagerAdapter.addFragment(OrderListFragment.newInstance(Order_Type_DaiFuKuan), "待付款");
        viewPagerAdapter.addFragment(OrderListFragment.newInstance(Order_Type_DaiFaHuo), "待发货");
        viewPagerAdapter.addFragment(OrderListFragment.newInstance(Order_Type_DaiShouHuo), "待收货");
        viewPagerAdapter.addFragment(OrderListFragment.newInstance(Order_Type_DaiPinJia), "待评价");
        viewPagerContent.setAdapter(viewPagerAdapter);
        viewPagerContent.setOffscreenPageLimit(3);
        layoutTablayout.setupWithViewPager(viewPagerContent);
        viewPagerContent.setCurrentItem(getIntent().getIntExtra(Constants.key, 0));
        viewPagerContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                isRefresh = true;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
