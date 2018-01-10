package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.Adapter.GlideImageLoader;
import com.duma.ld.zhilianlift.Adapter.OrderGoodsAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.model.GoodsBean;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.view.main.shopping.ShoppingCartActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import static com.duma.ld.zhilianlift.util.Constants.Order_Text_ChaKanWuLiu;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_ChaKanXiangQin;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_PinJia;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_QuXiaoDinDan;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_QuZhiFu;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_QueRenShouHuo;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_ShanChuDinDan;
import static com.duma.ld.zhilianlift.util.Constants.Order_Text_ZaiCiGouMai;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiFaHuo;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiFuKuan;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiPinJia;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_DaiShouHuo;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_YiQuXiao;
import static com.duma.ld.zhilianlift.util.Constants.Order_Type_YiWanChen;
import static com.duma.ld.zhilianlift.util.HttpUrl.buy;
import static com.duma.ld.zhilianlift.util.HttpUrl.cancelOrder;
import static com.duma.ld.zhilianlift.util.HttpUrl.del_order;
import static com.duma.ld.zhilianlift.util.HttpUrl.orderConfirm;

/**
 * Created by liudong on 2017/12/14.
 */

public class PublicUtil {
    public static AlertDialog.Builder getAlertDialog(Activity activity, String title, String message) {
        return new AlertDialog.Builder(activity, R.style.AlertDialogTheme)
                .setTitle(title)
                .setMessage(message);
    }

    public static AlertDialog.Builder getAlertDialog_nessage(Activity activity, String message) {
        return new AlertDialog.Builder(activity, R.style.AlertDialogTheme)
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

    public static void getView_OrderGoods(final Activity mActivity, final BaseViewHolder helper, final OrderModel item, final boolean isShouHou, final String type) {
        RecyclerView rv_goodsList = helper.getView(R.id.rv_goodsList);
        final OrderGoodsAdapter orderGoodsAdapter = new OrderGoodsAdapter(rv_goodsList, mActivity, isShouHou);
        orderGoodsAdapter.getmAdapter().setNewData(item.getOrder_goods());
        orderGoodsAdapter.getmAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (isShouHou) {
                    //开启售后后 点击商品跳转商品详情页
                    IntentUtil.goGoodsDetails(mActivity, orderGoodsAdapter.getmAdapter().getData().get(position).getGoods_id());
                } else {
                    //没有开启 则跳转到订单详情
                    IntentUtil.goOrderInfo(mActivity, helper.getLayoutPosition(), item.getMaster_order_sn(), type);
                }
            }
        });
        if (isShouHou) {
            //开启售后后 按钮消失
            helper.setGone(R.id.layout_btn, false);
        } else {
            helper.setGone(R.id.layout_btn, true);
        }
        helper.setText(R.id.tv_orderType, item.getOrder_status_detail())
                .addOnClickListener(R.id.tv_hui)
                .addOnClickListener(R.id.tv_hong)
                .setText(R.id.tv_orderMoney, "共" + item.getAllgoodsNum() + "件商品,合计付款:¥" + item.getDaFuKuan() + "(含运费¥" + item.getShipping_price() + ")");
        TextView tv_hui = helper.getView(R.id.tv_hui);
        TextView tv_hong = helper.getView(R.id.tv_hong);
        PublicUtil.refreshOrderBut(tv_hui, tv_hong, item.getOrder_status_code(), true);
    }

    /**
     * 订单
     * 待付款->取消订单 去支付
     * 待发货->  --     查看详情
     * 待收货->查看物流 确认收货
     * 待评价->再次购买 评价
     * 已取消->删除订单 再次购买
     * 已完成->删除订单 再次购买
     * <p>
     * isXiangQin 要不要查看详情按钮
     */
    public static void refreshOrderBut(TextView tvhui, TextView tvhong, String type, boolean isXiangQin) {
        switch (type) {
            case Order_Type_DaiFuKuan:
                tvhui.setText(Order_Text_QuXiaoDinDan);
                tvhong.setText(Order_Text_QuZhiFu);
                tvhui.setVisibility(View.VISIBLE);
                tvhong.setVisibility(View.VISIBLE);
                break;
            case Order_Type_DaiFaHuo:
                tvhong.setText(Order_Text_ChaKanXiangQin);
                tvhui.setVisibility(View.GONE);
                if (isXiangQin) {
                    tvhong.setVisibility(View.VISIBLE);
                } else {
                    tvhong.setVisibility(View.GONE);
                }
                break;
            case Order_Type_DaiShouHuo:
                tvhui.setText(Order_Text_ChaKanWuLiu);
                tvhong.setText(Order_Text_QueRenShouHuo);
                tvhui.setVisibility(View.VISIBLE);
                tvhong.setVisibility(View.VISIBLE);
                break;
            case Order_Type_DaiPinJia:
                tvhui.setText(Order_Text_ZaiCiGouMai);
                tvhong.setText(Order_Text_PinJia);
                tvhui.setVisibility(View.VISIBLE);
                tvhong.setVisibility(View.VISIBLE);
                break;
            case Order_Type_YiQuXiao:
                tvhui.setText(Order_Text_ShanChuDinDan);
                tvhong.setText(Order_Text_ZaiCiGouMai);
                tvhui.setVisibility(View.VISIBLE);
                tvhong.setVisibility(View.VISIBLE);
                break;
            case Order_Type_YiWanChen:
                tvhui.setText(Order_Text_ShanChuDinDan);
                tvhong.setText(Order_Text_ZaiCiGouMai);
                tvhui.setVisibility(View.VISIBLE);
                tvhong.setVisibility(View.VISIBLE);
                break;
            default:
                tvhong.setText(Order_Text_ChaKanXiangQin);
                tvhui.setVisibility(View.GONE);
                tvhong.setVisibility(View.VISIBLE);
                break;
        }
    }


    /**
     * 删除订单
     */
    public static void HttpDeleteOrder(final Activity mActivity, final int position, final int order_id, final MyJsonCallback<HttpResModel<OrderModel>> jsonCallback) {
        AlertDialog.Builder builder = PublicUtil.getAlertDialog_nessage(mActivity, "确定要删除该订单嘛?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OkGo.<HttpResModel<OrderModel>>get(del_order)
                                .params("order_id", order_id)
                                .execute(jsonCallback);
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false);
        builder.show();
    }

    /**
     * 取消订单
     */
    public static void HttpCancelOrder(final Activity mActivity, final int position, final int order_id, final MyJsonCallback<HttpResModel<OrderModel>> jsonCallback) {
        AlertDialog.Builder builder = PublicUtil.getAlertDialog_nessage(mActivity, "确定要取消订单嘛?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OkGo.<HttpResModel<OrderModel>>get(cancelOrder)
                                .params("order_id", order_id)
                                .execute(jsonCallback);
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false);
        builder.show();
    }

    /**
     * 确认收货
     */
    public static void HttpQuRenShouHuo(final Activity mActivity, final int position, final int order_id, final MyJsonCallback<HttpResModel<OrderModel>> jsonCallback) {
        AlertDialog.Builder builder = PublicUtil.getAlertDialog_nessage(mActivity, "确定已经收到商品嘛?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OkGo.<HttpResModel<OrderModel>>get(orderConfirm)
                                .params("order_id", order_id)
                                .execute(jsonCallback);
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false);
        builder.show();
    }

    /**
     * 再次购买
     */
    public static void HttpZaiCiGouMai(String sn, final Activity mActivity) {
        OkGo.<HttpResModel<OrderModel>>get(buy)
                .params("sn", sn)
                .execute(new MyJsonCallback<HttpResModel<OrderModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<OrderModel>> respons, HttpResModel<OrderModel> orderModelHttpResModel) {
                        mActivity.startActivity(new Intent(mActivity, ShoppingCartActivity.class));
                    }
                }.isDialog(mActivity));
    }
}
