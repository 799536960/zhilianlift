package com.duma.ld.zhilianlift.view.main.house;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HouseHttpModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.imageSelect.ImageSelectManager;

import butterknife.BindView;

/**
 * 房屋信息
 * Created by liudong on 2018/1/9.
 */

public class HouseImageFragment extends BaseMyFragment {
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    private HouseHttpModel model;
    private ImageSelectManager imageSelectManager;

    public static HouseImageFragment newInstance(HouseHttpModel model) {
        HouseImageFragment fragment = new HouseImageFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Model, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_house_image);
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
        imageSelectManager = ImageSelectManager.create(mActivity)
                .setMaxNum(6)
                .setFragment(this)
                .starRvStyle(rvPhoto);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageSelectManager.onActivityResult(requestCode, resultCode, data);
        model.setList(imageSelectManager.getFileList());
    }
}
