package com.duma.ld.zhilianlift.base.baseView;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.duma.ld.baselibrary.base.BaseActivity;
import com.duma.ld.zhilianlift.R;


/**
 * Created by liudong on 2017/6/9.
 */

public abstract class BaseDownDialog extends Dialog {
    private View mView;
    protected BaseActivity mBaseActivity;

    public BaseDownDialog(@NonNull BaseActivity context) {
        super(context, R.style.PopupDialog);
        this.mBaseActivity = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public void show() {
        super.show();
        Window win = this.getWindow();
        win.setGravity(Gravity.BOTTOM);                       //从下方弹出
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //宽度填满
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;  //高度自适应
        win.setAttributes(lp);
    }


    public View getmView() {
        return mView;
    }
}
