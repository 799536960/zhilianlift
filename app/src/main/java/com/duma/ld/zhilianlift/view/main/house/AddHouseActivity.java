package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.util.StringUtils;
import com.duma.ld.baselibrary.base.OnTopBarRightListener;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.MyViewPagerAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HouseAddressModel;
import com.duma.ld.zhilianlift.model.HouseChuZuInfoModel;
import com.duma.ld.zhilianlift.model.HouseHttpInfoModel;
import com.duma.ld.zhilianlift.model.HouseHttpModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.editlease;
import static com.duma.ld.zhilianlift.util.HttpUrl.editsecond;
import static com.duma.ld.zhilianlift.util.HttpUrl.lease;
import static com.duma.ld.zhilianlift.util.HttpUrl.second;

/**
 * 添加出租房或者二手房
 * 有值就是二手房
 * Created by liudong on 2018/1/9.
 */

public class AddHouseActivity extends BaseMyActivity implements OnTopBarRightListener {
    @BindView(R.id.layout_tablayout)
    TabLayout layoutTablayout;
    @BindView(R.id.viewPager_content)
    ViewPager viewPagerContent;
    //是不是二手房
    private boolean isChuZU;
    private HouseHttpModel model;
    private HouseChuZuInfoModel houseChuZuInfoModel;


    public HouseChuZuInfoModel getHouseChuZuInfoModel() {
        return houseChuZuInfoModel;
    }

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_house, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        model = new HouseHttpModel();
        isChuZU = getIntent().getBooleanExtra(Constants.Type, false);
        houseChuZuInfoModel = (HouseChuZuInfoModel) getIntent().getSerializableExtra(Constants.Model);
        if (isEdit()) {
            //编辑
            initEdit();
        }
        if (isChuZU) {
            mActivityConfig.setTopBar_A("我要出租", "提交", this);
            model.setRental(true);
        } else {
            mActivityConfig.setTopBar_A("我要出售", "提交", this);
            model.setRental(false);
        }
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(AddHouseInfoFragment.newInstance(model), "房屋信息");
        viewPagerAdapter.addFragment(AddHouseImageFragment.newInstance(model), "房屋图片");
        viewPagerAdapter.addFragment(AddHouseOtherFragment.newInstance(model), "配套补充");
        viewPagerContent.setAdapter(viewPagerAdapter);
        viewPagerContent.setOffscreenPageLimit(3);
        layoutTablayout.setupWithViewPager(viewPagerContent);
    }

    public boolean isEdit() {
        if (houseChuZuInfoModel != null) {
            //编辑
            return true;
        }
        return false;
    }

    private void initEdit() {
        HouseChuZuInfoModel.HouseBean house = houseChuZuInfoModel.getHouse();
        model.setFangWuMinCheng(house.getHouse_name());
        model.setXiaoQuGaiKuang(house.getVillage());
        model.setJiaoTongZhuangKuang(house.getTraffic());
        model.setZhouBianPeiTao(house.getPeriphery());
        model.setFangWuJianJie(house.getSynopsis());
        model.setXiangXiDiZhi(house.getHouse_address());
        model.setJiShi(house.getDoor_door());
        model.setJiTing(house.getOffice());
        model.setJiWei(house.getToilet());
        model.setJiLou(house.getFloor());
        model.setGongJiCeng(house.getFloorall());
        model.setJianZhuMianJi(house.getArchitecture());
        model.setZuJing(house.getRent());
        model.setShouJia(house.getAllprice());
        model.setFangWuZhuangXiu(house.getRenovation());
        model.setNianDai(house.getYears());
        model.setFangWuChaoXiang(house.getOrientation());
        model.setXinMing(house.getUser_name());
        model.setLianXiDianHua(house.getHouse_telephone());
        model.setLouPanMinCheng(house.getPremises_name());
        model.setJianZhuLieBie(house.getArchitecture_type());
        model.setChanQuanNianXian(house.getProperty());
        model.setTingCheWei(house.getParkinglot());
        model.setRongJiLv(house.getVolume());
        model.setLvHuaLv(house.getGreen());
        model.setJunGongShiJian(house.getCompleted_time() + "");
        model.setKaiFaShang(house.getDevelopers());
        model.setAddresModel(new HouseAddressModel(house.getTotal_house_address1(), house.getProvince_id(), house.getCity_id(), house.getDistrict()));

        model.setChuZuleiXin(house.getLease_type());
        model.setXinBieYaoQiu(house.getSex());
        int laiYuan = 0;
        try {
            laiYuan = Integer.parseInt(house.getSource());
        } catch (NumberFormatException e) {
            TsUtils.show("转化异常");
        }
        model.setLaiYuan(laiYuan);

        model.setWuYeLeiXinModel(new HouseHttpInfoModel.FilterAttrBean.ItemBean(house.getPurpose_name(), house.getPurpose()));
        model.setFuKuanFangShiModel(new HouseHttpInfoModel.FilterAttrBean.ItemBean(house.getPayment()));
    }

    @Override
    public void onClick() {
        if (model.getList() == null || model.getList().size() == 0) {
            TsUtils.show("请至少选择一张房屋图片!");
            if (viewPagerContent.getCurrentItem() != 1) {
                viewPagerContent.setCurrentItem(1);
            }
            return;
        }
        if (StringUtils.isEmpty(model.getFangWuMinCheng())) {
            TsUtils.show("请输入房屋名称!");
            return;
        }
        if (StringUtils.isEmpty(model.getLouPanMinCheng())) {
            TsUtils.show("请输入楼盘名称!");
            return;
        }
        if (model.getWuYeLeiXinModel() == null) {
            TsUtils.show("请选择物业类型!");
            return;
        }
        if (model.getAddresModel() == null) {
            TsUtils.show("请选择地区!");
            return;
        }
        if (StringUtils.isEmpty(model.getXiangXiDiZhi())) {
            TsUtils.show("请输入详细地址!");
            return;
        }
        if (StringUtils.isEmpty(model.getJiShi())) {
            TsUtils.show("请输入户型!");
            return;
        }
        if (StringUtils.isEmpty(model.getJiTing())) {
            TsUtils.show("请输入户型!");
            return;
        }
        if (StringUtils.isEmpty(model.getJiWei())) {
            TsUtils.show("请输入户型!");
            return;
        }
        if (StringUtils.isEmpty(model.getJiLou())) {
            TsUtils.show("请输入楼层信息!");
            return;
        }
        if (StringUtils.isEmpty(model.getGongJiCeng())) {
            TsUtils.show("请输入楼层信息!");
            return;
        }
        if (StringUtils.isEmpty(model.getJianZhuMianJi())) {
            TsUtils.show("请输入建筑面积!");
            return;
        }
        if (StringUtils.isEmpty(model.getXinMing())) {
            TsUtils.show("请输入姓名!");
            return;
        }
        if (StringUtils.isEmpty(model.getLianXiDianHua())) {
            TsUtils.show("请输入联系电话!");
            return;
        }
        if (model.getmListTeSe() != null) {
            boolean kong = true;
            for (int i = 0; i < model.getmListTeSe().size(); i++) {
                if (model.getmListTeSe().get(i).isCheck()) {
                    kong = false;
                }
            }
            if (kong) {
                TsUtils.show("请至少选择一种房屋特色!");
                return;
            }
        }
        if (model.isRental()) {
            //验证出租信息
            if (model.getmListSheShi() != null) {
                boolean kong = true;
                for (int i = 0; i < model.getmListSheShi().size(); i++) {
                    if (model.getmListSheShi().get(i).isCheck()) {
                        kong = false;
                    }
                }
                if (kong) {
                    TsUtils.show("请至少选择一种房屋设施!");
                    return;
                }
            }
            if (model.getFuKuanFangShiModel() == null) {
                TsUtils.show("请选择付款方式!");
                return;
            }
            if (StringUtils.isEmpty(model.getZuJing())) {
                TsUtils.show("请输入租金!");
                return;
            }
        } else {
            if (StringUtils.isEmpty(model.getShouJia())) {
                TsUtils.show("请输入售价!");
                return;
            }
        }
        Logger.e(new Gson().toJson(model));
        DialogUtil.getInstance().show_noBack(mActivity, "上传图片中...");
        if (model.isRental()) {
            //出租
            chuZuHttp();
        } else {
            //出售
            chuShouHttp();
        }

    }

    private void chuShouHttp() {
        PostRequest<HttpResModel<String>> params;
        if (isEdit()) {
            params = OkGo.<HttpResModel<String>>post(editsecond)
                    .tag(httpTag);
            params.params("house_id_edit", houseChuZuInfoModel.getHouse().getHouse_id());
        } else {
            params = OkGo.<HttpResModel<String>>post(second)
                    .tag(httpTag);
        }
        setPublicParam(params);
        params.params("house_name", model.getFangWuMinCheng())
                .params("synopsis", model.getFangWuJianJie())
                .params("purpose", model.getWuYeLeiXinModel().getSo_value())
                .params("premises_name", model.getLouPanMinCheng())
                .params("house_address", model.getXiangXiDiZhi())
                .params("door_door", model.getJiShi())
                .params("floor", model.getJiLou())
                .params("floorall", model.getGongJiCeng())
                .params("architecture", model.getJianZhuMianJi())
                .params("allprice", model.getShouJia())
                .params("renovation", model.getFangWuZhuangXiu())
                .params("years", model.getNianDai())
                .params("orientation", model.getFangWuChaoXiang())
                .params("source", model.getLaiYuan())
                .params("house_telephone", model.getLianXiDianHua())
                .params("user_name", model.getXinMing())
                .params("village", model.getXiaoQuGaiKuang())
                .params("traffic", model.getJiaoTongZhuangKuang())
                .params("periphery", model.getZhouBianPeiTao())
                .params("toilet", model.getJiWei())
                .params("office", model.getJiTing());
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < model.getmListTeSe().size(); i++) {
            if (model.getmListTeSe().get(i).isCheck()) {
                strings.add(model.getmListTeSe().get(i).getSo_value());
            }
        }
        params.addUrlParams("characteristics[]", strings)
                .addFileParams("house_img[]", model.getList())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        TsUtils.show("提交成功!");
                        finish();
                    }
                });
    }

    private void setPublicParam(PostRequest<HttpResModel<String>> request) {
        request.params("architecture_type", model.getJianZhuLieBie())
                .params("property", model.getChanQuanNianXian())
                .params("parkinglot", model.getTingCheWei())
                .params("volume", model.getRongJiLv())
                .params("green", model.getLvHuaLv())
                .params("completed_time", model.getJunGongShiJian())
                .params("developers", model.getKaiFaShang())
                .params("province_id", model.getAddresModel().getProvince_id())
                .params("city_id", model.getAddresModel().getCity_id())
                .params("district", model.getAddresModel().getDistrict());
    }

    private void chuZuHttp() {
        PostRequest<HttpResModel<String>> params;
        if (isEdit()) {
            params = OkGo.<HttpResModel<String>>post(editlease)
                    .tag(httpTag);
            params.params("house_id_edit", houseChuZuInfoModel.getHouse().getHouse_id());
        } else {
            params = OkGo.<HttpResModel<String>>post(lease)
                    .tag(httpTag);
        }
        setPublicParam(params);
        params.params("house_name", model.getFangWuMinCheng())
                .params("synopsis", model.getFangWuJianJie())
                .params("purpose", model.getWuYeLeiXinModel().getSo_value())
                .params("premises_name", model.getLouPanMinCheng())
                .params("house_address", model.getXiangXiDiZhi())
                .params("lease_type", model.getChuZuleiXin())//出租类型
                .params("door_door", model.getJiShi())
                .params("floor", model.getJiLou())
                .params("floorall", model.getGongJiCeng())
                .params("architecture", model.getJianZhuMianJi())
                .params("rent", model.getZuJing())
                .params("payment", model.getFuKuanFangShiModel().getSo_name())//付款方式
                .params("renovation", model.getFangWuZhuangXiu())
                .params("years", model.getNianDai())
                .params("orientation", model.getFangWuChaoXiang())
                .params("source", model.getLaiYuan())
                .params("house_telephone", model.getLianXiDianHua())
                .params("user_name", model.getXinMing())
                .params("village", model.getXiaoQuGaiKuang())
                .params("traffic", model.getJiaoTongZhuangKuang())
                .params("periphery", model.getZhouBianPeiTao())
                .params("toilet", model.getJiWei())
                .params("office", model.getJiTing())
                .params("sex", model.getXinBieYaoQiu());

        String sheshi = "";
        for (int i = 0; i < model.getmListSheShi().size(); i++) {
            if (model.getmListSheShi().get(i).isCheck()) {
                if (sheshi.isEmpty()) {
                    sheshi = model.getmListSheShi().get(i).getSo_name();
                } else {
                    sheshi = sheshi + "," + model.getmListSheShi().get(i).getSo_name();
                }
            }
        }
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < model.getmListTeSe().size(); i++) {
            if (model.getmListTeSe().get(i).isCheck()) {
                strings.add(model.getmListTeSe().get(i).getSo_value());
            }
        }
        params.params("facilities", sheshi)
                .addUrlParams("characteristics[]", strings)
                .addFileParams("house_img[]", model.getList())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        TsUtils.show("提交成功!");
                        finish();
                    }
                });
    }
}
