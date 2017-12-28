package com.duma.ld.zhilianlift.view.main.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.ShoppingCartAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.ShoppingCartListModel;
import com.duma.ld.zhilianlift.model.ShoppingCartModel;
import com.duma.ld.zhilianlift.model.ShoppingCartStoreModel;
import com.duma.ld.zhilianlift.widget.CheckBoxGoodsList;
import com.duma.ld.zhilianlift.widget.CheckBoxNoOnClick;
import com.duma.ld.zhilianlift.widget.LinearImageLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.cartList;

/**
 * Created by liudong on 2017/11/29.
 */

public class ShoppingCartFragment extends BaseMyFragment {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.cb_type)
    CheckBoxGoodsList cbType;
    @BindView(R.id.layout_messgae)
    LinearImageLayout layoutMessgae;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_content)
    FrameLayout layoutContent;
    @BindView(R.id.cb_Select_All)
    CheckBoxNoOnClick cbSelectAll;
    @BindView(R.id.tv_All_Money)
    TextView tvAllMoney;
    @BindView(R.id.tv_settlement)
    TextView tvSettlement;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    private ShoppingCartAdapter mAdapter;
    private List<ShoppingCartListModel> mList;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_shopping_cart).setRefresh_f(R.id.sw_loading, R.id.layout_content, R.id.sw_loading);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mList = new ArrayList<>();
        mAdapter = new ShoppingCartAdapter(mList);
        rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        rvList.setAdapter(mAdapter);
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<ShoppingCartModel>>get(cartList)
                .execute(new MyJsonCallback<HttpResModel<ShoppingCartModel>>(mFragmentConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ShoppingCartModel>> respons, HttpResModel<ShoppingCartModel> shoppingCartModelHttpResModel) {
                        initData(shoppingCartModelHttpResModel.getResult());
                    }
                });
    }

    private void initData(ShoppingCartModel result) {
        mList.clear();
        for (int i = 0; i < result.getStoreList().size(); i++) {
            ShoppingCartStoreModel shoppingCartStoreModel = result.getStoreList().get(i);
            mList.add(ShoppingCartListModel.newHeadModel(shoppingCartStoreModel));
            for (int i1 = 0; i1 < shoppingCartStoreModel.getCartList().size(); i1++) {
                mList.add(ShoppingCartListModel.newGoodsModel(shoppingCartStoreModel.getCartList().get(i1)));
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.layout_back, R.id.cb_type, R.id.layout_messgae, R.id.tv_settlement, R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                break;
            case R.id.cb_type:
                break;
            case R.id.layout_messgae:
                break;
            case R.id.tv_settlement:
                break;
            case R.id.tv_delete:
                break;
        }
    }
}
