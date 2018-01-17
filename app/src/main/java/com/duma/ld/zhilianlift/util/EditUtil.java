package com.duma.ld.zhilianlift.util;

import android.text.Editable;
import android.text.TextWatcher;

import com.duma.ld.zhilianlift.view.main.house.OnTextChangeListener;

/**
 * Created by liudong on 2017/6/15.
 */

public class EditUtil implements TextWatcher {
    private OnTextChangeListener onTextChanged;

    public EditUtil(OnTextChangeListener onTextChanged) {
        this.onTextChanged = onTextChanged;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        onTextChanged.textChanged(s);
    }
}
