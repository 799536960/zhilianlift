package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.ZhiFuBaoUserModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.event_addZhiFuBao_success;
import static com.duma.ld.zhilianlift.util.HttpUrl.editalipay;

/**
 * 添加或者编辑支付宝账号
 * Created by liudong on 2018/1/31.
 */

public class AddZhiFuBaoActivity extends BaseMyActivity {
    @BindView(R.id.edit_zhanghao)
    EditText editZhanghao;
    @BindView(R.id.edit_zhanghao2)
    EditText editZhanghao2;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_zhifubao, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        ZhiFuBaoUserModel model = (ZhiFuBaoUserModel) getIntent().getSerializableExtra(Constants.Model);
        if (model != null && StringUtils.isEmpty(model.getAlipay_name())) {
            mActivityConfig.setTopBar_A("添加支付宝账号");
        } else {
            mActivityConfig.setTopBar_A("修改支付宝账号");
            editName.setText(model.getAlipay_realname());
            editZhanghao.setText(model.getAlipay_name());
            editZhanghao2.setText(model.getAlipay_name());
        }
    }

    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
        if (editZhanghao.getText().toString().isEmpty() || editZhanghao2.getText().toString().isEmpty()) {
            TsUtils.show("请输入支付宝账号!");
            return;
        }
        if (!editZhanghao.getText().toString().equals(editZhanghao2.getText().toString())) {
            TsUtils.show("两次输入的支付宝账号不一致!");
            return;
        }
        if (editName.getText().toString().isEmpty()) {
            TsUtils.show("请输入真实姓名");
            return;
        }
        OkGo.<HttpResModel<ZhiFuBaoUserModel>>get(editalipay)
                .tag(httpTag)
                .params("new_alipay_name", editZhanghao.getText().toString())
                .params("sure_alipay_name", editZhanghao2.getText().toString())
                .params("alipay_realname", editName.getText().toString())
                .execute(new MyJsonCallback<HttpResModel<ZhiFuBaoUserModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ZhiFuBaoUserModel>> respons, HttpResModel<ZhiFuBaoUserModel> zhiFuBaoUserModelHttpResModel) {
                        EventBusUtil.sendModel(event_addZhiFuBao_success);
                        finish();
                    }
                }.isDialog(mActivity));
    }
}
