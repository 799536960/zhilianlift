package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HouseChuZuInfoModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 二手房 租房 详情页
 * Created by liudong on 2018/1/22.
 */

public class MyHouseInfoFragment extends BaseMyFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_houseName)
    TextView tvHouseName;
    @BindView(R.id.tv_jiaGe_title)
    TextView tvJiaGeTitle;
    @BindView(R.id.tv_jiaGe)
    TextView tvJiaGe;
    @BindView(R.id.tv_huXin)
    TextView tvHuXin;
    @BindView(R.id.tv_mianji)
    TextView tvMianji;
    @BindView(R.id.tv_leixin)
    TextView tvLeixin;
    @BindView(R.id.tv_xinBie)
    TextView tvXinBie;
    @BindView(R.id.layout_xinBie)
    LinearLayout layoutXinBie;
    @BindView(R.id.layout_leixin)
    LinearLayout layoutLeixin;
    @BindView(R.id.tv_zhuangXiu)
    TextView tvZhuangXiu;
    @BindView(R.id.tv_nianDai)
    TextView tvNianDai;
    @BindView(R.id.tv_chaoXiang)
    TextView tvChaoXiang;
    @BindView(R.id.tv_louCheng)
    TextView tvLouCheng;
    @BindView(R.id.tv_sheShi)
    TextView tvSheShi;
    @BindView(R.id.tv_louPanXinXi)
    TextView tvLouPanXinXi;
    @BindView(R.id.tv_xiaoQu_title)
    TextView tvXiaoQuTitle;
    @BindView(R.id.tv_xiaoQu)
    TextView tvXiaoQu;
    @BindView(R.id.tv_jiaoTong_title)
    TextView tvJiaoTongTitle;
    @BindView(R.id.tv_jiaoTong)
    TextView tvJiaoTong;
    @BindView(R.id.tv_zhouBian_title)
    TextView tvZhouBianTitle;
    @BindView(R.id.tv_zhouBian)
    TextView tvZhouBian;
    private HouseChuZuInfoModel model;

    public static MyHouseInfoFragment newInstance(HouseChuZuInfoModel model) {
        MyHouseInfoFragment fragment = new MyHouseInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Model, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_my_house_info, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            model = (HouseChuZuInfoModel) args.getSerializable(Constants.Model);
        } else {
            TsUtils.show("获取失败!");
            mActivity.finish();
        }
        //标题加粗
        TextPaint paint = tvHouseName.getPaint();
        paint.setFakeBoldText(true);
        paint = tvLouPanXinXi.getPaint();
        paint.setFakeBoldText(true);
        paint = tvXiaoQuTitle.getPaint();
        paint.setFakeBoldText(true);
        paint = tvJiaoTongTitle.getPaint();
        paint.setFakeBoldText(true);
        paint = tvZhouBianTitle.getPaint();
        paint.setFakeBoldText(true);
        //banner
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < model.getHouseImagesList().size(); i++) {
            list.add(model.getHouseImagesList().get(i).getImage_url());
        }
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                IntentUtil.goPhoto(mActivity, list, position);
            }
        });
        banner = PublicUtil.initBanner(banner)
                .setImages(list)
                .start();
        HouseChuZuInfoModel.HouseBean house = model.getHouse();
        //什么房
        switch (house.getHouse_type()) {
            case 2:
                //二手房
                layoutLeixin.setVisibility(View.GONE);
                tvJiaGeTitle.setText("售价");
//                tvJiaGe.setText();
                break;
            case 3:
                //租房
                tvJiaGeTitle.setText("租金");
                break;
        }
        tvHouseName.setText(house.getSynopsis() + "");

    }

}
