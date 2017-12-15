package com.duma.ld.zhilianlift.view.main.wode.realName;

import android.os.Bundle;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.Constants;

import butterknife.BindView;

/**
 * Created by liudong on 2017/12/14.
 */

public class RealNameSendSuccessActivity extends BaseMyActivity {
    @BindView(R.id.textView5)
    TextView textView5;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_real_name_send_success, false).setTopBar_A("提交成功");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        textView5.setText("您的实名认证已提交成功，材料审核需一个工作日，请耐心等待审核，如有疑问，请拨打客服热线：" + Constants.kefu);
    }
}
