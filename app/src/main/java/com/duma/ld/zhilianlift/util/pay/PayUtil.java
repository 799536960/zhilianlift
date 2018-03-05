package com.duma.ld.zhilianlift.util.pay;

import android.app.Activity;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.duma.ld.zhilianlift.base.MyApplication;
import com.duma.ld.zhilianlift.model.WeiXinModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import static com.duma.ld.zhilianlift.util.Constants.SDK_PAY_FLAG;

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

}
