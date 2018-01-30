package com.duma.ld.zhilianlift.view.start;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.base.OnTopBarRightListener;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.ApiCacheModel;
import com.duma.ld.zhilianlift.util.ClipboardUtils;
import com.duma.ld.zhilianlift.util.PublicUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.sp_Api;

/**
 * 显示接口信息 调试用
 * Created by liudong on 2018/1/30.
 */

public class ApiActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    FrameLayout layoutRoot;
    private BaseAdapter<ApiCacheModel> mAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_api).setTopBar_A("api列表", "清空", new OnTopBarRightListener() {
            @Override
            public void onClick() {
                DataSupport.deleteAll(ApiCacheModel.class);
                onClickLoadingRefresh();
            }
        }).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }

    @Override
    protected void onDestroy() {
        SPUtils.getInstance().put(sp_Api, true);
        super.onDestroy();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mAdapter = new BaseAdapter.Builder<ApiCacheModel>(rvList, mActivity, R.layout.adapter_api)
                .build(new OnBaseAdapterListener<ApiCacheModel>() {
                    @Override
                    public void convert(BaseViewHolder helper, final ApiCacheModel item) {
                        TextView tv_httpUrl = helper.getView(R.id.tv_httpUrl);
                        TextView tv_content = helper.getView(R.id.tv_content);
                        TextView tv_type = helper.getView(R.id.tv_type);
                        TextView tv_time = helper.getView(R.id.tv_time);
                        TextView tv_web = helper.getView(R.id.tv_web);
                        tv_time.setText(ZhuanHuanUtil.Time2fen(item.getTime()));
                        tv_web.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                Uri url = Uri.parse(item.getUrl());
                                intent.setData(url);
                                startActivity(intent);
                            }
                        });
                        if (StringUtils.isEmpty(item.getCanShu())) {
                            tv_content.setVisibility(View.GONE);
                        } else {
                            tv_content.setVisibility(View.VISIBLE);
                            tv_content.setText("参数:" + item.getCanShu());
                        }
                        if (item.isQinQiu()) {
                            tv_type.setText("请求类型:" + item.getHttpType());
                            tv_httpUrl.setText(item.getUrl());
                            tv_httpUrl.setTextColor(ZhuanHuanUtil.getColor(R.color.lv));
                        } else {
                            int code = item.getCode();
                            tv_type.setText("响应码:" + code);
                            tv_httpUrl.setText(item.getUrl());
                            tv_httpUrl.setTextColor(ZhuanHuanUtil.getColor(R.color.huang2));
                            if (code != 200) {
                                tv_httpUrl.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                            }
                        }

                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ApiCacheModel apiCacheModel = mAdapter.getData().get(position);
                String message = "";
                if (!StringUtils.isEmpty(apiCacheModel.getBody())) {
                    message = apiCacheModel.getBody();
                } else if (!StringUtils.isEmpty(apiCacheModel.getCanShu())) {
                    message = apiCacheModel.getCanShu();
                }
                if (StringUtils.isEmpty(message)) {
                    TsUtils.show("没有可复制的!");
                    return;
                }
                final String finalMessage = message;
                AlertDialog.Builder builder = PublicUtil.getAlertDialog_nessage(mActivity, message)
                        .setPositiveButton("复制", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ClipboardUtils.copyText(finalMessage);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(false);
                builder.show();
            }
        });
        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                ClipboardUtils.copyText(mAdapter.getData().get(position).getUrl());
                TsUtils.show("复制uri成功");
                return true;
            }
        });
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        List<ApiCacheModel> modelList = DataSupport.order("id desc").find(ApiCacheModel.class);
        mAdapter.setNewData(modelList);
        mActivityConfig.hideLoadingView();
    }

    @OnClick(R.id.rv_list)
    public void onViewClicked() {

    }
}
