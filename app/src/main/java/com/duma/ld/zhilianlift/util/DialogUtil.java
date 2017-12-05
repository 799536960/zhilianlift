package com.duma.ld.zhilianlift.util;


import android.app.Activity;

import com.duma.ld.zhilianlift.view.dialog.LoadineDialog;


/**
 * Created by 79953 on 2016/9/30.
 */

public class DialogUtil {

    private DialogUtil() {
    }

    public static synchronized DialogUtil getInstance() {
        return DialogUtilHolder.instance;
    }

    private static class DialogUtilHolder {
        private static final DialogUtil instance = new DialogUtil();
    }

    private LoadineDialog loadineDialog;

    public void show(Activity activity) {
        hide();
        loadineDialog = new LoadineDialog(activity);
        loadineDialog.show();
    }

    public void show_noBack(Activity activity) {
        hide();
        loadineDialog = new LoadineDialog(activity, false, "加载中...");
        loadineDialog.show();
    }

    public void show(Activity activity, String title) {
        hide();
        loadineDialog = new LoadineDialog(activity, title);
        loadineDialog.show();
    }

    public void show_noBack(Activity activity, String title) {
        hide();
        loadineDialog = new LoadineDialog(activity, false, title);
        loadineDialog.show();
    }

    public void setTiTle(String title) {
        if (loadineDialog != null) {
            loadineDialog.setText(title);
        }
    }

    public void hide() {
        if (loadineDialog != null)
            if (loadineDialog.isShowing()) {
                try {
                    loadineDialog.dismiss();
                    loadineDialog = null;
                } catch (Exception e) {
                }
            }
    }
}
