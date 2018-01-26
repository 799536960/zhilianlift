package com.duma.ld.zhilianlift.view.main.wode;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.MyFinanceModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.getCreditList;

/**
 * 我的贷款
 * Created by liudong on 2018/1/26.
 */

public class MyFinanceActivity extends BaseMyActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.radio_left)
    RadioButton radioLeft;
    @BindView(R.id.radio_right)
    RadioButton radioRight;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;
    // 0  新居贷  1 按揭
    private int type;
    private BaseAdapter<MyFinanceModel> mAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_my_finance).setTopBar_A("我的贷款", new OnTopBarLeftListener() {
            @Override
            public void onClick() {
                onBack();
            }
        }).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        radioGroup.setOnCheckedChangeListener(this);
        mAdapter = new BaseAdapter.Builder<MyFinanceModel>(rvList, mActivity, R.layout.adapter_my_finance)
                .buildLoad(new OnBaseLoadAdapterListener<MyFinanceModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<List<MyFinanceModel>>>get(getCreditList)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .params("plan_type", type)
                                .execute(new MyJsonCallback<HttpResModel<List<MyFinanceModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<MyFinanceModel>>> respons, HttpResModel<List<MyFinanceModel>> listHttpResModel) {
                                        mAdapter.setLoadData(listHttpResModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, MyFinanceModel item) {
                        helper.setText(R.id.tv_money, "申请金额：" + item.getMoney());
                        helper.setText(R.id.tv_name, "" + item.getPlan_name());
                        helper.setText(R.id.tv_time, "申请时间：" + ZhuanHuanUtil.Time2fen(item.getAdd_time() * 1000));
                        TextView tv_status = helper.getView(R.id.tv_status);
                        //0 真在 1通过 2失败 3放款
                        switch (item.getCredit_is()) {
                            case 1:
                                tv_status.setText("审核通过");
                                tv_status.setTextColor(ZhuanHuanUtil.getColor(R.color.lv2));
                                break;
                            case 2:
                                tv_status.setText("审核失败");
                                tv_status.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                                break;
                            case 3:
                                tv_status.setText("已放款");
                                tv_status.setTextColor(ZhuanHuanUtil.getColor(R.color.lv2));
                                break;
                            default:
                                tv_status.setText("正在审核");
                                tv_status.setTextColor(ZhuanHuanUtil.getColor(R.color.hei2));
                                break;
                        }
                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtil.goFinanceInfo(mActivity, mAdapter.getData().get(position).getId());
            }
        });
        radioLeft.setChecked(true);
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_left:
                type = 0;
                break;
            case R.id.radio_right:
                type = 1;
                break;
        }
        onClickLoadingRefresh();
    }

    @Override
    protected void onBack() {
        IntentUtil.goMain(mActivity);
    }
}
