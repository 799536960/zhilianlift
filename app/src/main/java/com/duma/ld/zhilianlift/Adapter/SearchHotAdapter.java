package com.duma.ld.zhilianlift.Adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.util.CacheUtil;
import com.google.android.flexbox.FlexboxLayoutManager;

/**
 * Created by liudong on 2017/12/6.
 */

public class SearchHotAdapter extends BaseAdapter<String> {
    private Activity activity;
    private RecyclerView recyclerView;
    private boolean isFlexboxLayout;
    //防止第一次加载出不来问题
    private boolean isOne = true;

    public SearchHotAdapter(Activity activity, RecyclerView recyclerView) {
        super(R.layout.adapter_hot);
        this.activity = activity;
        this.recyclerView = recyclerView;
    }

    public void switchType() {
        //如果有缓存 就是横着排列
        boolean b = !CacheUtil.isCache();
        //布局一样就不需要改变
        if (!isOne && b == this.isFlexboxLayout) {
            return;
        }
        isOne = false;
        this.isFlexboxLayout = b;
        if (isFlexboxLayout) {
            FlexboxLayoutManager manager = new FlexboxLayoutManager(activity);
            recyclerView.setLayoutManager(manager);
        } else {
            LinearLayoutManager layout = new LinearLayoutManager(activity);
            layout.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layout);
        }
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(this);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title, item);
        if (isFlexboxLayout) {

        }
    }
}
