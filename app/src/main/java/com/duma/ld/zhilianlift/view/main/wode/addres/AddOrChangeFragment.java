package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.EventBusUtil;
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
import com.lzy.okgo.request.PostRequest;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.event_addresList_add;
import static com.duma.ld.zhilianlift.util.Constants.event_addresList_edit;
import static com.duma.ld.zhilianlift.util.HttpUrl.addAddress;

/**
 * 添加或者编辑地址fragment
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
    private AddresModel addresModel;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_add_change, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        addresModel = (AddresModel) mActivity.getIntent().getSerializableExtra(Constants.Model);
        if (addresModel != null) {
            mFragmentConfig.setTopBar_f("编辑收货人");
            id = addresModel.getAddress_id() + "";
            edtName.setText(addresModel.getConsignee());
            edtName.setSelection(edtName.getText().toString().length());
            edtPhone.setText(addresModel.getMobile());
            edtAddres.setText(addresModel.getAddress());
            tvDiquTV.setText(addresModel.getProvince_city_district());
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
                if (tvDiquTV.getText().toString().isEmpty()) {
                    TsUtils.show("请选择所在地区!");
                    return;
                }
                saveHttp();
                break;
        }
    }

    private void saveHttp() {
        DialogUtil.getInstance().show_noBack(mActivity);
        PostRequest<HttpResModel<AddresModel>> params = OkGo.<HttpResModel<AddresModel>>post(addAddress)
                .tag(httpTag)
                .params("address_id", id)
                .params("consignee", edtName.getText().toString())
                .params("address", edtAddres.getText().toString())
                .params("mobile", edtPhone.getText().toString());
        if (model != null) {
            //选择地区后
            params
                    .params("province", model.getProvinceModel().getId())
                    .params("city", model.getCityModel().getId())
                    .params("district", model.getDistrictModel().getId());
        } else {
            //没有选择地区 而又有值 说明是编辑
            params
                    .params("province", addresModel.getProvince())
                    .params("city", addresModel.getCity())
                    .params("district", addresModel.getDistrict());
        }

        params
                .execute(new MyJsonCallback<HttpResModel<AddresModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<AddresModel>> respons, HttpResModel<AddresModel> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        TsUtils.show(stringHttpResModel.getMsg());
                        if (id.isEmpty()) {
                            //添加
                            EventBusUtil.sendModel(event_addresList_add, stringHttpResModel.getResult());
                        } else {
                            //编辑
                            EventBusUtil.sendModel(event_addresList_edit, stringHttpResModel.getResult());
                        }
                        mActivity.finish();
                    }
                });
    }
}
