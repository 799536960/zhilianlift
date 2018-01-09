package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.IntentUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的出租
 * Created by liudong on 2018/1/9.
 */

public class MyRentalHouseActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    FrameLayout layoutRoot;
    @BindView(R.id.tv_sell)
    TextView tvSell;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_my_rental_house).setTopBar_A("我的出租").setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }


    @OnClick(R.id.tv_sell)
    public void onViewClicked() {
        IntentUtil.goAddRentalHouse(mActivity);
    }
}
