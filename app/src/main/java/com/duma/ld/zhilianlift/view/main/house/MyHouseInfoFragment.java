package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.LinearLayout;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HouseChuZuInfoModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.widget.TvNoNullText;
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
    TvNoNullText tvHouseName;
    @BindView(R.id.tv_jiaGe_title)
    TvNoNullText tvJiaGeTitle;
    @BindView(R.id.tv_jiaGe)
    TvNoNullText tvJiaGe;
    @BindView(R.id.tv_huXin)
    TvNoNullText tvHuXin;
    @BindView(R.id.tv_mianji)
    TvNoNullText tvMianji;
    @BindView(R.id.tv_leixin)
    TvNoNullText tvLeixin;
    @BindView(R.id.tv_xinBie)
    TvNoNullText tvXinBie;
    @BindView(R.id.layout_xinBie)
    LinearLayout layoutXinBie;
    @BindView(R.id.layout_leixin)
    LinearLayout layoutLeixin;
    @BindView(R.id.tv_zhuangXiu)
    TvNoNullText tvZhuangXiu;
    @BindView(R.id.tv_nianDai)
    TvNoNullText tvNianDai;
    @BindView(R.id.tv_chaoXiang)
    TvNoNullText tvChaoXiang;
    @BindView(R.id.tv_louCheng)
    TvNoNullText tvLouCheng;
    @BindView(R.id.tv_sheShi)
    TvNoNullText tvSheShi;
    @BindView(R.id.tv_louPanXinXi)
    TvNoNullText tvLouPanXinXi;
    @BindView(R.id.tv_xiaoQu_title)
    TvNoNullText tvXiaoQuTitle;
    @BindView(R.id.tv_xiaoQu)
    TvNoNullText tvXiaoQu;
    @BindView(R.id.tv_jiaoTong_title)
    TvNoNullText tvJiaoTongTitle;
    @BindView(R.id.tv_jiaoTong)
    TvNoNullText tvJiaoTong;
    @BindView(R.id.tv_zhouBian_title)
    TvNoNullText tvZhouBianTitle;
    @BindView(R.id.tv_zhouBian)
    TvNoNullText tvZhouBian;
    @BindView(R.id.tv_louPanMinCheng)
    TvNoNullText tvLouPanMinCheng;
    @BindView(R.id.tv_wuYeleiXin)
    TvNoNullText tvWuYeleiXin;
    @BindView(R.id.tv_jianZhuLeiBie)
    TvNoNullText tvJianZhuLeiBie;
    @BindView(R.id.tv_chanQuanNianXian)
    TvNoNullText tvChanQuanNianXian;
    @BindView(R.id.tv_tingCheWei)
    TvNoNullText tvTingCheWei;
    @BindView(R.id.tv_rongJiLv)
    TvNoNullText tvRongJiLv;
    @BindView(R.id.tv_lvHuaLv)
    TvNoNullText tvLvHuaLv;
    @BindView(R.id.tv_kaiFaShang)
    TvNoNullText tvKaiFaShang;
    @BindView(R.id.tv_junGongShiJian)
    TvNoNullText tvJunGongShiJian;
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
                tvJiaGeTitle.setNewText("售价");
                tvJiaGe.setNewText(house.getAllprice() + "万元");
                break;
            case 3:
                //租房
                tvJiaGeTitle.setNewText("租金");
                tvJiaGe.setNewText(house.getRent() + "元/月");
                break;
        }
        /*
          房屋数据
         */
        tvHouseName.setNewText(house.getSynopsis());
        tvHuXin.setNewText(house.getDoor_door() + "室" + house.getOffice() + "厅" + house.getToilet() + "卫");
        tvMianji.setNewText(house.getArchitecture() + "m²");
        tvLeixin.setNewText(house.getLease_type_string());
        tvXinBie.setNewText(house.getSex_string());
        tvLouCheng.setNewText(house.getFloor() + "楼/共" + house.getFloorall() + "层");
        tvSheShi.setNewText(house.getFacilities());
        //可以不填
        tvZhuangXiu.setNewText(house.getRenovation());
        tvNianDai.setNewText(house.getYears(), "年");
        tvChaoXiang.setNewText(house.getOrientation());
        tvXiaoQu.setNewText(house.getVillage());
        tvJiaoTong.setNewText(house.getTraffic());
        tvZhouBian.setNewText(house.getPeriphery());

        /*
          楼盘信息
         */
        tvLouPanMinCheng.setNewText(house.getPremises_name());
        tvJianZhuLeiBie.setNewText(house.getArchitecture_type());
        tvChanQuanNianXian.setNewText(house.getProperty(), "年");
        tvTingCheWei.setNewText(house.getParkinglot());
        tvRongJiLv.setNewText(house.getVolume(), "%");
        tvLvHuaLv.setNewText(house.getGreen(), "%");
        tvKaiFaShang.setNewText(house.getDevelopers());
        tvWuYeleiXin.setNewText(house.getPurpose());
        if (house.getCompleted_time() == 0) {
            tvJunGongShiJian.setNewText("");
        } else {
            tvJunGongShiJian.setNewText(ZhuanHuanUtil.Time2nian2(house.getCompleted_time()));
        }
    }
}
