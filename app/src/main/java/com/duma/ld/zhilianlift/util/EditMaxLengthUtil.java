package com.duma.ld.zhilianlift.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.view.main.house.OnTextChangeListener;

/**
 * Created by liudong on 2017/6/15.
 */

public class EditMaxLengthUtil implements TextWatcher {
    private TextView textView;
    private EditText editText;
    private int max;
    private OnTextChangeListener onTextChanged;

    public EditMaxLengthUtil(EditText editText, TextView textView, int max) {
        this.textView = textView;
        this.editText = editText;
        this.max = max;
        editText.addTextChangedListener(this);
        textView.setText("0/" + max);
    }

    public void setOnTextChanged(OnTextChangeListener onTextChanged) {
        this.onTextChanged = onTextChanged;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        textView.setText(max + "");
        textView.setText(s.toString().length() + "/" + max);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        textView.setText((max - editText.length()) + "");
        textView.setText(s.toString().length() + "/" + max);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (onTextChanged != null) {
            onTextChanged.textChanged(s);
        }
        if (editText.length() == max) {
            textView.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
        } else {
            textView.setTextColor(ZhuanHuanUtil.getColor(R.color.hui1));
        }
    }
}
