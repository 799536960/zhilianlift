package com.duma.ld.zhilianlift.Adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.model.AdBean;
import com.duma.ld.zhilianlift.model.GoodsBean;
import com.duma.ld.zhilianlift.model.HomeModel;
import com.duma.ld.zhilianlift.model.HomeMultipleModel;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.view.main.home.MainActivity;
import com.duma.ld.zhilianlift.view.start.PinPaiActivity;
import com.youth.banner.listener.OnBannerListener;

/**
 * 监听
 * Created by liudong on 2017/12/4.
 */

public class HomeClickTypeListener implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener, OnBannerListener {
    private HomeModel result;
    private MainActivity mActivity;

    public HomeClickTypeListener(MainActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void setResult(HomeModel result) {
        this.result = result;
    }

    /**
     * type=1 商品  goodsModel imgUrl不能为空
     * type=2 分类商品列表 id title imgUrl
     * type=3 网页 h5 url title  imgUrl不能为空
     * type=4 搜索  title  imgUrl不能为空
     * 5  装修
     * 6  找新房
     * 7  二手房
     * 8  租房
     * 9  合作品牌
     */
    private void clickItem(AdBean adBean) {
//        TsUtils.show("type:" + adBean.getType());
        switch (adBean.getType()) {
            case 1:
                GoodsBean goods = adBean.getGoods();
                if (goods == null) {
                    TsUtils.show("没有该商品");
                    return;
                }
                IntentUtil.goGoodsDetails(mActivity, goods.getGoods_id());
                break;
            case 2:
                IntentUtil.goGoodsList_class(mActivity, adBean.getCat_id() + "");
                break;
            case 3:
                IntentUtil.goWebView(mActivity, adBean.getUrl());
                break;
            case 4:
                IntentUtil.goGoodsList_search(mActivity, adBean.getTitle() + "");
                break;
            case 5:
                mActivity.showFinance();
                break;
            case 6:
                //新房
                IntentUtil.goHouseList_xinFang(mActivity);
                break;
            case 7:
                //二手房
                IntentUtil.goHouseList_erShoufang(mActivity);
                break;
            case 8:
                //出租房
                IntentUtil.goHouseList_zuFang(mActivity);
                break;
            case 9:
                mActivity.startActivity(new Intent(mActivity, PinPaiActivity.class));
                break;
            case 10:
                //房产详情
                IntentUtil.goHouseInfo(mActivity, adBean.getGoods_id());
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_1:
                clickItem(result.getAd1());
                break;
            case R.id.img_2:
                clickItem(result.getAd2());
                break;
            case R.id.img_4:
                clickItem(result.getAd3());
                break;
            case R.id.img_3:
                clickItem(result.getAd4());
                break;
            case R.id.img_5:
                clickItem(result.getAd5());
                break;
        }

    }

    /**
     * 轮播的点击事件
     *
     * @param position 下标
     */
//    @Override
//    public void onItemClick(int position) {
//        clickItem(result.getLunbo().get(position));
//    }
    @Override
    public void OnBannerClick(int position) {
        clickItem(result.getLunbo().get(position));
    }

    /**
     * 分类adapter的点击事件
     *
     * @param position 下标
     */

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeMultipleModel multipleModel = (HomeMultipleModel) adapter.getData().get(position);
        switch (multipleModel.getItemType()) {
            case HomeMultipleModel.fenlei:
                clickItem(multipleModel.getClassModel());
                break;
            case HomeMultipleModel.goodsHead:
                mActivity.showClass();
                break;
            case HomeMultipleModel.goods:
                IntentUtil.goGoodsDetails(mActivity, multipleModel.getGoodsModel().getGoods_id());
                break;
        }
    }


}
