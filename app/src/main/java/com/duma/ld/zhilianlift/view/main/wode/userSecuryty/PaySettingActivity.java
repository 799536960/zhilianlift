package com.duma.ld.zhilianlift.view.main.wode.userSecuryty;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.SettingAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.SettingModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.Constants.event_pay_success_order;
import static com.duma.ld.zhilianlift.util.Constants.type_verify;
import static com.duma.ld.zhilianlift.util.HttpUrl.paypwd_is;

/**
 * 支付密码
 * Created by liudong on 2017/12/18.
 */

public class PaySettingActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private SettingAdapter settingAdapter;
    private List<SettingModel> list;
    private String isSetting;
    private String key;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_pay_setting).setTopBar_A("支付密码");
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == Constants.event_pay_success) {
            setSetting_true();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOrder() && !isSetting()) {
            EventBusUtil.sendModel(event_pay_success_order);
            finish();
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        key = getIntent().getStringExtra(Constants.key);
        isSetting = "1";
        settingAdapter = new SettingAdapter();
        rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        rvList.setAdapter(settingAdapter);
        settingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        if (isSetting()) {
                            //跳转设置支付密码
                            IntentUtil.goVerifyPhone_payPassword(mActivity);
                        } else {
                            //修改支付密码
                            IntentUtil.goPayPassword(mActivity, type_verify);
                        }
                        break;
                    case 1:
                        //忘记支付密码
                        IntentUtil.goVerifyPhone_payPassword_forget(mActivity);
                        break;
                }
            }
        });
        list = new ArrayList<>();
        onClickLoadingRefresh();
    }

    private boolean isOrder() {
        if (key == null || key.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<String>>get(paypwd_is)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<String>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        isSetting = stringHttpResModel.getResult();
                        initData();
                    }
                });
    }

    public void initData() {
        list.clear();
        if (isSetting()) {
            list.add(new SettingModel("支付密码", "立即设置"));
        } else {
            list.add(new SettingModel("修改6位数字支付密码"));
            list.add(new SettingModel("忘记6位数字支付密码"));
        }
        settingAdapter.setNewData(list);
    }

    /**
     * result: 1   没有设置支付密码
     * result: 2   有设置支付密码
     * <p>
     * true 咩有
     */
    public boolean isSetting() {
        if (isSetting.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    public void setSetting_true() {
        isSetting = "2";
        initData();
    }
}
