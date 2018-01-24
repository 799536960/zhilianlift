package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.addPreparation;

/**
 * 添加报备
 * Created by liudong on 2018/1/22.
 */

public class AddBaoBeiActivity extends BaseMyActivity {
    @BindView(R.id.edit_beiTuiJian_name)
    EditText editBeiTuiJianName;
    @BindView(R.id.edit_beiTuiJian_phone)
    EditText editBeiTuiJianPhone;
    @BindView(R.id.edit_beizhu)
    EditText editBeizhu;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    private String houseId;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_baobei, false).setTopBar_A("推荐朋友买房");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        houseId = getIntent().getStringExtra(Constants.id);
    }

    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
        if (StringUtils.isEmpty(editBeiTuiJianName.getText().toString())) {
            TsUtils.show("请输入被推荐人姓名");
            return;
        }
        if (StringUtils.isEmpty(editName.getText().toString())) {
            TsUtils.show("请输入您的姓名");
            return;
        }
        if (!RegexUtils.isMobileSimple(editBeiTuiJianPhone.getText().toString())) {
            TsUtils.show("请输入正确的被推荐人手机号码");
            return;
        }
        if (!RegexUtils.isMobileSimple(editPhone.getText().toString())) {
            TsUtils.show("请输入正确的您的手机号");
            return;
        }
        OkGo.<HttpResModel<String>>post(addPreparation)
                .tag(httpTag)
                .params("house_id", houseId)
                .params("recommended_mobile", editBeiTuiJianPhone.getText().toString())
                .params("recommended_name", editBeiTuiJianName.getText().toString())
                .params("recommend_mobile", editPhone.getText().toString())
                .params("recommend_name", editName.getText().toString())
                .params("remarks", editBeizhu.getText().toString())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        TsUtils.show("添加成功!");
                        finish();
                    }
                }.isDialog(mActivity));
    }
}
