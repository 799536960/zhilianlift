package com.duma.ld.zhilianlift.Adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.model.HomeMultipleModel;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.PublicUtil;

import java.util.List;

/**
 * Created by liudong on 2017/12/18.
 */

public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeMultipleModel, BaseViewHolder> {
    private HomeClickTypeListener listener;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(List<HomeMultipleModel> data, HomeClickTypeListener listener) {
        super(data);
        this.listener = listener;
        addItemType(HomeMultipleModel.fenlei, R.layout.adapter_home_class);
        addItemType(HomeMultipleModel.ad, R.layout.adapter_home_ad);
        addItemType(HomeMultipleModel.goodsHead, R.layout.adapter_home_head);
        addItemType(HomeMultipleModel.goods, R.layout.adapter_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMultipleModel item) {
        switch (helper.getItemViewType()) {
            case HomeMultipleModel.fenlei:
                ImageLoader.with(item.getClassModel().getImg_url(), (ImageView) helper.getView(R.id.img_class));
                helper.setText(R.id.tv_class, item.getClassModel().getTitle());
                break;
            case HomeMultipleModel.ad:
                ImageView imageView1 = helper.getView(R.id.img_1);
                ImageView imageView2 = helper.getView(R.id.img_2);
                ImageView imageView3 = helper.getView(R.id.img_3);
                ImageView imageView4 = helper.getView(R.id.img_4);
                ImageView imageView5 = helper.getView(R.id.img_5);
                ImageLoader.with(item.getAdModel().getAd1().getImg_url(), imageView1);
                ImageLoader.with(item.getAdModel().getAd2().getImg_url(), imageView2);
                ImageLoader.with(item.getAdModel().getAd3().getImg_url(), imageView3);
                ImageLoader.with(item.getAdModel().getAd4().getImg_url(), imageView4);
                ImageLoader.with(item.getAdModel().getAd5().getImg_url(), imageView5);
                imageView1.setOnClickListener(listener);
                imageView2.setOnClickListener(listener);
                imageView3.setOnClickListener(listener);
                imageView4.setOnClickListener(listener);
                imageView5.setOnClickListener(listener);
                break;
            case HomeMultipleModel.goodsHead:
                ImageLoader.with(item.getHeadModel().getImg_url(), (ImageView) helper.getView(R.id.img_title));
                break;
            case HomeMultipleModel.goods:
                PublicUtil.goodsGetView(helper, item.getGoodsModel());
                break;
        }
    }
}
