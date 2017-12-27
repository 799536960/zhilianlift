package com.duma.ld.zhilianlift.Adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.model.GoodsSpecModel;

import java.util.List;

/**
 * 商品列表页的筛选
 * Created by liudong on 2017/12/21.
 */

public class SpecAdapter extends BaseMultiItemQuickAdapter<GoodsSpecModel, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SpecAdapter(List<GoodsSpecModel> data) {
        super(data);
        addItemType(GoodsSpecModel.head, R.layout.adapter_spec_head);
        addItemType(GoodsSpecModel.content, R.layout.adapter_spec_content);
        addItemType(GoodsSpecModel.footer, R.layout.adapter_head_spec);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsSpecModel item) {
        switch (helper.getItemViewType()) {
            case GoodsSpecModel.head:
                helper.setText(R.id.tv_name, item.getName());
                break;
            case GoodsSpecModel.content:
                helper.setText(R.id.tv_content, item.getSpecListBean().getItem());
                TextView tv_content = helper.getView(R.id.tv_content);
                if (item.getSpecListBean().isSelect()) {
                    tv_content.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.bx_hong));
                    tv_content.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                } else {
                    tv_content.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.bx_hei));
                    tv_content.setTextColor(ZhuanHuanUtil.getColor(R.color.hei1));
                }
                break;
        }
    }
}
