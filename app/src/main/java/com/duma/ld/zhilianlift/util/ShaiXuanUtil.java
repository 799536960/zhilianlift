package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.Adapter.ScreeningAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.model.ScreeningModel;
import com.duma.ld.zhilianlift.model.ScreeningSelectListModel;
import com.duma.ld.zhilianlift.widget.CheckBoxGoodsList;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品筛选 和 房产筛选的控制
 * <p>
 * 打开
 * 重置
 * 确定
 * set数据
 * 获取选中数据
 * <p>
 * Created by liudong on 2018/1/18.
 */

public class ShaiXuanUtil {
    private List<ScreeningSelectListModel> list_shaiXuan;
    private List<ScreeningModel> listScreen;
    private ScreeningAdapter screeningAdapter;
    private boolean flag;
    private DrawerLayout layoutDrawerLayout;
    private RecyclerView rvScreen;
    private Activity mActivity;
    private CheckBoxGoodsList cbShaiXuan;
    private OnShaiXuanUtilListener shaiXuanUtilListener;

    public ShaiXuanUtil(DrawerLayout layoutDrawerLayout, RecyclerView rvScreen, Activity mActivity, CheckBoxGoodsList cbShaiXuan, OnShaiXuanUtilListener shaiXuanUtilListener) {
        this.layoutDrawerLayout = layoutDrawerLayout;
        this.rvScreen = rvScreen;
        this.mActivity = mActivity;
        this.cbShaiXuan = cbShaiXuan;
        this.shaiXuanUtilListener = shaiXuanUtilListener;
        initScreenAdapter();
    }


    public interface OnShaiXuanUtilListener {
        void onRefresh();
    }

    //初始化筛选适配器
    private void initScreenAdapter() {
        list_shaiXuan = new ArrayList<>();
        layoutDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        layoutDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                if (list_shaiXuan.size() == 0) {
                    cbShaiXuan.setChecked(false);
                } else {
                    cbShaiXuan.setChecked(true);
                }
                if (flag) {
                    flag = false;
                    shaiXuanUtilListener.onRefresh();
                }
            }
        });
        listScreen = new ArrayList<>();
        screeningAdapter = new ScreeningAdapter(listScreen);
        screeningAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return listScreen.get(position).getSpanSize();
            }
        });
        rvScreen.setLayoutManager(new GridLayoutManager(mActivity, 3));
        rvScreen.setAdapter(screeningAdapter);
        screeningAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //筛选点击事件
                ScreeningModel model = (ScreeningModel) adapter.getData().get(position);
                if (model.getItemType() == ScreeningModel.content) {
                    model.setChenked(!model.isChenked());
                    TextView textView = (TextView) adapter.getViewByPosition(rvScreen, position, R.id.tv_content);
                    flag = true;
                    if (model.isChenked()) {
                        //选中
                        ScreeningSelectListModel.addModel(model.getItemBean(), list_shaiXuan);
                        textView.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.bx_4_hong));
                        textView.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                    } else {
                        //反选
                        ScreeningSelectListModel.removeModel(model.getItemBean(), list_shaiXuan);
                        textView.setBackground(ZhuanHuanUtil.getDrawable(R.drawable.lr_4_hui2));
                        textView.setTextColor(ZhuanHuanUtil.getColor(R.color.hei1));
                    }
                }
            }
        });
    }


    public void openDrawer() {
        if (listScreen == null || listScreen.size() == 0) {
            TsUtils.show("暂无筛选数据!");
        } else {
            layoutDrawerLayout.openDrawer(GravityCompat.END);
        }
    }

    public void closeDrawer() {
        layoutDrawerLayout.closeDrawer(GravityCompat.END);
    }

    public void cancelDrawer() {
        for (int i = 0; i < listScreen.size(); i++) {
            listScreen.get(i).setChenked(false);
        }
        screeningAdapter.notifyDataSetChanged();
        //当前记录清空
        list_shaiXuan.clear();
        flag = true;
        layoutDrawerLayout.closeDrawer(GravityCompat.END);
    }

    public List<ScreeningModel> getListScreen() {
        return listScreen;
    }

    public List<ScreeningSelectListModel> getList_shaiXuan() {
        return list_shaiXuan;
    }

    public ScreeningAdapter getScreeningAdapter() {
        return screeningAdapter;
    }
}
