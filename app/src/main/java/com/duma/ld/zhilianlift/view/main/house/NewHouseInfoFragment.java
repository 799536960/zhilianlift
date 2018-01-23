package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HouseChuZuInfoModel;
import com.duma.ld.zhilianlift.model.HouseLabelBean;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.widget.TvNoNullText;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 新房 详情页
 * Created by liudong on 2018/1/22.
 */

public class NewHouseInfoFragment extends BaseMyFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_houseName)
    TextView tvHouseName;
    @BindView(R.id.tv_jianJie)
    TextView tvJianJie;
    @BindView(R.id.rv_biaoQian)
    RecyclerView rvBiaoQian;
    @BindView(R.id.tv_jiaGe)
    TvNoNullText tvJiaGe;
    @BindView(R.id.tv_louPanDiZhi)
    TvNoNullText tvLouPanDiZhi;
    @BindView(R.id.tv_zuiXinKaiPan)
    TvNoNullText tvZuiXinKaiPan;
    @BindView(R.id.tv_zaiShouHuXin)
    TvNoNullText tvZaiShouHuXin;
    @BindView(R.id.rv_huXin)
    RecyclerView rvHuXin;
    @BindView(R.id.rv_louPanZhaoPian)
    RecyclerView rvLouPanZhaoPian;
    @BindView(R.id.tv_louPanXinXi)
    TvNoNullText tvLouPanXinXi;
    @BindView(R.id.tv_louPanMinCheng)
    TvNoNullText tvLouPanMinCheng;
    @BindView(R.id.tv_zhanDiMianJi)
    TvNoNullText tvZhanDiMianJi;
    @BindView(R.id.tv_tingCheWei)
    TvNoNullText tvTingCheWei;
    @BindView(R.id.tv_jianZhuLeiBie)
    TvNoNullText tvJianZhuLeiBie;
    @BindView(R.id.tv_wuYeleiXin)
    TvNoNullText tvWuYeleiXin;
    @BindView(R.id.tv_rongJiLv)
    TvNoNullText tvRongJiLv;
    @BindView(R.id.tv_lvHuaLv)
    TvNoNullText tvLvHuaLv;
    @BindView(R.id.tv_chanQuanNianXian)
    TvNoNullText tvChanQuanNianXian;
    @BindView(R.id.tv_junGongShiJian)
    TvNoNullText tvJunGongShiJian;
    @BindView(R.id.tv_shouLouXuKe)
    TvNoNullText tvShouLouXuKe;
    @BindView(R.id.tv_kaiFaShang)
    TvNoNullText tvKaiFaShang;
    @BindView(R.id.tv_shouLouDiZhi)
    TvNoNullText tvShouLouDiZhi;
    @BindView(R.id.tv_huXin)
    TvNoNullText tvHuXin;
    @BindView(R.id.tv_louPanZhaoPian)
    TvNoNullText tvLouPanZhaoPian;
    @BindView(R.id.tv_zhouBian)
    TvNoNullText tvZhouBian;
    private HouseChuZuInfoModel model;

    public static NewHouseInfoFragment newInstance(HouseChuZuInfoModel model) {
        NewHouseInfoFragment fragment = new NewHouseInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Model, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_new_house_info, false);
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
        paint = tvHuXin.getPaint();
        paint.setFakeBoldText(true);
        paint = tvLouPanZhaoPian.getPaint();
        paint.setFakeBoldText(true);
        paint = tvZhouBian.getPaint();
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
        //标签
        BaseAdapter<HouseLabelBean> adapter = new BaseAdapter.Builder<HouseLabelBean>(rvBiaoQian, mActivity, R.layout.adapter_my_house_item)
                .setNoEnpty()
                .isNested()
                .setLayoutManager(new FlexboxLayoutManager(mActivity))
                .build(new OnBaseAdapterListener<HouseLabelBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, HouseLabelBean item) {
                        helper.setText(R.id.tv_name, item.getSo_name());
                    }
                });
        adapter.setNewData(model.getHouseLabel());
        rvBiaoQian.setAdapter(adapter);
        //房屋数据
        HouseChuZuInfoModel.HouseBean house = model.getHouse();
        tvLouPanDiZhi.setNewText(house.getHouse_address());
        tvZuiXinKaiPan.setNewText(ZhuanHuanUtil.Time2nian2(house.getStart_time() * 1000));
        //楼盘数据
        tvLouPanMinCheng.setNewText(house.getPremises_name());
        tvJianZhuLeiBie.setNewText(house.getArchitecture_type());
        tvChanQuanNianXian.setNewText(house.getProperty(), "年");
        tvTingCheWei.setNewText(house.getParkinglot());
        tvRongJiLv.setNewText(house.getVolume(), "%");
        tvLvHuaLv.setNewText(house.getGreen(), "%");
        tvKaiFaShang.setNewText(house.getDevelopers());
        tvWuYeleiXin.setNewText(house.getPurpose_name());
        if (house.getCompleted_time() == 0) {
            tvJunGongShiJian.setNewText("");
        } else {
            tvJunGongShiJian.setNewText(ZhuanHuanUtil.Time2nian2(house.getCompleted_time()));
        }
        tvZhanDiMianJi.setNewText("");
        tvShouLouXuKe.setNewText("");
        tvShouLouDiZhi.setNewText("");
    }

}
