package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.PCDAddresModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.view.dialog.SelectAddresDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 2017/12/12.
 */

public class AddOrChangeFragment extends BaseMyFragment {
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

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_add_change, false).setTopBar_f("新建收货人");
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        super.onReceiveEvent(eventModel);
        if (eventModel.getCode() == Constants.event_addres_add) {
            PCDAddresModel model = (PCDAddresModel) eventModel.getData();
            tvDiquTV.setText(model.getProvinceModel().getName() + model.getCityModel().getName() + model.getDistrictModel().getName());
        }
    }

    @OnClick({R.id.layout_diqu, R.id.tv_sava_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_diqu:
                extraTransaction().startDontHideSelf(new SelectAddresDialog());
                break;
            case R.id.tv_sava_btn:
                break;
        }
    }
}
