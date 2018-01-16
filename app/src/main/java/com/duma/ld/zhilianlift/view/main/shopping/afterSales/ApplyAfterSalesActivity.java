package com.duma.ld.zhilianlift.view.main.shopping.afterSales;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.AddresModel;
import com.duma.ld.zhilianlift.model.AfterSalesModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.imageSelect.ImageSelectManager;
import com.duma.ld.zhilianlift.widget.NumInputLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.event_address;
import static com.duma.ld.zhilianlift.util.HttpUrl.return_goods;

/**
 * 申请售后
 * Created by liudong on 2018/1/15.
 */

public class ApplyAfterSalesActivity extends BaseMyActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.radio_type)
    RadioGroup radioType;
    @BindView(R.id.numInput_num)
    NumInputLayout numInputNum;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    @BindView(R.id.radio_kuaidi)
    RadioGroup radioKuaidi;
    @BindView(R.id.edit_address_name)
    EditText editAddressName;
    @BindView(R.id.edit_address_phone)
    EditText editAddressPhone;
    @BindView(R.id.edit_address_info)
    TextView editAddressInfo;
    @BindView(R.id.layout_add_address)
    LinearLayout layoutAddAddress;
    @BindView(R.id.layout_shangmen_kuaidi)
    LinearLayout layoutShangmenKuaidi;
    @BindView(R.id.edit_kuaidi_name)
    EditText editKuaidiName;
    @BindView(R.id.edit_kuaidi_num)
    EditText editKuaidiNum;
    @BindView(R.id.layout_other_kuaidi)
    LinearLayout layoutOtherKuaidi;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.radio_btn_tuihuo)
    RadioButton radioBtnTuihuo;
    @BindView(R.id.radio_btn_huanhuo)
    RadioButton radioBtnHuanhuo;
    @BindView(R.id.radio_btn_shangmen)
    RadioButton radioBtnShangmen;
    @BindView(R.id.radio_btn_kuaidi)
    RadioButton radioBtnKuaidi;
    private ImageSelectManager imageSelectManager;
    private OrderModel.OrderGoodsBean order_goods;
    private BaseAdapter<AfterSalesModel> mAdapter;
    //type = 1  退货退款    type = 2 换货
    private int type;
    // 1 : 上门  2 快递
    private int get_goods;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_apply_after_sales).setTopBar_A("申请售后");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        type = 1;
        get_goods = 1;
        order_goods = (OrderModel.OrderGoodsBean) getIntent().getSerializableExtra(Constants.Model);
        mAdapter = new BaseAdapter.Builder<AfterSalesModel>(rvList, mActivity, R.layout.adapter_order_goods3)
                .setNoEnpty()
                .build(new OnBaseAdapterListener<AfterSalesModel>() {
                    @Override
                    public void convert(BaseViewHolder helper, AfterSalesModel item) {
                        PublicUtil.getViewOrderGoods(helper, item, mActivity);
                        helper.setGone(R.id.tv_afterSales, false);
                    }
                });
        imageSelectManager = ImageSelectManager.create(mActivity)
                .setMaxNum(6)
                .starRvStyle(rvPhoto);
        radioKuaidi.setOnCheckedChangeListener(this);
        radioType.setOnCheckedChangeListener(this);
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<AfterSalesModel>>get(return_goods)
                .tag(httpTag)
                .params("rec_id", order_goods.getRec_id())
                .execute(new MyJsonCallback<HttpResModel<AfterSalesModel>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<AfterSalesModel>> respons, HttpResModel<AfterSalesModel> afterSalesModelHttpResModel) {
                        AfterSalesModel result = afterSalesModelHttpResModel.getResult();
                        mAdapter.addData(result);
                        numInputNum.setMaxNum(result.getGoods_num());
                        editAddressPhone.setText(result.getMobile());
                        editAddressName.setText(result.getConsignee());
                        editAddressInfo.setText(result.getTotal_address());
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageSelectManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_tuihuo:
                type = 1;
                break;
            case R.id.radio_btn_huanhuo:
                type = 2;
                break;
            case R.id.radio_btn_shangmen:
                get_goods = 1;
                layoutShangmenKuaidi.setVisibility(View.VISIBLE);
                layoutOtherKuaidi.setVisibility(View.GONE);
                break;
            case R.id.radio_btn_kuaidi:
                get_goods = 2;
                layoutShangmenKuaidi.setVisibility(View.GONE);
                layoutOtherKuaidi.setVisibility(View.VISIBLE);
                break;
        }
    }


    @OnClick({R.id.layout_add_address, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_add_address:
                IntentUtil.goAddresListById(mActivity);
                break;
            case R.id.tv_submit:
                if (StringUtils.isEmpty(editContent.getText().toString())) {
                    TsUtils.show("请填写问题描述!");
                    return;
                }
                switch (radioKuaidi.getCheckedRadioButtonId()) {
                    case R.id.radio_btn_shangmen:
                        if (StringUtils.isEmpty(editAddressName.getText().toString())) {
                            TsUtils.show("联系人不能为空!");
                            return;
                        }
                        if (StringUtils.isEmpty(editAddressPhone.getText().toString())) {
                            TsUtils.show("联系电话不能为空!");
                            return;
                        }
                        break;
                    case R.id.radio_btn_kuaidi:
                        if (StringUtils.isEmpty(editKuaidiName.getText().toString())) {
                            TsUtils.show("快递公司名称不能为空!");
                            return;
                        }
                        if (StringUtils.isEmpty(editKuaidiNum.getText().toString())) {
                            TsUtils.show("快递单号不能为空!");
                            return;
                        }
                        break;
                }
                AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "提交申请", "您确定要提交申请?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tijiaoHttp();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(false);
                builder.show();
                break;
        }
    }

    private void tijiaoHttp() {
        AfterSalesModel model = mAdapter.getData().get(0);
        OkGo.<HttpResModel<String>>post(return_goods)
                .tag(httpTag)
                .params("rec_id", model.getRec_id())
                .params("goods_num", numInputNum.getNum())
                .params("type", type)
                .params("goods_id", model.getGoods_id())
                .params("get_goods", get_goods)
                .params("spec_key", model.getSpec_key())
                .params("order_id", model.getOrder_id())
                .params("order_sn", model.getOrder_sn())
                .params("express", editKuaidiName.getText().toString())
                .params("express_code", editKuaidiNum.getText().toString())
                .params("address", editAddressInfo.getText().toString())
                .params("consignee", editAddressName.getText().toString())
                .params("mobile", editAddressPhone.getText().toString())
                .params("reason", editContent.getText().toString())
                .addFileParams("return_imgs[]", imageSelectManager.getFileList())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        TsUtils.show("提交成功!");
                        IntentUtil.goAfterSalesInfo(mActivity, stringHttpResModel.getResult());
                        finish();

                    }
                }.isDialog(mActivity));
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == event_address) {
            AddresModel model = (AddresModel) eventModel.getData();
            editAddressPhone.setText(model.getMobile());
            editAddressName.setText(model.getConsignee());
            editAddressInfo.setText(model.getProvince_city_district() + model.getAddress());
        }
    }
}
