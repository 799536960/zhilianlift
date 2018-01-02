package com.duma.ld.zhilianlift.view.main.shopping;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;

/**
 * Created by liudong on 2018/1/2.
 */

public class ShoppingCartActivity extends BaseMyActivity {
    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_shopping_cart);
    }
}
