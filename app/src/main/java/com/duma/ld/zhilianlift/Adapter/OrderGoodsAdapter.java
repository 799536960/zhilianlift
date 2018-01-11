package com.duma.ld.zhilianlift.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private BaseAdapter<OrderModel.OrderGoodsBean> mAdapter;

    public OrderGoodsAdapter(RecyclerView recyclerView, final Activity activity, final boolean isShouHou) {
        mAdapter = new BaseAdapter.Builder<OrderModel.OrderGoodsBean>(recyclerView, activity, R.layout.adapter_order_goods2)
                .isNested()
                .setNoEnpty()
                .build(new OnBaseAdapterListener<OrderModel.OrderGoodsBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, OrderModel.OrderGoodsBean item) {
                        helper.setText(R.id.tv_title, item.getGoods_name())
                                .setText(R.id.tv_spec, "数量:" + item.getGoods_num() + " " + item.getSpec_key_name_noNull())
                                .setText(R.id.tv_price, "¥" + item.getGoods_price());
                        ImageView img_icon = helper.getView(R.id.img_icon);
                        ImageLoader.with(activity, item.getOriginal_img(), img_icon);
                        TextView tv_afterSales = helper.getView(R.id.tv_afterSales);
                        if (isShouHou) {
                            tv_afterSales.setVisibility(View.VISIBLE);
                        } else {
                            tv_afterSales.setVisibility(View.GONE);
                        }
                        tv_afterSales.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO: 2018/1/10 申请售后

                            }
                        });
                    }
                });
    }

    public BaseAdapter<OrderModel.OrderGoodsBean> getmAdapter() {
        return mAdapter;
    }
}
