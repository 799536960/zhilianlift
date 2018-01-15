package com.duma.ld.zhilianlift.view.popupWindow;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.model.QuModel;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.widget.LinearImageLayout;

import razerdp.basepopup.BasePopupWindow;

/**
 * 展示商品详情页面的区域pop
 * Created by liudong on 2017/12/21.
 */

public class GoodsInfoPopupWindow extends BasePopupWindow implements View.OnClickListener {
    private Activity activity;
    public BaseAdapter<QuModel> adapter;
    private LinearLayout layout_message, layout_home, layout_search, layout_collect, layout_jilu;
    private LinearImageLayout img_message;

    public GoodsInfoPopupWindow(Activity context) {
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
        return createPopupById(R.layout.pop_goods_info);
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    protected void initData() {
        layout_message = getPopupWindowView().findViewById(R.id.layout_message);
        layout_home = getPopupWindowView().findViewById(R.id.layout_home);
        layout_search = getPopupWindowView().findViewById(R.id.layout_search);
        layout_collect = getPopupWindowView().findViewById(R.id.layout_collect);
        layout_jilu = getPopupWindowView().findViewById(R.id.layout_jilu);
        img_message = getPopupWindowView().findViewById(R.id.img_message);

        layout_message.setOnClickListener(this);
        layout_home.setOnClickListener(this);
        layout_search.setOnClickListener(this);
        layout_collect.setOnClickListener(this);
        layout_jilu.setOnClickListener(this);
    }

    public void setMessageNum(int num) {
        img_message.setNum(num + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_home:
                IntentUtil.goMain(activity);
                return;
            case R.id.layout_search:
                IntentUtil.goSearch(activity);
                return;
        }
        if (!SpDataUtil.isLogin()) {
            IntentUtil.goLogin(activity);
            return;
        }
        switch (v.getId()) {
            case R.id.layout_message:
                IntentUtil.goMessage(activity);
                break;
            case R.id.layout_collect:
                IntentUtil.goMyCollect(activity);
                break;
            case R.id.layout_jilu:
                IntentUtil.goMyRecord(activity);
                break;
        }
    }
}
