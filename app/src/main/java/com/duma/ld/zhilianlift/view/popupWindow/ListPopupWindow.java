package com.duma.ld.zhilianlift.view.popupWindow;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.BasePopWindos;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.model.QuModel;
import com.duma.ld.zhilianlift.widget.CheckBoxNoOnClick;

import java.util.List;

/**
 * Created by liudong on 2017/12/21.
 */

public class ListPopupWindow extends BasePopWindos {
    public BaseAdapter<QuModel> adapter;

    public ListPopupWindow(Activity activity) {
        super(activity, R.layout.pop_list);
    }


    @Override
    protected void initData(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_list);
        adapter = new BaseAdapter.Builder<QuModel>(recyclerView, mActivity, R.layout.adapter_pop_list)
                .build(new OnBaseAdapterListener<QuModel>() {
                    @Override
                    public void convert(BaseViewHolder helper, QuModel item) {
                        TextView tv_name = helper.getView(R.id.tv_name);
                        CheckBoxNoOnClick cb_checked = helper.getView(R.id.cb_checked);
                        tv_name.setText(item.getName());
                    }
                });
    }

    public void setList(List<QuModel> list) {
        adapter.setNewData(list);
    }
}
