package com.duma.ld.zhilianlift.view.main.finance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.AddFinanceModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.imageSelect.ImageSelectManager;
import com.duma.ld.zhilianlift.util.imageSelect.OnSelectFileListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加贷款的身份证上传
 * Created by liudong on 2017/12/14.
 */

public class AddFinanceIdCardActivity extends BaseMyActivity {
    @BindView(R.id.img_img1)
    ImageView imgImg1;
    @BindView(R.id.img_img2)
    ImageView imgImg2;
    @BindView(R.id.img_img3)
    ImageView imgImg3;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    private ImageSelectManager imageSelectManager;
    private File file1, file2, file3;
    private AddFinanceModel model;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_finance_idcard, false).setTopBar_A("实名认证");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        model = (AddFinanceModel) getIntent().getSerializableExtra(Constants.Model);
        imageSelectManager = ImageSelectManager.create(mActivity).setMaxNum(1).setIsSave(false);
        imageSelectManager.setOnSelectFileListener(new OnSelectFileListener() {
            @Override
            public void getFile(File file, int code) {
                switch (code) {
                    case 1:
                        file1 = file;
                        ImageLoader.with(mActivity, file1, imgImg1);
                        break;
                    case 2:
                        file2 = file;
                        ImageLoader.with(mActivity, file2, imgImg2);
                        break;
                    case 3:
                        file3 = file;
                        ImageLoader.with(mActivity, file3, imgImg3);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.img_img1, R.id.img_img2, R.id.img_img3, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_img1:
                imageSelectManager.dialog_show(1);
                break;
            case R.id.img_img2:
                imageSelectManager.dialog_show(2);
                break;
            case R.id.img_img3:
                imageSelectManager.dialog_show(3);
                break;
            case R.id.tv_ok:
                if (file1 == null || file2 == null || file3 == null) {
                    TsUtils.show("请上传您的身份证照片!");
                    return;
                }
                List<File> list = new ArrayList<>();
                list.add(file1);
                list.add(file2);
                list.add(file3);
                model.setIdcard_img(list);
                Intent intent = new Intent(mActivity, AddFinanceActivity.class);
                intent.putExtra(Constants.Model, model);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageSelectManager.onActivityResult(requestCode, resultCode, data);
    }
}
