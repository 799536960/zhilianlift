package com.duma.ld.zhilianlift.view.home.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.GoodsAdapter;
import com.duma.ld.zhilianlift.Adapter.HomeClickTypeListener;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.AdBean;
import com.duma.ld.zhilianlift.model.GoodsAllBean;
import com.duma.ld.zhilianlift.model.HomeModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.LocalImageHolderView;
import com.duma.ld.zhilianlift.util.LocationUtil;
import com.duma.ld.zhilianlift.util.PermissionUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.home.city.SelectCityActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.homePage;

/**
 * 首页
 * Created by liudong on 2017/11/29.
 */

public class HomeFragment extends BaseMyFragment {
    @BindView(R.id.banner_top)
    ConvenientBanner bannerTop;
    @BindView(R.id.rv_class)
    RecyclerView rvClass;
    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.img_4)
    ImageView img4;
    @BindView(R.id.img_3)
    ImageView img3;
    @BindView(R.id.img_5)
    ImageView img5;
    @BindView(R.id.rv_class_goods)
    RecyclerView rvClassGoods;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.layout_city)
    FrameLayout layoutCity;
    @BindView(R.id.layout_search)
    LinearLayout layoutSearch;
    @BindView(R.id.layout_scan)
    FrameLayout layoutScan;
    @BindView(R.id.layout_content)
    LinearLayout layoutContent;
    private MyJsonCallback<HttpResModel<HomeModel>> callback;
    private BaseAdapter<AdBean> mFenleiAdapter;
    private BaseAdapter<GoodsAllBean> mAdClassAdapter;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_home).setRefreshByFragment(R.id.sw_loading, R.id.layout_content);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == Constants.event_location) {
            final BDLocation bdLocation = (BDLocation) eventModel.getData();
            if (!SpDataUtil.isCity(bdLocation)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                        .setTitle("切换城市")
                        .setMessage("当前城市和您所在的城市不同,是否切换为 " + bdLocation.getCity() + "?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SpDataUtil.setLocation(bdLocation);
                                onClickLoadingRefresh();
                            }
                        })
                        .setNegativeButton("否", null)
                        .setCancelable(false);
                builder.show();
            }
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
        PermissionUtil permissionUtil = new PermissionUtil(mActivity, new PermissionUtil.onPermissionListener() {
            @Override
            public void onResult(int requestCode, boolean result) {
                if (result) {
                    //开启定位
                    LocationUtil.getInstance().start();
                }
            }

        });
        permissionUtil.openLocation();
        //网络请求
        callback = new MyJsonCallback<HttpResModel<HomeModel>>(mFragmentConfig) {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<HomeModel>> respons, HttpResModel<HomeModel> homeModelHttpResModel) {
                initData(homeModelHttpResModel.getResult());
            }
        };
        //初始哈adapter
        initAdapter();
        //请求数据
        onClickLoadingRefresh();
    }

    private void initAdapter() {
        mFenleiAdapter = new BaseAdapter<AdBean>(R.layout.adapter_home_class) {
            @Override
            protected void convert(BaseViewHolder helper, AdBean item) {
                ImageLoader.with(mActivity, item.getImg_url(), (ImageView) helper.getView(R.id.img_class));
                helper.setText(R.id.tv_class, item.getTitle());
            }
        };
        rvClass.setFocusable(false);
        rvClass.setNestedScrollingEnabled(false);
        rvClass.setLayoutManager(new GridLayoutManager(mActivity, 5));
        rvClass.setAdapter(mFenleiAdapter);
        mAdClassAdapter = new BaseAdapter<GoodsAllBean>(R.layout.adapter_home_class_goods) {
            @Override
            protected void convert(BaseViewHolder helper, GoodsAllBean item) {
                ImageLoader.with(mActivity, item.getImg_url(), (ImageView) helper.getView(R.id.img_title));
                RecyclerView rvGoods = helper.getView(R.id.tv_goods);
                rvGoods.setFocusable(false);
                rvGoods.setNestedScrollingEnabled(false);
                rvGoods.setLayoutManager(new GridLayoutManager(mActivity, 2));
                GoodsAdapter goodsAdapter = new GoodsAdapter(mActivity);
                rvGoods.setAdapter(goodsAdapter);
                goodsAdapter.setNewData(item.getGoods());
                goodsAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        TsUtils.show("商品详情");
                    }
                });
            }
        };
        rvClassGoods.setFocusable(false);
        rvClassGoods.setNestedScrollingEnabled(false);
        rvClassGoods.setLayoutManager(new LinearLayoutManager(mActivity));
        rvClassGoods.setAdapter(mAdClassAdapter);
    }

    private void initData(HomeModel result) {
        HomeClickTypeListener listener = new HomeClickTypeListener(result);
        //轮播图数据
        bannerTop.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, result.getLunbo())
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.lunbo_unselected, R.drawable.lunbo_selected})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(listener);
        //分类的数据
        mFenleiAdapter.setNewData(result.getGoodsCategoryList());
        mFenleiAdapter.setOnItemClickListener(listener);
        //中间的五张图片
        ImageLoader.with(mActivity, result.getAd1().getImg_url(), img1);
        ImageLoader.with(mActivity, result.getAd2().getImg_url(), img2);
        ImageLoader.with(mActivity, result.getAd3().getImg_url(), img3);
        ImageLoader.with(mActivity, result.getAd4().getImg_url(), img4);
        ImageLoader.with(mActivity, result.getAd5().getImg_url(), img5);
        img1.setOnClickListener(listener);
        img2.setOnClickListener(listener);
        img3.setOnClickListener(listener);
        img4.setOnClickListener(listener);
        img5.setOnClickListener(listener);
        //商品分类的数据
        mAdClassAdapter.setNewData(result.getGoodsAll());
    }

    @Override
    public void onClickLoadingRefresh() {
        tvCity.setText(SpDataUtil.getCity());
        OkGo.<HttpResModel<HomeModel>>get(homePage)
                .tag(httpTag)
                .params("city_name", SpDataUtil.getCity())
                .execute(callback);
    }

    @OnClick({R.id.layout_city, R.id.layout_search, R.id.layout_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_city:
                startActivity(new Intent(mActivity, SelectCityActivity.class));
                break;
            case R.id.layout_search:
                break;
            case R.id.layout_scan:
                break;
        }
    }
}
