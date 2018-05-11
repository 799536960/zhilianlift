package com.duma.ld.zhilianlift.view.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.AddFinanceModel;
import com.duma.ld.zhilianlift.model.FinanceListModel;
import com.duma.ld.zhilianlift.model.FinanceTypeModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.main.finance.AddFinanceInfoActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.getCredit;
import static com.duma.ld.zhilianlift.util.HttpUrl.plan;

/**
 * 金融 贷款产品 列表
 * Created by liudong on 2018/2/1.
 */

public class FinanceListFragment extends BaseMyFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private BaseAdapter<FinanceListModel> mAdapter;
    // 0  新居贷  1 按揭
    private int type;

    public static FinanceListFragment newInstance(int type) {
        FinanceListFragment fragment = new FinanceListFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.Type, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_finance_list).setRefresh_f(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mFragmentConfig.showLoadingView();
        Bundle args = getArguments();
        if (args != null) {
            type = args.getInt(Constants.Type);
        }
        mAdapter = new BaseAdapter.Builder<FinanceListModel>(rvList, mActivity, R.layout.adapter_finance)
                .buildLoad(new OnBaseLoadAdapterListener<FinanceListModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<List<FinanceListModel>>>get(plan)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .params("plan_type", type)
                                .execute(new MyJsonCallback<HttpResModel<List<FinanceListModel>>>(mFragmentConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<FinanceListModel>>> respons, HttpResModel<List<FinanceListModel>> listHttpResModel) {
                                        mAdapter.setLoadData(listHttpResModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, FinanceListModel item) {
                        ImageLoader.with(item.getPlan_img(), (ImageView) helper.getView(R.id.img_icon));
                        helper.setText(R.id.tv_money, item.getPlan_money() + "")
                                .setText(R.id.tv_name, item.getPlan_name() + "")
                                .setText(R.id.tv_info, item.getPlan_text() + "")
                                .setText(R.id.tv_shijian, item.getPlan_time() + "")
                                .setText(R.id.tv_zhouqi, item.getPlan_zhouqi() + "")
                                .setText(R.id.tv_fangshi, item.getPlan_fangshi() + "")
                                .setText(R.id.tv_lilv, item.getPlan_lilv() + "");
                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                addFinanceHttp(position);
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        onClickLoadingRefresh();
    }

    private void addFinanceHttp(final int position) {
        if (!SpDataUtil.isLogin()) {
            IntentUtil.goLogin(mActivity);
            return;
        }
        OkGo.<HttpResModel<FinanceTypeModel>>get(getCredit)
                .tag(httpTag)
                .params("plan_id", mAdapter.getData().get(position).getId())
                .execute(new MyJsonCallback<HttpResModel<FinanceTypeModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<FinanceTypeModel>> respons, HttpResModel<FinanceTypeModel> stringHttpResModel) {
                        if (StringUtils.isEmpty(stringHttpResModel.getResult().getId())) {
                            Intent intent = new Intent(mActivity, AddFinanceInfoActivity.class);
                            intent.putExtra(Constants.Model, new AddFinanceModel(mAdapter.getData().get(position).getId() + ""));
                            startActivity(intent);
                        } else {
                            IntentUtil.goFinanceInfo(mActivity, stringHttpResModel.getResult().getId());
                        }
                    }
                }.isDialog(mActivity));
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }
}
