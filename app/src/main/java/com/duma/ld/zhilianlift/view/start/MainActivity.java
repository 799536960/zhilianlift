package com.duma.ld.zhilianlift.view.start;

import android.os.Bundle;
import android.widget.TextView;

import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.BaseMyActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author liudong
 */
public class MainActivity extends BaseMyActivity {
    @BindView(R.id.tv_btn)
    TextView tvBtn;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig
                .setLayoutIdByActivity(R.layout.activity_main)
                .setTopBar("首页", new OnTopBarLeftListener() {
                    @Override
                    public void onClick() {
                        mActivityConfig.showErrorView();
                        TsUtils.show("点击返回键");
                    }
                });
    }


    @OnClick(R.id.tv_btn)
    public void onViewClicked() {
        TsUtils.show("tv_btn");
    }

}
