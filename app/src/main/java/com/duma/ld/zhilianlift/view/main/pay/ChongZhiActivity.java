package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.WeiXinModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.pay.PayUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.addRecharge;
import static com.duma.ld.zhilianlift.util.HttpUrl.get_code_recharge;

/**
 * 充值
 * Created by liudong on 2018/1/31.
 */

public class ChongZhiActivity extends BaseMyActivity implements RadioGroup.OnCheckedChangeListener {
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
    //0 微信 1 支付宝 2 英联
    private int type;
    private PayUtil payUtil;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        super.onReceiveEvent(eventModel);
        switch (eventModel.getCode()) {
            case Constants.event_zhifuBao_success:
                TsUtils.show("支付成功!");
                finish();
                break;
        }
    }

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_chongzhi, false).setTopBar_A("充值");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        payUtil = new PayUtil(mActivity);
        radioGroup.setOnCheckedChangeListener(this);
        radioWeiXin.setChecked(true);
    }

    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
        int money = 0;
        try {
            money = Integer.parseInt(editMoney.getText().toString());
        } catch (NumberFormatException e) {
            TsUtils.show("请输入正确的金额!");
            return;
        }
        if (money == 0) {
            TsUtils.show("请输入正确的金额!");
            return;
        }

        switch (type) {
            case 0:
                weixinHttp();
                break;
            case 1:
                zhiFuBaoHttp();
                break;
            case 2:
                TsUtils.show("银联支付");
                break;
        }
    }

    private void zhiFuBaoHttp() {
        OkGo.<HttpResModel<String>>get(addRecharge)
                .tag(httpTag)
                .params("account", editMoney.getText().toString())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        payUtil.starZhiFuBao(stringHttpResModel.getResult());
                    }
                }.isDialog(mActivity));
    }

    private void weixinHttp() {
        OkGo.<HttpResModel<WeiXinModel>>get(get_code_recharge)
                .tag(httpTag)
                .params("account", editMoney.getText().toString())
                .execute(new MyJsonCallback<HttpResModel<WeiXinModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<WeiXinModel>> respons, HttpResModel<WeiXinModel> stringHttpResModel) {
                        payUtil.starWeiXin(stringHttpResModel.getResult());
                    }
                }.isDialog(mActivity));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_weiXin:
                setType(0);
                break;
            case R.id.radio_zhiFuBao:
                setType(1);
                break;
            case R.id.radio_yinlian:
                setType(2);
                break;
        }
    }

    private void setType(int i) {
        type = i;
    }

}
