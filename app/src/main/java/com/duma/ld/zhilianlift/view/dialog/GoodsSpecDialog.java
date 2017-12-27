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
import com.google.android.flexbox.FlexboxLayoutManager;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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

    public GoodsSpecDialog(@NonNull Activity context) {
        super(context);
    }

    @Override
    protected void initData() {
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
                specAdapter.notifyDataSetChanged();

                //改变model刷新价格
                int upPosition = goodsSpecModel.getSpecListBean().getUpPosition();
                int thisPosition = goodsSpecModel.getSpecListBean().getThisPosition();
                model.getGoods_spec_list().get(upPosition).setSelect(thisPosition);
                refreshSpec();

            }
        });
//        specAdapter.setFooterView(mActivity.getLayoutInflater().inflate(R.layout.adapter_head_spec, (ViewGroup) rv_spec.getParent(), false));
        FlexboxLayoutManager manager = new FlexboxLayoutManager(mActivity);
        rv_spec.setLayoutManager(manager);
        rv_spec.setAdapter(specAdapter);
    }

    private void setData() {
        ImageLoader.with(mActivity, model.getGoods().getOriginal_img(), img_icon);
        tv_num.setText("商品编号：" + model.getGoods().getGoods_sn());
        tv_store.setText("库存" + model.getGoods().getStore_count() + "件");
        tv_price.setText("¥" + model.getGoods().getShop_price());
        refreshSpec();
    }

    //刷新当前选中的规格 价格 库存
    private void refreshSpec() {
        //没有规格
        if (model.getGoods_spec_list() == null || model.getGoods_spec_list().size() == 0) {
            tv_spec_list.setVisibility(View.INVISIBLE);
            return;
        }
        //获取已选规格名字
        StringBuffer stringBuffer = new StringBuffer("已选");
        for (int i = 0; i < model.getGoods_spec_list().size(); i++) {
            GoodsSpecListBean goodsSpecListBean = model.getGoods_spec_list().get(i);
            stringBuffer.append("“").append(goodsSpecListBean.getSpec_list().get(goodsSpecListBean.getSelect()).getItem()).append("“");
        }
        tv_spec_list.setText(stringBuffer);
        //获取价格和库存
        String selectSpecKey = getSelectSpecKey();
        SpecGoodsPriceBean specGoodsPriceBean = queryBySpecKey(selectSpecKey);
        if (specGoodsPriceBean == null) {
            return;
        }
        tv_store.setText("库存" + specGoodsPriceBean.getStore_count() + "件");
        tv_price.setText("¥" + specGoodsPriceBean.getPrice());
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                dismiss();
                break;
            case R.id.tv_btn1:
                dismiss();
                break;
            case R.id.tv_btn2:
                dismiss();
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

    @Override
    public void show() {
        if (model == null) {
            TsUtils.show("加载中...");
        } else {
            super.show();
        }
    }

    public void setModel(GoodsMainModel model) {
        //一个是上一级的 positiong 和 他在这一级的positiong
//        model.getGoods_spec_list().get( ?).setSelect( ?);
        mList = new ArrayList<>();
        for (int i = 0; i < model.getGoods_spec_list().size(); i++) {
            GoodsSpecListBean goodsSpecListBean = model.getGoods_spec_list().get(i);
            mList.add(GoodsSpecModel.newHead(goodsSpecListBean.getSpec_name()));
            for (int i1 = 0; i1 < goodsSpecListBean.getSpec_list().size(); i1++) {
                int select = goodsSpecListBean.getSelect();
                GoodsSpecModel e = GoodsSpecModel.newContent(goodsSpecListBean.getSpec_list().get(i1), select == i1, i, i1);
                mList.add(e);
            }
        }
        mList.add(GoodsSpecModel.newFooter());
        this.model = model;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.dialog_goods_spec;
    }


}
