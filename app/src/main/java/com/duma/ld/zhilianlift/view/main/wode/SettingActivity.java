package com.duma.ld.zhilianlift.view.main.wode;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.SettingAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.RealNameModel;
import com.duma.ld.zhilianlift.model.SettingModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.main.wode.realName.RealNameMainActivity;
import com.duma.ld.zhilianlift.view.main.wode.realName.RealNameSendSuccessActivity;
import com.duma.ld.zhilianlift.view.main.wode.userSecuryty.UserSecurityActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.getcertification;

/**
 * Created by liudong on 2017/12/8.
 */

public class SettingActivity extends BaseMyActivity {
    @BindView(R.id.tv_list)
    RecyclerView tvList;
    @BindView(R.id.tv_remove_login)
    TextView tvRemoveLogin;

    //    private BaseAdapter<String> adapter;
    private List<SettingModel> list;
    private SettingAdapter settingAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_setting, false).setTopBar_A("账户设置");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
//        adapter = new BaseAdapter<String>(R.layout.adapter_setting) {
//            @Override
//            protected void convert(BaseViewHolder helper, String item) {
//                helper.setText(R.id.tv_name, item);
//                ImageView imageView = helper.getView(R.id.img_jiantou);
//                if (helper.getLayoutPosition() == 5) {
//                    imageView.setVisibility(View.INVISIBLE);
//                } else {
//                    imageView.setVisibility(View.VISIBLE);
//                }
//            }
//        };
        settingAdapter = new SettingAdapter();
        settingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //个人资料
                        IntentUtil.goUserData(mActivity);
                        break;
                    case 1:
                        //实名认证
                        QueryRealNameHttp();
                        break;
                    case 2:
                        //地址管理
                        IntentUtil.goAddresList(mActivity);
                        break;
                    case 3:
                        //账户与安全
                        startActivity(new Intent(mActivity, UserSecurityActivity.class));
                        break;
                    case 4:
                        //建议反馈
                        startActivity(new Intent(mActivity, YiJianFanKuiActivity.class));
                        break;
                    case 5:
                        //清理缓存
                        TsUtils.show("清除成功!");
                        break;
                    case 6:
                        //关于我们
                        startActivity(new Intent(mActivity, GuanYuActivity.class));
                        break;
                }
            }
        });
        tvList.setLayoutManager(new LinearLayoutManager(mActivity));
        tvList.setAdapter(settingAdapter);
        initData();
    }

    //查询实名认证情况
    private void QueryRealNameHttp() {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<RealNameModel>>get(getcertification)
                .execute(new MyJsonCallback<HttpResModel<RealNameModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<RealNameModel>> respons, HttpResModel<RealNameModel> realNameModelHttpResModel) {
                        DialogUtil.getInstance().hide();
//                        获取实名状态
//                        certification_is = 0  //已提交 但是 未处理
//                        certification_is = 1 //通过
//                        certification_is = 2 //拒绝
//                        certification_is = 3  //没有提交实名信息
                        switch (realNameModelHttpResModel.getResult().getCertification_is()) {
                            case 0:
                                startActivity(new Intent(mActivity, RealNameSendSuccessActivity.class));
                                break;
                            case 1:
                                Intent intent = new Intent(mActivity, RealNameMainActivity.class);
                                intent.putExtra(Constants.Model, realNameModelHttpResModel.getResult());
                                startActivity(intent);
                                break;
                            case 2:
                                IntentUtil.goRealName_change(mActivity, realNameModelHttpResModel.getResult());
                                TsUtils.show("审核未通过,请修改后再提交审核!");
                                break;
                            case 3:
                                IntentUtil.goVerifyPhone_RealName(mActivity);
                                break;
                        }
                    }
                });
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new SettingModel("个人资料"));
        list.add(new SettingModel("实名认证"));
        list.add(new SettingModel("地址管理"));
        list.add(new SettingModel("账户与安全"));
        list.add(new SettingModel("建议反馈"));
        list.add(new SettingModel("清理缓存", "", false));
        list.add(new SettingModel("关于我们"));
        settingAdapter.setNewData(list);
    }

    @OnClick(R.id.tv_remove_login)
    public void onViewClicked() {
        AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "退出登录", "您确定要退出登录嘛?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SpDataUtil.removeUser();
                        finish();
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false);
        builder.show();

    }
}
