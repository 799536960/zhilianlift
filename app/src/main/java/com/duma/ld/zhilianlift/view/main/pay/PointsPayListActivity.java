package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HousePayListModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.account_log;

/**
 * 积分明细
 * Created by liudong on 2018/1/16.
 */

public class PointsPayListActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;
    private BaseAdapter<HousePayListModel.AccountLogListBean> mAdapter;
    private TextView tv_money, tv_yue, tv_all;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_points_pay_list).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading).setTopBar_A("补贴积分");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mAdapter = new BaseAdapter.Builder<HousePayListModel.AccountLogListBean>(rvList, mActivity, R.layout.adapter_house_pay_list)
                .isNested()
                .buildLoad(new OnBaseLoadAdapterListener<HousePayListModel.AccountLogListBean>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<HousePayListModel>>get(account_log)
                                .tag(httpTag)
                                .params("type", 2)
                                .params(Constants.Page, page)
                                .execute(new MyJsonCallback<HttpResModel<HousePayListModel>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<HousePayListModel>> respons, HttpResModel<HousePayListModel> myRecordModel) {
                                        mAdapter.setLoadData(myRecordModel.getResult().getAccount_logList());
                                        setData(myRecordModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, HousePayListModel.AccountLogListBean item) {
                        helper.setText(R.id.tv_content, item.getDesc() + "(订单号：" + item.getOrder_sn() + ")")
                                .setText(R.id.tv_time, ZhuanHuanUtil.Time2fen(item.getChange_time() * 1000));
                        TextView tv_num = helper.getView(R.id.tv_num);
                        if (item.getPay_points() < 0) {
                            tv_num.setText("" + item.getPay_points());
                            tv_num.setTextColor(ZhuanHuanUtil.getColor(R.color.lv));
                        } else {
                            tv_num.setText("+" + item.getPay_points());
                            tv_num.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                        }
                    }
                });
        View view = mAdapter.getView(R.layout.head_points_pay);
        tv_money = view.findViewById(R.id.tv_money);
        tv_yue = view.findViewById(R.id.tv_yue);
        tv_all = view.findViewById(R.id.tv_all);
        mAdapter.addHeaderView(view);
        mAdapter.setHeaderAndEmpty(true);
        onClickLoadingRefresh();
    }

    private void setData(HousePayListModel users) {
        tv_money.setText(users.getUsers().getPay_points());
        tv_yue.setText("本月获取：" + users.getPay_points_sum_monthNull());
        tv_all.setText("累计获取：" + users.getPay_points_sumNulll());
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }

}
