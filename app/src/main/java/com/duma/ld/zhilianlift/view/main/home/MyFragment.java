package com.duma.ld.zhilianlift.view.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.view.main.wode.SettingActivity;
import com.duma.ld.zhilianlift.widget.LinearImageLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
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
    FrameLayout layoutUser;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_my);
    }

    @OnClick({R.id.layout_user, R.id.layout_setting, R.id.layout_messgae, R.id.layout_order_daifukuan, R.id.layout_order_daishouhuo, R.id.layout_order_daipinjia, R.id.layout_order_shouhou, R.id.layout_order, R.id.layout_money_zhuangxiuzijin, R.id.layout_money_youhuijuan, R.id.layout_money_yu_e, R.id.layout_money_jifen, R.id.layout_wode_daikuan, R.id.layout_wode_baobei, R.id.layout_wode_chuzu, R.id.layout_wode_ershoufang, R.id.layout_wode_shoucang, R.id.layout_wode_jilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_setting:
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
            case R.id.layout_messgae:
                break;
            case R.id.layout_order_daifukuan:
                break;
            case R.id.layout_order_daishouhuo:
                break;
            case R.id.layout_order_daipinjia:
                break;
            case R.id.layout_order_shouhou:
                break;
            case R.id.layout_order:
                break;
            case R.id.layout_money_zhuangxiuzijin:
                break;
            case R.id.layout_money_youhuijuan:
                break;
            case R.id.layout_money_yu_e:
                break;
            case R.id.layout_money_jifen:
                break;
            case R.id.layout_wode_daikuan:
                break;
            case R.id.layout_wode_baobei:
                break;
            case R.id.layout_wode_chuzu:
                break;
            case R.id.layout_wode_ershoufang:
                break;
            case R.id.layout_wode_shoucang:
                break;
            case R.id.layout_wode_jilu:
                break;
            case R.id.layout_user:
                break;
        }
    }
}
