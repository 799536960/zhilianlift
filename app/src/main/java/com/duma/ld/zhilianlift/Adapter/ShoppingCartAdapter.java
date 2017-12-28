package com.duma.ld.zhilianlift.Adapter;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.model.ShoppingCartListModel;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.widget.CheckBoxNoOnClick;
import com.duma.ld.zhilianlift.widget.NumInputLayout;

import java.util.List;

/**
 * Created by liudong on 2017/12/28.
 */

public class ShoppingCartAdapter extends BaseMultiItemQuickAdapter<ShoppingCartListModel, BaseViewHolder> {
    //是否编辑
    private boolean isEdit;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ShoppingCartAdapter(List<ShoppingCartListModel> data) {
        super(data);
        isEdit = false;
        addItemType(ShoppingCartListModel.head, R.layout.adapter_shopping_cart_head);
        addItemType(ShoppingCartListModel.goods, R.layout.adapter_shopping_cart_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingCartListModel item) {
        switch (helper.getItemViewType()) {
            case ShoppingCartListModel.head:
                helper.setText(R.id.tv_store_name, item.getStoreName());
                CheckBoxNoOnClick cb_Select_All = helper.getView(R.id.cb_Select_All);
                break;
            case ShoppingCartListModel.goods:
                goodsView(helper, item);
                break;
        }
    }

    private void goodsView(BaseViewHolder helper, ShoppingCartListModel item) {
        CheckBoxNoOnClick cb_Select = helper.getView(R.id.cb_Select);
        LinearLayout layout_goods_type = helper.getView(R.id.layout_goods_type);
        NumInputLayout numInput = helper.getView(R.id.numInput);
        TextView tv_goods_type = helper.getView(R.id.tv_goods_type);
        ImageLoader.with(item.getShoppingCartStoreGoodsModel().getOriginal_img(), (ImageView) helper.getView(R.id.img_icon), 6);
        helper.setText(R.id.tv_goods_name, item.getShoppingCartStoreGoodsModel().getGoods_name())
                .setText(R.id.tv_goods_type2, item.getShoppingCartStoreGoodsModel().getSpec_key_name())
                .setText(R.id.tv_goods_type, item.getShoppingCartStoreGoodsModel().getSpec_key_name());
        SpannableStringBuilder spannableStringBuilder = new SpanUtils()
                .append("¥")
                .setFontSize(ConvertUtils.sp2px(13))
                .append(item.getShoppingCartStoreGoodsModel().getGoods_price())
                .setFontSize(ConvertUtils.sp2px(18))
                .create();
        helper.setText(R.id.tv_goods_money, spannableStringBuilder);
        numInput.setNum(item.getShoppingCartStoreGoodsModel().getGoods_num());

        if (isEdit) {
            //编辑状态
            layout_goods_type.setVisibility(View.VISIBLE);
            tv_goods_type.setVisibility(View.GONE);
        } else {
            layout_goods_type.setVisibility(View.GONE);
            tv_goods_type.setVisibility(View.VISIBLE);
        }
    }
}
