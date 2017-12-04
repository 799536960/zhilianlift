package com.duma.ld.zhilianlift.base.baseView;

import android.os.Bundle;

/**
 * Created by liudong on 2017/12/4.
 */

public abstract class BaseHttpFragment extends BaseMyFragment {
    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mFragmentConfig.showLoadingView();
        onClickLoadingRefresh();
    }

}
