package com.duma.ld.zhilianlift.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.util.ImageLoader;

/**
 * 订单页面的商品展示页面
 * Created by liudong on 2018/1/9.
 */

public class OrderGoodsAdapter {
    private Activity activity;
    private BaseAdapter<OrderModel.OrderGoodsBean> mAdapter;

    public OrderGoodsAdapter(RecyclerView recyclerView, final Activity activity) {
        this.activity = activity;
        mAdapter = new BaseAdapter.Builder<OrderModel.OrderGoodsBean>(recyclerView, activity, R.layout.adapter_order_goods)
                .isNested()
                .setNoEnpty()
                .build(new OnBaseAdapterListener<OrderModel.OrderGoodsBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, OrderModel.OrderGoodsBean item) {
                        helper.setText(R.id.tv_title, item.getGoods_name())
                                .setText(R.id.tv_spec, item.getSpec_key_name())
                                .setText(R.id.tv_price, "¥" + item.getGoods_price())
                                .setText(R.id.tv_num, "x" + item.getGoods_num());
                        ImageView img_icon = helper.getView(R.id.img_icon);
                        ImageLoader.with(activity, item.getOriginal_img(), img_icon);
                    }
                });
    }

    public BaseAdapter<OrderModel.OrderGoodsBean> getmAdapter() {
        return mAdapter;
    }
}
