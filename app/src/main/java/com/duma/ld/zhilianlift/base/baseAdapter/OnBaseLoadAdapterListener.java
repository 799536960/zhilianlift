package com.duma.ld.zhilianlift.base.baseAdapter;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by liudong on 2017/12/13.
 */

public interface OnBaseLoadAdapterListener<T> {
    void onLoadHttp(int page, int size);

    void convert(BaseViewHolder helper, T item);
}
