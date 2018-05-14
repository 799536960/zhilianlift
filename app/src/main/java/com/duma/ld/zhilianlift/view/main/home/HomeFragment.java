package com.duma.ld.zhilianlift.view.main.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.HomeAdapter;
import com.duma.ld.zhilianlift.Adapter.HomeClickTypeListener;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.AdBean;
import com.duma.ld.zhilianlift.model.AdModel;
import com.duma.ld.zhilianlift.model.BaiDuLocationModel;
import com.duma.ld.zhilianlift.model.GoodsAllBean;
import com.duma.ld.zhilianlift.model.HomeModel;
import com.duma.ld.zhilianlift.model.HomeMultipleModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.LocationUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.main.city.SelectCityActivity;
import com.duma.ld.zhilianlift.widget.VerticalSwipeRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.event_location_home;
import static com.duma.ld.zhilianlift.util.HttpUrl.homePage;
import static com.lzy.okgo.cache.CacheMode.FIRST_CACHE_THEN_REQUEST;

/**
 * 首页
 * Created by liudong on 2017/11/29.
 */

public class HomeFragment extends BaseMyFragment {
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.layout_city)
    FrameLayout layoutCity;
    @BindView(R.id.layout_search)
    LinearLayout layoutSearch;
    @BindView(R.id.layout_scan)
    LinearLayout layoutScan;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    VerticalSwipeRefreshLayout swLoading;

    private HomeClickTypeListener listener;
    //轮播图
    private Banner mBanner;
    private List<String> list;
    private HomeAdapter mAdapter;
    private List<HomeMultipleModel> mList;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_home).setRefresh_f(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == Constants.event_location_home) {
            final BaiDuLocationModel model = (BaiDuLocationModel) eventModel.getData();
            String city = model.getResult().getAddressComponent().getCity();
            if (!SpDataUtil.isCity(city)) {
                AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "切换城市", "当前城市和您所在的城市不同,是否切换为 " + city + "?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SpDataUtil.setLocationData(model);
                                onClickLoadingRefresh();
                            }
                        })
                        .setNegativeButton("否", null)
                        .setCancelable(false);
                builder.show();
            } else {
                SpDataUtil.setLocationData(model);
            }
        } else if (eventModel.getCode() == Constants.event_select_city) {
            onClickLoadingRefresh();
        }
    }

    /**
     * 先请求缓存地址数据 没有缓存默认 湖州
     * 获取权限 失败不管 成功后定位对比当前地址 不一样就提示是否切换 切换就把新地址缓存后http 否就取消
     */
    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        //获取权限 和定位
        LocationUtil.getInstance().start(mActivity, event_location_home);
        //初始化home所有的监听
        listener = new HomeClickTypeListener((MainActivity) mActivity);
        //初始adapter
        mList = new ArrayList<>();
        mAdapter = new HomeAdapter(mList, listener);
        rvList.setLayoutManager(new GridLayoutManager(mActivity, 10));
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mList.get(position).getSpanSize();
            }
        });
        View inflate = getLayoutInflater().inflate(R.layout.adapter_head_banner, (ViewGroup) rvList.getParent(), false);
        mBanner = inflate.findViewById(R.id.banner);
        mAdapter.addHeaderView(inflate);
        rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(listener);
        //初始化banner
        list = new ArrayList<>();
        //请求数据
        onClickLoadingRefresh();
    }

    //设置home数据
    private void initData(HomeModel result) {
        listener.setResult(result);
        //轮播图数据
        list.clear();
        for (int i = 0; i < result.getLunbo().size(); i++) {
            list.add(result.getLunbo().get(i).getImg_url());
        }
        mBanner = PublicUtil.initBanner_Main(mBanner)
                .setOnBannerListener(listener)
                .setImages(list)
                .start();
        mList.clear();
        //添加分类
        List<AdBean> goodsCategoryList = result.getGoodsCategoryList();
        for (int i = 0; i < goodsCategoryList.size(); i++) {
            mList.add(HomeMultipleModel.getClassTypeModel(goodsCategoryList.get(i)));
        }
        //添加中间ad
        AdModel adModel = new AdModel(result.getAd1(), result.getAd2(), result.getAd3(), result.getAd4(), result.getAd5());
        mList.add(HomeMultipleModel.getAdTypeModel(adModel));
        /**
         * 添加head和goods
         */
        List<GoodsAllBean> goodsAll = result.getGoodsAll();
        for (int i = 0; i < goodsAll.size(); i++) {
            //添加head
            mList.add(HomeMultipleModel.getHeadTypeModel(goodsAll.get(i)));
            //添加goods
            for (int i1 = 0; i1 < goodsAll.get(i).getGoods().size(); i1++) {
                mList.add(HomeMultipleModel.getGoodsTypeModel(goodsAll.get(i).getGoods().get(i1)));
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickLoadingRefresh() {
        tvCity.setText(SpDataUtil.getCity());
        OkGo.<HttpResModel<HomeModel>>get(homePage)
                .cacheMode(FIRST_CACHE_THEN_REQUEST)
                .tag(httpTag)
                .params("city_name", SpDataUtil.getCity())
                .execute(new MyJsonCallback<HttpResModel<HomeModel>>(mFragmentConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<HomeModel>> respons, HttpResModel<HomeModel> homeModelHttpResModel) {
                        initData(homeModelHttpResModel.getResult());
                    }
                }.openCacheRefresh());
    }

    @OnClick({R.id.layout_city, R.id.layout_search, R.id.layout_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_city:
                startActivity(new Intent(mActivity, SelectCityActivity.class));
                break;
            case R.id.layout_search:
                IntentUtil.goSearch(mActivity);
                break;
            case R.id.layout_scan:
                IntentUtil.goSaoMa(mActivity);
                break;
        }
    }
}
