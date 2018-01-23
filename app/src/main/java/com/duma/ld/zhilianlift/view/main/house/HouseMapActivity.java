package com.duma.ld.zhilianlift.view.main.house;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.blankj.utilcode.util.AppUtils;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMapActivity;
import com.duma.ld.zhilianlift.model.HouseMapModel;
import com.duma.ld.zhilianlift.util.Constants;

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
        LatLng point = new LatLng(model.getLatitude(), model.getLongitude());
        MapStatus mapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(16)
                .build();
        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaiduMap.setMapStatus(statusUpdate);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.tubiao1);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
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
        } else
            if (AppUtils.isInstallApp("com.google.android.apps.maps")) {
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
