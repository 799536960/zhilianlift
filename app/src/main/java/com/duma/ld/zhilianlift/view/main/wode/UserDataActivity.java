package com.duma.ld.zhilianlift.view.main.wode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.UserModel;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.util.imageSelect.ImageSelectManager;
import com.duma.ld.zhilianlift.util.imageSelect.OnSelectFileListener;
import com.duma.ld.zhilianlift.view.dialog.XiuGaiNiChengDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.updateUserInfo;
import static com.duma.ld.zhilianlift.util.HttpUrl.upload_headpic;

/**
 * 修改个人信息
 * 实名认证
 * Created by liudong on 2017/12/11.
 */

public class UserDataActivity extends BaseMyActivity {
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.layout_icon)
    LinearLayout layoutIcon;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_Nick_name)
    TextView tvNickName;
    @BindView(R.id.layout_name)
    LinearLayout layoutName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.layout_sex)
    LinearLayout layoutSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.layout_birthday)
    LinearLayout layoutBirthday;
    private ImageSelectManager imageSelectManager;
    private TimePickerView timePickerView;
    private OptionsPickerView optionsPickerView;
    private List<String> stringList;
    private XiuGaiNiChengDialog niChenDialog;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_user_data, false).setTopBar_A("个人资料");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        imageSelectManager = ImageSelectManager.create(mActivity).setMaxNum(1);
        imageSelectManager.setOnSelectFileListener(new OnSelectFileListener() {
            @Override
            public void getFile(File file, int code) {
                upLoadImgHead(file);
            }
        });
        UserModel user = SpDataUtil.getUser();
        tvNickName.setText(user.getNickname());
        tvBirthday.setText(ZhuanHuanUtil.Time2nian(user.getBirthday() * 1000));
        tvSex.setText(user.getSex());
        tvPhone.setText(user.getMobile_xx());
        ImageLoader.with_head(mActivity, user.getHead_pic(), imgIcon);
        timePickerView = new TimePickerView.Builder(mActivity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                setBirthdayHttp(date.getTime());
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setSubmitColor(ZhuanHuanUtil.getColor(R.color.primary_hong))
                .setCancelColor(ZhuanHuanUtil.getColor(R.color.primary_hong))
                .build();
        stringList = new ArrayList<>();
        stringList.add("保密");
        stringList.add("男");
        stringList.add("女");
        optionsPickerView = new OptionsPickerView.Builder(mActivity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (options1 == 0) {
                    setSexHttp(3);
                } else {
                    setSexHttp(options1);
                }

            }
        })
                .setSubmitColor(ZhuanHuanUtil.getColor(R.color.primary_hong))
                .setCancelColor(ZhuanHuanUtil.getColor(R.color.primary_hong))
                .build();
        optionsPickerView.setNPicker(stringList, null, null);
        niChenDialog = new XiuGaiNiChengDialog(mActivity);
        niChenDialog.setClicklistener(new XiuGaiNiChengDialog.ClickListenerInterface() {
            @Override
            public void onRes(String res) {
                setNickName(res);
            }
        });
    }

    private void setBirthdayHttp(final long time) {
        DialogUtil.getInstance().show_noBack(mActivity);
        final long value = time / 1000;
        OkGo.<HttpResModel<String>>post(updateUserInfo)
                .params("birthday", value)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        SpDataUtil.setBirthday(value);
                        tvBirthday.setText(ZhuanHuanUtil.Time2nian(time));
                    }
                });
    }

    private void setSexHttp(final int options1) {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<String>>post(updateUserInfo)
                .params("sex", options1)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        SpDataUtil.setSex(options1);
                        tvSex.setText(SpDataUtil.getUser().getSex());
                    }
                });
    }

    private void setNickName(final String nickName) {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<String>>post(updateUserInfo)
                .params("nickname", nickName)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        SpDataUtil.setNiceName(nickName);
                        tvNickName.setText(nickName);
                    }
                });
    }

    @OnClick({R.id.layout_icon, R.id.layout_name, R.id.layout_sex, R.id.layout_birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_icon:
                imageSelectManager.dialog_show();
                break;
            case R.id.layout_name:
                niChenDialog.show();
                break;
            case R.id.layout_sex:
                optionsPickerView.show();
                break;
            case R.id.layout_birthday:
                timePickerView.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageSelectManager.onActivityResult(requestCode, resultCode, data);
    }


    private void upLoadImgHead(final File file) {
        DialogUtil.getInstance().show_noBack(mActivity, "上传中..");
        OkGo.<HttpResModel<String>>post(upload_headpic)
                .params("head_pic", file)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        ImageLoader.with_head(mActivity, file, imgIcon);
                        SpDataUtil.setImgHead(stringHttpResModel.getResult());
                    }
                });
    }
}
