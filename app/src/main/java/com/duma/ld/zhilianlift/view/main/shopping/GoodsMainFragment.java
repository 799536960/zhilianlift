package com.duma.ld.zhilianlift.view.main.shopping;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
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
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.widget.SimpleRatingBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    private String id;
    private BaseAdapter<CommentModel> adapter;

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
                        img_icon = helper.getView(R.id.img_icon);
                        layout_img4 = helper.getView(R.id.layout_img4);
                        layout_imageList = helper.getView(R.id.layout_imageList);
                        //设置
                        rating_rank.setRating(Float.parseFloat(item.getGoods_rank()));
                        ImageLoader.with_head(mActivity, item.getHead_pic(), img_icon);
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
                            img_1.setVisibility(View.GONE);
                            img_2.setVisibility(View.GONE);
                            img_3.setVisibility(View.GONE);
                            layout_img4.setVisibility(View.GONE);

                            tv_image_num.setText("共" + item.getImg().size() + "张");
                            for (int i = 0; i < item.getImg().size(); i++) {
                                switch (i) {
                                    case 0:
                                        img_1.setVisibility(View.VISIBLE);
                                        ImageLoader.with(mActivity, item.getImg().get(i), img_1);
                                        break;
                                    case 1:
                                        img_2.setVisibility(View.VISIBLE);
                                        ImageLoader.with(mActivity, item.getImg().get(i), img_2);
                                        break;
                                    case 2:
                                        img_3.setVisibility(View.VISIBLE);
                                        ImageLoader.with(mActivity, item.getImg().get(i), img_3);
                                        break;
                                    case 3:
                                        layout_img4.setVisibility(View.VISIBLE);
                                        ImageLoader.with(mActivity, item.getImg().get(i), img_4);
                                        break;
                                }
                            }

                        }

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
                .params("id", id)
                .execute(new MyJsonCallback<HttpResModel<GoodsMainModel>>(mFragmentConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<GoodsMainModel>> respons, HttpResModel<GoodsMainModel> goodsMainModelHttpResModel) {
                        initData(goodsMainModelHttpResModel.getResult());
                    }
                });
    }

    private void initData(GoodsMainModel result) {
        //banner
        List<String> list = new ArrayList<>();
        for (int i = 0; i < result.getGallery().size(); i++) {
            list.add(result.getGallery().get(i).getImage_url());
        }
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
        tvGoodsCity.setText("仅限" + "湖州吴兴区,长兴县");
        //店铺名字
        tvGoodsStore.setText("由 " + result.getStore().getStore_name() + " 提供");
        //默认规格
        tvGoodsSize.setText("请选择");
        //库存
        tvGoodsNum.setText("仅剩" + result.getGoods().getStore_count() + "件");
        //评价数量
        tvGoodsCommentNum.setText("评价(" + result.getGoods().getComment_count() + ")");
        //好评度
        tvGoodsCommentJilv.setText(result.getGoods().getGood_comment_rate() + "%");

        adapter.setNewData(result.getComment());
    }

}
