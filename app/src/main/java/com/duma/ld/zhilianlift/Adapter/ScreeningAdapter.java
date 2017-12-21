package com.duma.ld.zhilianlift.Adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.model.ScreeningModel;

import java.util.List;

/**
 * 商品列表页的筛选
 * Created by liudong on 2017/12/21.
 */

public class ScreeningAdapter extends BaseMultiItemQuickAdapter<ScreeningModel, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ScreeningAdapter(List<ScreeningModel> data) {
        super(data);
        addItemType(ScreeningModel.head, R.layout.adapter_screen_head);
        addItemType(ScreeningModel.content, R.layout.adapter_screen_cont);
        addItemType(ScreeningModel.view, R.layout.adapter_screen_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScreeningModel item) {
        switch (helper.getItemViewType()) {
            case ScreeningModel.head:
                helper.setText(R.id.tv_name, item.getName());
                break;
            case ScreeningModel.content:
                TextView textView = helper.getView(R.id.tv_content);
                textView.setText(item.getItemBean().getName());
                if (item.isChenked()) {
                    textView.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.bx_4_hong));
                    textView.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                } else {
                    textView.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hui2));
                    textView.setTextColor(ZhuanHuanUtil.getColor(R.color.hei1));
                }
                break;
        }
    }
}
