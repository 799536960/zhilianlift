package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 2017/12/11.
 */

public class AddresListActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.tv_addres)
    TextView tvAddres;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_addres_list).setTopBar("地址管理");
    }


    @OnClick(R.id.tv_addres)
    public void onViewClicked() {
        startActivity(new Intent(mActivity, AddOrChangeActivity.class));
    }
}
