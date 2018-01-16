package com.duma.ld.zhilianlift.view.main.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.ShoppingCartAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.GoodsMainModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.ShoppingCartListModel;
import com.duma.ld.zhilianlift.model.ShoppingCartModel;
import com.duma.ld.zhilianlift.model.ShoppingCartSelectModel;
import com.duma.ld.zhilianlift.model.ShoppingCartStoreGoodsModel;
import com.duma.ld.zhilianlift.model.ShoppingCartStoreModel;
import com.duma.ld.zhilianlift.model.ShoppingChangeNumModel;
import com.duma.ld.zhilianlift.model.SpecGoodsPriceBean;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.view.dialog.GoodsSpecDialog;
import com.duma.ld.zhilianlift.widget.CheckBoxGoodsList;
import com.duma.ld.zhilianlift.widget.CheckBoxNoOnClick;
import com.duma.ld.zhilianlift.widget.LinearImageLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.model.ShoppingCartListModel.goods;
import static com.duma.ld.zhilianlift.model.ShoppingCartListModel.head;
import static com.duma.ld.zhilianlift.util.Constants.ok;
import static com.duma.ld.zhilianlift.util.HttpUrl.cartList;
import static com.duma.ld.zhilianlift.util.HttpUrl.change;
import static com.duma.ld.zhilianlift.util.HttpUrl.changeGoodsNum;
import static com.duma.ld.zhilianlift.util.HttpUrl.changeNum;
import static com.duma.ld.zhilianlift.util.HttpUrl.goodsInfo;

/**
 * 购物车
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
    @BindView(R.id.cb_Select_All)
    CheckBoxNoOnClick cbSelectAll;
    @BindView(R.id.tv_All_Money)
    TextView tvAllMoney;
    @BindView(R.id.tv_settlement)
    TextView tvSettlement;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;

    private ShoppingCartAdapter mAdapter;
    private List<ShoppingCartListModel> mList;
    private List<ShoppingCartSelectModel> mSelectList;
    private boolean isShoppingAllSelect;
    private GoodsSpecDialog goodsSpecDialog;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_shopping_cart).setRefresh_f(R.id.sw_loading, R.id.layout_root, R.id.layout_content);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        if (mActivity instanceof HomeActivity) {
            layoutBack.setVisibility(View.GONE);
        }
        isShoppingAllSelect = false;
        mList = new ArrayList<>();
        mSelectList = new ArrayList<>();
        initAdapter();
        LinearLayoutManager layout = new LinearLayoutManager(mActivity);
        rvList.setLayoutManager(layout);
        rvList.setAdapter(mAdapter);
        mAdapter.setEmptyLayout("您现在的购物车是空的哦～", R.drawable.shoppingcard1, rvList);
        mFragmentConfig.showLoadingView();
    }

    private void initAdapter() {
        mAdapter = new ShoppingCartAdapter(mList, new ShoppingCartAdapter.OnSelectClickListener() {
            @Override
            public void allSelect(int storeId, boolean isSelect) {
                if (mAdapter.isEdit()) {
                    //编辑
                    //吧storeId的 选中变为isSelect 判断全选
                    for (int i = 0; i < mList.size(); i++) {
                        ShoppingCartListModel shoppingCartListModel = mList.get(i);
                        if (shoppingCartListModel.getStoreId() == storeId &&
                                shoppingCartListModel.getItemType() == goods &&
                                shoppingCartListModel.getShoppingCartStoreGoodsModel().isSelectDelete() != isSelect) {
                            shoppingCartListModel.getShoppingCartStoreGoodsModel().setSelectDelete(isSelect);
                        }
                    }
                    isAllSelectDelete();
                } else {
                    mSelectList.clear();
                    for (int i = 0; i < mList.size(); i++) {
                        ShoppingCartListModel shoppingCartListModel = mList.get(i);
                        if (shoppingCartListModel.getStoreId() == storeId &&
                                shoppingCartListModel.getItemType() == goods &&
                                shoppingCartListModel.getShoppingCartStoreGoodsModel().isSelected() != isSelect) {
                            mSelectList.add(new ShoppingCartSelectModel(shoppingCartListModel, isSelect, i));
                        }
                    }
                    changeSelect();
                }
            }

            @Override
            public void selectGoods(int position, boolean isSelect) {
                if (mAdapter.isEdit()) {
                    int storeId = mList.get(position).getStoreId();
                    //编辑
                    //判断所有storeId的item是不是true 是就让店铺全选 不是就false 判断全选
                    int HeadPosition = -1;
                    for (int i = 0; i < mList.size(); i++) {
                        ShoppingCartListModel shoppingCartListModel = mList.get(i);
                        if (shoppingCartListModel.getStoreId() == storeId &&
                                shoppingCartListModel.getItemType() == head) {
                            HeadPosition = i;
                        }
                    }
                    boolean isAll = true;
                    for (int i = 0; i < mList.size(); i++) {
                        ShoppingCartListModel shoppingCartListModel = mList.get(i);
                        if (shoppingCartListModel.getStoreId() == storeId &&
                                shoppingCartListModel.getItemType() == goods &&
                                !shoppingCartListModel.getShoppingCartStoreGoodsModel().isSelectDelete()) {
                            isAll = false;
                            break;
                        }
                    }
                    if (HeadPosition != 1) {
                        mList.get(HeadPosition).setAllDelectSelect(isAll);
                    }
                    isAllSelectDelete();
                } else {
                    mSelectList.clear();
                    ShoppingCartListModel shoppingCartListModel = mList.get(position);
                    mSelectList.add(new ShoppingCartSelectModel(shoppingCartListModel, isSelect, position));
                    changeSelect();
                }

            }

            @Override
            public void changeNum(final int position, int num) {
                OkGo.getInstance().cancelTag(httpTag);
                OkGo.<HttpResModel<ShoppingChangeNumModel>>get(changeGoodsNum)
                        .tag(httpTag)
                        .params("id", mList.get(position).getShoppingCartStoreGoodsModel().getId())
                        .params("goods_num", num)
                        .execute(new MyJsonCallback<HttpResModel<ShoppingChangeNumModel>>(mFragmentConfig) {
                            @Override
                            protected void onJsonSuccess(Response<HttpResModel<ShoppingChangeNumModel>> respons, HttpResModel<ShoppingChangeNumModel> stringHttpResModel) {
                                mList.get(position).getShoppingCartStoreGoodsModel().setGoods_num(stringHttpResModel.getResult().getCart().getGoods_num());
                                mAdapter.notifyItemChanged(position);
                                refreshNum(stringHttpResModel.getResult().getTotal_price());
                            }
                        });
            }
        }, mActivity);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.tv_goods_type2:
                        //修改规格
                        DialogUtil.getInstance().show_noBack(mActivity);
                        OkGo.<HttpResModel<GoodsMainModel>>get(goodsInfo)
                                .tag(httpTag)
                                .params("id", mList.get(position).getShoppingCartStoreGoodsModel().getGoods_id())
                                .execute(new MyJsonCallback<HttpResModel<GoodsMainModel>>() {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<GoodsMainModel>> respons, HttpResModel<GoodsMainModel> goodsMainModelHttpResModel) {
                                        goodsSpecDialog = new GoodsSpecDialog(mActivity, new GoodsSpecDialog.OnDialogListener() {
                                            @Override
                                            public void onSpecString(StringBuffer stringBuffer) {

                                            }

                                            @Override
                                            public void onSelectNum(int num) {

                                            }

                                            @Override
                                            public void onSpec(SpecGoodsPriceBean specGoodsPriceBean) {

                                            }

                                            @Override
                                            public void onClickBtn(String type) {
                                                if (type.equals(ok)) {
                                                    changeSpec(goodsSpecDialog.getSpecGoodsPriceBean(), position);
                                                }
                                            }
                                        });
                                        goodsSpecDialog.setModel_noFooter(goodsMainModelHttpResModel.getResult());
                                        DialogUtil.getInstance().hide();
                                        goodsSpecDialog.showOk();
                                    }
                                });
                        break;
                    default:
                        IntentUtil.goGoodsDetails(mActivity, mList.get(position).getShoppingCartStoreGoodsModel().getGoods_id());
                        break;
                }
            }
        });
        cbSelectAll.setOnNewClickListener(new CheckBoxNoOnClick.OnNewClickListener() {
            @Override
            public void onClick() {
                if (mAdapter.isEdit()) {
                    //编辑
                    for (int i = 0; i < mList.size(); i++) {
                        ShoppingCartListModel shoppingCartListModel = mList.get(i);
                        if (shoppingCartListModel.getItemType() == goods) {
                            shoppingCartListModel.getShoppingCartStoreGoodsModel().setSelectDelete(!cbSelectAll.isChecked());
                        } else {
                            shoppingCartListModel.setAllDelectSelect(!cbSelectAll.isChecked());
                        }
                    }
                    isAllSelectDelete();
                } else {
                    mSelectList.clear();
                    for (int i = 0; i < mList.size(); i++) {
                        ShoppingCartListModel shoppingCartListModel = mList.get(i);
                        if (shoppingCartListModel.getItemType() == goods &&
                                shoppingCartListModel.getShoppingCartStoreGoodsModel().isSelected() == cbSelectAll.isChecked()) {
                            mSelectList.add(new ShoppingCartSelectModel(shoppingCartListModel, !cbSelectAll.isChecked(), i));
                        }
                    }
                    changeSelect();
                }
            }
        });
    }

    /**
     * 修改规格
     *
     * @param specGoodsPriceBean
     * @param position
     */
    private void changeSpec(SpecGoodsPriceBean specGoodsPriceBean, final int position) {
        if (specGoodsPriceBean.getKey().equals(mList.get(position).getShoppingCartStoreGoodsModel().getSpec_key())) {
            return;
        }
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<ShoppingChangeNumModel>>get(change)
                .tag(httpTag)
                .params("id", mList.get(position).getShoppingCartStoreGoodsModel().getId())
                .params("item_id", specGoodsPriceBean.getItem_id())
                .execute(new MyJsonCallback<HttpResModel<ShoppingChangeNumModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ShoppingChangeNumModel>> respons, HttpResModel<ShoppingChangeNumModel> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        mList.get(position).setShoppingCartStoreGoodsModel(stringHttpResModel.getResult().getCart());
                        mAdapter.notifyItemChanged(position);
                        refreshNum(stringHttpResModel.getResult().getTotal_price());
                    }
                });
    }

    /**
     * 判断在编辑状态下是不是全选
     * 根据全部商品的head判断
     */
    private void isAllSelectDelete() {
        boolean isAdd = true;
        for (int i = 0; i < mList.size(); i++) {
            ShoppingCartListModel shoppingCartListModel = mList.get(i);
            if (shoppingCartListModel.getItemType() == head &&
                    !shoppingCartListModel.isAllDelectSelect()) {
                isAdd = false;
                break;
            }
        }
        refreshAdapter();
        cbSelectAll.setChecked(isAdd);
    }

    private void refreshChangeList(ShoppingCartModel.TotalPriceBean total_price, List<ShoppingCartListModel> selectList) {
        if (selectList != null) {
            //删除
            mList.removeAll(selectList);
        } else {
            //吧数据同步
            for (int i = 0; i < mSelectList.size(); i++) {
                mList.get(mSelectList.get(i).getGoodsPosition()).getShoppingCartStoreGoodsModel().setSelected(mSelectList.get(i).getSelected());
            }
        }
        refreshNum(total_price);
        //判断是不是全选
        //购物车全选
        boolean AllShopping = true;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getItemType() == head) {
                //店铺全选
                boolean isAll = true;
                for (int n = 0; n < mList.size(); n++) {
                    //所有商品中 storeId相同 有一个选中为false 则店铺全选为false
                    if (mList.get(n).getItemType() == goods &&
                            mList.get(n).getStoreId() == mList.get(i).getStoreId() &&
                            !mList.get(n).getShoppingCartStoreGoodsModel().isSelected()) {
                        isAll = false;
                        break;
                    }
                }
                //有一个店铺全选为false 则购物车全选为false
                if (!isAll) {
                    AllShopping = false;
                }
                mList.get(i).setAllSelect(isAll);
            }
        }
        isShoppingAllSelect = AllShopping;
        if (!mAdapter.isEdit()) {
            refreshCbAll();
        }
        refreshAdapter();
    }

    private void refreshCbAll() {
        cbSelectAll.setChecked(isShoppingAllSelect);
    }

    /**
     * 初始化原始数据
     *
     * @param result 购物车model
     */
    private void initData(ShoppingCartModel result) {
        mList.clear();
        //用来判断是不是购物车全选
        boolean isShopAllSelect = true;
        List<ShoppingCartStoreModel> storeList = result.getStoreList();
        if (storeList == null) {
            storeList = new ArrayList<>();
        }
        for (int i = 0; i < storeList.size(); i++) {
            ShoppingCartStoreModel shoppingCartStoreModel = storeList.get(i);
            if (shoppingCartStoreModel == null) {
                break;
            }
            List<ShoppingCartStoreGoodsModel> cartList = shoppingCartStoreModel.getCartList();
            if (cartList == null) {
                break;
            }
            //添加head
            mList.add(ShoppingCartListModel.newHeadModel(shoppingCartStoreModel));
            //用来判断是不是商家全选
            boolean isAllselect = true;

            for (int i1 = 0; i1 < cartList.size(); i1++) {
                ShoppingCartStoreGoodsModel shoppingCartStoreGoodsModel = cartList.get(i1);
                //判断是不是全选
                if (!shoppingCartStoreGoodsModel.isSelected()) {
                    isAllselect = false;
                }
                //添加goods
                mList.add(ShoppingCartListModel.newGoodsModel(shoppingCartStoreGoodsModel, shoppingCartStoreModel.getStore_id()));
            }
            if (!isAllselect) {
                isShopAllSelect = false;
            }
            mList.get(i).setAllSelect(isAllselect);
        }
        isShoppingAllSelect = isShopAllSelect;
        if (mAdapter.isEdit()) {
            cbSelectAll.setChecked(false);
        } else {
            refreshCbAll();
        }
        refreshNum(result.getTotal_price());
        refreshAdapter();
    }

    private void refreshAdapter() {
        if (mList.isEmpty()) {
            layoutBottom.setVisibility(View.GONE);
            setEdit(false);
            cbType.setVisibility(View.GONE);
        } else {
            layoutBottom.setVisibility(View.VISIBLE);
            cbType.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.layout_back, R.id.cb_type, R.id.layout_messgae, R.id.tv_settlement, R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                mActivity.finish();
                break;
            case R.id.cb_type:
                setEdit(!mAdapter.isEdit());
                break;
            case R.id.layout_messgae:
                IntentUtil.goMessage(mActivity);
                break;
            case R.id.tv_settlement:
                IntentUtil.goConfirmOrder(mActivity);
                break;
            case R.id.tv_delete:
                PublicUtil.getAlertDialog(mActivity, "删除商品", "确认要删除此商品吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DelectGoodsHttp();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(false)
                        .create()
                        .show();
                break;
        }
    }

    private void changeSelect() {
        DialogUtil.getInstance().show(mActivity);
        OkGo.<HttpResModel<ShoppingCartModel>>post(changeNum)
                .tag(httpTag)
                .params("cart_form_data", new Gson().toJson(mSelectList))
                .params("status", "0")
                .execute(new MyJsonCallback<HttpResModel<ShoppingCartModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ShoppingCartModel>> respons, HttpResModel<ShoppingCartModel> shoppingCartModelHttpResModel) {
                        DialogUtil.getInstance().hide();
                        refreshChangeList(shoppingCartModelHttpResModel.getResult().getTotal_price(), null);
                    }
                });
    }

    private void DelectGoodsHttp() {
        DialogUtil.getInstance().show(mActivity);
        //没有head的list
        final List<ShoppingCartSelectModel> list = new ArrayList<>();
        //有head的list
        final List<ShoppingCartListModel> selectList = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            //商品选中的
            if (mList.get(i).getItemType() == goods && mList.get(i).getShoppingCartStoreGoodsModel().isSelectDelete()) {
                list.add(new ShoppingCartSelectModel(mList.get(i), true, i));
                selectList.add(mList.get(i));
            }
            //head选中的
            if (mList.get(i).getItemType() == head && mList.get(i).isAllDelectSelect()) {
                selectList.add(mList.get(i));
            }

        }
        OkGo.<HttpResModel<ShoppingCartModel>>post(changeNum)
                .tag(httpTag)
                .params("cart_form_data", new Gson().toJson(list))
                .params("status", "1")
                .execute(new MyJsonCallback<HttpResModel<ShoppingCartModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ShoppingCartModel>> respons, HttpResModel<ShoppingCartModel> shoppingCartModelHttpResModel) {
                        DialogUtil.getInstance().hide();
                        refreshChangeList(shoppingCartModelHttpResModel.getResult().getTotal_price(), selectList);
                    }
                });
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<ShoppingCartModel>>get(cartList)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<ShoppingCartModel>>(mFragmentConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ShoppingCartModel>> respons, HttpResModel<ShoppingCartModel> shoppingCartModelHttpResModel) {
                        initData(shoppingCartModelHttpResModel.getResult());
                    }
                });
    }

    private void refreshNum(ShoppingCartModel.TotalPriceBean result) {
        tvAllMoney.setText("合计:¥ " + result.getTotal_fee());
        tvSettlement.setText("去结算(" + result.getNum() + ")");
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        onClickLoadingRefresh();
        setEdit(false);
        layoutMessgae.setNum(PublicUtil.getMessageNum());
    }

    private void setEdit(boolean intEdit) {
        mAdapter.setEdit(intEdit);
        if (intEdit) {
            //编辑状态
            cbType.setText("完成");
            tvAllMoney.setVisibility(View.GONE);
            tvSettlement.setVisibility(View.GONE);
            tvDelete.setVisibility(View.VISIBLE);
            isAllSelectDelete();
        } else {
            cbType.setText("编辑");
            tvAllMoney.setVisibility(View.VISIBLE);
            tvSettlement.setVisibility(View.VISIBLE);
            tvDelete.setVisibility(View.GONE);
            refreshCbAll();
        }
    }

}
