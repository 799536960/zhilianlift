package com.duma.ld.zhilianlift.base.baseView;

import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.UiSettings;

/**
 * 地图的抽象
 * Created by liudong on 2018/1/23.
 */

public abstract class BaseMapActivity extends BaseMyActivity {
    protected BaiduMap mBaiduMap;
    protected UiSettings mUiSettings;

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mBaiduMap = getMapView().getMap();
        mUiSettings = mBaiduMap.getUiSettings();
        //关闭指南针
        mUiSettings.setCompassEnabled(false);
        //关闭比例尺
        getMapView().showScaleControl(false);
        //关闭缩放按钮
        getMapView().showZoomControls(false);

    }

    protected abstract MapView getMapView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        getMapView().onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        getMapView().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        getMapView().onPause();
    }
}
