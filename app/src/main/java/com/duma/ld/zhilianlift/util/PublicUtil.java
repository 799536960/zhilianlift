package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.support.v7.app.AlertDialog;

import com.duma.ld.zhilianlift.R;

/**
 * Created by liudong on 2017/12/14.
 */

public class PublicUtil {
    public static AlertDialog.Builder getAlertDialog(Activity activity, String title, String message) {
        return new AlertDialog.Builder(activity, R.style.AlertDialogTheme)
                .setTitle(title)
                .setMessage(message);
    }

    public static AlertDialog.Builder getAlertDialog(Activity activity, String title) {
        return new AlertDialog.Builder(activity, R.style.AlertDialogTheme)
                .setTitle(title);
    }
}
