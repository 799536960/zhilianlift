package com.duma.ld.zhilianlift.view.main.wode;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 2017/12/8.
 */

public class SettingActivity extends BaseMyActivity {
    @BindView(R.id.tv_list)
    RecyclerView tvList;
    @BindView(R.id.tv_remove_login)
    TextView tvRemoveLogin;

    private BaseAdapter<String> adapter;
    private List<String> list;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_setting, false).setTopBar_A("账户设置");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        adapter = new BaseAdapter<String>(R.layout.adapter_setting) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_name, item);
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //个人资料
                        IntentUtil.goUserData(mActivity);
                        break;
                    case 1:
                        //实名认证
                        break;
                    case 2:
                        //地址管理
                        IntentUtil.goAddresList(mActivity);
                        break;
                    case 3:
                        //账户与安全
                        break;
                    case 4:
                        //建议反馈
                        break;
                    case 5:
                        //清理缓存
                        break;
                    case 6:
                        //关于我们
                        break;
                }
            }
        });
        tvList.setLayoutManager(new LinearLayoutManager(mActivity));
        tvList.setAdapter(adapter);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add("个人资料");
        list.add("实名认证");
        list.add("地址管理");
        list.add("账户与安全");
        list.add("建议反馈");
        list.add("清理缓存");
        list.add("关于我们");
        adapter.setNewData(list);
    }

    @OnClick(R.id.tv_remove_login)
    public void onViewClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                .setTitle("退出登录")
                .setMessage("您确定要退出登录嘛?")
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
