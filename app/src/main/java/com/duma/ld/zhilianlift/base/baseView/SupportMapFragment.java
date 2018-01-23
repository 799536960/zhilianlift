//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.duma.ld.zhilianlift.base.baseView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.util.Constants;

import me.yokeyword.fragmentation.SupportFragment;

public class SupportMapFragment extends SupportFragment {
    private MapView mMapView;
    private BaiduMapOptions mBaiduMapOptions;
    private double longitude;
    private double latitude;


    public static SupportMapFragment newInstance(BaiduMapOptions model, double longitude, double latitude) {
        SupportMapFragment fragment = new SupportMapFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.Model, model);
        args.putDouble("longitude", longitude);
        args.putDouble("latitude", latitude);
        fragment.setArguments(args);
        return fragment;
    }


    public BaiduMap getBaiduMap() {
        return this.mMapView == null ? null : this.mMapView.getMap();
    }

    public MapView getMapView() {
        return this.mMapView;
    }


    public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
        Bundle args = getArguments();
        this.mBaiduMapOptions = args.getParcelable(Constants.Model);
        longitude = args.getDouble("longitude");
        latitude = args.getDouble("latitude");
        this.mMapView = new MapView(this.getActivity(), this.mBaiduMapOptions);
        return this.mMapView;
    }


    public void onStart() {
        super.onStart();
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.tubiao1);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mMapView.getMap().addOverlay(option);
    }

    public void onResume() {
        super.onResume();
        this.mMapView.onResume();
    }


    public void onPause() {
        super.onPause();
        this.mMapView.onPause();
    }


    public void onDestroyView() {
        super.onDestroyView();
        this.mMapView.onDestroy();
    }


}
