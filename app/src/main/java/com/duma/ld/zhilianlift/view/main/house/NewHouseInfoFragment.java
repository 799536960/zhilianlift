package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HouseChuZuInfoModel;
import com.duma.ld.zhilianlift.model.HouseHuXinBean;
import com.duma.ld.zhilianlift.model.HouseLabelBean;
import com.duma.ld.zhilianlift.model.HouseMapModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.widget.MapFrameLayout;
import com.duma.ld.zhilianlift.widget.TvNoNullText;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新房 详情页
 * Created by liudong on 2018/1/22.
 */

public class NewHouseInfoFragment extends BaseMyFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_houseName)
    TvNoNullText tvHouseName;
    @BindView(R.id.tv_jianJie)
    TvNoNullText tvJianJie;
    @BindView(R.id.rv_biaoQian)
    RecyclerView rvBiaoQian;
    @BindView(R.id.tv_jiaGe)
    TvNoNullText tvJiaGe;
    @BindView(R.id.tv_louPanDiZhi)
    TvNoNullText tvLouPanDiZhi;
    @BindView(R.id.tv_zuiXinKaiPan)
    TvNoNullText tvZuiXinKaiPan;
    @BindView(R.id.tv_zaiShouHuXin)
    TvNoNullText tvZaiShouHuXin;
    @BindView(R.id.rv_huXin)
    RecyclerView rvHuXin;
    @BindView(R.id.rv_louPanZhaoPian)
    RecyclerView rvLouPanZhaoPian;
    @BindView(R.id.tv_louPanXinXi)
    TvNoNullText tvLouPanXinXi;
    @BindView(R.id.tv_louPanMinCheng)
    TvNoNullText tvLouPanMinCheng;
    @BindView(R.id.tv_zhanDiMianJi)
    TvNoNullText tvZhanDiMianJi;
    @BindView(R.id.tv_tingCheWei)
    TvNoNullText tvTingCheWei;
    @BindView(R.id.tv_jianZhuLeiBie)
    TvNoNullText tvJianZhuLeiBie;
    @BindView(R.id.tv_wuYeleiXin)
    TvNoNullText tvWuYeleiXin;
    @BindView(R.id.tv_rongJiLv)
    TvNoNullText tvRongJiLv;
    @BindView(R.id.tv_lvHuaLv)
    TvNoNullText tvLvHuaLv;
    @BindView(R.id.tv_chanQuanNianXian)
    TvNoNullText tvChanQuanNianXian;
    @BindView(R.id.tv_junGongShiJian)
    TvNoNullText tvJunGongShiJian;
    @BindView(R.id.tv_shouLouXuKe)
    TvNoNullText tvShouLouXuKe;
    @BindView(R.id.tv_kaiFaShang)
    TvNoNullText tvKaiFaShang;
    @BindView(R.id.tv_shouLouDiZhi)
    TvNoNullText tvShouLouDiZhi;
    @BindView(R.id.tv_huXin)
    TvNoNullText tvHuXin;
    @BindView(R.id.tv_louPanZhaoPian)
    TvNoNullText tvLouPanZhaoPian;
    @BindView(R.id.tv_zhouBian)
    TvNoNullText tvZhouBian;
    @BindView(R.id.layout_map)
    LinearLayout layoutMap;
    @BindView(R.id.tv_info_huXin)
    TextView tvInfoHuXin;
    @BindView(R.id.tv_info_zhaoPian)
    TextView tvInfoZhaoPian;
    @BindView(R.id.tv_info_zhouBian)
    TextView tvInfoZhouBian;
    @BindView(R.id.mapView)
    TextureMapView mapView;
    @BindView(R.id.layout_mapView)
    MapFrameLayout layoutMapView;
    private HouseChuZuInfoModel model;
    private HouseChuZuInfoModel.HouseBean house;

    public static NewHouseInfoFragment newInstance(HouseChuZuInfoModel model) {
        NewHouseInfoFragment fragment = new NewHouseInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Model, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_new_house_info, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            model = (HouseChuZuInfoModel) args.getSerializable(Constants.Model);
        } else {
            TsUtils.show("获取失败!");
            mActivity.finish();
        }
        //标题加粗
        TextPaint paint = tvHouseName.getPaint();
        paint.setFakeBoldText(true);
        paint = tvLouPanXinXi.getPaint();
        paint.setFakeBoldText(true);
        paint = tvHuXin.getPaint();
        paint.setFakeBoldText(true);
        paint = tvLouPanZhaoPian.getPaint();
        paint.setFakeBoldText(true);
        paint = tvZhouBian.getPaint();
        paint.setFakeBoldText(true);
        //banner
        final List<String> list = new ArrayList<>();
        if (model.getHouseImagesList_() != null) {
            for (int i = 0; i < model.getHouseImagesList_().size(); i++) {
                list.add(model.getHouseImagesList_().get(i).getImage_url());
            }
        }
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                IntentUtil.goPhoto(mActivity, list, position);
            }
        });
        banner = PublicUtil.initBanner(banner)
                .setImages(list)
                .start();
        //标签
        BaseAdapter<HouseLabelBean> adapter = new BaseAdapter.Builder<HouseLabelBean>(rvBiaoQian, mActivity, R.layout.adapter_my_house_item)
                .setNoEnpty()
                .isNested()
                .setLayoutManager(new FlexboxLayoutManager(mActivity))
                .build(new OnBaseAdapterListener<HouseLabelBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, HouseLabelBean item) {
                        helper.setText(R.id.tv_name, item.getSo_name());
                    }
                });
        adapter.setNewData(model.getHouseLabel());
        rvBiaoQian.setAdapter(adapter);
        house = model.getHouse();
        /**
         * 配置地图
         */
        LatLng point = new LatLng(house.getLatitude(), house.getLongitude());
        BaiduMap mBaiduMap = mapView.getMap();
        UiSettings mUiSettings = mBaiduMap.getUiSettings();
        //关闭指南针
        mUiSettings.setCompassEnabled(false);
        //关闭比例尺
        mapView.showScaleControl(false);
        //关闭缩放按钮
        mapView.showZoomControls(false);
        PublicUtil.setGoMarker(mBaiduMap, house.getLatitude(), house.getLongitude(), 19);
        //房屋数据
        tvZaiShouHuXin.setNewText(house.getSales_door());
        tvLouPanDiZhi.setNewText(house.getTotal_house_address());
        tvJiaGe.setNewText(house.getOn_chang_text());
        tvZuiXinKaiPan.setNewText(ZhuanHuanUtil.Time2nian2(house.getStart_time() * 1000));
        //楼盘数据
        tvHouseName.setNewText(house.getHouse_name());
        tvJianJie.setNewText(house.getSynopsis());
        tvLouPanMinCheng.setNewText(house.getPremises_name());
        tvJianZhuLeiBie.setNewText(house.getArchitecture_type());
        tvChanQuanNianXian.setNewText(house.getProperty(), "年");
        tvTingCheWei.setNewText(house.getParkinglot());
        tvRongJiLv.setNewText(house.getVolume());
        tvLvHuaLv.setNewText(house.getGreen());
        tvKaiFaShang.setNewText(house.getDevelopers());
        tvWuYeleiXin.setNewText(house.getPurpose_name());
        if (house.getCompleted_time() == 0) {
            tvJunGongShiJian.setNewText("");
        } else {
            tvJunGongShiJian.setNewText(ZhuanHuanUtil.Time2nian2(house.getCompleted_time()));
        }
        tvZhanDiMianJi.setNewText(house.getIsset());
        tvShouLouXuKe.setNewText(house.getSales());
        tvShouLouDiZhi.setNewText(house.getSales_address());
        //户型
        BaseAdapter<HouseHuXinBean> huXinAdapter = new BaseAdapter.Builder<HouseHuXinBean>(rvHuXin, mActivity, R.layout.adapter_house_info_huxin)
                .setNoEnpty()
                .isNested()
                .setLayoutManager(new LinearLayoutManager(mActivity, LinearLayout.HORIZONTAL, false))
                .build(new OnBaseAdapterListener<HouseHuXinBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, HouseHuXinBean item) {
                        helper.setText(R.id.tv_biaoti, "" + item.getScale())
                                .setText(R.id.tv_name, "建筑面积" + item.getArchitecture_area() + "㎡");
                        ImageLoader.with(item.getDoor_img(), (ImageView) helper.getView(R.id.img_icon));
                    }
                });
        huXinAdapter.setNewData(model.getHouse_doorList());
        huXinAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtil.goHuXinInfo(mActivity, model.getHouse().getHouse_id());
            }
        });
        tvHuXin.setText("户型(" + model.getHouse_doorList().size() + ")");
        //楼盘照片
        final BaseAdapter<HouseChuZuInfoModel.HouseImagesListBean> zhaoPianAdapter = new BaseAdapter.Builder<HouseChuZuInfoModel.HouseImagesListBean>(rvLouPanZhaoPian, mActivity, R.layout.adapter_house_info_zhaopian)
                .setNoEnpty()
                .isNested()
                .setLayoutManager(new LinearLayoutManager(mActivity, LinearLayout.HORIZONTAL, false))
                .build(new OnBaseAdapterListener<HouseChuZuInfoModel.HouseImagesListBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, HouseChuZuInfoModel.HouseImagesListBean item) {
                        helper.setText(R.id.tv_biaoti, item.getSo_name() + " (" + item.getHouseImages().size() + ")");
                        ImageLoader.with(item.getHouseImages().get(0).getImage_url(), (ImageView) helper.getView(R.id.img_icon));
                    }
                });

        List<HouseChuZuInfoModel.HouseImagesListBean> houseImagesList = model.getHouseImagesList();
        if (houseImagesList != null) {
            Iterator<HouseChuZuInfoModel.HouseImagesListBean> iterator = houseImagesList.iterator();
            while (iterator.hasNext()) {
                HouseChuZuInfoModel.HouseImagesListBean bean = iterator.next();
                if (bean.getHouseImages() == null || bean.getHouseImages().size() == 0) {
                    iterator.remove();
                }
            }
            zhaoPianAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    List<HouseChuZuInfoModel.HouseImagesListBean.HouseImagesBean> houseImages = zhaoPianAdapter.getData().get(position).getHouseImages();
                    List<String> imgList = new ArrayList<>();
                    for (int i = 0; i < houseImages.size(); i++) {
                        imgList.add(houseImages.get(i).getImage_url());
                    }
                    IntentUtil.goPhoto(mActivity, imgList, 0);
                }
            });
            zhaoPianAdapter.setNewData(houseImagesList);
        }
    }


    public void onResume() {
        super.onResume();
        this.mapView.onResume();
    }


    public void onPause() {
        super.onPause();
        this.mapView.onPause();
    }

    public void onDestroyView() {
        this.mapView.onDestroy();
        super.onDestroyView();
    }

    @OnClick({R.id.layout_map, R.id.tv_info_huXin, R.id.tv_info_zhaoPian, R.id.tv_info_zhouBian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_map:
                IntentUtil.goHouseMap(mActivity, new HouseMapModel(house.getPremises_name(), house.getHouse_address(), house.getLatitude(), house.getLongitude()));
                break;
            case R.id.tv_info_huXin:
                IntentUtil.goHuXinInfo(mActivity, model.getHouse().getHouse_id());
                break;
            case R.id.tv_info_zhaoPian:
                break;
            case R.id.tv_info_zhouBian:
                break;
        }
    }

}
