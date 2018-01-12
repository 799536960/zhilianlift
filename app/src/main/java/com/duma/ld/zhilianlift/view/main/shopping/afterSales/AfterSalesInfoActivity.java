package com.duma.ld.zhilianlift.view.main.shopping.afterSales;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;

/**
 * 售后详情
 * Created by liudong on 2018/1/12.
 */

public class AfterSalesInfoActivity extends BaseMyActivity {
    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_after_sales_info).setTopBar_A("退款详情");
    }
}
