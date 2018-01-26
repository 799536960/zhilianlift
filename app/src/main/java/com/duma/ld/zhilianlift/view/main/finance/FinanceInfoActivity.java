package com.duma.ld.zhilianlift.view.main.finance;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import com.duma.ld.zhilianlift.model.FinanceInfoModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.getCredit_Schedule;

/**
 * 金融详情
 * Created by liudong on 2018/1/26.
 */

public class FinanceInfoActivity extends BaseMyActivity {
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private String id;
    private BaseAdapter<FinanceInfoModel.ScheduleCreditListBean> mAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_finance_info).setTopBar_A("申请进度");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        id = getIntent().getStringExtra(Constants.id);
        mAdapter = new BaseAdapter.Builder<FinanceInfoModel.ScheduleCreditListBean>(rvList, mActivity, R.layout.adapter_after_sales_info)
                .setNoEnpty()
                .isNested()
                .build(new OnBaseAdapterListener<FinanceInfoModel.ScheduleCreditListBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, FinanceInfoModel.ScheduleCreditListBean item) {
                        PublicUtil.getViewType(helper, mAdapter);
                        helper.setText(R.id.tv_name, item.getContext())
                                .setText(R.id.tv_time, ZhuanHuanUtil.Time2fen(item.getAdd_time() * 1000));
                    }
                });
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<FinanceInfoModel>>get(getCredit_Schedule)
                .params("id", id)
                .execute(new MyJsonCallback<HttpResModel<FinanceInfoModel>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<FinanceInfoModel>> respons, HttpResModel<FinanceInfoModel> stringHttpResModel) {
                        initData(stringHttpResModel.getResult());
                    }
                });
    }

    private void initData(FinanceInfoModel result) {
        mAdapter.setNewData(result.getSchedule_creditList());
        if (result.getCredit().getCredit_is() == 3) {
            tvMoney.setText(result.getCredit().getMoney() + "");
        }
    }
}
