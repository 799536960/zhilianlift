package com.duma.ld.zhilianlift.view.home;

import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.BaseMyFragment;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.LocationUtil;
import com.duma.ld.zhilianlift.util.PermissionUtil;

import butterknife.BindView;

/**
 * Created by liudong on 2017/11/29.
 */

public class HomeFragment extends BaseMyFragment {

    @BindView(R.id.tv_city)
    TextView tvCity;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_home);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == Constants.event_location) {
            mFragmentConfig.hideLoadingView();
            tvCity.setText(SPUtils.getInstance().getString(Constants.sp_city, "获取错误"));
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mFragmentConfig.showLoadingView();
        //获取权限
        PermissionUtil permissionUtil = new PermissionUtil(mActivity, new PermissionUtil.onPermissionListener() {
            @Override
            public void onResult(int requestCode, boolean result) {
                if (result) {
                    //开启定位
                    LocationUtil.getInstance().start();
                } else {
                    //给一个默认
                    mFragmentConfig.hideLoadingView();
                    tvCity.setText("湖州");
                }
            }

        });
        permissionUtil.openLocation();
    }
}
