package com.duma.ld.zhilianlift.view.start;

import android.os.Bundle;
import android.widget.ImageView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;


/**
 * 扫码二维码
 * Created by liudong on 2017/9/21.
 */

public class SaoMaActivity extends BaseMyActivity implements QRCodeView.Delegate {
    @BindView(R.id.img_deng)
    ImageView imgDeng;
    private QRCodeView mQRCodeView;
    private boolean isOpen;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_saoma, false).setTopBar_A("付款");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mQRCodeView = (ZBarView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();
        isOpen = false;
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        TsUtils.show(result);
        mQRCodeView.startSpot();
        IntentUtil.goScanPay(mActivity, result);
        finish();
        Logger.e("扫码结果:" + result);
    }


    @Override
    public void onScanQRCodeOpenCameraError() {
        Logger.e("打开相机出错");
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }


    @OnClick(R.id.img_deng)
    public void onViewClicked() {
        if (isOpen) {
            mQRCodeView.closeFlashlight();
        } else {
            mQRCodeView.openFlashlight();
        }
        isOpen = !isOpen;
    }
}
