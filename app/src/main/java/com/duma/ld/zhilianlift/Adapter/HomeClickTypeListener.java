package com.duma.ld.zhilianlift.Adapter;

import android.app.Activity;
import android.view.View;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.model.AdBean;
import com.duma.ld.zhilianlift.model.GoodsBean;
import com.duma.ld.zhilianlift.model.HomeModel;
import com.duma.ld.zhilianlift.model.HomeMultipleModel;
import com.duma.ld.zhilianlift.util.IntentUtil;

/**
 * Created by liudong on 2017/12/4.
 */

public class HomeClickTypeListener implements View.OnClickListener, OnItemClickListener, BaseQuickAdapter.OnItemClickListener {
    private HomeModel result;
    private Activity mActivity;

    public HomeClickTypeListener(Activity mActivity) {
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
     */
    private void clickItem(AdBean adBean) {
        TsUtils.show("name:" + adBean.getTitle() + " type:" + adBean.getType());
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
                break;
            case 3:
                break;
            case 4:
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
    @Override
    public void onItemClick(int position) {
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
                break;
            case HomeMultipleModel.goods:
                IntentUtil.goGoodsDetails(mActivity, multipleModel.getGoodsModel().getGoods_id());
                break;
        }
    }
}
