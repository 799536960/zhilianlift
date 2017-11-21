package com.duma.ld.zhilianlift;

import android.os.Bundle;
import android.view.View;

import com.duma.ld.baselibrary.base.BaseActivity;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.util.ActivityConfig;
import com.duma.ld.baselibrary.util.TypeConfig;

public class MainActivity extends BaseActivity {

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, TypeConfig typeConfig) {
        return typeConfig
                .setLayoutId(R.layout.activity_main, false)
                .setTopBar("首页", new OnTopBarLeftListener() {
                    @Override
                    public void onClick() {
                        mActivityConfig.setShowError(true);
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
}
