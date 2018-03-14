package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.PayStoreModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.paypwd_is;

/**
 * 确认付款
 * Created by liudong on 2018/1/29.
 */

public class ConfirmPayFragment extends BaseMyFragment {
    private static final int REQ_CODE = 100;
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.tv_money_payType)
    TextView tvMoneyPayType;
    @BindView(R.id.layout_moneyType)
    LinearLayout layoutMoneyType;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    private PayStoreModel model;

    public static ConfirmPayFragment newInstance(PayStoreModel model) {
        ConfirmPayFragment fragment = new ConfirmPayFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Model, model);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_confirm_pay);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            model = (PayStoreModel) args.getSerializable(Constants.Model);
        } else {
            TsUtils.show("获取失败!");
            mActivity.finish();
        }
        tvMoney.setText("￥" + model.getMoney());
        tvName.setText(model.getName());
        refreshPayType();
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            // 在此通过Bundle data 获取返回的数据
//            tvMoneyPayType.setText(data.getString("title"));
            refreshPayType();
        }
    }

    private void refreshPayType() {
        //0装修 1积分 2余额
        switch (model.getOnClick()) {
            case 0:
                tvMoneyPayType.setText("装修资金（可用:" + model.getRenovation_money() + "）");
                break;
            case 1:
                tvMoneyPayType.setText("补贴积分（可用:" + model.getPay_points() + "）");
                break;
            case 2:
                tvMoneyPayType.setText("余额（可用:" + model.getUser_money() + "）");
                break;
        }
    }

    @OnClick({R.id.layout_back, R.id.layout_moneyType, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                mActivity.finish();
                break;
            case R.id.layout_moneyType:
                startForResult(SelectPayTypeFragment.newInstance(model), REQ_CODE);
                break;
            case R.id.tv_ok:
                /**
                 * 先判断 是否设置密码
                 */
                OkGo.<HttpResModel<String>>get(paypwd_is)
                        .tag(httpTag)
                        .execute(new MyJsonCallback<HttpResModel<String>>() {
                            @Override
                            protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                                if (isSetting(stringHttpResModel.getResult())) {
                                    //跳转设置支付密码
                                    IntentUtil.goVerifyPhone_payPassword(mActivity);
                                    TsUtils.show("请设置支付密码!");
                                } else {
                                    start(PayInputPasswordFragment.newInstance(model));
                                }
                            }
                        }.isDialog(mActivity));
                break;
        }
    }

    /**
     * result: 1   没有设置支付密码
     * result: 2   有设置支付密码
     * <p>
     * true 咩有
     */
    public boolean isSetting(String isSetting) {
        if (isSetting.equals("1")) {
            return true;
        } else {
            return false;
        }
    }
}
