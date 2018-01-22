package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
import com.duma.ld.zhilianlift.widget.LinearImageLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

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

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_zufang_info).setLoadingOrErrorView_A(R.id.layout_root, R.id.layout_content);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        houseId = getIntent().getStringExtra(Constants.id);
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<HouseChuZuInfoModel>>get(gethoustInfo)
                .params("house_id", houseId)
                .execute(new MyJsonCallback<HttpResModel<HouseChuZuInfoModel>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<HouseChuZuInfoModel>> respons, HttpResModel<HouseChuZuInfoModel> houseChuZuInfoModelHttpResModel) {
                        initData(houseChuZuInfoModelHttpResModel.getResult());
                    }
                });
    }

    private void initData(HouseChuZuInfoModel result) {
        HouseChuZuInfoModel.HouseBean house = result.getHouse();
        tvName.setText(house.getHouse_name());
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
    }

}
