package com.duma.ld.zhilianlift.view.login;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.widget.DragSeekBarView;

import butterknife.BindView;

/**
 * Created by liudong on 2017/12/7.
 */

public class CodeBarActivity extends BaseMyActivity {
    @BindView(R.id.drag_sb)
    DragSeekBarView dragSb;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_codebar, false).setTopBar_A("仿盗刷校验");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        dragSb.setOnMaxProgress(new DragSeekBarView.onMaxProgress() {
            @Override
            public void onMax() {
                EventBusUtil.sendModel(Constants.event_codeMax_success);
                finish();
            }
        });
    }
}
