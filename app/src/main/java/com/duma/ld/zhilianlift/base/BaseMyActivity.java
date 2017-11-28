package com.duma.ld.zhilianlift.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.duma.ld.baselibrary.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * @author liudong
 * @date 2017/11/28
 */

public abstract class BaseMyActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    protected void init() {

    }
}
