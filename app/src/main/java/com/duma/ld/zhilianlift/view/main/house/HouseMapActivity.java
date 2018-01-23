package com.duma.ld.zhilianlift.view.main.house;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
import com.blankj.utilcode.util.AppUtils;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMapActivity;
import com.duma.ld.zhilianlift.model.HouseMapModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.PublicUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 地图
 * Created by liudong on 2018/1/23.
 */

public class HouseMapActivity extends BaseMapActivity {
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.tv_biaoti)
    TextView tvBiaoti;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.layout_daoHang)
    FrameLayout layoutDaoHang;
    private HouseMapModel model;

    @Override
    protected MapView getMapView() {
        return mapView;
    }

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_house_map);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        model = (HouseMapModel) getIntent().getSerializableExtra(Constants.Model);
        mActivityConfig.setTopBar_A(model.getLouPanMinCheng() + "");
        tvBiaoti.setText(model.getLouPanMinCheng() + "");
        tvAddress.setText(model.getAddress() + "");
        PublicUtil.setGoMarker(mBaiduMap, model.getLatitude(), model.getLongitude(), 16);
    }

    @OnClick(R.id.layout_daoHang)
    public void onViewClicked() {
        String appName = "com.duma.ld.zhilianlift";
        String uri;
        if (AppUtils.isInstallApp("com.baidu.BaiduMap")) {
            uri = "baidumap://map/navi?location=" + model.getLatitude() + "," + model.getLongitude();
            startDaoHang(uri);
        } else if (AppUtils.isInstallApp("com.autonavi.minimap")) {
            uri = "androidamap://navi?sourceApplication=" + appName + "&lat=" + model.getLatitude() + "&lon=" + model.getLongitude() + "&dev=0&style=0";
            startDaoHang(uri);
        } else if (AppUtils.isInstallApp("com.google.android.apps.maps")) {
            uri = "google.navigation:q=" + model.getLatitude() + "," + model.getLongitude();
            startDaoHang(uri);
        } else {
            TsUtils.show("在本机上没找到可用的导航软件!");
        }
    }

    private void startDaoHang(String uri) {
        Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(uri));
        startActivity(naviIntent);
    }
}
