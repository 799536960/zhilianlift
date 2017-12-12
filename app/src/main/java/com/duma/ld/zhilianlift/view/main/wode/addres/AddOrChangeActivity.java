package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.view.dialog.SelectAddresDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 2017/12/12.
 */

public class AddOrChangeActivity extends BaseMyActivity {
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.tv_diquTV)
    TextView tvDiquTV;
    @BindView(R.id.layout_diqu)
    LinearLayout layoutDiqu;
    @BindView(R.id.edt_addres)
    EditText edtAddres;
    @BindView(R.id.tv_sava_btn)
    TextView tvSavaBtn;

    private SelectAddresDialog selectAddresDialog;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_change, false).setTopBar("新建收货人");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        selectAddresDialog = new SelectAddresDialog(mActivity);
    }

    @OnClick({R.id.layout_diqu, R.id.tv_sava_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_diqu:
                selectAddresDialog.show();
                break;
            case R.id.tv_sava_btn:
                break;
        }
    }
}
