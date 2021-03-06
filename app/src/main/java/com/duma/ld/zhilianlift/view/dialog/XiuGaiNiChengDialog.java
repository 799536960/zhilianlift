package com.duma.ld.zhilianlift.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.zhilianlift.R;


public class XiuGaiNiChengDialog extends Dialog {

    private Context context;
    private ClickListenerInterface setOnClickListener;

    private LinearLayout layout_onClick;
    private EditText edit_nicheng;

    public interface ClickListenerInterface {
        void onRes(String res);

    }

    public void setClicklistener(ClickListenerInterface setOnClickListener) {
        this.setOnClickListener = setOnClickListener;
    }

    public XiuGaiNiChengDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_xiugainicheng);
        init();
    }

    private void init() {
        layout_onClick = (LinearLayout) findViewById(R.id.layout_onClick);
        edit_nicheng = (EditText) findViewById(R.id.edit_nicheng);

        layout_onClick.setOnClickListener(new clickListener());
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.layout_onClick:
                    if (edit_nicheng.getText().toString().isEmpty()) {
                        TsUtils.show("请输入昵称~");
                        return;
                    }
                    dismiss();
                    setOnClickListener.onRes(edit_nicheng.getText().toString());
                    break;
            }
        }
    }
}
