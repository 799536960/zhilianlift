package com.duma.ld.zhilianlift.base.baseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by liudong on 2017/12/4.
 */

public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }
}
