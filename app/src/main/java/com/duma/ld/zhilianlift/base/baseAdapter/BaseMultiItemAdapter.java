package com.duma.ld.zhilianlift.base.baseAdapter;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;

import java.util.List;

/**
 * Created by liudong on 2017/12/29.
 */

public abstract class BaseMultiItemAdapter<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter<T, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseMultiItemAdapter(List<T> data) {
        super(data);
    }

    public void setEmptyLayout(RecyclerView recyclerView) {
        setEmptyLayout("哦~该列表空空的~", R.drawable.img_81, recyclerView);
    }

    public void setEmptyLayout(String title, RecyclerView recyclerView) {
        setEmptyLayout(title, R.drawable.img_81, recyclerView);
    }

    public void setEmptyLayout(@DrawableRes int drawableId, RecyclerView recyclerView) {
        setEmptyLayout("哦~该列表空空的~", drawableId, recyclerView);
    }

    public void setEmptyLayout(String title, @DrawableRes int drawableId, RecyclerView recyclerView) {
        setEmptyView(R.layout.include_no_data, recyclerView);
        View view = getEmptyView();
        TextView textView = view.findViewById(R.id.tv_adapter_name);
        ImageView imageView = view.findViewById(R.id.img_adapter_nodata);
        textView.setText(title);
        imageView.setImageDrawable(ZhuanHuanUtil.getDrawable(drawableId));

    }
}
