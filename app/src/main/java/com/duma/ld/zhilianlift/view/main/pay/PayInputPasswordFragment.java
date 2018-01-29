package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.PayStoreModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.widget.PayInputLayout;
import com.duma.ld.zhilianlift.widget.PayPasswordLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.event_pay_password;
import static com.duma.ld.zhilianlift.util.HttpUrl.StoreMoney;

/**
 * 输入支付密码页面
 * Created by liudong on 2018/1/8.
 */

public class PayInputPasswordFragment extends BaseMyFragment {
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.layout_password)
    PayPasswordLayout layoutPassword;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.layout_input)
    PayInputLayout layoutInput;

    private PayStoreModel model;

    public static PayInputPasswordFragment newInstance(PayStoreModel model) {
        PayInputPasswordFragment fragment = new PayInputPasswordFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Model, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_pay_input_password, false);
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            model = (PayStoreModel) args.getSerializable(Constants.Model);
        }
        layoutPassword.setPayInputLayout(layoutInput);
        layoutPassword.setOnPasswordType(new PayPasswordLayout.OnPasswordType() {
            @Override
            public void onEnd(String password) {
                if (model == null) {
                    EventBusUtil.sendModel(event_pay_password, password);
                    mActivity.finish();
                } else {
                    pay(password);
                }

            }

            @Override
            public void onDelete() {

            }
        });
    }

    private void pay(String password) {
        PostRequest<HttpResModel<Void>> request = OkGo.<HttpResModel<Void>>post(StoreMoney)
                .tag(httpTag);
        //0装修 1积分 2余额
        switch (model.getOnClick()) {
            case 0:
                request.params("renovation_money", model.getMoney());
                break;
            case 1:
                request.params("pay_points", model.getMoney());
                break;
            case 2:
                request.params("user_money", model.getMoney());
                break;
        }
        request
                .params("store_id", model.getId())
                .params("desc", model.getRemark())
                .params("paypwd", password)
                .execute(new MyJsonCallback<HttpResModel<Void>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<Void>> respons, HttpResModel<Void> stringHttpResModel) {
                        IntentUtil.goPayStoreSuccess(mActivity, model);
                    }
                }.isDialog(mActivity));
    }

    @OnClick({R.id.layout_back, R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                mActivity.finish();
                break;
            case R.id.tv_forget_password:
                IntentUtil.goPaySetting(mActivity);
                break;
        }
    }
}
