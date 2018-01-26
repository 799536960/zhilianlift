package com.duma.ld.zhilianlift.view.main.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.ClassModel;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 分类的右边商品列表 里面是 二级和三级 分类
 * Created by liudong on 2017/12/4.
 */

public class Class_2_Fragment extends BaseMyFragment {

    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.rv_class_2)
    RecyclerView rvClass2;

    private BaseAdapter<ClassModel.ListBean> baseAdapter;
    private ClassModel model;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_class_2, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        baseAdapter = new BaseAdapter<ClassModel.ListBean>(R.layout.adapter_class_2) {
            @Override
            protected void convert(BaseViewHolder helper, ClassModel.ListBean item) {
                helper.setText(R.id.tv_name, item.getMobile_name());
                final BaseAdapter<ClassModel.ListBean.SubCategoryBean> adapter_3 = new BaseAdapter<ClassModel.ListBean.SubCategoryBean>(R.layout.adapter_class_3) {
                    @Override
                    protected void convert(BaseViewHolder helper, ClassModel.ListBean.SubCategoryBean item) {
                        ImageLoader.with(mActivity, item.getImage(), (ImageView) helper.getView(R.id.img_title));
                        helper.setText(R.id.tv_name, item.getMobile_name());
                    }
                };
                adapter_3.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (adapter_3.getData().get(position).getId()) {
                            case 855:
                                //二手房
                                IntentUtil.goHouseList_erShoufang(mActivity);
                                break;
                            case 854:
                                //出租房
                                IntentUtil.goHouseList_zuFang(mActivity);
                                break;
                            case 853:
                                //新房
                                IntentUtil.goHouseList_xinFang(mActivity);
                                break;
                            default:
                                IntentUtil.goGoodsList_class(mActivity, adapter_3.getData().get(position).getId() + "");
                        }
                    }
                });
                RecyclerView rv_class_3 = helper.getView(R.id.rv_class_3);
                rv_class_3.setLayoutManager(new GridLayoutManager(mActivity, 3));
                rv_class_3.setFocusable(false);
                rv_class_3.setNestedScrollingEnabled(false);
                rv_class_3.setAdapter(adapter_3);
                adapter_3.setNewData(item.getSub_category());
            }
        };
        rvClass2.setLayoutManager(new LinearLayoutManager(mActivity));
        rvClass2.setFocusable(false);
        rvClass2.setNestedScrollingEnabled(false);
        rvClass2.setAdapter(baseAdapter);
    }

    @OnClick(R.id.img_title)
    public void onViewClicked() {
        IntentUtil.goWebView(mActivity, model.getUrl());
    }

    public void setData(ClassModel model) {
        this.model = model;
        baseAdapter.setNewData(model.getList());
        ImageLoader.with(mActivity, model.getImage(), imgTitle);
    }
}
