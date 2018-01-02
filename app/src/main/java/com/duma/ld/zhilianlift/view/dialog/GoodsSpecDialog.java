package com.duma.ld.zhilianlift.view.dialog;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.zhilianlift.Adapter.SpecAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.BaseDownDialog;
import com.duma.ld.zhilianlift.model.GoodsMainModel;
import com.duma.ld.zhilianlift.model.GoodsSpecListBean;
import com.duma.ld.zhilianlift.model.GoodsSpecModel;
import com.duma.ld.zhilianlift.model.SpecGoodsPriceBean;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.widget.NumInputLayout;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.duma.ld.zhilianlift.util.Constants.addShopCart;
import static com.duma.ld.zhilianlift.util.Constants.ok;
import static com.duma.ld.zhilianlift.util.Constants.shop;

/**
 * 选择规格的dialog
 * 没有规格的时候是必须弹的 因为不知道数量是否选好
 * Created by liudong on 2017/12/26.
 */

public class GoodsSpecDialog extends BaseDownDialog implements View.OnClickListener {
    private TextView tv_price, tv_num, tv_store, tv_spec_list, tv_btn1, tv_btn2;
    private ImageView img_icon;
    private FrameLayout layout_back;
    private RecyclerView rv_spec;

    private GoodsMainModel model;
    private int type;
    private List<GoodsSpecModel> mList;
    private SpecAdapter specAdapter;
    private int goodsCount;//库存
    private StringBuffer stringBuffer;//返回的规格选中
    private SpecGoodsPriceBean specGoodsPriceBean;
    private boolean isSpec;//又没有规格
    private OnDialogListener onDialogListener;

    public SpecAdapter getSpecAdapter() {
        return specAdapter;
    }


    public SpecGoodsPriceBean getSpecGoodsPriceBean() {
        return specGoodsPriceBean;
    }

    /**
     * 选择规格监听
     * 按钮监听
     * 选择规格监听 选择规格后把选择的规格列表拼接后的字符串 数目 规格model返回
     * 按钮监听 把按钮类型返回就好了
     */
    public interface OnDialogListener {
        //选择规格的语句监听
        void onSpecString(StringBuffer stringBuffer);

        //选择的规格数量
        void onSelectNum(int num);

        //选择的规格
        void onSpec(SpecGoodsPriceBean specGoodsPriceBean);

        void onClickBtn(String type);
    }

    public GoodsSpecDialog(@NonNull Activity context, OnDialogListener onDialogListener) {
        super(context);
        this.onDialogListener = onDialogListener;
    }

    @Override
    protected void initData() {
        isSpec = true;
        tv_price = findViewById(R.id.tv_price);
        tv_num = findViewById(R.id.tv_num);
        tv_store = findViewById(R.id.tv_store);
        tv_spec_list = findViewById(R.id.tv_spec_list);
        tv_btn1 = findViewById(R.id.tv_btn1);
        tv_btn2 = findViewById(R.id.tv_btn2);
        img_icon = findViewById(R.id.img_icon);
        layout_back = findViewById(R.id.layout_back);
        rv_spec = findViewById(R.id.rv_spec);
        layout_back.setOnClickListener(this);
        tv_btn1.setOnClickListener(this);
        tv_btn2.setOnClickListener(this);
        //初始化适配器
        initAdapter();
        initType();
        setData();
    }

    private void initAdapter() {
        specAdapter = new SpecAdapter(mList);
        specAdapter.setOnInputListener(new NumInputLayout.OnInputListener() {
            @Override
            public void onInput(int num) {
                if (onDialogListener != null) {
                    onDialogListener.onSelectNum(num);
                }
            }
        });
        specAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //改变颜色 改变自己item 改变大model刷新价格
                List<GoodsSpecModel> data = specAdapter.getData();
                GoodsSpecModel goodsSpecModel = data.get(position);
                //判断是不是内容 是不是已经选中
                if (goodsSpecModel.getItemType() == GoodsSpecModel.head || goodsSpecModel.getItemType() == GoodsSpecModel.footer || goodsSpecModel.getSpecListBean().isSelect()) {
                    return;
                }
                /**
                 * 改变item 先把同级所有item变为未选中 在吧自己改为选中
                 * upP和自己一样的就是同级的
                 */
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getItemType() != GoodsSpecModel.head && data.get(i).getItemType() != GoodsSpecModel.footer && data.get(i).getSpecListBean().getUpPosition() == goodsSpecModel.getSpecListBean().getUpPosition()) {
                        data.get(i).getSpecListBean().setSelect(false);
                    }
                }
                goodsSpecModel.getSpecListBean().setSelect(true);

                //改变model刷新价格
                int upPosition = goodsSpecModel.getSpecListBean().getUpPosition();
                int thisPosition = goodsSpecModel.getSpecListBean().getThisPosition();
                model.getGoods_spec_list().get(upPosition).setSelect(thisPosition);
                refreshSpec();
                adapterRefresh();

            }
        });
        FlexboxLayoutManager manager = new FlexboxLayoutManager(mActivity);
        rv_spec.setLayoutManager(manager);
        rv_spec.setAdapter(specAdapter);
    }

    private void setData() {
        ImageLoader.with(mActivity, model.getGoods().getOriginal_img(), img_icon);
        tv_num.setText("商品编号：" + model.getGoods().getGoods_sn());
        //没有规格的库存
        goodsCount = model.getGoods().getStore_count();
        tv_store.setText("库存" + goodsCount + "件");
        tv_price.setText("¥" + model.getGoods().getShop_price());
        refreshSpec();
        adapterRefresh();
    }

    private void adapterRefresh() {
        mList.get(mList.size() - 1).setGoodsCount(goodsCount);
        specAdapter.notifyDataSetChanged();
    }

    //刷新当前选中的规格 价格 库存
    private void refreshSpec() {
        //没有规格
        if (model.getGoods_spec_list() == null || model.getGoods_spec_list().size() == 0) {
            tv_spec_list.setVisibility(View.INVISIBLE);
            isSpec = false;
            return;
        }
        //判断规格有没有选择完成
        if (!isSelect()) {
            //规格未选择完成的语句
            StringBuffer dialogString = new StringBuffer("请选择");
            stringBuffer = new StringBuffer("请选择");
            for (int i = 0; i < model.getGoods_spec_list().size(); i++) {
                GoodsSpecListBean goodsSpecListBean = model.getGoods_spec_list().get(i);
                if (goodsSpecListBean.getSelect() == -1) {
                    dialogString.append(" ").append(goodsSpecListBean.getSpec_name());
                    //给页面返回的语句
                    if (stringBuffer.toString().equals("请选择")) {
                        stringBuffer.append(goodsSpecListBean.getSpec_name());
                    } else {
                        stringBuffer.append(",").append(goodsSpecListBean.getSpec_name());
                    }
                }
            }
            tv_spec_list.setText(dialogString);
            if (onDialogListener != null) {
                onDialogListener.onSpecString(stringBuffer);
            }
            return;
        }
        /**
         * 已经选择完成的
         * 需要查询价格和库存
         * 语句也改变了
         */
        //选择完成的语句
        StringBuffer dialogString = new StringBuffer("已选:");
        stringBuffer = new StringBuffer("已选择");
        for (int i = 0; i < model.getGoods_spec_list().size(); i++) {
            GoodsSpecListBean goodsSpecListBean = model.getGoods_spec_list().get(i);
            dialogString.append("“").append(goodsSpecListBean.getSpec_list().get(goodsSpecListBean.getSelect()).getItem()).append("”");
            stringBuffer.append("“").append(goodsSpecListBean.getSpec_list().get(goodsSpecListBean.getSelect()).getItem()).append("”");
        }
        tv_spec_list.setText(dialogString);

        //获取价格和库存
        String selectSpecKey = getSelectSpecKey();
        specGoodsPriceBean = queryBySpecKey(selectSpecKey);
        if (onDialogListener != null) {
            onDialogListener.onSpecString(stringBuffer);
        }
        if (specGoodsPriceBean == null) {
            return;
        }
        if (onDialogListener != null) {
            onDialogListener.onSpec(specGoodsPriceBean);
        }
        goodsCount = specGoodsPriceBean.getStore_count();
        tv_store.setText("库存" + goodsCount + "件");
        tv_price.setText("¥" + specGoodsPriceBean.getPrice());
    }

    //判断规格有没有选择完成
    private boolean isSelect() {
        boolean isSelect = true;
        for (int i = 0; i < model.getGoods_spec_list().size(); i++) {
            GoodsSpecListBean goodsSpecListBean = model.getGoods_spec_list().get(i);
            if (goodsSpecListBean.getSelect() == -1) {
                isSelect = false;
                break;
            }
        }
        return isSelect;
    }

    //根据规格key来查询当前商品信息
    private SpecGoodsPriceBean queryBySpecKey(String selectSpecKey) {
        Logger.e(selectSpecKey);
        for (int i = 0; i < model.getSpec_goods_price().size(); i++) {
            if (selectSpecKey.equals(model.getSpec_goods_price().get(i).getKey())) {
                return model.getSpec_goods_price().get(i);
            }
        }
        return null;
    }

    //获取当前选中的key 用来后面查询
    private String getSelectSpecKey() {
        String key = "";
        //获取选中id
        int[] intArray = new int[model.getGoods_spec_list().size()];
        for (int i = 0; i < model.getGoods_spec_list().size(); i++) {
            GoodsSpecListBean goodsSpecListBean = model.getGoods_spec_list().get(i);
            intArray[i] = goodsSpecListBean.getSpec_list().get(goodsSpecListBean.getSelect()).getItem_id();
        }
        //排序后 拼接
        Arrays.sort(intArray);
        for (int i = 0; i < intArray.length; i++) {
            if (i == 0) {
                key = key + intArray[i];
            } else {
                key = key + "_" + intArray[i];
            }
        }
        return key;
    }

    /**
     * 在这个方法之前是没有show的
     * 当类型数组只有一个时默认选中
     *
     * @param model 商品的model
     */
    public void setModel(GoodsMainModel model) {
        setModel_noFooter(model);
        //添加footer
        mList.add(GoodsSpecModel.newFooter());
    }

    public void setModel_noFooter(GoodsMainModel model) {
        //一个是上一级的 positiong 和 他在这一级的positiong
//        model.getGoods_spec_list().get( ?).setSelect( ?);
        mList = new ArrayList<>();
        for (int i = 0; i < model.getGoods_spec_list().size(); i++) {
            GoodsSpecListBean goodsSpecListBean = model.getGoods_spec_list().get(i);
            //添加head
            mList.add(GoodsSpecModel.newHead(goodsSpecListBean.getSpec_name()));
            for (int i1 = 0; i1 < goodsSpecListBean.getSpec_list().size(); i1++) {
                //添加选择规格的内容
                if (goodsSpecListBean.getSpec_list().size() == 1) {
                    //说明就一个默认选中
                    goodsSpecListBean.setSelect(0);
                }
                int select = goodsSpecListBean.getSelect();
                GoodsSpecModel e = GoodsSpecModel.newContent(goodsSpecListBean.getSpec_list().get(i1), select == i1, i, i1);
                mList.add(e);
            }
        }
        this.model = model;
    }

    @Override
    public void onClick(View v) {
        Logger.e(specAdapter.getCount() + " num");
        switch (v.getId()) {
            case R.id.layout_back:
                dismiss();
                break;
            case R.id.tv_btn1:
                //加入购物车
                //只有没有规格或者已经选择好了规格才可以下一步
                if (!isSpec || specGoodsPriceBean != null) {
                    dismiss();
                    if (onDialogListener != null) {
                        onDialogListener.onClickBtn(addShopCart);
                    }
                } else {
                    TsUtils.show("请选择规格!");
                }
                break;
            case R.id.tv_btn2:
                if (!isSpec || specGoodsPriceBean != null) {
                    dismiss();
                    if (onDialogListener != null) {
                        if (type == 4) {
                            onDialogListener.onClickBtn(ok);
                        } else {
                            onDialogListener.onClickBtn(shop);
                        }

                    }
                } else {
                    TsUtils.show("请选择规格!");
                }
                break;
        }
    }

    private void initType() {
        if (tv_btn1 == null) {
            return;
        }
        switch (type) {
            case 1:
                tv_btn1.setVisibility(View.VISIBLE);
                tv_btn2.setVisibility(View.GONE);
                break;
            case 2:
                tv_btn1.setVisibility(View.GONE);
                tv_btn2.setVisibility(View.VISIBLE);
                break;
            case 3:
                tv_btn1.setVisibility(View.VISIBLE);
                tv_btn2.setVisibility(View.VISIBLE);
                break;
            case 4:
                tv_btn1.setVisibility(View.GONE);
                tv_btn2.setVisibility(View.VISIBLE);
                tv_btn2.setText("确定");
                break;
        }
    }

    public void showShopCart() {
        type = 1;
        initType();
        show();
    }


    public void showShop() {
        type = 2;
        initType();
        show();
    }

    public void showAll() {
        type = 3;
        initType();
        show();
    }

    public void showOk() {
        type = 4;
        initType();
        show();
    }

    @Override
    public void show() {
        if (model == null) {
            TsUtils.show("加载中...");
        } else {
            super.show();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.dialog_goods_spec;
    }


}
