package com.duma.ld.zhilianlift.view.main.wode;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.base.OnTopBarRightListener;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.PublicUtil;

/**
 * 消息中心
 * Created by liudong on 2018/1/15.
 */

public class MessageActivity extends BaseMyActivity {
    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_message).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading).setTopBar_A("消息中心", new OnTopBarLeftListener() {
            @Override
            public void onClick() {
                onBack();
            }
        }, R.drawable.lajitong, new OnTopBarRightListener() {
            @Override
            public void onClick() {
                AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "清空消息", "您确定要清空消息中心嘛?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteAll();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(false);
                builder.show();
            }
        });
    }

    private void deleteAll() {

    }
}
