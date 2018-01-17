package com.duma.ld.zhilianlift.view.dialog;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.BaseDownDialog;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.model.HouseHttpInfoModel;

import java.util.List;

/**
 * 添加房屋页面的 物业类型 dialog
 * Created by liudong on 2018/1/12.
 */

public class HouseListDialog extends BaseDownDialog {
    private FrameLayout layout_back;
    private RecyclerView rv_list;
    private TextView tv_name;
    private OnStringClickListener onStringClickListener;
    private List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mList;
    private BaseAdapter<HouseHttpInfoModel.FilterAttrBean.ItemBean> mAdapter;

    public HouseListDialog(@NonNull Activity context, List<HouseHttpInfoModel.FilterAttrBean.ItemBean> list, OnStringClickListener onStringClickListener) {
        super(context);
        this.onStringClickListener = onStringClickListener;
        this.mList = list;
    }


    public interface OnStringClickListener {
        void onItemClick(HouseHttpInfoModel.FilterAttrBean.ItemBean itemBean, int position);
    }

    @Override
    protected void initData() {
        layout_back = findViewById(R.id.layout_back);
        rv_list = findViewById(R.id.rv_list);
        tv_name = findViewById(R.id.tv_name);
        tv_name.setText("物业类型");
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mAdapter = new BaseAdapter.Builder<HouseHttpInfoModel.FilterAttrBean.ItemBean>(rv_list, mActivity, R.layout.adapter_dialog_house)
                .setNoEnpty()
                .build(new OnBaseAdapterListener<HouseHttpInfoModel.FilterAttrBean.ItemBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, HouseHttpInfoModel.FilterAttrBean.ItemBean item) {
                        helper.setText(R.id.tv_text, item.getSo_name());
                    }
                });
        mAdapter.setNewData(mList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onStringClickListener.onItemClick(mAdapter.getItem(position), position);
                dismiss();
            }
        });
    }

    public void setName(String s) {
        tv_name.setText(s);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.dialog_apply_refund;
    }
}
