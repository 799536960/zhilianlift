package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.Adapter.GlideImageLoader;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.model.GoodsBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

/**
 * Created by liudong on 2017/12/14.
 */

public class PublicUtil {
    public static AlertDialog.Builder getAlertDialog(Activity activity, String title, String message) {
        return new AlertDialog.Builder(activity, R.style.AlertDialogTheme)
                .setTitle(title)
                .setMessage(message);
    }

    public static AlertDialog.Builder getAlertDialog(Activity activity, String title) {
        return new AlertDialog.Builder(activity, R.style.AlertDialogTheme)
                .setTitle(title);
    }

    public static void goodsGetView(BaseViewHolder helper, GoodsBean item) {
        ImageLoader.with(item.getOriginal_img(), (ImageView) helper.getView(R.id.img_pic));
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

    public static Banner initBanner(Banner banner) {
        return banner.setImageLoader(new GlideImageLoader())
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setBannerStyle(BannerConfig.NUM_INDICATOR);
    }
}
