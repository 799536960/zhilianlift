package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HouseHttpModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.EditMaxLengthUtil;
import com.duma.ld.zhilianlift.util.EditUtil;

import butterknife.BindView;

/**
 * 房屋信息
 * Created by liudong on 2018/1/9.
 */

public class AddHouseOtherFragment extends BaseMyFragment {
    @BindView(R.id.edit_xiaoQu)
    EditText editXiaoQu;
    @BindView(R.id.tv_xiaoQu)
    TextView tvXiaoQu;
    @BindView(R.id.edit_jiaoTong)
    EditText editJiaoTong;
    @BindView(R.id.tv_jiaoTong)
    TextView tvJiaoTong;
    @BindView(R.id.edit_zhoubian)
    EditText editZhoubian;
    @BindView(R.id.tv_zhoubian)
    TextView tvZhoubian;
    @BindView(R.id.edit_louPanMinCheng)
    EditText editLouPanMinCheng;
    @BindView(R.id.edit_jianZhuLieBie)
    EditText editJianZhuLieBie;
    @BindView(R.id.edit_chanQuanNianXian)
    EditText editChanQuanNianXian;
    @BindView(R.id.edit_tingCheWei)
    EditText editTingCheWei;
    @BindView(R.id.edit_rongJiLv)
    EditText editRongJiLv;
    @BindView(R.id.edit_lvhuaLv)
    EditText editLvhuaLv;
    @BindView(R.id.edit_junGongShiJian)
    EditText editJunGongShiJian;
    @BindView(R.id.edit_KaiFaShang)
    EditText editKaiFaShang;
    private HouseHttpModel model;
    private EditMaxLengthUtil editMaxLengthUtil1, editMaxLengthUtil2, editMaxLengthUtil3;

    public static AddHouseOtherFragment newInstance(HouseHttpModel model) {
        AddHouseOtherFragment fragment = new AddHouseOtherFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Model, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_house_other);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            model = (HouseHttpModel) args.getSerializable(Constants.Model);
        } else {
            TsUtils.show("获取失败!");
            mActivity.finish();
        }
        editMaxLengthUtil1 = new EditMaxLengthUtil(editXiaoQu, tvXiaoQu, 500);
        editMaxLengthUtil2 = new EditMaxLengthUtil(editJiaoTong, tvJiaoTong, 500);
        editMaxLengthUtil3 = new EditMaxLengthUtil(editZhoubian, tvZhoubian, 500);
        editMaxLengthUtil1.setOnTextChanged(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setXiaoQuGaiKuang(s.toString());
            }
        });
        editMaxLengthUtil2.setOnTextChanged(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setJiaoTongZhuangKuang(s.toString());
            }
        });
        editMaxLengthUtil3.setOnTextChanged(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setZhouBianPeiTao(s.toString());
            }
        });

        //初始化编辑框监听
        initEdit();
    }

    private void initEdit() {
        editLouPanMinCheng.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setLouPanMinCheng(s.toString());
            }
        }));
        editJianZhuLieBie.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setJianZhuLieBie(s.toString());
            }
        }));
        editChanQuanNianXian.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setChanQuanNianXian(s.toString());
            }
        }));
        editTingCheWei.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setTingCheWei(s.toString());
            }
        }));
        editRongJiLv.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setRongJiLv(s.toString());
            }
        }));
        editLvhuaLv.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setLvHuaLv(s.toString());
            }
        }));
        editJunGongShiJian.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setJunGongShiJian(s.toString());
            }
        }));
        editKaiFaShang.addTextChangedListener(new EditUtil(new OnTextChangeListener() {
            @Override
            public void textChanged(Editable s) {
                model.setKaiFaShang(s.toString());
            }
        }));
    }

}
