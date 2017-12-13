package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.AddresModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.PCDAddresModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.view.dialog.SelectAddresDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.addAddress;

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

    private PCDAddresModel model;
    private String id = "";

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_add_change, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        AddresModel model = (AddresModel) mActivity.getIntent().getSerializableExtra(Constants.Model);
        if (model != null) {
            mFragmentConfig.setTopBar_f("编辑收货人");
            id = model.getAddress_id() + "";
            edtName.setText(model.getConsignee());
            edtPhone.setText(model.getMobile());
            edtAddres.setText(model.getAddress());
            tvDiquTV.setText(model.getProvince_city_district());
        } else {
            mFragmentConfig.setTopBar_f("新建收货人");
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        super.onReceiveEvent(eventModel);
        if (eventModel.getCode() == Constants.event_addres_add) {
            model = (PCDAddresModel) eventModel.getData();
            tvDiquTV.setText(model.getProvinceModel().getName() + model.getCityModel().getName() + model.getDistrictModel().getName());
        }
    }

    @OnClick({R.id.layout_diqu, R.id.tv_sava_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_diqu:
                KeyboardUtils.hideSoftInput(edtAddres);
                KeyboardUtils.hideSoftInput(edtName);
                KeyboardUtils.hideSoftInput(edtPhone);
                extraTransaction().startDontHideSelf(new SelectAddresDialog());
                break;
            case R.id.tv_sava_btn:
                if (edtPhone.getText().toString().isEmpty()) {
                    TsUtils.show("请填写电话号码!");
                    return;
                }
                if (!RegexUtils.isMobileSimple(edtPhone.getText().toString())) {
                    TsUtils.show("请填写正确的手机号码!");
                    return;
                }
                if (edtName.getText().toString().isEmpty()) {
                    TsUtils.show("请填写收货人!");
                    return;
                }
                if (edtAddres.getText().toString().isEmpty()) {
                    TsUtils.show("请填写详细地址!");
                    return;
                }
                if (model == null) {
                    TsUtils.show("请选择所在地区!");
                    return;
                }
                saveHttp();
                break;
        }
    }

    private void saveHttp() {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<String>>post(addAddress)
                .params("address_id", id)
                .params("consignee", edtName.getText().toString())
                .params("address", edtAddres.getText().toString())
                .params("mobile", edtPhone.getText().toString())
                .params("province", model.getProvinceModel().getId())
                .params("city", model.getCityModel().getId())
                .params("district", model.getDistrictModel().getId())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        TsUtils.show(stringHttpResModel.getMsg());
                        mActivity.finish();
                    }
                });
    }
}
