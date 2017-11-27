package com.duma.ld.zhilianlift;

import android.os.Bundle;
import android.view.View;

import com.duma.ld.baselibrary.base.BaseActivity;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.util.ActivityConfig;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.TypeConfig;
import com.orhanobut.logger.Logger;


/**
 * @author liudong
 */
public class MainActivity extends BaseActivity {
    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, TypeConfig typeConfig) {
        return typeConfig
                .setLayoutId(R.layout.activity_main)
                .setTopBar("首页", new OnTopBarLeftListener() {
                    @Override
                    public void onClick() {
                        Logger.e("首页");
                        mActivityConfig.setShowError(true);
                        TsUtils.show("首页");
                    }
                })
                .end();
    }


    @Override
    protected void init() {
        findViewById(R.id.tv_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityConfig.setShowLoading(true);
            }
        });

    }

    @Override
    public void onLoadingRefresh() {
    }
}
