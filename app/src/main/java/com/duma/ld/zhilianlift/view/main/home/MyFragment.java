package com.duma.ld.zhilianlift.view.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.UserModel;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.main.pay.HousePayListActivity;
import com.duma.ld.zhilianlift.view.main.pay.PointsPayListActivity;
import com.duma.ld.zhilianlift.view.main.pay.YuEListActivity;
import com.duma.ld.zhilianlift.view.main.shopping.afterSales.AfterSalesListActivity;
import com.duma.ld.zhilianlift.view.main.wode.MyFinanceActivity;
import com.duma.ld.zhilianlift.view.main.wode.SettingActivity;
import com.duma.ld.zhilianlift.view.main.wode.UserDataActivity;
import com.duma.ld.zhilianlift.view.main.wode.WoDeBaoBeiActivity;
import com.duma.ld.zhilianlift.widget.LinearImageLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.userInfo;

/**
 * 我的
 * Created by liudong on 2017/11/29.
 */

public class MyFragment extends BaseMyFragment {
    @BindView(R.id.layout_setting)
    FrameLayout layoutSetting;
    @BindView(R.id.layout_messgae)
    LinearImageLayout layoutMessgae;
    @BindView(R.id.layout_order_daifukuan)
    LinearImageLayout layoutOrderDaifukuan;
    @BindView(R.id.layout_order_daishouhuo)
    LinearImageLayout layoutOrderDaishouhuo;
    @BindView(R.id.layout_order_daipinjia)
    LinearImageLayout layoutOrderDaipinjia;
    @BindView(R.id.layout_order_shouhou)
    LinearImageLayout layoutOrderShouhou;
    @BindView(R.id.layout_order)
    LinearImageLayout layoutOrder;
    @BindView(R.id.layout_money_zhuangxiuzijin)
    LinearLayout layoutMoneyZhuangxiuzijin;
    @BindView(R.id.layout_money_youhuijuan)
    LinearLayout layoutMoneyYouhuijuan;
    @BindView(R.id.layout_money_yu_e)
    LinearLayout layoutMoneyYuE;
    @BindView(R.id.layout_money_jifen)
    LinearLayout layoutMoneyJifen;
    @BindView(R.id.layout_wode_daikuan)
    LinearLayout layoutWodeDaikuan;
    @BindView(R.id.layout_wode_baobei)
    LinearLayout layoutWodeBaobei;
    @BindView(R.id.layout_wode_chuzu)
    LinearLayout layoutWodeChuzu;
    @BindView(R.id.layout_wode_ershoufang)
    LinearLayout layoutWodeErshoufang;
    @BindView(R.id.layout_wode_shoucang)
    LinearLayout layoutWodeShoucang;
    @BindView(R.id.layout_wode_jilu)
    LinearLayout layoutWodeJilu;
    @BindView(R.id.layout_user)
    LinearLayout layoutUser;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_Nick_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_zhuangXiuZiJin)
    TextView tvZhuangXiuZiJin;
    @BindView(R.id.tv_youHuiJuan)
    TextView tvYouHuiJuan;
    @BindView(R.id.tv_yuE)
    TextView tvYuE;
    @BindView(R.id.tv_jiFen)
    TextView tvJiFen;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_my, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        swLoading.setColorSchemeColors(ContextCompat.getColor(mActivity, com.duma.ld.baselibrary.R.color.textColor));
        swLoading.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (SpDataUtil.isLogin()) {
                    getUserDataHttp();
                } else {
                    swLoading.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        refreshUserData();
        if (SpDataUtil.isLogin()) {
            getUserDataHttp();
        }
    }

    private void refreshUserData() {
        UserModel user = SpDataUtil.getUser();
        if (user != null) {
            //登录后
//            Logger.e("user: " + user.toString());
            ImageLoader.with_head(user.getHead_pic(), imgIcon);
            tvPhone.setVisibility(View.VISIBLE);
            tvName.setText(user.getNickname());
            tvPhone.setText("用户名:" + user.getMobile_xx());
            tvZhuangXiuZiJin.setText(user.getRenovation_money());
            tvYouHuiJuan.setText(user.getCoupon_count());
            tvYuE.setText(user.getUser_money());
            tvJiFen.setText(user.getPay_points() + "");
            layoutMessgae.setNum(user.getNews());
            layoutOrderDaifukuan.setNum(user.getWaitPay());
            layoutOrderDaishouhuo.setNum(user.getWaitReceive());
            layoutOrderDaipinjia.setNum(user.getWaitcomment());
            layoutOrderShouhou.setNum(user.getReturn_count());
        } else {
            Logger.e("user: null");
            imgIcon.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.img_60));
            //没有登录 初始化数据
            tvPhone.setVisibility(View.GONE);
            tvName.setText("未登录");
            tvZhuangXiuZiJin.setText("0");
            tvYouHuiJuan.setText("0");
            tvYuE.setText("0");
            tvJiFen.setText("0");
            layoutMessgae.setNum("0");
            layoutOrderDaifukuan.setNum("0");
            layoutOrderDaishouhuo.setNum("0");
            layoutOrderDaipinjia.setNum("0");
            layoutOrderShouhou.setNum("0");
        }
    }

    private void getUserDataHttp() {
        swLoading.setRefreshing(true);
        OkGo.<HttpResModel<UserModel>>get(userInfo)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<UserModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<UserModel>> respons, HttpResModel<UserModel> userModelHttpResModel) {
                        swLoading.setRefreshing(false);
                        SpDataUtil.setUser(userModelHttpResModel.getResult());
                        refreshUserData();
                    }

                    @Override
                    public void onError(Response<HttpResModel<UserModel>> response) {
                        super.onError(response);
                        swLoading.setRefreshing(false);
                    }
                });
    }


    @OnClick({R.id.layout_user, R.id.layout_setting, R.id.layout_messgae, R.id.layout_order_daifukuan, R.id.layout_order_daishouhuo, R.id.layout_order_daipinjia, R.id.layout_order_shouhou, R.id.layout_order, R.id.layout_money_zhuangxiuzijin, R.id.layout_money_youhuijuan, R.id.layout_money_yu_e, R.id.layout_money_jifen, R.id.layout_wode_daikuan, R.id.layout_wode_baobei, R.id.layout_wode_chuzu, R.id.layout_wode_ershoufang, R.id.layout_wode_shoucang, R.id.layout_wode_jilu})
    public void onViewClicked(View view) {
        if (!SpDataUtil.isLogin()) {
            IntentUtil.goLogin(mActivity);
            return;
        }
        switch (view.getId()) {
            case R.id.layout_setting:
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
            case R.id.layout_messgae:
                IntentUtil.goMessage(mActivity);
                break;
            case R.id.layout_order_daifukuan:
                IntentUtil.goOrderList(mActivity, 1);
                break;
            case R.id.layout_order_daishouhuo:
                IntentUtil.goOrderList(mActivity, 3);
                break;
            case R.id.layout_order_daipinjia:
                IntentUtil.goOrderList(mActivity, 4);
                break;
            case R.id.layout_order_shouhou:
                startActivity(new Intent(mActivity, AfterSalesListActivity.class));
                break;
            case R.id.layout_order:
                IntentUtil.goOrderList(mActivity);
                break;
            case R.id.layout_money_zhuangxiuzijin:
                startActivity(new Intent(mActivity, HousePayListActivity.class));
                break;
            case R.id.layout_money_youhuijuan:
                IntentUtil.goCoupons(mActivity);
                break;
            case R.id.layout_money_yu_e:
                startActivity(new Intent(mActivity, YuEListActivity.class));
                break;
            case R.id.layout_money_jifen:
                startActivity(new Intent(mActivity, PointsPayListActivity.class));
                break;
            case R.id.layout_wode_daikuan:
                startActivity(new Intent(mActivity, MyFinanceActivity.class));
                break;
            case R.id.layout_wode_baobei:
                startActivity(new Intent(mActivity, WoDeBaoBeiActivity.class));
                break;
            case R.id.layout_wode_chuzu:
                IntentUtil.goRental(mActivity);
                break;
            case R.id.layout_wode_ershoufang:
                IntentUtil.goSecondHouse(mActivity);
                break;
            case R.id.layout_wode_shoucang:
                IntentUtil.goMyCollect(mActivity);
                break;
            case R.id.layout_wode_jilu:
                IntentUtil.goMyRecord(mActivity);
                break;
            case R.id.layout_user:
//                IntentUtil.goUserData(mActivity);
                Intent intent = new Intent(mActivity, UserDataActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(mActivity,
                                imgIcon, "testImg");
                startActivity(intent, options.toBundle());
                break;
        }
    }

}
