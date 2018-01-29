package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.GetPayMoneyModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.PayStoreModel;
import com.duma.ld.zhilianlift.model.ShangHuModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.EditUtil;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PointLengthFilter;
import com.duma.ld.zhilianlift.view.main.house.OnTextChangeListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.getRenovationCat;
import static com.duma.ld.zhilianlift.util.HttpUrl.getStoreInfo;

/**
 * 扫码支付
 */

public class ScanPayActivity extends BaseMyActivity {
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.edit_money)
    EditText editMoney;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.edit_beizhu)
    EditText editBeizhu;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    private String id;
    private double money;
    private PayStoreModel model;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_scan_pay).setTopBar_A("付款");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        id = getIntent().getStringExtra(Constants.id);
        editMoney.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                try {
                    money = Double.parseDouble(s.toString());
                } catch (NumberFormatException e) {
                    money = 0;
                }
                initBut();
            }
        }));
        editMoney.setFilters(new InputFilter[]{new PointLengthFilter()});
        initBut();
        onClickLoadingRefresh();
    }

    public void initBut() {
        if (money != 0) {
            tvOk.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hong));
            tvOk.setTextColor(ZhuanHuanUtil.getColor(R.color.white));
        } else {
            tvOk.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hui2));
            tvOk.setTextColor(ZhuanHuanUtil.getColor(R.color.hui7));
        }
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<ShangHuModel>>get(getStoreInfo)
                .tag(this)
                .params("store_id", id)
                .execute(new MyJsonCallback<HttpResModel<ShangHuModel>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ShangHuModel>> respons, HttpResModel<ShangHuModel> stringHttpResModel) {
                        initData(stringHttpResModel.getResult());
                    }
                });
    }

    private void initData(ShangHuModel result) {
        if (result == null) {
            finish();
            TsUtils.show("查询出错!");
            return;
        }
        tvName.setText(result.getStore_name());
        ImageLoader.with(result.getStore_logo(), imgIcon);
        model = new PayStoreModel(result.getStore_name(), result.getStore_logo(), id);
    }

    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
        if (money != 0) {
            OkGo.<HttpResModel<GetPayMoneyModel>>get(getRenovationCat)
                    .tag(httpTag)
                    .params("store_id", id)
                    .execute(new MyJsonCallback<HttpResModel<GetPayMoneyModel>>() {
                        @Override
                        protected void onJsonSuccess(Response<HttpResModel<GetPayMoneyModel>> respons, HttpResModel<GetPayMoneyModel> getPayMoneyModelHttpResModel) {
                            model.setMoney(money);
                            model.setRemark(editBeizhu.getText().toString());
                            GetPayMoneyModel result = getPayMoneyModelHttpResModel.getResult();
                            model.setOnClick(-1);
                            if (result.getRenovation_money() == 1) {
                                model.setRenovation_money(result.getUsers().getRenovation_money());
                                model.setOnClickDefaut(0);
                            } else {
                                model.setRenovation_money(-1);
                            }
                            if (result.getPay_points() == 1) {
                                model.setPay_points(result.getUsers().getUser_money());
                                model.setOnClickDefaut(1);
                            } else {
                                model.setPay_points(-1);
                            }
                            model.setOnClickDefaut(2);
                            model.setUser_money(result.getUsers().getUser_money());
                            IntentUtil.goPayStore(mActivity, model);
                        }
                    }.isDialog(mActivity));
        }
    }
}
