package com.duma.ld.zhilianlift.view.main.house;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.StringUtils;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HouseChuZuInfoModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.main.home.MainActivity;
import com.duma.ld.zhilianlift.view.popupWindow.GoodsInfoPopupWindow;
import com.duma.ld.zhilianlift.widget.LinearImageLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.collectHouseOrNo;
import static com.duma.ld.zhilianlift.util.HttpUrl.gethoustInfo;

/**
 * 房产 详情
 * Created by liudong on 2018/1/19.
 */

public class HouseInfoActivity extends BaseMyActivity {
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
    @BindView(R.id.layout_fragment)
    FrameLayout layoutFragment;
    @BindView(R.id.layout_collect)
    LinearImageLayout layoutCollect;
    @BindView(R.id.layout_phone)
    TextView layoutPhone;
    @BindView(R.id.layout_content)
    LinearLayout layoutContent;
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;
    @BindView(R.id.layout_baobei)
    LinearImageLayout layoutBaobei;
    private String houseId;
    private GoodsInfoPopupWindow goodsInfoPopupWindow;
    private HouseChuZuInfoModel HouseBean;
    private AlertDialog dialog;


    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_zufang_info).setLoadingOrErrorView_A(R.id.layout_root, R.id.layout_content);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Intent intent = getIntent();
        houseId = intent.getStringExtra(Constants.id);
        if (StringUtils.isEmpty(houseId)) {
            Uri uri = intent.getData();
            if (uri != null) {
                houseId = uri.getQueryParameter(Constants.id);
            }
        }
        if (StringUtils.isEmpty(houseId)) {
            TsUtils.show("获取id异常");
            finish();
        }
        goodsInfoPopupWindow = new GoodsInfoPopupWindow(mActivity);
        goodsInfoPopupWindow.setMessageNum(SpDataUtil.getMessageNum());
        onClickLoadingRefresh();
    }


    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<HouseChuZuInfoModel>>get(gethoustInfo)
                .tag(httpTag)
                .params("house_id", houseId)
                .execute(new MyJsonCallback<HttpResModel<HouseChuZuInfoModel>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<HouseChuZuInfoModel>> respons, HttpResModel<HouseChuZuInfoModel> houseChuZuInfoModelHttpResModel) {
                        initData(houseChuZuInfoModelHttpResModel.getResult());
                    }
                });
    }

    private void initData(HouseChuZuInfoModel result) {
        HouseBean = result;
        String telephone = result.getHouse().getAdmin_telephone();
        if (StringUtils.isEmpty(telephone)) {
//            telephone = Constants.kefu;
            telephone = result.getHouse().getHouse_telephone();
        }
        final String finalTelephone = telephone;
        dialog = PublicUtil.getAlertDialog(mActivity, "确认拨打", "即将为您拨打 " + telephone)
                .setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PhoneUtils.dial(finalTelephone);
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false)
                .create();
        HouseChuZuInfoModel.HouseBean house = result.getHouse();
        tvName.setText(house.getPremises_nameNull());
        //判断什么房子
        switch (house.getHouse_type()) {
            case 1:
                //新房
                layoutBaobei.setVisibility(View.VISIBLE);
                loadRootFragment(R.id.layout_fragment, NewHouseInfoFragment.newInstance(result));
                break;
            case 2:
            case 3:
                layoutBaobei.setVisibility(View.GONE);
                loadRootFragment(R.id.layout_fragment, MyHouseInfoFragment.newInstance(result));
                break;
        }
        //是否收藏
        HouseChuZuInfoModel.CollectBean collect = result.getCollect();
        if (collect != null) {
            setShouCang(collect.getResult() + "");
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (!ActivityUtils.isActivityExistsInStack(MainActivity.class)) {
            IntentUtil.goMain(mActivity);
        }
    }

    @OnClick({R.id.layout_back, R.id.layout_share, R.id.layout_menu, R.id.layout_collect, R.id.layout_baobei, R.id.layout_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                return;
            case R.id.layout_share:
                if (HouseBean != null) {
                    PublicUtil.shareHouse(mActivity, HouseBean.getHouse().getHouse_name(), houseId);
                } else {
                    TsUtils.show("加载中");
                }
                return;
            case R.id.layout_menu:
                goodsInfoPopupWindow.showPopupWindow(R.id.layout_tobBar);
                return;
            case R.id.layout_phone:
                dialog.show();
                return;
        }
        if (!SpDataUtil.isLogin()) {
            IntentUtil.goLogin(mActivity);
            return;
        }
        switch (view.getId()) {
            case R.id.layout_collect:
                //收藏
                collectHouse();
                break;
            case R.id.layout_baobei:
                IntentUtil.goAddBaoBei(mActivity, houseId);
                break;

        }
    }

    private void collectHouse() {
        OkGo.getInstance().cancelTag(httpTag);
        OkGo.<HttpResModel<String>>get(collectHouseOrNo)
                .tag(httpTag)
                .params("house_id", HouseBean.getHouse().getHouse_id())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        TsUtils.show(stringHttpResModel.getMsg());
                        setShouCang(stringHttpResModel.getResult());
                    }
                });
    }

    private void setShouCang(String string) {
        if (string.equals("1")) {
            layoutCollect.setIcon(ZhuanHuanUtil.getDrawable(R.drawable.shoucang2));
        } else {
            layoutCollect.setIcon(ZhuanHuanUtil.getDrawable(R.drawable.shoucang));
        }
    }
}
