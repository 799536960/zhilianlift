package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.PayStoreModel;
import com.duma.ld.zhilianlift.util.Constants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择支付方式
 * Created by liudong on 2018/1/29.
 */

public class SelectPayTypeFragment extends BaseMyFragment {
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.layout_img_1)
    FrameLayout layoutImg1;
    @BindView(R.id.layout_1)
    LinearLayout layout1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.layout_img_2)
    FrameLayout layoutImg2;
    @BindView(R.id.layout_2)
    LinearLayout layout2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.layout_img_3)
    FrameLayout layoutImg3;
    @BindView(R.id.layout_3)
    LinearLayout layout3;
    private PayStoreModel model;

    public static SelectPayTypeFragment newInstance(PayStoreModel model) {
        SelectPayTypeFragment fragment = new SelectPayTypeFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Model, model);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_select_pay_type);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            model = (PayStoreModel) args.getSerializable(Constants.Model);
        } else {
            TsUtils.show("获取失败!");
            mActivity.finish();
        }
        tv1.setText("装修资金（可用:" + model.getRenovation_money() + "）");
        tv2.setText("补贴积分（可用:" + model.getPay_points() + "）");
        tv3.setText("余额（可用:" + model.getUser_money() + "）");
        if (model.getRenovation_money() == -1) {
            layout1.setVisibility(View.GONE);
        }
        if (model.getPay_points() == -1) {
            layout2.setVisibility(View.GONE);
        }
        //0装修 1积分 2余额
        switch (model.getOnClick()) {
            case 0:
                layoutImg1.setVisibility(View.VISIBLE);
                break;
            case 1:
                layoutImg2.setVisibility(View.VISIBLE);
                break;
            case 2:
                layoutImg3.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.layout_back, R.id.layout_1, R.id.layout_2, R.id.layout_3})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("title", "xxxx");
        switch (view.getId()) {
            case R.id.layout_back:
                mActivity.finish();
                break;
            case R.id.layout_1:
                model.setOnClick(0);
                setFragmentResult(RESULT_OK, bundle);
                pop();
                break;
            case R.id.layout_2:
                model.setOnClick(1);
                setFragmentResult(RESULT_OK, bundle);
                pop();
                break;
            case R.id.layout_3:
                model.setOnClick(2);
                setFragmentResult(RESULT_OK, bundle);
                pop();
                break;
        }
    }
}
