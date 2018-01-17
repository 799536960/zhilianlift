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
    private boolean isSecondHouse;
    private HouseHttpModel model;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_house, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        isSecondHouse = false;
        if (getIntent().getStringExtra(Constants.key) != null) {
            isSecondHouse = true;
        }
        model = new HouseHttpModel();
        if (isSecondHouse) {
            mActivityConfig.setTopBar_A("我要出售", "提交", this);
            model.setRental(false);
        } else {
            mActivityConfig.setTopBar_A("我要出租", "提交", this);
            model.setRental(true);
        }
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(HouseInfoFragment.newInstance(model), "房屋信息");
        viewPagerAdapter.addFragment(HouseImageFragment.newInstance(model), "房屋图片");
        viewPagerAdapter.addFragment(HouseOtherFragment.newInstance(model), "配套补充");
        viewPagerContent.setAdapter(viewPagerAdapter);
        viewPagerContent.setOffscreenPageLimit(3);
        layoutTablayout.setupWithViewPager(viewPagerContent);
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
        if (model.getWuYeLeiXinModel() == null) {
            TsUtils.show("请选择物业类型!");
            return;
        }
        if (model.getAddresModel() == null) {
            TsUtils.show("请选择地区!");
            return;
        }
        if (StringUtils.isEmpty(model.getFangWuJianJie())) {
            TsUtils.show("请输入房屋简介!");
            return;
        }
        if (StringUtils.isEmpty(model.getLouPanMinCheng())) {
            TsUtils.show("请输入楼盘名称!");
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
        PostRequest<HttpResModel<String>> params = OkGo.<HttpResModel<String>>post(second)
                .tag(httpTag)
                .params("house_name", model.getFangWuMinCheng())
                .params("synopsis", model.getFangWuJianJie())
                .params("purpose", model.getWuYeLeiXinModel().getSo_value())
                .params("premises_name", model.getLouPanMinCheng())
                .params("province_id", model.getAddresModel().getProvinceModel().getId())
                .params("city_id", model.getAddresModel().getCityModel().getId())
                .params("district", model.getAddresModel().getDistrictModel().getId())
                .params("house_address", model.getXiangXiDiZhi())
                .params("door_door", model.getJiShi())
                .params("floor", model.getJiLou())
                .params("floorall", model.getGongJiCeng())
                .params("architecture", model.getJianZhuMianJi())
                .params("house_price", model.getShouJia())
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

    private void chuZuHttp() {
        PostRequest<HttpResModel<String>> params = OkGo.<HttpResModel<String>>post(lease)
                .tag(httpTag)
                .params("house_name", model.getFangWuMinCheng())
                .params("synopsis", model.getFangWuJianJie())
                .params("purpose", model.getWuYeLeiXinModel().getSo_value())
                .params("premises_name", model.getLouPanMinCheng())
                .params("province_id", model.getAddresModel().getProvinceModel().getId())
                .params("city_id", model.getAddresModel().getCityModel().getId())
                .params("district", model.getAddresModel().getDistrictModel().getId())
                .params("house_address", model.getXiangXiDiZhi())
                .params("lease_type", model.getChuZuleiXin())//出租类型
                .params("door_door", model.getJiShi())
                .params("floor", model.getJiLou())
                .params("floorall", model.getGongJiCeng())
                .params("architecture", model.getJianZhuMianJi())
                .params("house_price", model.getZuJing())
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
        params.params("facilities", sheshi)
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
