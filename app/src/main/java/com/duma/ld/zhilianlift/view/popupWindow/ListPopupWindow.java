package com.duma.ld.zhilianlift.view.popupWindow;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.model.QuModel;
import com.duma.ld.zhilianlift.util.SpDataUtil;

import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * 展示商品详情页面的区域pop
 * Created by liudong on 2017/12/21.
 */

public class ListPopupWindow extends BasePopupWindow {
    private Activity activity;
    public BaseAdapter<QuModel> adapter;

    public ListPopupWindow(Activity context) {
        super(context);
        this.activity = context;
        initData();
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.pop_list);
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    protected void initData() {
        RecyclerView recyclerView = getPopupWindowView().findViewById(R.id.rv_list);
        adapter = new BaseAdapter.Builder<QuModel>(recyclerView, activity, R.layout.adapter_pop_list)
                .build(new OnBaseAdapterListener<QuModel>() {
                    @Override
                    public void convert(BaseViewHolder helper, QuModel item) {
                        TextView tv_name = helper.getView(R.id.tv_name);
                        tv_name.setText(item.getName());
                        if (item.getName().contains(SpDataUtil.getLocation().getDistrict())) {
                            tv_name.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                        } else {
                            tv_name.setTextColor(ZhuanHuanUtil.getColor(R.color.hei1));
                        }
                    }
                });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                QuModel model = (QuModel) adapter.getData().get(position);
                SpDataUtil.setDistrict(model.getName(), model.getCode());
                adapter.notifyDataSetChanged();
                dismiss();
            }
        });
    }

    public void setList(List<QuModel> list) {
        adapter.setNewData(list);
    }
}
