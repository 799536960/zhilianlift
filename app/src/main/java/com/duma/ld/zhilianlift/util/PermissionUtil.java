package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.duma.ld.zhilianlift.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

/**
 * Created by liudong on 2017/9/21.
 */

public class PermissionUtil {
    private Activity mActivity;
    private PermissionListener callback;
    private onPermissionListener onPermissionListener;
    private int codeLocation = 100;

    public interface onPermissionListener {
        void onResult(int requestCode, boolean result);
    }

    public PermissionUtil(Activity mActivity, PermissionUtil.onPermissionListener onPermissionListener) {
        this.mActivity = mActivity;
        this.onPermissionListener = onPermissionListener;
        initCallback(mActivity);
    }

    private void initCallback(final Activity mActivity) {
        callback = new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                onPermissionListener.onResult(requestCode, true);
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                if (AndPermission.hasAlwaysDeniedPermission(mActivity, deniedPermissions)) {
                    // 第一种：用AndPermission默认的提示语。
                    AndPermission.defaultSettingDialog(mActivity, requestCode).show();
                }
                onPermissionListener.onResult(requestCode, false);
            }
        };

    }

    /**
     * 开启蓝牙需要定位权限
     */
    public void openLocation() {
        if (!AndPermission.hasPermission(mActivity, Permission.LOCATION)) {
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    AndPermission.with(mActivity)
                            .requestCode(codeLocation)
                            .permission(Permission.LOCATION)
                            .callback(callback)
                            .start();
                }
            };
            AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, mActivity.getString(R.string.permission1), mActivity.getString(R.string.permission2))
                    .setPositiveButton(mActivity.getString(R.string.permission3), listener)
                    .setCancelable(false);
            ;
            builder.show();
        } else {
            AndPermission.with(mActivity)
                    .requestCode(codeLocation)
                    .permission(Permission.LOCATION)
                    .callback(callback)
                    .start();
        }

    }


}
