package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.Adapter.GlideImageLoader;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.model.AfterSalesListModel;
import com.duma.ld.zhilianlift.model.AfterSalesModel;
import com.duma.ld.zhilianlift.model.GoodsBean;
import com.duma.ld.zhilianlift.model.HouseLabelBean;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.MyHouseModel;
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

    //这个eventType 只是订单详情回到订单列表的时候eventbus发的事件用的
    public static void getViewOrder(final Activity mActivity, final BaseViewHolder helper,
                                    final OrderModel item, final boolean isOrderInfo,
                                    BaseQuickAdapter.OnItemClickListener itemClickListener) {
        final String type = item.getOrder_status_code();
        RecyclerView rv_goodsList = helper.getView(R.id.rv_goodsList);
        BaseAdapter<OrderModel.OrderGoodsBean> adapter = new BaseAdapter.Builder<OrderModel.OrderGoodsBean>(rv_goodsList, mActivity, R.layout.adapter_order_goods2)
                .isNested()
                .setNoEnpty()
                .build(new OnBaseAdapterListener<OrderModel.OrderGoodsBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, final OrderModel.OrderGoodsBean goodsBean) {
                        getViewOrderGoods(helper, goodsBean, mActivity);
                        TextView tv_afterSales = helper.getView(R.id.tv_afterSales);
                        //默认没有售后按钮 除非在订单详情中
                        boolean isShouHou = false;
                        if (isOrderInfo) {
                            if (type.equals(Order_Type_DaiFaHuo) || type.equals(Order_Type_DaiPinJia) || type.equals(Order_Type_YiWanChen)) {
                                isShouHou = true;
                            }
                        }
                        if (isShouHou) {
                            tv_afterSales.setVisibility(View.VISIBLE);
                        } else {
                            tv_afterSales.setVisibility(View.GONE);
                        }
                        tv_afterSales.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                IntentUtil.goAddAfterSalesList(mActivity, goodsBean, item.getOrder_id() + "");
                            }
                        });
                    }
                });
        adapter.setNewData(item.getOrder_goods());
        adapter.setOnItemClickListener(itemClickListener);
        //订单ui
        if (isOrderInfo) {
            //订单详情 按钮消失
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
        PublicUtil.refreshOrderBut(tv_hui, tv_hong, type, true);
    }

    public static void getViewOrderGoods(BaseViewHolder helper, OrderModel.OrderGoodsBean item, Activity activity) {
        helper.setText(R.id.tv_title, item.getGoods_name())
                .setText(R.id.tv_spec, "数量:" + item.getGoods_num() + " " + item.getSpec_key_name_noNull())
                .setText(R.id.tv_price, "¥" + item.getGoods_price());
        ImageView img_icon = helper.getView(R.id.img_icon);
        ImageLoader.with(activity, item.getOriginal_img(), img_icon);
    }

    public static void getViewOrderGoods(BaseViewHolder helper, AfterSalesModel item, Activity activity) {
        helper.setText(R.id.tv_title, item.getGoods_name())
                .setText(R.id.tv_spec, "数量:" + item.getGoods_num() + " " + item.getSpec_key_name_noNull())
                .setText(R.id.tv_price, "¥" + item.getGoods_price());
        ImageView img_icon = helper.getView(R.id.img_icon);
        ImageLoader.with(activity, item.getOriginal_img(), img_icon);
    }

    public static void getViewOrderGoods(BaseViewHolder helper, AfterSalesListModel item, Activity activity) {
        helper.setText(R.id.tv_title, item.getGoods_name())
                .setText(R.id.tv_spec, "数量:" + item.getGoods_num() + " " + item.getSpec_key_name_noNull())
                .setText(R.id.tv_price, "¥" + item.getGoods_price());
        ImageView img_icon = helper.getView(R.id.img_icon);
        ImageLoader.with(activity, item.getOriginal_img(), img_icon);
    }

    public static void getViewHouse(BaseViewHolder helper, MyHouseModel item, Activity mActivity, boolean isChuZu) {
        getViewHousePublic(helper, item, mActivity);
        helper.setText(R.id.tv_spec1, item.getDoor_door() + "室" + item.getOffice() + "厅" + item.getToilet() + "卫  " +
                item.getArchitecture() + "㎡  " + item.getOrientationNoNull());
        TextView tv_money2 = helper.getView(R.id.tv_money2);
        TextView tv_money = helper.getView(R.id.tv_money);
        if (isChuZu) {
            tv_money2.setVisibility(View.GONE);
            tv_money.setText(item.getRent() + "元/月");
        } else {
            tv_money2.setVisibility(View.VISIBLE);
            tv_money2.setText(item.getHouse_price() + "元/平");
            tv_money.setText(item.getAllprice() + "万元");
        }
    }

    public static void getViewHouseNew(BaseViewHolder helper, MyHouseModel item, Activity mActivity) {
        getViewHousePublic(helper, item, mActivity);
        TextView tv_money = helper.getView(R.id.tv_money);
        TextView tv_type = helper.getView(R.id.tv_type);
        tv_money.setText(item.getOn_chang_text());
        switch (item.getSales_type()) {
            case 1:
                tv_type.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_2_lv));
                tv_type.setTextColor(ZhuanHuanUtil.getColor(R.color.white));
                tv_type.setText("在售");
                break;
            case 2:
                tv_type.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_2_lan));
                tv_type.setTextColor(ZhuanHuanUtil.getColor(R.color.white));
                tv_type.setText("待售");
                break;
            case 3:
                tv_type.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_2_hui2));
                tv_type.setTextColor(ZhuanHuanUtil.getColor(R.color.hui2));
                tv_type.setText("售罄");
                break;
        }
    }

    public static void getViewHousePublic(BaseViewHolder helper, MyHouseModel item, Activity mActivity) {
        helper.setText(R.id.tv_name, item.getHouse_name() + "")
                .setText(R.id.tv_spec2, item.getCompany_district_name() + " " + item.getHouse_address());
        ImageView img_house = helper.getView(R.id.img_house);
        ImageLoader.with(item.getOriginal_img(), img_house);
        RecyclerView rv_list = helper.getView(R.id.rv_list);
        if (item.getHouseLabel() == null || item.getHouseLabel().size() == 0) {
            rv_list.setVisibility(View.GONE);
        } else {
            rv_list.setVisibility(View.VISIBLE);
            BaseAdapter<HouseLabelBean> adapter = new BaseAdapter.Builder<HouseLabelBean>(rv_list, mActivity, R.layout.adapter_my_house_item)
                    .setNoEnpty()
                    .isNested()
                    .setLayoutManager(new LinearLayoutManager(mActivity, LinearLayout.HORIZONTAL, false))
                    .build(new OnBaseAdapterListener<HouseLabelBean>() {
                        @Override
                        public void convert(BaseViewHolder helper, HouseLabelBean item) {
                            helper.setText(R.id.tv_name, item.getSo_name());
                        }
                    });
            adapter.setNewData(item.getHouseLabel());
            rv_list.setAdapter(adapter);
        }
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
                                .tag(mActivity)
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
                                .tag(mActivity)
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
                                .tag(mActivity)
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
                .tag(mActivity)
                .params("sn", sn)
                .execute(new MyJsonCallback<HttpResModel<OrderModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<OrderModel>> respons, HttpResModel<OrderModel> orderModelHttpResModel) {
                        mActivity.startActivity(new Intent(mActivity, ShoppingCartActivity.class));
                    }
                }.isDialog(mActivity));
    }

    public static void setGoMarker(BaiduMap mBaiduMap, double Latitude, double Longitude, int zoom) {
        LatLng point = new LatLng(Latitude, Longitude);
        MapStatus mapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(zoom)
                .build();
        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaiduMap.setMapStatus(statusUpdate);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.tubiao1);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }
}
