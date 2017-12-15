package com.duma.ld.zhilianlift.view.main.wode;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.kefu;
import static com.duma.ld.zhilianlift.util.Constants.youxiang;


/**
 * Created by liudong on 2017/6/27.
 */

public class GuanYuActivity extends BaseMyActivity {
    @BindView(R.id.tv_dianhua)
    TextView tvDianhua;
    @BindView(R.id.layout_dianhua)
    LinearLayout layoutDianhua;
    @BindView(R.id.tv_banben)
    TextView tvBanben;
    @BindView(R.id.tv_youxiang)
    TextView tvYouxiang;
    @BindView(R.id.layout_youxiang)
    LinearLayout layoutYouxiang;


    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_guanyu, false).setTopBar_A("关于");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        tvDianhua.setText(kefu);
        tvYouxiang.setText(youxiang);
        tvBanben.setText("v" + AppUtils.getAppVersionName());
    }

    @OnClick({R.id.tv_banben, R.id.layout_dianhua})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_dianhua:
                PhoneUtils.dial(tvDianhua.getText().toString());
                break;
            case R.id.tv_banben:
                break;
        }
    }

    @OnClick(R.id.layout_youxiang)
    public void onClick() {
    }


}
