package com.duma.ld.zhilianlift.view.main.shopping;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.MyViewPagerAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.Constants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 2017/12/22.
 */

public class GoodsDetailsActivity extends BaseMyActivity {
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.layout_share)
    FrameLayout layoutShare;
    @BindView(R.id.layout_menu)
    FrameLayout layoutMenu;
    @BindView(R.id.layout_tablayout)
    TabLayout layoutTablayout;
    @BindView(R.id.viewPager_content)
    ViewPager viewPagerContent;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_goods_details);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        String id = getIntent().getStringExtra(Constants.id);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(GoodsMainFragment.newInstance(id), "商品");
        viewPagerAdapter.addFragment(GoodsInfoFragment.newInstance(id), "详情");
        viewPagerAdapter.addFragment(GoodsCommentFragment.newInstance(id), "评价");
        viewPagerContent.setAdapter(viewPagerAdapter);
        viewPagerContent.setOffscreenPageLimit(3);
        layoutTablayout.setupWithViewPager(viewPagerContent);
    }

    @OnClick({R.id.layout_back, R.id.layout_share, R.id.layout_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_share:
                break;
            case R.id.layout_menu:
                break;
        }
    }
}
