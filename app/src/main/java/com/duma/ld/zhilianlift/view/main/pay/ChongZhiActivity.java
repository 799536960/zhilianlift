package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;

import butterknife.BindView;

/**
 * 充值
 * Created by liudong on 2018/1/31.
 */

public class ChongZhiActivity extends BaseMyActivity {
    @BindView(R.id.edit_money)
    EditText editMoney;
    @BindView(R.id.radio_weiXin)
    RadioButton radioWeiXin;
    @BindView(R.id.radio_zhiFuBao)
    RadioButton radioZhiFuBao;
    @BindView(R.id.radio_yinlian)
    RadioButton radioYinlian;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_chongzhi, false).setTopBar_A("充值");
    }

}
