package com.duma.ld.zhilianlift.view.main.wode;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.CouponsModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.getCouponList;

/**
 * 优惠券列表
 * last_order_amount 有的话 就是重购物车来的
 * Created by liudong on 2018/1/4.
 */

public class CouponsActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    FrameLayout layoutRoot;
    private BaseAdapter<CouponsModel> adapter;
    private String last_order_amount;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_coupons).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading).setTopBar_A("优惠券");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        last_order_amount = getIntent().getStringExtra(Constants.key);
        adapter = new BaseAdapter.Builder<CouponsModel>(rvList, mActivity, R.layout.adapter_coupons)
                .setTitleOrDrawableId("当前没有可用的优惠券~", R.drawable.youhuquan)
                .buildLoad(new OnBaseLoadAdapterListener<CouponsModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        GetRequest<HttpResModel<List<CouponsModel>>> httpResModelGetRequest = OkGo.<HttpResModel<List<CouponsModel>>>get(getCouponList).tag(httpTag);
                        if (last_order_amount != null) {
                            httpResModelGetRequest.params("last_order_amount", last_order_amount);
                        }
                        httpResModelGetRequest
                                .params(Constants.Page, page)
                                .execute(new MyJsonCallback<HttpResModel<List<CouponsModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<CouponsModel>>> respons, HttpResModel<List<CouponsModel>> listHttpResModel) {
                                        adapter.setLoadData(listHttpResModel.getResult());
                                    }
                                }.setLoadAdapter(adapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, CouponsModel item) {
                        SpannableStringBuilder spannableStringBuilder = new SpanUtils()
                                .append("¥")
                                .setFontSize(ConvertUtils.sp2px(15))
                                .append(item.getMoney())
                                .setFontSize(ConvertUtils.sp2px(30))
                                .create();
                        helper.setText(R.id.tv_money, spannableStringBuilder)
                                .setText(R.id.tv_name, item.getName())
                                .setText(R.id.tv_type, "使用说明：满" + item.getCondition() + "减" + item.getMoney())
                                .setText(R.id.tv_time, "有效期：" + ZhuanHuanUtil.Time2nian2(item.getUse_start_time_j()) + "至" + ZhuanHuanUtil.Time2nian2(item.getUse_end_time_j()))
                                .addOnClickListener(R.id.card_view);
                    }
                });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (last_order_amount != null) {
                    EventBusUtil.sendModel(Constants.event_conpons, CouponsActivity.this.adapter.getData().get(position).getId() + "");
                    finish();
                }
            }
        });
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        adapter.onRefresh();
    }

}
