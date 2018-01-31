package com.duma.ld.zhilianlift.util.pay;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.TsUtils;
import com.orhanobut.logger.Logger;

import java.util.Map;

import static com.duma.ld.zhilianlift.util.Constants.SDK_PAY_FLAG;
import static com.duma.ld.zhilianlift.util.Constants.event_zhifuBao_success;

public class MyHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SDK_PAY_FLAG: {
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                // 同步返回需要验证的信息
                String resultInfo = payResult.getResult();
                String resultStatus = payResult.getResultStatus();
//                    Logger.e("resultInfo ", resultInfo);
//                    Logger.e("resultStatus ", resultStatus);
                // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                if (TextUtils.equals(resultStatus, "9000")) {
                    EventBusUtil.sendModel(event_zhifuBao_success);
                } else {
                    // 判断resultStatus 为非"9000"则代表可能支付失败
                    // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                    if (TextUtils.equals(resultStatus, "8000")) {
                        Logger.e("支付结果确认中！");
                    } else {
                        // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                        TsUtils.show("支付失败");
                    }
                }
                break;
            }
        }
    }
}