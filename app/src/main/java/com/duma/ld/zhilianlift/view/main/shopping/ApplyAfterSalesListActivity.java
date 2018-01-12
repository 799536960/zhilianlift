package com.duma.ld.zhilianlift.view.main.shopping;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.get_return_goods_status;

/**
 * 申请售后列表
 * Created by liudong on 2018/1/12.
 */

public class ApplyAfterSalesListActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private List<OrderModel.OrderGoodsBean> mList;
    private String master_order_sn;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_after_sales_list, false).setTopBar_A("申请售后");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        master_order_sn = getIntent().getStringExtra(Constants.key);
        mList = (List<OrderModel.OrderGoodsBean>) getIntent().getSerializableExtra(Constants.Model);
        if (mList == null || mList.size() == 0) {
            finish();
            TsUtils.show("获取商品出错!");
        }
        if (mList.size() == 1) {
            afterSalesHttp(mList.get(0));
        }
        final BaseAdapter<OrderModel.OrderGoodsBean> build = new BaseAdapter.Builder<OrderModel.OrderGoodsBean>(rvList, mActivity, R.layout.adapter_order_goods3)
                .build(new OnBaseAdapterListener<OrderModel.OrderGoodsBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, OrderModel.OrderGoodsBean item) {
                        PublicUtil.getViewOrderGoods(helper, item, mActivity);
                        helper.addOnClickListener(R.id.tv_afterSales);
                    }
                });
        build.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                afterSalesHttp(build.getData().get(position));
            }
        });
        build.setNewData(mList);
    }

    private void afterSalesHttp(final OrderModel.OrderGoodsBean orderGoodsBean) {
        OkGo.<HttpResModel<String>>get(get_return_goods_status)
                .params("master_order_sn", master_order_sn)
                .params("rec_id", orderGoodsBean.getRec_id())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        switch (stringHttpResModel.getResult()) {
                            case "1":
                                //售后详情
                                IntentUtil.goAfterSalesInfo(mActivity, orderGoodsBean);
                                break;
                            case "2":
                                //申请退款页面
                                IntentUtil.goApplyRefund(mActivity, orderGoodsBean);
                                break;
                            case "3":
                                //申请退货页面
                                IntentUtil.goApplyAfterSales(mActivity, orderGoodsBean);
                                break;
                        }
                        if (mList.size() == 1) {
                            finish();
                        }
                    }
                }.isDialog(mActivity));
    }
}
