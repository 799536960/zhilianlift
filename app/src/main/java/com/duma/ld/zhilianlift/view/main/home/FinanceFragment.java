package com.duma.ld.zhilianlift.view.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.FinanceListModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.plan;

/**
 * 金融
 * Created by liudong on 2017/11/29.
 */

public class FinanceFragment extends BaseMyFragment implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private BaseAdapter<FinanceListModel> mAdapter;
    private ImageView img_icon;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    // 0  新居贷  1 按揭
    private int type;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_finance).setRefresh_f(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
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
                                .setText(R.id.tv_info, item.getPlan_text() + "");
                    }
                });
        View view = mAdapter.getView(R.layout.adapter_head_finance);
        img_icon = view.findViewById(R.id.img_icon);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton = view.findViewById(R.id.radio_left);
        radioGroup.setOnCheckedChangeListener(this);
        mAdapter.addHeaderView(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        radioButton.setChecked(true);
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
}
