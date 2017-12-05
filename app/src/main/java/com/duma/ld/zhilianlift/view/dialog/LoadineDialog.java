package com.duma.ld.zhilianlift.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.duma.ld.zhilianlift.R;


public class LoadineDialog extends Dialog {

    private boolean isBack = true;

    private String text = "";
    private TextView textView;

    public LoadineDialog(Activity activit) {
        this(activit, "加载中...");
    }

    public LoadineDialog(Activity activity, String title) {
        this(activity, true, title);
    }

    public LoadineDialog(Activity activity, boolean isBack, String title) {
        super(activity);
        this.isBack = isBack;
        this.text = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loadine);
        this.setCancelable(isBack);
        textView = (TextView) findViewById(R.id.tv_title);
        textView.setText(text);
    }

    public void setText(String text) {
        if (textView != null) {
            textView.setText(text);
        }
    }
}
