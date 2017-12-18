package com.duma.ld.zhilianlift.Adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.model.SettingModel;

/**
 * Created by liudong on 2017/12/18.
 */

public class SettingAdapter extends BaseQuickAdapter<SettingModel, BaseViewHolder> {
    public SettingAdapter() {
        super(R.layout.adapter_setting);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingModel item) {
        helper.setText(R.id.tv_name, item.getTitle());
        helper.setText(R.id.tv_phone, item.getMessage());
        ImageView imageView = helper.getView(R.id.img_jiantou);
        if (item.isImg()) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
