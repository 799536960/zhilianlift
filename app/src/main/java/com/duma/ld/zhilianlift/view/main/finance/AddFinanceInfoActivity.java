package com.duma.ld.zhilianlift.view.main.finance;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.AddFinanceModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.EditUtil;
import com.duma.ld.zhilianlift.view.main.house.OnTextChangeListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加
 * Created by liudong on 2018/1/25.
 */

public class AddFinanceInfoActivity extends BaseMyActivity {
    @BindView(R.id.layout_close)
    FrameLayout layoutClose;
    @BindView(R.id.layout_message)
    LinearLayout layoutMessage;
    @BindView(R.id.edit_address)
    EditText editAddress;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_idcard)
    EditText editIdcard;
    @BindView(R.id.edit_money)
    EditText editMoney;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    private AddFinanceModel model;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_finance_info, false).setTopBar_A("贷款申请");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        model = (AddFinanceModel) getIntent().getSerializableExtra(Constants.Model);
        editMoney.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                if (StringUtils.isEmpty(s)) {
                    tvMoney.setText("0");
                } else {
                    tvMoney.setText(s);
                }
            }
        }));
    }


    @OnClick({R.id.layout_close, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_close:
                layoutMessage.setVisibility(View.GONE);
                break;
            case R.id.tv_ok:
                if (StringUtils.isEmpty(editAddress.getText().toString())) {
                    TsUtils.show("请输入您的详细地址");
                    return;
                }
                if (StringUtils.isEmpty(editName.getText().toString())) {
                    TsUtils.show("请输入申请人姓名");
                    return;
                }
                if (!RegexUtils.isIDCard18(editIdcard.getText().toString())) {
                    TsUtils.show("请输入正确的身份证号码");
                    return;
                }
                if (StringUtils.isEmpty(editMoney.getText().toString())) {
                    TsUtils.show("请输入贷款金额");
                    return;
                }
                if (editMoney.getText().toString().equals("0")) {
                    TsUtils.show("请输入贷款金额");
                    return;
                }
                model.setAddress(editAddress.getText().toString());
                model.setIdcard(editIdcard.getText().toString());
                model.setMoney(editMoney.getText().toString());
                model.setRealname(editName.getText().toString());
                Intent intent = new Intent(mActivity, AddFinanceIdCardActivity.class);
                intent.putExtra(Constants.Model, model);
                startActivity(intent);
                break;
        }
    }
}
