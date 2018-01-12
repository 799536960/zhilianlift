package com.duma.ld.zhilianlift.view.dialog;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.BaseDownDialog;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 退款原因
 * Created by liudong on 2018/1/12.
 */

public class ApplyRefundDialog extends BaseDownDialog {
    private FrameLayout layout_back;
    private RecyclerView rv_list;
    private OnStringClickListener onStringClickListener;
    private int position = -1;

    public ApplyRefundDialog(@NonNull Activity context, OnStringClickListener onStringClickListener) {
        super(context);
        this.onStringClickListener = onStringClickListener;
    }

    public interface OnStringClickListener {
        void onItemClick(boolean isOther, String content, int position);
    }

    @Override
    protected void initData() {
        layout_back = findViewById(R.id.layout_back);
        rv_list = findViewById(R.id.rv_list);
        final List<String> list = new ArrayList<>();
        list.add("操作有误（商品／地址选错）");
        list.add("重复下单／误下单");
        list.add("缺货");
        list.add("其他");
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        BaseAdapter<String> mAdapter = new BaseAdapter.Builder<String>(rv_list, mActivity, R.layout.adapter_dialog_apply_refund)
                .setNoEnpty()
                .build(new OnBaseAdapterListener<String>() {
                    @Override
                    public void convert(BaseViewHolder helper, String item) {
                        CheckBox cb_text = helper.getView(R.id.cb_text);
                        cb_text.setText(item);
                        if (position == helper.getLayoutPosition()) {
                            if (!cb_text.isChecked()) {
                                cb_text.setChecked(true);
                            }
                        } else {
                            cb_text.setChecked(false);
                        }
                        helper.addOnClickListener(R.id.cb_text);
                    }
                });
        mAdapter.setNewData(list);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ApplyRefundDialog.this.position = position;
                boolean isOther = false;
                if (position == (list.size() - 1)) {
                    isOther = true;
                }
                onStringClickListener.onItemClick(isOther, list.get(position), position);
                adapter.notifyDataSetChanged();
                dismiss();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_apply_refund;
    }
}
