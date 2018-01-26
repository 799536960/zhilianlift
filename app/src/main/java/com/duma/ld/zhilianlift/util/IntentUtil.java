package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.content.Intent;

import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.model.AddresModel;
import com.duma.ld.zhilianlift.model.CommitOrderModel;
import com.duma.ld.zhilianlift.model.HouseMapModel;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.model.RealNameModel;
import com.duma.ld.zhilianlift.model.ShoppingSpacModel;
import com.duma.ld.zhilianlift.view.login.LoginOrRegisterActivity;
import com.duma.ld.zhilianlift.view.main.finance.FinanceInfoActivity;
import com.duma.ld.zhilianlift.view.main.home.MainActivity;
import com.duma.ld.zhilianlift.view.main.house.AddBaoBeiActivity;
import com.duma.ld.zhilianlift.view.main.house.AddHouseActivity;
import com.duma.ld.zhilianlift.view.main.house.HouseInfoActivity;
import com.duma.ld.zhilianlift.view.main.house.HouseListActivity;
import com.duma.ld.zhilianlift.view.main.house.HouseMapActivity;
import com.duma.ld.zhilianlift.view.main.house.HuXinListActivity;
import com.duma.ld.zhilianlift.view.main.house.MyHouseActivity;
import com.duma.ld.zhilianlift.view.main.pay.PayActivity;
import com.duma.ld.zhilianlift.view.main.pay.PayInputPasswordActivity;
import com.duma.ld.zhilianlift.view.main.pay.PaySuccessActivity;
import com.duma.ld.zhilianlift.view.main.shopping.AddCommentActivity;
import com.duma.ld.zhilianlift.view.main.shopping.SearchActivity;
import com.duma.ld.zhilianlift.view.main.shopping.afterSales.AfterSalesInfoActivity;
import com.duma.ld.zhilianlift.view.main.shopping.afterSales.ApplyAfterSalesActivity;
import com.duma.ld.zhilianlift.view.main.shopping.afterSales.ApplyAfterSalesListActivity;
import com.duma.ld.zhilianlift.view.main.shopping.afterSales.ApplyRefundActivity;
import com.duma.ld.zhilianlift.view.main.shopping.goods.GoodsDetailsActivity;
import com.duma.ld.zhilianlift.view.main.shopping.goods.GoodsListActivity;
import com.duma.ld.zhilianlift.view.main.shopping.order.ConfirmOrderActivity;
import com.duma.ld.zhilianlift.view.main.shopping.order.OrderInfoActivity;
import com.duma.ld.zhilianlift.view.main.shopping.order.OrderListActivity;
import com.duma.ld.zhilianlift.view.main.wode.CouponsActivity;
import com.duma.ld.zhilianlift.view.main.wode.MessageActivity;
import com.duma.ld.zhilianlift.view.main.wode.MyCollectActivity;
import com.duma.ld.zhilianlift.view.main.wode.MyRecordActivity;
import com.duma.ld.zhilianlift.view.main.wode.UserDataActivity;
import com.duma.ld.zhilianlift.view.main.wode.VerifyPhoneActivity;
import com.duma.ld.zhilianlift.view.main.wode.addres.AddOrChangeActivity;
import com.duma.ld.zhilianlift.view.main.wode.addres.AddresListActivity;
import com.duma.ld.zhilianlift.view.main.wode.addres.SelectAddresDialogActivity;
import com.duma.ld.zhilianlift.view.main.wode.realName.RealNameEditActivity;
import com.duma.ld.zhilianlift.view.main.wode.userSecuryty.PayPasswordActivity;
import com.duma.ld.zhilianlift.view.main.wode.userSecuryty.PayPasswordSuccessActivity;
import com.duma.ld.zhilianlift.view.main.wode.userSecuryty.PaySettingActivity;
import com.duma.ld.zhilianlift.view.start.PhotoQueryActivity;
import com.duma.ld.zhilianlift.view.start.PinPaiMenDIanActivity;
import com.duma.ld.zhilianlift.view.start.WebViewActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static com.duma.ld.zhilianlift.util.Constants.ClassId;
import static com.duma.ld.zhilianlift.util.Constants.Res;
import static com.duma.ld.zhilianlift.util.Constants.SearchString;
import static com.duma.ld.zhilianlift.util.Constants.Type;

/**
 * Created by liudong on 2017/12/6.
 */

public class IntentUtil {
    public static void goGoodsList_class(Activity activity, String classId) {
        goGoodsList(activity, ClassId, classId);
    }

    public static void goGoodsList_search(Activity activity, String searchString) {
        goGoodsList(activity, SearchString, searchString);
    }

    /**
     * 跳转商品列表也
     *
     * @param activity content
     * @param type     类型 分类和搜索
     * @param res      携带的值 分类的话就是分类id 搜索就是搜索内容
     */
    private static void goGoodsList(Activity activity, String type, String res) {
        Intent intent = new Intent(activity, GoodsListActivity.class);
        intent.putExtra(Type, type);
        intent.putExtra(Res, res);
        activity.startActivity(intent);
    }

    public static void goLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginOrRegisterActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.out_to_left2, R.anim.in_from_right);
    }

    public static void goUserData(Activity activity) {
        Intent intent = new Intent(activity, UserDataActivity.class);
        activity.startActivity(intent);
    }

    public static void goAddresList(Activity activity) {
        Intent intent = new Intent(activity, AddresListActivity.class);
        activity.startActivity(intent);
    }

    public static void goAddresListById(Activity activity) {
        Intent intent = new Intent(activity, AddresListActivity.class);
        intent.putExtra(Constants.key, Constants.key);
        activity.startActivity(intent);
    }

    public static void goAdd_Addres(Activity activity) {
        goAddOrChange(activity, null);
    }

    public static void goAddOrChange(Activity activity, AddresModel model) {
        Intent intent = new Intent(activity, AddOrChangeActivity.class);
        intent.putExtra(Constants.Model, model);
        activity.startActivity(intent);
    }

    /**
     * 跳转验证手机
     *
     * @param type 类型
     */
    private static void goVerifyPhone(Activity activity, String type) {
        Intent intent = new Intent(activity, VerifyPhoneActivity.class);
        intent.putExtra(Type, type);
        activity.startActivity(intent);
    }

    //验证手机后 跳转实名认证
    public static void goVerifyPhone_RealName(Activity activity) {
        goVerifyPhone(activity, Constants.Verify_RealName);
    }

    //验证手机后 跳转设置支付密码
    public static void goVerifyPhone_payPassword(Activity activity) {
        goVerifyPhone(activity, Constants.Verify_payPassword);
    }

    //验证手机后 跳转忘记密码的设置支付密码
    public static void goVerifyPhone_payPassword_forget(Activity activity) {
        goVerifyPhone(activity, Constants.Verify_payPassword_forget);
    }

    //添加实名认证
    public static void goRealName_add(Activity activity) {
        goRealName(activity, null);
    }

    //修改实名认证
    public static void goRealName_change(Activity activity, RealNameModel model) {
        goRealName(activity, model);
    }

    private static void goRealName(Activity activity, RealNameModel model) {
        Intent intent = new Intent(activity, RealNameEditActivity.class);
        intent.putExtra(Constants.Model, model);
        activity.startActivity(intent);
    }

    public static void goMain(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    //支付设置页面
    public static void goPaySetting(Activity activity) {
        Intent intent = new Intent(activity, PaySettingActivity.class);
//        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    //支付设置页面 从订单页面进入的 设置成功后就退出
    public static void goPaySetting_order(Activity activity) {
        Intent intent = new Intent(activity, PaySettingActivity.class);
        intent.putExtra(Constants.key, Constants.key);
//        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    //支付密码页面
    public static void goPayPassword(Activity activity, String type) {
        /**
         * //第一次设置支付密码 "type_new";
         * //验证原支付密码 "type_verify";
         * //验证后设置支付密码 "type_verify_new";
         * //忘记支付密码 验证手机后设置新支付密码 "type_forget_verify_new";
         */
        Intent intent = new Intent(activity, PayPasswordActivity.class);
        intent.putExtra(Constants.Type, type);
        activity.startActivity(intent);
    }

    //支付密码成功页面
    public static void goPayPasswordSuccess(Activity activity, String type) {
        Intent intent = new Intent(activity, PayPasswordSuccessActivity.class);
        intent.putExtra(Constants.Type, type);
        activity.startActivity(intent);
    }

    //搜索页面
    public static void goSearch(Activity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }

    //商品详情页
    public static void goGoodsDetails(Activity activity, int goodsId) {
        Intent intent = new Intent(activity, GoodsDetailsActivity.class);
        intent.putExtra(Constants.id, goodsId + "");
        activity.startActivity(intent);
    }

    //我的收藏
    public static void goMyCollect(Activity activity) {
        Intent intent = new Intent(activity, MyCollectActivity.class);
        activity.startActivity(intent);
    }

    //我的记录
    public static void goMyRecord(Activity activity) {
        Intent intent = new Intent(activity, MyRecordActivity.class);
        activity.startActivity(intent);
    }

    //图片查看页面
    public static void goPhoto(Activity activity, List<String> list, int position) {
        if (list == null || list.size() == 0) {
            return;
        }
        Intent intent = new Intent(activity, PhotoQueryActivity.class);
        intent.putExtra(Constants.Model, (Serializable) list);
        intent.putExtra(Constants.key, position + "");
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.a5, 0);
    }

    //确认订单_直接购买
    public static void goConfirmOrder(Activity activity, ShoppingSpacModel model) {
        Intent intent = new Intent(activity, ConfirmOrderActivity.class);
        intent.putExtra(Constants.Model, model);
        activity.startActivity(intent);
    }

    //确认订单_购物车进入
    public static void goConfirmOrder(Activity activity) {
        Intent intent = new Intent(activity, ConfirmOrderActivity.class);
        activity.startActivity(intent);
    }

    //去优惠券列表
    public static void goCoupons(Activity activity) {
        Intent intent = new Intent(activity, CouponsActivity.class);
        activity.startActivity(intent);
    }

    //去优惠券列表
    public static void goCoupons(Activity activity, String money) {
        Intent intent = new Intent(activity, CouponsActivity.class);
        intent.putExtra(Constants.key, money);
        activity.startActivity(intent);
    }

    //去支付密码
    public static void goPayInputPassword(Activity activity) {
        Intent intent = new Intent(activity, PayInputPasswordActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.out_to_left2, R.anim.in_from_right);
    }

    //去支付成功
    public static void goPaySuccess(Activity activity) {
        Intent intent = new Intent(activity, PaySuccessActivity.class);
        activity.startActivity(intent);
    }

    //收银台
    public static void goPay(Activity activity, CommitOrderModel model) {
        Intent intent = new Intent(activity, PayActivity.class);
        intent.putExtra(Constants.Model, model);
        activity.startActivity(intent);
    }

    public static void goPay(Activity activity, String master_order_sn, double order_amount) {
        goPay(activity, new CommitOrderModel(master_order_sn, order_amount));
    }

    //订单列表
    public static void goOrderList(Activity activity) {
        Intent intent = new Intent(activity, OrderListActivity.class);
        activity.startActivity(intent);
    }

    //订单列表_跳转各个tab
    public static void goOrderList(Activity activity, int position) {
        Intent intent = new Intent(activity, OrderListActivity.class);
        intent.putExtra(Constants.key, position);
        activity.startActivity(intent);
    }

    //我的出租
    public static void goRental(Activity activity) {
        goHouse(activity, "1");
    }

    //我的二手房
    public static void goSecondHouse(Activity activity) {
        goHouse(activity, "");
    }

    //我的房
    private static void goHouse(Activity activity, String key) {
        Intent intent = new Intent(activity, MyHouseActivity.class);
        intent.putExtra(Constants.key, key);
        activity.startActivity(intent);
    }

    //添加二手房
    public static void goAddSecondHouse(Activity activity) {
        Intent intent = new Intent(activity, AddHouseActivity.class);
        intent.putExtra(Constants.key, Constants.key);
        activity.startActivity(intent);
    }

    //添加出租
    public static void goAddRentalHouse(Activity activity) {
        Intent intent = new Intent(activity, AddHouseActivity.class);
        activity.startActivity(intent);
    }


    /**
     * 跳转商品详情页
     *
     * @param activity  不解释
     * @param position  rv中的位置 用于修改和更新
     * @param sn        订单号
     * @param eventType 是那个分类跳转过来的 用于eventbus发消息
     */
    public static void goOrderInfo(Activity activity, int position, String sn, String eventType) {
        Intent intent = new Intent(activity, OrderInfoActivity.class);
        intent.putExtra(Constants.position, position);
        intent.putExtra(Constants.key, sn);
        intent.putExtra(Constants.Type, eventType);
        activity.startActivity(intent);
    }

    /**
     * 跳转评论页面
     *
     * @param activity  不解释
     * @param position  rv中的位置 用于修改和更新
     * @param order_id  order_id
     * @param eventType 是那个分类跳转过来的 用于eventbus发消息
     */
    public static void goAddComment(Activity activity, int position, String order_id, String eventType) {
        Intent intent = new Intent(activity, AddCommentActivity.class);
        intent.putExtra(Constants.position, position);
        intent.putExtra(Constants.key, order_id);
        intent.putExtra(Constants.Type, eventType);
        activity.startActivity(intent);
    }

    public static void goWebView(Activity activity, String url, String title) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra(Constants.Url, url);
        intent.putExtra(Constants.Name, title);
        activity.startActivity(intent);
    }

    public static void goWebView(Activity activity, String url) {
        goWebView(activity, url, "");
    }


    /**
     * 跳转申请售后列表页面
     */
    public static void goAddAfterSalesList(Activity activity, List<OrderModel.OrderGoodsBean> list, String order_id) {
        Intent intent = new Intent(activity, ApplyAfterSalesListActivity.class);
        intent.putExtra(Constants.Model, (Serializable) list);
        intent.putExtra(Constants.id, order_id);
        activity.startActivity(intent);
    }

    public static void goAddAfterSalesList(Activity activity, OrderModel.OrderGoodsBean order_goods, String order_id) {
        List<OrderModel.OrderGoodsBean> list = new ArrayList<>();
        list.add(order_goods);
        goAddAfterSalesList(activity, list, order_id);
    }

    /**
     * 跳转申请退款
     */
    public static void goApplyRefund(Activity activity, OrderModel.OrderGoodsBean order_goods) {
        Intent intent = new Intent(activity, ApplyRefundActivity.class);
        intent.putExtra(Constants.Model, order_goods);
        activity.startActivity(intent);
    }

    /**
     * 跳转申请退换货 申请售后页面
     */
    public static void goApplyAfterSales(Activity activity, OrderModel.OrderGoodsBean order_goods) {
        Intent intent = new Intent(activity, ApplyAfterSalesActivity.class);
        intent.putExtra(Constants.Model, order_goods);
        activity.startActivity(intent);
    }

    /**
     * 跳转售后详情
     */
    public static void goAfterSalesInfo(Activity activity, String asId) {
        Intent intent = new Intent(activity, AfterSalesInfoActivity.class);
        intent.putExtra(Constants.id, asId);
        activity.startActivity(intent);
    }

    /**
     * 消息中心
     */
    public static void goMessage(Activity activity) {
        Intent intent = new Intent(activity, MessageActivity.class);
        activity.startActivity(intent);
    }

    /**
     * 选择地区
     */
    public static void goAddress(Activity activity) {
        Intent intent = new Intent(activity, SelectAddresDialogActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.out_to_left2, R.anim.in_from_right);
    }

    /**
     * 房产列表
     */
    private static void goHouseList(Activity activity, int type) {
        Intent intent = new Intent(activity, HouseListActivity.class);
        intent.putExtra(Constants.Type, type);
        activity.startActivity(intent);
    }

    public static void goHouseList_xinFang(Activity activity) {
        goHouseList(activity, 0);
    }

    public static void goHouseList_erShoufang(Activity activity) {
        goHouseList(activity, 1);
    }

    public static void goHouseList_zuFang(Activity activity) {
        goHouseList(activity, 2);
    }


    //租房详情页
    public static void goHouseInfo(Activity activity, int houseId) {
        Intent intent = new Intent(activity, HouseInfoActivity.class);
        intent.putExtra(Constants.id, houseId + "");
        activity.startActivity(intent);
    }

    //户型详情页
    public static void goHuXinInfo(Activity activity, int houseId) {
        Intent intent = new Intent(activity, HuXinListActivity.class);
        intent.putExtra(Constants.id, houseId + "");
        activity.startActivity(intent);
    }

    //添加报备
    public static void goAddBaoBei(Activity activity, int houseId) {
        Intent intent = new Intent(activity, AddBaoBeiActivity.class);
        intent.putExtra(Constants.id, houseId + "");
        activity.startActivity(intent);
    }

    //品牌门店
    public static void goMenDian(Activity activity, int id) {
        Intent intent = new Intent(activity, PinPaiMenDIanActivity.class);
        intent.putExtra(Constants.id, id + "");
        activity.startActivity(intent);
    }

    //去地图
    public static void goHouseMap(Activity activity, HouseMapModel model) {
        Intent intent = new Intent(activity, HouseMapActivity.class);
        intent.putExtra(Constants.Model, model);
        activity.startActivity(intent);
    }


    //贷款详情
    public static void goFinanceInfo(Activity activity, int id) {
        Intent intent = new Intent(activity, FinanceInfoActivity.class);
        intent.putExtra(Constants.id, id + "");
        activity.startActivity(intent);
    }
}
