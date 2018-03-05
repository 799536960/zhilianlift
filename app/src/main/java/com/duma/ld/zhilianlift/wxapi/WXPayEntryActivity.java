package com.duma.ld.zhilianlift.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.util.Constants;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.duma.ld.zhilianlift.util.Constants.event_weixin_success;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, Constants.Weixin);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                EventBusUtil.sendModel(event_weixin_success);
            } else {
                Logger.e("code:" + resp.errCode + " errStr:" + resp.errStr + " transaction:" + resp.transaction);
            }
        }
        finish();
    }
}