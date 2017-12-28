package com.duma.ld.zhilianlift.view.main.shopping;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.MyViewPagerAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.GoodsMainModel;
import com.duma.ld.zhilianlift.model.GoodsNumModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.ShopCartNumModel;
import com.duma.ld.zhilianlift.model.SpecGoodsPriceBean;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.dialog.GoodsSpecDialog;
import com.duma.ld.zhilianlift.view.popupWindow.GoodsInfoPopupWindow;
import com.duma.ld.zhilianlift.widget.LinearImageLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.addShopCart;
import static com.duma.ld.zhilianlift.util.Constants.shop;
import static com.duma.ld.zhilianlift.util.HttpUrl.addCart;
import static com.duma.ld.zhilianlift.util.HttpUrl.collectGoodsOrNo;
import static com.duma.ld.zhilianlift.util.HttpUrl.getInfo;

/**
 * Created by liudong on 2017/12/22.
 */

public class GoodsDetailsActivity extends BaseMyActivity {
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.layout_share)
    FrameLayout layoutShare;
    @BindView(R.id.layout_menu)
    FrameLayout layoutMenu;
    @BindView(R.id.layout_tablayout)
    TabLayout layoutTablayout;
    @BindView(R.id.viewPager_content)
    ViewPager viewPagerContent;
    @BindView(R.id.layout_collect)
    LinearImageLayout layoutCollect;
    @BindView(R.id.layout_servicePhone)
    LinearImageLayout layoutServicePhone;
    @BindView(R.id.layout_shopCart)
    LinearImageLayout layoutShopCart;
    @BindView(R.id.layout_AddShopCart)
    TextView layoutAddShopCart;
    @BindView(R.id.layout_shop)
    TextView layoutShop;

    private AlertDialog dialog;
    private GoodsNumModel goodsNumModel;
    private String id;
    private GoodsSpecDialog goodsSpecDialog;
    private SpecGoodsPriceBean mSpecGoodsPriceBean;
    private int count;//商品数量
    private String SpecString;//规格语句
    private GoodsInfoPopupWindow goodsInfoPopupWindow;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_goods_details);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        id = getIntent().getStringExtra(Constants.id);
        count = 1;
        goodsInfoPopupWindow = new GoodsInfoPopupWindow(mActivity);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(GoodsMainFragment.newInstance(id), "商品");
        viewPagerAdapter.addFragment(GoodsInfoFragment.newInstance(id), "详情");
        viewPagerAdapter.addFragment(GoodsCommentFragment.newInstance(id), "评价");
        viewPagerContent.setAdapter(viewPagerAdapter);
        viewPagerContent.setOffscreenPageLimit(3);
        layoutTablayout.setupWithViewPager(viewPagerContent);
        dialog = PublicUtil.getAlertDialog(mActivity, "确认拨打", "即将为您拨打 " + Constants.kefu)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PhoneUtils.dial(Constants.kefu);
                    }
                })
                .setNegativeButton("否", null)
                .setCancelable(false)
                .create();
        goodsSpecDialog = new GoodsSpecDialog(mActivity, new GoodsSpecDialog.OnDialogListener() {
            @Override
            public void onSpecString(StringBuffer stringBuffer) {
                setSpecString(stringBuffer.toString());
                getGoodsMainFragment().refreshSpecString();
            }

            @Override
            public void onSelectNum(int num) {
                count = num;
                getGoodsMainFragment().refreshSpecString();
            }

            @Override
            public void onSpec(SpecGoodsPriceBean specGoodsPriceBean) {
                mSpecGoodsPriceBean = specGoodsPriceBean;
            }

            @Override
            public void onClickBtn(String type) {
                switch (type) {
                    case addShopCart:
                        //添加购物车
                        addShopCart();
                        break;
                    case shop:
                        newShop();
                        break;
                }
            }
        });

    }

    public GoodsMainFragment getGoodsMainFragment() {
        return findFragment(GoodsMainFragment.class);
    }

    public int getCount() {
        return count;
    }

    public String getSpecString() {
        return SpecString;
    }

    public void setSpecString(String specString) {
        SpecString = specString;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (goodsNumModel == null) {
            OkGo.<HttpResModel<GoodsNumModel>>get(getInfo)
                    .params("goods_id", id)
                    .execute(new MyJsonCallback<HttpResModel<GoodsNumModel>>() {
                        @Override
                        protected void onJsonSuccess(Response<HttpResModel<GoodsNumModel>> respons, HttpResModel<GoodsNumModel> goodsNumModelHttpResModel) {
                            goodsNumModel = goodsNumModelHttpResModel.getResult();
                            initNum();
                        }
                    });
        }
    }

    private void initNum() {
        layoutShopCart.setNum(goodsNumModel.getCart_count() + "");
        if (goodsNumModel.getGoods_collect() == 1) {
            layoutCollect.setIcon(ZhuanHuanUtil.getDrawable(R.drawable.shoucang2));
        } else {
            layoutCollect.setIcon(ZhuanHuanUtil.getDrawable(R.drawable.shoucang));
        }
        goodsInfoPopupWindow.setMessageNum(goodsNumModel.getNews());
    }

    public void tabComment() {
        viewPagerContent.setCurrentItem(2, true);
    }

    public void tabInfo() {
        viewPagerContent.setCurrentItem(1, true);
    }

    public void showAll() {
        goodsSpecDialog.showAll();
    }

    public void setGoodsModel(GoodsMainModel model) {
        goodsSpecDialog.setModel(model);
    }

    @OnClick({R.id.layout_back, R.id.layout_share, R.id.layout_menu, R.id.layout_collect, R.id.layout_servicePhone, R.id.layout_shopCart, R.id.layout_AddShopCart, R.id.layout_shop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                return;
            case R.id.layout_share:
                // TODO: 2017/12/26 分享
                TsUtils.show("分享商品 id=" + id);
                return;
            case R.id.layout_menu:
                goodsInfoPopupWindow.showPopupWindow(R.id.layout_tobBar);
                return;
            case R.id.layout_servicePhone:
                //客服 写死的
                dialog.show();
                return;
        }
        if (!SpDataUtil.isLogin()) {
            IntentUtil.goLogin(mActivity);
            return;
        }
        switch (view.getId()) {
            case R.id.layout_collect:
                //收藏商品
                collectGoods();
                return;
            case R.id.layout_shopCart:
                //跳转购物车
                return;
            case R.id.layout_AddShopCart:
                //添加购物车
                if (mSpecGoodsPriceBean == null) {
                    goodsSpecDialog.showShopCart();
                } else {
                    addShopCart();
                }
                return;
            case R.id.layout_shop:
                //立即购买
                if (mSpecGoodsPriceBean == null) {
                    goodsSpecDialog.showShop();
                } else {
                    newShop();
                }
                return;
        }
    }

    //立即购买
    private void newShop() {
        // TODO: 2017/12/28  直接购买
        TsUtils.show("直接购买 数量:" + count);
    }

    //收藏商品
    private void collectGoods() {
        OkGo.getInstance().cancelTag(httpTag);
        OkGo.<HttpResModel<String>>get(collectGoodsOrNo)
                .tag(httpTag)
                .params("goods_id", id)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        TsUtils.show(stringHttpResModel.getMsg());
                        if (stringHttpResModel.getResult().equals("1")) {
                            layoutCollect.setIcon(ZhuanHuanUtil.getDrawable(R.drawable.shoucang2));
                        } else {
                            layoutCollect.setIcon(ZhuanHuanUtil.getDrawable(R.drawable.shoucang));
                        }
                    }
                });
    }

    //添加购物车
    private void addShopCart() {
        DialogUtil.getInstance().show(mActivity);
        GetRequest<HttpResModel<ShopCartNumModel>> params = OkGo.<HttpResModel<ShopCartNumModel>>get(addCart)
                .tag(httpTag)
                .params("goods_id", id)
                .params("goods_num", count);
        if (mSpecGoodsPriceBean != null) {
            params.params("item_id", mSpecGoodsPriceBean.getItem_id());
        }
        params.execute(new MyJsonCallback<HttpResModel<ShopCartNumModel>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<ShopCartNumModel>> respons, HttpResModel<ShopCartNumModel> stringHttpResModel) {
                TsUtils.show("添加成功!");
                DialogUtil.getInstance().hide();
                layoutShopCart.setNum(stringHttpResModel.getResult().getUserCartGoodsNum() + "");
            }
        });

    }

}
