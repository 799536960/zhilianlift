package com.duma.ld.zhilianlift.util.imageSelect;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.duma.ld.zhilianlift.R;


public class PaiZhaoDialog extends Dialog {

    private Context context;
    private ClickListenerInterface clickListenerInterface;

    private LinearLayout paizhao_onClick, xiangce_onClick;

    public interface ClickListenerInterface {
        void paiZhao();

        void xiangce();
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    public PaiZhaoDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_paizhao);

        init();
    }

    private void init() {
        paizhao_onClick = (LinearLayout) findViewById(R.id.paizhao_onClick);
        xiangce_onClick = (LinearLayout) findViewById(R.id.xiangce_onClick);

        xiangce_onClick.setOnClickListener(new clickListener());
        paizhao_onClick.setOnClickListener(new clickListener());
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.paizhao_onClick:
                    dismiss();
                    clickListenerInterface.paiZhao();
                    break;
                case R.id.xiangce_onClick:
                    dismiss();
                    clickListenerInterface.xiangce();
                    break;
            }
        }
    }
}
