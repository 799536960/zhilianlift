package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.widget.PayInputLayout;
import com.duma.ld.zhilianlift.widget.PayPasswordLayout;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.event_pay_password;

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

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_pay_input_password, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        layoutPassword.setPayInputLayout(layoutInput);
        layoutPassword.setOnPasswordType(new PayPasswordLayout.OnPasswordType() {
            @Override
            public void onEnd(String password) {
                EventBusUtil.sendModel(event_pay_password, password);
                mActivity.finish();
            }

            @Override
            public void onDelete() {

            }
        });
    }

    @OnClick({R.id.layout_back, R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                mActivity.finish();
                break;
            case R.id.tv_forget_password:
                // TODO: 2018/1/8 忘记密码
                break;
        }
    }
}
