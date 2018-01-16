package com.duma.ld.zhilianlift.view.main.shopping.afterSales;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.AfterSalesInfoModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.return_goods_info;

/**
 * 售后详情
 * Created by liudong on 2018/1/12.
 */

public class AfterSalesInfoActivity extends BaseMyActivity {
    @BindView(R.id.img_courier_state)
    ImageView imgCourierState;
    @BindView(R.id.tv_courier_state)
    TextView tvCourierState;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_spec)
    TextView tvSpec;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_YuanYin)
    TextView tvYuanYin;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_Num)
    TextView tvNum;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_goods_title)
    TextView tvGoodsTitle;
    private String id;
    private BaseAdapter<AfterSalesInfoModel.OrderStatusContextBean> mAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_after_sales_info).setTopBar_A("售后详情");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        id = getIntent().getStringExtra(Constants.id);
        mAdapter = new BaseAdapter.Builder<AfterSalesInfoModel.OrderStatusContextBean>(rvList, mActivity, R.layout.adapter_after_sales_info)
                .setNoEnpty()
                .isNested()
                .build(new OnBaseAdapterListener<AfterSalesInfoModel.OrderStatusContextBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, AfterSalesInfoModel.OrderStatusContextBean item) {
                        View view_top = helper.getView(R.id.view_top);
                        View view_bottom = helper.getView(R.id.view_bottom);
                        ImageView img_icon = helper.getView(R.id.img_icon);
                        if (helper.getLayoutPosition() == 0) {
                            view_top.setVisibility(View.INVISIBLE);
                            view_bottom.setVisibility(View.VISIBLE);
                            img_icon.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.dian1));
                        } else if (helper.getLayoutPosition() == (mAdapter.getData().size() - 1)) {
                            view_top.setVisibility(View.VISIBLE);
                            view_bottom.setVisibility(View.INVISIBLE);
                            img_icon.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.dian2));
                        } else {
                            view_top.setVisibility(View.VISIBLE);
                            view_bottom.setVisibility(View.VISIBLE);
                            img_icon.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.dian2));
                        }
                        helper.setText(R.id.tv_name, item.getContext())
                                .setText(R.id.tv_time, ZhuanHuanUtil.Time2fen(item.getAdd_time() * 1000));
                    }
                });
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<AfterSalesInfoModel>>get(return_goods_info)
                .tag(httpTag)
                .params("id", id)
                .execute(new MyJsonCallback<HttpResModel<AfterSalesInfoModel>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<AfterSalesInfoModel>> respons, HttpResModel<AfterSalesInfoModel> afterSalesInfoModelHttpResModel) {
                        initData(afterSalesInfoModelHttpResModel.getResult());
                    }
                });
    }

    private void initData(AfterSalesInfoModel result) {
        tvGoodsTitle.setText(result.getGoods_name());
        tvSpec.setText("数量:" + result.getGoods_num() + " " + result.getSpec_key_name_noNull());
        tvPrice.setText("¥" + result.getGoods_price());
        ImageLoader.with(mActivity, result.getOriginal_img(), imgIcon);
        double money = result.getRefund_integral() + result.getRenovation_money() + result.getRefund_money();
        mAdapter.setNewData(result.getOrder_status_context());
        switch (result.getType()) {
            case 0:
                //退款
                tvYuanYin.setText("退款原因：" + result.getReason());
                tvMoney.setText("退款金额：¥" + money + "(积分" + result.getRefund_integral() + "+装修资金" + result.getRenovation_money() + "+余额" + result.getRefund_money() + ")");
                tvNum.setText("退款编号：" + result.getReturn_code());
                break;
            case 1:
                //退货
                tvYuanYin.setText("退货原因：" + result.getReason());
                tvMoney.setText("退货数量：" + result.getGoods_num());
                tvNum.setText("退货编号：" + result.getReturn_code());
                break;
            case 2:
                //换货
                tvYuanYin.setText("换货原因：" + result.getReason());
                tvMoney.setText("换货数量：" + result.getGoods_num());
                tvNum.setText("换货编号：" + result.getReturn_code());
                break;
        }
        if (result.getOrder_status_context() == null || result.getOrder_status_context().size() == 0) {
            tvCourierState.setText("审核中...");
        } else {
            tvCourierState.setText(result.getOrder_status_context().get(0).getContext());
        }
    }

}
