package com.duma.ld.zhilianlift.base.baseView;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.duma.ld.baselibrary.base.BaseActivity;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;
import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * @author liudong
 * @date 2017/11/28
 */

public abstract class BaseMyActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(mActivity, ZhuanHuanUtil.getColor(R.color.white));
    }

    @Override
    public void onClickLoadingRefresh() {

    }

    protected void init(Bundle savedInstanceState) {

    }
}
