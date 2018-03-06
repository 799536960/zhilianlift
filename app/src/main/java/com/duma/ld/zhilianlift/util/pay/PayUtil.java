package com.duma.ld.zhilianlift.util.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.zhilianlift.base.MyApplication;
import com.duma.ld.zhilianlift.model.WeiXinModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;

import java.util.Map;

import static com.duma.ld.zhilianlift.util.Constants.SDK_PAY_FLAG;
import static com.duma.ld.zhilianlift.util.Constants.event_yinlian_success;

/**
 * 支付工具
 * Created by liudong on 2018/1/30.
 */

public class PayUtil {
    private Activity mActivity;
    private MyHandler mHandler;

    public PayUtil(Activity mActivity) {
        this.mActivity = mActivity;
        mHandler = new MyHandler();
    }

    public void starZhiFuBao(final String payInfo) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(mActivity);
                // 调用支付接口，获取支付结果
                Map<String, String> result = alipay.payV2(payInfo, true);
                Logger.e("支付宝结果: " + result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        });
        thread.start();
    }

    public void starWeiXin(WeiXinModel weiXinBean) {
        PayReq req = new PayReq();
        req.appId = weiXinBean.getAppid();
        req.partnerId = weiXinBean.getPartnerid();
        req.prepayId = weiXinBean.getPrepayid();
        req.nonceStr = weiXinBean.getNoncestr();
        req.timeStamp = weiXinBean.getTimestamp();
        req.packageValue = "Sign=WXPay";
        req.sign = weiXinBean.getPaySign();
        IWXAPI wxapi = WXAPIFactory.createWXAPI(MyApplication.getInstance(), Constants.Weixin, false);
//        wxapi.registerApp(Constants.Weixin);
        wxapi.sendReq(req);
    }

    public void starYL(String sn) {
        Logger.e("sn:" + sn);
        String serverMode = "01";
        int i = UPPayAssistEx.startPay(mActivity, null, null, sn, serverMode);
        switch (i) {
            case UPPayAssistEx.PLUGIN_VALID:
                break;
            case UPPayAssistEx.PLUGIN_NOT_FOUND:
                TsUtils.show("请安装银联支付控件!");
                break;
        }
    }

    public void yinLanResult(Intent data) {
        if (data == null) {
            return;
        }
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str == null) {
            return;
        }
        if (str.equalsIgnoreCase("success")) {
            EventBusUtil.sendModel(event_yinlian_success);
        } else if (str.equalsIgnoreCase("fail")) {
            TsUtils.show("支付失败!");
        } else if (str.equalsIgnoreCase("cancel")) {
            TsUtils.show("用户取消支付!");
        }
    }
}
