package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HouseChuZuInfoModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.widget.LinearImageLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.gethoustInfo;

/**
 * 租房 二手房 详情
 * Created by liudong on 2018/1/19.
 */

public class ZuFangInfoActivity extends BaseMyActivity {
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.layout_share)
    FrameLayout layoutShare;
    @BindView(R.id.layout_menu)
    FrameLayout layoutMenu;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.layout_tobBar)
    ConstraintLayout layoutTobBar;
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
    @BindView(R.id.layout_collect)
    LinearImageLayout layoutCollect;
    @BindView(R.id.layout_phone)
    TextView layoutPhone;
    private String houseId;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_zufang_info).setLoadingOrErrorView_A(R.id.layout_root, R.id.layout_content);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        houseId = getIntent().getStringExtra(Constants.id);
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
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<HouseChuZuInfoModel>>get(gethoustInfo)
                .params("house_id", houseId)
                .execute(new MyJsonCallback<HttpResModel<HouseChuZuInfoModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<HouseChuZuInfoModel>> respons, HttpResModel<HouseChuZuInfoModel> houseChuZuInfoModelHttpResModel) {
                        initData(houseChuZuInfoModelHttpResModel.getResult());
                    }
                });
    }

    private void initData(HouseChuZuInfoModel result) {
        HouseChuZuInfoModel.HouseBean house = result.getHouse();
        tvName.setText(house.getHouse_name());
        //banner
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < result.getHouseImagesList().size(); i++) {
            list.add(result.getHouseImagesList().get(i).getImage_url());
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
        //判断什么房子
        switch (house.getHouse_type()) {
            case 2:
                //二手房
                layoutLeixin.setVisibility(View.GONE);
                tvJiaGeTitle.setText("售价");
                break;
            case 3:
                //租房
                tvJiaGeTitle.setText("租金");
                break;
        }
    }
}
