package com.duma.ld.zhilianlift.view.main.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.imageSelect.ImageSelectManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请退款
 * Created by liudong on 2018/1/12.
 */

public class ApplyRefundActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.layout_edit)
    FrameLayout layoutEdit;
    @BindView(R.id.edit_money)
    EditText editMoney;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private ImageSelectManager imageSelectManager;
    private OrderModel.OrderGoodsBean order_goods;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_apply_refund, false).setTopBar_A("申请退款");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        order_goods = (OrderModel.OrderGoodsBean) getIntent().getSerializableExtra(Constants.Model);
        BaseAdapter<OrderModel.OrderGoodsBean> build = new BaseAdapter.Builder<OrderModel.OrderGoodsBean>(rvList, mActivity, R.layout.adapter_order_goods3)
                .build(new OnBaseAdapterListener<OrderModel.OrderGoodsBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, OrderModel.OrderGoodsBean item) {
                        PublicUtil.getViewOrderGoods(helper, item, mActivity);
                        helper.setGone(R.id.tv_afterSales, false);
                    }
                });
        build.addData(order_goods);
        imageSelectManager = ImageSelectManager.create(mActivity)
                .setMaxNum(6)
                .starRvStyle(rvPhoto);
    }

    @OnClick({R.id.layout_other, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_other:
                break;
            case R.id.tv_submit:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageSelectManager.onActivityResult(requestCode, resultCode, data);
    }

}
