package com.duma.ld.zhilianlift.view.main.pay;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.ZhiFuBaoUserModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.EditUtil;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PointLengthFilter;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.view.main.house.OnTextChangeListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.addtixian;
import static com.duma.ld.zhilianlift.util.HttpUrl.getalipay;

/**
 * 提现
 * Created by liudong on 2018/1/31.
 */

public class TiXianActivity extends BaseMyActivity {
    @BindView(R.id.tv_yaoxiang)
    TextView tvYaoxiang;
    @BindView(R.id.layout_zhiFuBaoUser)
    LinearLayout layoutZhiFuBaoUser;
    @BindView(R.id.tv_zongNum)
    TextView tvZongNum;
    @BindView(R.id.tv_quanBuTixian)
    TextView tvQuanBuTixian;
    @BindView(R.id.edit_num)
    EditText editNum;
    @BindView(R.id.tv_minNum)
    TextView tvMinNum;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;

    private double money;
    private ZhiFuBaoUserModel model;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        super.onReceiveEvent(eventModel);
        switch (eventModel.getCode()) {
            case Constants.event_addZhiFuBao_success:
                onClickLoadingRefresh();
                break;
        }
    }

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_tixian).setTopBar_A("提现");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        editNum.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                try {
                    money = Double.parseDouble(s.toString());
                    if (model != null) {
                        if (money > model.getUser_money()) {
                            editNum.setText(model.getUser_money() + "");
                            editNum.setSelection(editNum.getText().length());
                        }
                    }
                } catch (NumberFormatException e) {
                    money = 0;
                }
                initBut();
            }
        }));
        editNum.setFilters(new InputFilter[]{new PointLengthFilter()});
        onClickLoadingRefresh();
    }

    public void initBut() {
        if (money != 0) {
            tvTixian.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hong));
            tvTixian.setTextColor(ZhuanHuanUtil.getColor(R.color.white));
        } else {
            tvTixian.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hui2));
            tvTixian.setTextColor(ZhuanHuanUtil.getColor(R.color.hui7));
        }
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<ZhiFuBaoUserModel>>get(getalipay)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<ZhiFuBaoUserModel>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ZhiFuBaoUserModel>> respons, HttpResModel<ZhiFuBaoUserModel> zhiFuBaoUserModelHttpResModel) {
                        model = zhiFuBaoUserModelHttpResModel.getResult();
                        initData();
                    }
                });
    }

    private void initData() {
        if (StringUtils.isEmpty(model.getAlipay_name())) {
            IntentUtil.goAddZhiFuBao(mActivity, model);
            TsUtils.show("请先添加支付宝账号!");
            tvYaoxiang.setText("点击添加支付宝账号");
        } else {
            tvYaoxiang.setText(model.getAlipay_name() + "");
        }
        tvZongNum.setText(model.getUser_money() + "");
    }

    @OnClick({R.id.layout_zhiFuBaoUser, R.id.tv_quanBuTixian, R.id.tv_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_zhiFuBaoUser:
                IntentUtil.goAddZhiFuBao(mActivity, model);
                break;
            case R.id.tv_quanBuTixian:
                editNum.setText(model.getUser_money() + "");
                editNum.setSelection(editNum.getText().length());
                break;
            case R.id.tv_tixian:
                if (StringUtils.isEmpty(model.getAlipay_name())) {
                    startActivity(new Intent(mActivity, AddZhiFuBaoActivity.class));
                    TsUtils.show("请先添加支付宝账号!");
                    return;
                }
                if (money == 0) {
                    TsUtils.show("请输入提现金额!");
                    return;
                }
                tiXianHttp();
                break;
        }
    }

    private void tiXianHttp() {
        AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "确认提现", "您确定要提现该账户嘛?(提现成功将无法撤回)")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OkGo.<HttpResModel<String>>get(addtixian)
                                .tag(httpTag)
                                .params("money", money)
                                .execute(new MyJsonCallback<HttpResModel<String>>() {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> zhiFuBaoUserModelHttpResModel) {
                                        TsUtils.show("提现成功!");
                                        finish();
                                    }
                                }.isDialog(mActivity));
                    }
                })
                .setNegativeButton("否", null)
                .setCancelable(false);
        builder.show();

    }
}
