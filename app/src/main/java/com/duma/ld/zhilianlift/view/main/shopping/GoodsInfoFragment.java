package com.duma.ld.zhilianlift.view.main.shopping;

import android.os.Bundle;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.util.Constants;

/**
 * 商品详情
 * Created by liudong on 2017/12/22.
 */

public class GoodsInfoFragment extends BaseMyFragment {

    private String id;

    public static GoodsInfoFragment newInstance(String id) {
        GoodsInfoFragment fragment = new GoodsInfoFragment();
        Bundle args = new Bundle();
        args.putString(Constants.id, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_goods_info);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString(Constants.id);
        } else {
            TsUtils.show("商品id获取失败!");
            mActivity.finish();
        }
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
    }
}
