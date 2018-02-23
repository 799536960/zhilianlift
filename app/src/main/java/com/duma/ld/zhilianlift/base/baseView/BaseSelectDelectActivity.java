package com.duma.ld.zhilianlift.base.baseView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.widget.CheckBoxNoOnClick;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 2018/1/2.
 */

public abstract class BaseSelectDelectActivity extends BaseMyActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete_all)
    TextView tvDeleteAll;
    @BindView(R.id.cb_type)
    TextView cbType;
    @BindView(R.id.rv_list)
    protected RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.cb_Select_All)
    protected CheckBoxNoOnClick cbSelectAll;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.layout_content)
    LinearLayout layoutContent;
    @BindView(R.id.layout_root)
    FrameLayout layoutRoot;

    protected boolean isEdit;
    private BaseAdapter baseAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_base_selelct_delect).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.layout_content);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        tvTitle.setText(getTitleName());
        baseAdapter = initAdapter();
        onClickLoadingRefresh();
        setEdit(false);
        cbSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllSelect(cbSelectAll.isChecked());
                baseAdapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.layout_back, R.id.tv_delete_all, R.id.tv_delete, R.id.cb_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                mActivity.finish();
                break;
            case R.id.tv_delete_all:
                if (baseAdapter.getData().size() == 0) {
                    TsUtils.show("没有需要清空的哦~");
                    return;
                }
                AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "清空", "是否清空" + getTitleName())
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                removeAll();
                            }
                        })
                        .setNegativeButton("否", null)
                        .setCancelable(false);
                builder.show();
                break;
            case R.id.tv_delete:
                onDeleteOnclick();
                break;
            case R.id.cb_type:
                if (baseAdapter.getData().size() == 0) {
                    TsUtils.show("当前没有商品!");
                    return;
                }
                setEdit(!isEdit);
                break;
        }
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        baseAdapter.onRefresh();
    }

    protected void setEdit(boolean intEdit) {
        isEdit = intEdit;
        if (intEdit) {
            //编辑状态
            cbType.setText("完成");
            layoutBottom.setVisibility(View.VISIBLE);
            //编辑状态
            setAllSelect(false);
        } else {
            cbSelectAll.setChecked(false);
            cbType.setText("编辑");
            layoutBottom.setVisibility(View.GONE);
        }
        baseAdapter.notifyDataSetChanged();
    }

    protected abstract void setAllSelect(boolean checked);

    protected abstract BaseAdapter initAdapter();

    protected abstract String getTitleName();

    protected abstract void onDeleteOnclick();

    protected abstract void removeAll();
}
