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
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.AddFinanceModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.imageSelect.ImageSelectManager;
import com.duma.ld.zhilianlift.util.imageSelect.OnSelectFileListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.credit;

/**
 * 添加贷款房产证上传
 * Created by liudong on 2017/12/14.
 */

public class AddFinanceActivity extends BaseMyActivity {
    @BindView(R.id.img_img1)
    ImageView imgImg1;
    @BindView(R.id.img_img2)
    ImageView imgImg2;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    private ImageSelectManager imageSelectManager;
    private File file1, file2;
    private AddFinanceModel model;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_finance, false).setTopBar_A("房产证上传");
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
                }
            }
        });
    }

    @OnClick({R.id.img_img1, R.id.img_img2, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_img1:
                imageSelectManager.dialog_show(1);
                break;
            case R.id.img_img2:
                imageSelectManager.dialog_show(2);
                break;
            case R.id.tv_ok:
                if (file1 == null || file2 == null) {
                    TsUtils.show("请上传您的房产证照片!");
                    return;
                }
                List<File> list = new ArrayList<>();
                list.add(file1);
                list.add(file2);
                model.setHouse_img(list);
                sendHttp();
                break;
        }
    }

    private void sendHttp() {
        DialogUtil.getInstance().show_noBack(mActivity, "上传中");
        OkGo.<HttpResModel<String>>post(credit)
                .tag(httpTag)
                .params("realname", model.getRealname())
                .params("idcard", model.getIdcard())
                .params("address", model.getAddress())
                .params("money", model.getMoney())
                .params("plan_id", model.getPlan_id())
                .addFileParams("idcard_img[]", model.getIdcard_img())
                .addFileParams("house_img[]", model.getHouse_img())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        TsUtils.show("提交成功");
                        IntentUtil.goMain(mActivity);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageSelectManager.onActivityResult(requestCode, resultCode, data);
    }
}
