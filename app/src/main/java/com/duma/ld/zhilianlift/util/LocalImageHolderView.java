package com.duma.ld.zhilianlift.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.duma.ld.zhilianlift.model.AdBean;

/**
 * type=1 商品 goodsModel不能为空
 * type=2 分类 id title imgUrl
 * type=3 网页h5 url title  imgUrl不能为空
 */
public class LocalImageHolderView implements Holder<AdBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, final int position, AdBean bean) {
        ImageLoader.with(bean.getImg_url(), imageView);
    }
}