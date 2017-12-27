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
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.dialog.GoodsSpecDialog;
import com.duma.ld.zhilianlift.widget.LinearImageLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_goods_details);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        id = getIntent().getStringExtra(Constants.id);
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
        goodsSpecDialog = new GoodsSpecDialog(mActivity);
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
                return;
            case R.id.layout_menu:
                // TODO: 2017/12/26 菜单
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
                return;
            case R.id.layout_shopCart:
                //跳转购物车
                return;
            case R.id.layout_AddShopCart:
                //添加购物车
                goodsSpecDialog.showShopCart();
                return;
            case R.id.layout_shop:
                //立即购买
                goodsSpecDialog.showShop();
                return;
        }
    }

}
