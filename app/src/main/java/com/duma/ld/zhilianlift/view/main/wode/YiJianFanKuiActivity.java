package com.duma.ld.zhilianlift.view.main.wode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.EditMaxLengthUtil;
import com.duma.ld.zhilianlift.util.imageSelect.ImageSelectManager;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.suggestion;

/**
 * Created by liudong on 2017/6/27.
 */

public class YiJianFanKuiActivity extends BaseMyActivity {
    @BindView(R.id.edit_yijian)
    EditText editYijian;
    @BindView(R.id.tv_zishu)
    TextView tvZishu;
    @BindView(R.id.edit_lianXiFangShi)
    EditText editLianXiFangShi;
    @BindView(R.id.rv_zhaoPian)
    RecyclerView rvZhaoPian;
    @BindView(R.id.tv_btn)
    TextView tvBtn;

    private EditMaxLengthUtil editMaxLengthUtil;
    private ImageSelectManager imageSelectManager;


    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_yijianfankui, false).setTopBar_A("意见反馈");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        imageSelectManager = ImageSelectManager.create(mActivity)
                .setMaxNum(6)
                .starRvStyle(rvZhaoPian);
        editMaxLengthUtil = new EditMaxLengthUtil(editYijian, tvZishu, 100);
    }


    @OnClick(R.id.tv_btn)
    public void onClick() {
        if (editYijian.getText().toString().isEmpty()) {
            TsUtils.show("请填写您的建议~");
            return;
        }
        if (editLianXiFangShi.getText().toString().isEmpty()) {
            TsUtils.show("请填写您的联系方式~");
            return;
        }
        DialogUtil.getInstance().show_noBack(mActivity, "上传中...");

        OkGo.<HttpResModel<String>>post(suggestion)
                .params("suggestion", editYijian.getText().toString())
                .params("mobile", editLianXiFangShi.getText().toString())
                .addFileParams("img[]", imageSelectManager.getFileList())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(com.lzy.okgo.model.Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        TsUtils.show("提交成功!谢谢您的反馈~");
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageSelectManager.onActivityResult(requestCode, resultCode, data);
    }


}
