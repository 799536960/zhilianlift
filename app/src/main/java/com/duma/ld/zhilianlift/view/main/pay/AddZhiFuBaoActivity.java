package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加或者编辑支付宝账号
 * Created by liudong on 2018/1/31.
 */

public class AddZhiFuBaoActivity extends BaseMyActivity {
    @BindView(R.id.edit_zhanghao)
    EditText editZhanghao;
    @BindView(R.id.edit_zhanghao2)
    EditText editZhanghao2;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_zhifubao, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);

    }

    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
        if (editZhanghao.getText().toString().isEmpty() || editZhanghao2.getText().toString().isEmpty()) {
            TsUtils.show("请输入支付宝账号!");
            return;
        }
        if (!editZhanghao.getText().toString().equals(editZhanghao2.getText().toString())) {
            TsUtils.show("两次输入的支付宝账号不一致!");
            return;
        }
        if (editName.getText().toString().isEmpty()) {
            TsUtils.show("请输入真实姓名");
            return;
        }
    }
}
