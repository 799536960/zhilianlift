package com.duma.ld.zhilianlift.view.main.shopping.goods;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.CommentModel;
import com.duma.ld.zhilianlift.model.GoodsMainModel;
import com.duma.ld.zhilianlift.model.GoodsMainStoreAddressModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.main.home.GoodsRefreshUtil;
import com.duma.ld.zhilianlift.widget.SimpleRatingBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.goodsInfo;

/**
 * 商品详情的第一个介绍页面
 * Created by liudong on 2017/12/22.
 */

public class GoodsMainFragment extends BaseMyFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_title)
    TextView tvGoodsTitle;
    @BindView(R.id.tv_goods_money)
    TextView tvGoodsMoney;
    @BindView(R.id.tv_goods_city)
    TextView tvGoodsCity;
    @BindView(R.id.tv_goods_store)
    TextView tvGoodsStore;
    @BindView(R.id.tv_goods_size)
    TextView tvGoodsSize;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_goods_commentNum)
    TextView tvGoodsCommentNum;
    @BindView(R.id.tv_goods_commentJilv)
    TextView tvGoodsCommentJilv;
    @BindView(R.id.tv_goods_rv)
    RecyclerView tvGoodsRv;
    @BindView(R.id.tv_goods_AllComment)
    TextView tvGoodsAllComment;
    @BindView(R.id.layout_type)
    LinearLayout layoutType;
    @BindView(R.id.scrollView)
    SmartRefreshLayout scrollView;
    @BindView(R.id.img_jantou)
    ImageView imgJantou;
    @BindView(R.id.tv_refresh)
    TextView tvRefresh;

    private String id;
    private BaseAdapter<CommentModel> adapter;
    private GoodsDetailsActivity activity;

    public static GoodsMainFragment newInstance(String id) {
        GoodsMainFragment fragment = new GoodsMainFragment();
        Bundle args = new Bundle();
        args.putString(Constants.id, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_goods_main);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString(Constants.id);
        } else {
            TsUtils.show("商品id获取失败!");
            mActivity.finish();
        }
        activity = (GoodsDetailsActivity) mActivity;
        scrollView.setOnMultiPurposeListener(new GoodsRefreshUtil() {
            @Override
            public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                if (percent > 1) {
                    tvRefresh.setText("松开查看图文详情");
                } else {
                    tvRefresh.setText("上拉查看图文详情");
                }
            }

            @Override
            public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                if (percent == 0) {
                    if (tvRefresh.getText().equals("松开查看图文详情")) {
                        activity.tabInfo();
                        tvRefresh.setText("上拉查看图文详情");
                    }
                }
            }
        });
//        scrollView.setOnMultiPurposeListener(new OnMultiPurposeListener() {
//            @Override
//            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
//
//            }
//
//            @Override
//            public void onHeaderReleased(RefreshHeader header, int headerHeight, int extendHeight) {
//
//            }
//
//            @Override
//            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
//
//            }
//
//            @Override
//            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int extendHeight) {
//
//            }
//
//            @Override
//            public void onHeaderFinish(RefreshHeader header, boolean success) {
//
//            }
//
//            @Override
//            public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
//                if (percent > 1) {
//                    tvRefresh.setText("松开查看图文详情");
//                } else {
//                    tvRefresh.setText("上拉查看图文详情");
//                }
//            }
//
//            @Override
//            public void onFooterReleased(RefreshFooter footer, int footerHeight, int extendHeight) {
//
//            }
//
//            @Override
//            public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
//                if (percent == 0) {
//                    if (tvRefresh.getText().equals("松开查看图文详情")) {
//                        activity.tabInfo();
//                        tvRefresh.setText("上拉查看图文详情");
//                    }
//                }
//            }
//
//            @Override
//            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int extendHeight) {
//
//            }
//
//            @Override
//            public void onFooterFinish(RefreshFooter footer, boolean success) {
//
//            }
//
//            @Override
//            public void onLoadMore(RefreshLayout refreshLayout) {
//
//            }
//
//            @Override
//            public void onRefresh(RefreshLayout refreshLayout) {
//
//            }
//
//            @Override
//            public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
//
//            }
//        });
        initCommentAdapter();
        onClickLoadingRefresh();
    }

    private void initCommentAdapter() {
        adapter = new BaseAdapter.Builder<CommentModel>(tvGoodsRv, mActivity, R.layout.adapter_goods_main_comment)
                .setNoEnpty()
                .build(new OnBaseAdapterListener<CommentModel>() {
                    @Override
                    public void convert(BaseViewHolder helper, CommentModel item) {
                        helper.setText(R.id.tv_content, item.getContent());
                        //初始化
                        SimpleRatingBar rating_rank = helper.getView(R.id.rating_rank);
                        TextView tv_Nick_name, tv_type, tv_image_num;
                        ImageView img_1, img_2, img_3, img_4, img_icon;
                        ViewGroup layout_img4, layout_imageList;
                        tv_Nick_name = helper.getView(R.id.tv_Nick_name);
                        tv_type = helper.getView(R.id.tv_type);
                        tv_image_num = helper.getView(R.id.tv_image_num);
                        img_1 = helper.getView(R.id.img_1);
                        img_2 = helper.getView(R.id.img_2);
                        img_3 = helper.getView(R.id.img_3);
                        img_4 = helper.getView(R.id.img_4);
                        helper.addOnClickListener(R.id.img_1)
                                .addOnClickListener(R.id.img_2)
                                .addOnClickListener(R.id.img_3)
                                .addOnClickListener(R.id.img_4);
                        img_icon = helper.getView(R.id.img_icon);
                        layout_img4 = helper.getView(R.id.layout_img4);
                        layout_imageList = helper.getView(R.id.layout_imageList);
                        //设置
                        rating_rank.setRating(item.getGoods_rank());
                        ImageLoader.with_head(item.getHead_pic(), img_icon);
                        if (item.getNickname() == null || item.getNickname().isEmpty()) {
                            tv_Nick_name.setText("<未设置>");
                        } else {
                            tv_Nick_name.setText(item.getNickname());
                        }
                        if (item.getSpec_key_name() == null || item.getSpec_key_name().isEmpty()) {
                            tv_type.setVisibility(View.GONE);
                        } else {
                            tv_type.setVisibility(View.VISIBLE);
                            tv_type.setText(item.getSpec_key_name());
                        }
                        if (item.getImg() == null || item.getImg().size() == 0) {
                            layout_imageList.setVisibility(View.GONE);
                        } else {
                            layout_imageList.setVisibility(View.VISIBLE);
                            img_1.setVisibility(View.INVISIBLE);
                            img_2.setVisibility(View.INVISIBLE);
                            img_3.setVisibility(View.INVISIBLE);
                            layout_img4.setVisibility(View.INVISIBLE);

                            tv_image_num.setText("共" + item.getImg().size() + "张");
                            for (int i = 0; i < item.getImg().size(); i++) {
                                switch (i) {
                                    case 0:
                                        img_1.setVisibility(View.VISIBLE);
                                        ImageLoader.with(item.getImg().get(i), img_1);
                                        break;
                                    case 1:
                                        img_2.setVisibility(View.VISIBLE);
                                        ImageLoader.with(item.getImg().get(i), img_2);
                                        break;
                                    case 2:
                                        img_3.setVisibility(View.VISIBLE);
                                        ImageLoader.with(item.getImg().get(i), img_3);
                                        break;
                                    case 3:
                                        layout_img4.setVisibility(View.VISIBLE);
                                        ImageLoader.with(item.getImg().get(i), img_4);
                                        break;
                                }
                            }

                        }
                    }
                });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int onclick = 0;
                switch (view.getId()) {
                    case R.id.img_1:
                        onclick = 0;
                        break;
                    case R.id.img_2:
                        onclick = 1;
                        break;
                    case R.id.img_3:
                        onclick = 2;
                        break;
                    case R.id.img_4:
                        onclick = 3;
                        break;
                }
                CommentModel model = (CommentModel) adapter.getData().get(position);
                IntentUtil.goPhoto(mActivity, model.getImg(), onclick);
            }
        });
        tvGoodsRv.setFocusable(false);
        tvGoodsRv.setNestedScrollingEnabled(false);
        tvGoodsRv.setLayoutManager(new LinearLayoutManager(mActivity));
        tvGoodsRv.setAdapter(adapter);
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<GoodsMainModel>>get(goodsInfo)
                .tag(httpTag)
                .params("id", id)
                .params("code", SpDataUtil.getLocation().getCode())
                .execute(new MyJsonCallback<HttpResModel<GoodsMainModel>>(mFragmentConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<GoodsMainModel>> respons, HttpResModel<GoodsMainModel> goodsMainModelHttpResModel) {
                        initData(goodsMainModelHttpResModel.getResult());
                    }
                });
    }

    private void initData(GoodsMainModel result) {
        //初始化dialog
        activity.setGoodsModel(result);
        //banner
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < result.getGallery().size(); i++) {
            list.add(result.getGallery().get(i).getImage_url());
        }
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                IntentUtil.goPhoto(mActivity, list, position);
            }
        });
        banner = PublicUtil.initBanner(banner)
                .setImages(list)
                .start();

        //标题
        tvGoodsName.setText(result.getGoods().getGoods_name());
        //价格
        SpannableStringBuilder spannableStringBuilder = new SpanUtils()
                .append("¥")
                .setFontSize(ConvertUtils.sp2px(13))
                .append(result.getGoods().getShop_price())
                .setFontSize(ConvertUtils.sp2px(18))
                .create();
        tvGoodsMoney.setText(spannableStringBuilder);
        //描述 没有的话隐藏
        String goods_remark = result.getGoods().getGoods_remark();
        if (goods_remark == null || goods_remark.isEmpty()) {
            tvGoodsTitle.setVisibility(View.GONE);
        } else {
            tvGoodsTitle.setText(goods_remark);
        }
        //区域
        List<GoodsMainStoreAddressModel> storeAddressAll = result.getStoreAddressAll();
        if (storeAddressAll != null && storeAddressAll.size() > 0) {
            GoodsMainStoreAddressModel addressModel = storeAddressAll.get(0);
            tvGoodsCity.setText("仅限" + addressModel.getAddress());
        } else {
            tvGoodsCity.setText("区域获取失败!");
        }
        //店铺名字
        tvGoodsStore.setText("由 " + result.getStore().getStore_name() + " 提供");
        //默认规格
        if (result.getGoods_spec_list() == null || result.getGoods_spec_list().size() == 0) {
            activity.setSpecString("");
        } else {
            StringBuilder res = new StringBuilder("请选择");
            for (int i = 0; i < result.getGoods_spec_list().size(); i++) {
                if (i == 0) {
                    res.append(result.getGoods_spec_list().get(i).getSpec_name());
                } else {
                    res.append(",").append(result.getGoods_spec_list().get(i).getSpec_name());
                }
            }
            activity.setSpecString(res.toString());
        }
        refreshSpecString();
        //库存
        tvGoodsNum.setText("仅剩" + result.getGoods().getStore_count() + "件");
        //评价数量
        tvGoodsCommentNum.setText("评价(" + result.getGoods().getComment_count() + ")");
        //好评度
        tvGoodsCommentJilv.setText(result.getGoods().getGood_comment_rate() + "%");

        adapter.setNewData(result.getComment());
    }

    public void refreshSpecString() {
        String specString = activity.getSpecString();
        if (specString == null || specString.isEmpty()) {
            tvGoodsSize.setText(activity.getCount() + "件");
        } else {
            tvGoodsSize.setText(specString + "," + activity.getCount() + "件");
        }

    }


    @OnClick({R.id.layout_type, R.id.tv_goods_AllComment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_type:
                if (!SpDataUtil.isLogin()) {
                    IntentUtil.goLogin(mActivity);
                    return;
                }
                activity.showAll();
                break;
            case R.id.tv_goods_AllComment:
                //跳转到评论
                activity.tabComment();
                break;
        }
    }

}
