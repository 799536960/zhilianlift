package com.duma.ld.zhilianlift.view.main.shopping.afterSales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.AfterSalesModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PointLengthFilter;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.imageSelect.ImageSelectManager;
import com.duma.ld.zhilianlift.view.dialog.ApplyRefundDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.return_goods;

/**
 * 申请退款
 * Created by liudong on 2018/1/12.
 */

public class ApplyRefundActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.layout_edit)
    FrameLayout layoutEdit;
    @BindView(R.id.edit_money)
    EditText editMoney;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_other)
    TextView tvOther;
    private ImageSelectManager imageSelectManager;
    private OrderModel.OrderGoodsBean order_goods;
    private ApplyRefundDialog dialog;
    private BaseAdapter<AfterSalesModel> mAdapter;
    private AfterSalesModel model;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_apply_refund).setTopBar_A("申请退款");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
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
        editMoney.setFilters(new InputFilter[]{new PointLengthFilter()});
        layoutEdit.setVisibility(View.GONE);
        dialog = new ApplyRefundDialog(mActivity, new ApplyRefundDialog.OnStringClickListener() {
            @Override
            public void onItemClick(boolean isOther, String content, int position) {
                tvOther.setText(content);
                if (isOther) {
                    layoutEdit.setVisibility(View.VISIBLE);
                } else {
                    layoutEdit.setVisibility(View.GONE);
                }
            }
        });
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
                        mAdapter.addData(afterSalesModelHttpResModel.getResult());
                        initData(afterSalesModelHttpResModel.getResult());
                    }

                    @Override
                    public void onError(Response<HttpResModel<AfterSalesModel>> response) {
                        super.onError(response);
                        finish();
                    }
                });
    }

    private void initData(AfterSalesModel result) {
        model = result;
        editMoney.setHint("最多¥" + result.getGoods_price());
    }

    @OnClick({R.id.layout_other, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_other:
                dialog.show();
                break;
            case R.id.tv_submit:
                if (StringUtils.isEmpty(editMoney.getText().toString())) {
                    TsUtils.show("请输入要退款的金额");
                    return;
                }
                double money = Double.parseDouble(editMoney.getText().toString());
                if (money <= 0 || money > model.getGoods_price()) {
                    TsUtils.show("请输入正确的金额不能大于" + model.getGoods_price() + "元");
                    return;
                }
                if (tvOther.getText().toString().equals("请选择")) {
                    TsUtils.show("请选择退款原因!");
                    return;
                }
                if (tvOther.getText().toString().equals("其他") && StringUtils.isEmpty(editContent.getText().toString())) {
                    TsUtils.show("请填写其他退款原因!");
                    return;
                }
                sendHttp();
                break;
        }
    }

    private void sendHttp() {
        String other = tvOther.getText().toString();
        if (tvOther.getText().toString().equals("其他")) {
            other = editContent.getText().toString();
        }
        AfterSalesModel model = mAdapter.getData().get(0);
        OkGo.<HttpResModel<String>>post(return_goods)
                .tag(httpTag)
                .params("rec_id", model.getRec_id())
                .params("goods_num", model.getGoods_num())
                .params("type", 0)
                .params("goods_id", model.getGoods_id())
                .params("spec_key", model.getSpec_key())
                .params("order_id", model.getOrder_id())
                .params("order_sn", model.getOrder_sn())
                .params("reason", other)
                .params("goods_price", Double.parseDouble(editMoney.getText().toString()))
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageSelectManager.onActivityResult(requestCode, resultCode, data);
    }

}
