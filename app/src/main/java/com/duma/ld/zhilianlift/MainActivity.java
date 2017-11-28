package com.duma.ld.zhilianlift;

import android.os.Bundle;
import android.widget.TextView;

import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.util.ActivityConfig;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.TypeConfig;
import com.duma.ld.zhilianlift.base.BaseMyActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author liudong
 */
public class MainActivity extends BaseMyActivity {
    @BindView(R.id.tv_btn)
    TextView tvBtn;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, TypeConfig typeConfig) {
        return typeConfig
                .setLayoutId(R.layout.activity_main)
                .setTopBar("首页", new OnTopBarLeftListener() {
                    @Override
                    public void onClick() {
                        Logger.e("首页");
                        mActivityConfig.showError(true);
                        TsUtils.show("首页");
                    }
                })
                .end();
    }

    @Override
    protected void init() {
//        findViewById(R.id.tv_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//        mActivityConfig.showLoading(true);
//            }
//        });

    }

    @Override
    public void onLoadingRefresh() {
    }


    @OnClick(R.id.tv_btn)
    public void onViewClicked() {
        TsUtils.show("tv_btn");
    }
}
