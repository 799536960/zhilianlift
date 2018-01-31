package com.duma.ld.zhilianlift.view.main.pay;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.EditUtil;
import com.duma.ld.zhilianlift.util.PointLengthFilter;
import com.duma.ld.zhilianlift.view.main.house.OnTextChangeListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提现
 * Created by liudong on 2018/1/31.
 */

public class TiXianActivity extends BaseMyActivity {
    @BindView(R.id.tv_yaoxiang)
    TextView tvYaoxiang;
    @BindView(R.id.layout_zhiFuBaoUser)
    LinearLayout layoutZhiFuBaoUser;
    @BindView(R.id.tv_zongNum)
    TextView tvZongNum;
    @BindView(R.id.tv_quanBuTixian)
    TextView tvQuanBuTixian;
    @BindView(R.id.edit_num)
    EditText editNum;
    @BindView(R.id.tv_minNum)
    TextView tvMinNum;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;

    private double money;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_tixian).setTopBar_A("提现");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        editNum.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                try {
                    money = Double.parseDouble(s.toString());
                } catch (NumberFormatException e) {
                    money = 0;
                }
                initBut();
            }
        }));
        editNum.setFilters(new InputFilter[]{new PointLengthFilter()});
        onClickLoadingRefresh();
    }

    public void initBut() {
        if (money != 0) {
            tvTixian.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hong));
            tvTixian.setTextColor(ZhuanHuanUtil.getColor(R.color.white));
        } else {
            tvTixian.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hui2));
            tvTixian.setTextColor(ZhuanHuanUtil.getColor(R.color.hui7));
        }
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();

    }

    @OnClick({R.id.layout_zhiFuBaoUser, R.id.tv_quanBuTixian, R.id.tv_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_zhiFuBaoUser:
                startActivity(new Intent(mActivity, AddZhiFuBaoActivity.class));
                break;
            case R.id.tv_quanBuTixian:
                break;
            case R.id.tv_tixian:
                break;
        }
    }
}
