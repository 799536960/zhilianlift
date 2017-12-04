package com.duma.ld.zhilianlift.Adapter;

import android.app.Activity;
import android.text.SpannableStringBuilder;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.model.GoodsBean;
import com.duma.ld.zhilianlift.util.ImageLoader;

/**
 * Created by liudong on 2017/12/4.
 */

public class GoodsAdapter extends BaseAdapter<GoodsBean> {
    private Activity mActivity;

    public GoodsAdapter(Activity mActivity) {
        super(R.layout.adapter_goods);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean item) {
        ImageLoader.with(mActivity, item.getOriginal_img(), (ImageView) helper.getView(R.id.img_pic));
        helper.setText(R.id.tv_title, item.getGoods_name())
                .setText(R.id.tv_sales, "月销" + item.getSales_sum() + "件")
                .setText(R.id.tv_comment, item.getGood_comment_rate() + "%好评");
        SpannableStringBuilder spannableStringBuilder = new SpanUtils()
                .append("¥")
                .setFontSize(ConvertUtils.sp2px(13))
                .append(item.getShop_price())
                .setFontSize(ConvertUtils.sp2px(18))
                .create();
        helper.setText(R.id.tv_price, spannableStringBuilder);
    }


}
