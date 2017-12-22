package com.duma.ld.zhilianlift.view.main.shopping;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.Constants;

/**
 * Created by liudong on 2017/12/22.
 */

public class GoodsDetailsActivity extends BaseMyActivity {
    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_goods_details).setTopBar_A("商品详情");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        String id = getIntent().getStringExtra(Constants.id);
        TsUtils.show(id);
    }
}
