package com.duma.ld.zhilianlift.view.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.Log;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.BaseMyFragment;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.model.HomeModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.LocalImageHolderView;
import com.duma.ld.zhilianlift.util.LocationUtil;
import com.duma.ld.zhilianlift.util.PermissionUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.homePage;

/**
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
    private MyJsonCallback<HttpResModel<HomeModel>> callback;

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
            public void onSuccess(Response<HttpResModel<HomeModel>> response) {
                super.onSuccess(response);
                Log.e("成功:" + response.body().getResult().toString());
                initData(response.body().getResult());
            }
        };
        //请求数据
        onClickLoadingRefresh();
    }

    private void initData(HomeModel result) {
        bannerTop.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, result.getLunbo())
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.home_lunbo_unselected, R.drawable.home_lunbo_selected})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        ImageLoader.with(mActivity, result.getAd1().getImg_url(), img1);
        ImageLoader.with(mActivity, result.getAd2().getImg_url(), img2);
        ImageLoader.with(mActivity, result.getAd3().getImg_url(), img3);
        ImageLoader.with(mActivity, result.getAd4().getImg_url(), img4);
        ImageLoader.with(mActivity, result.getAd5().getImg_url(), img5);
    }

    @Override
    public void onClickLoadingRefresh() {
        OkGo.<HttpResModel<HomeModel>>get(homePage)
                .tag(this)
                .execute(callback);
    }

    @OnClick({R.id.img_1, R.id.img_2, R.id.img_4, R.id.img_3, R.id.img_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_1:
                break;
            case R.id.img_2:
                break;
            case R.id.img_4:
                break;
            case R.id.img_3:
                break;
            case R.id.img_5:
                break;
        }
    }
}
