package com.duma.ld.zhilianlift.view.main.shopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.CommentModel;
import com.duma.ld.zhilianlift.model.GoodsCommentModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.widget.CheckBoxComment;
import com.duma.ld.zhilianlift.widget.GridLayout;
import com.duma.ld.zhilianlift.widget.SimpleRatingBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.getGoodsComment;

/**
 * 商品评论页面
 * Created by liudong on 2017/12/22.
 */

public class GoodsCommentFragment extends BaseMyFragment implements View.OnClickListener {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private CheckBoxComment cbComment1;
    private CheckBoxComment cbComment2;
    private CheckBoxComment cbComment3;
    private CheckBoxComment cbComment4;
    private String id;

    // 1 全部 2好评 3 中评 4差评
    private String type;
    private BaseAdapter<CommentModel> adapter;

    public static GoodsCommentFragment newInstance(String id) {
        GoodsCommentFragment fragment = new GoodsCommentFragment();
        Bundle args = new Bundle();
        args.putString(Constants.id, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_goods_comment).setRefresh_f(R.id.sw_loading, R.id.layout_content, R.id.rv_list);
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
        initAdapter();
        mFragmentConfig.showLoadingView();
    }


    private void initAdapter() {
        adapter = new BaseAdapter.Builder<CommentModel>(rvList, mActivity, R.layout.adapter_comment_info)
                .buildLoad(new OnBaseLoadAdapterListener<CommentModel>() {
                    @Override
                    public void onLoadHttp(int page, int httpTag) {
                        OkGo.<HttpResModel<GoodsCommentModel>>get(getGoodsComment)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .params("goods_id", id)
                                .params("type", type)
                                .execute(new MyJsonCallback<HttpResModel<GoodsCommentModel>>(mFragmentConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<GoodsCommentModel>> respons, HttpResModel<GoodsCommentModel> goodsCommentModelHttpResModel) {
                                        initTop(goodsCommentModelHttpResModel.getResult().getCommentrate());
                                        adapter.setLoadData(goodsCommentModelHttpResModel.getResult().getComment());
                                    }
                                }.setLoadAdapter(adapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, CommentModel item) {
                        String nickname = item.getNickname() == null || item.getNickname().isEmpty() ? "<未设置>" : item.getNickname();
                        helper.setText(R.id.tv_Nick_name, nickname)
                                .setText(R.id.tv_commentTime, ZhuanHuanUtil.Time2nian2(item.getAdd_time() * 1000))
                                .setText(R.id.tv_content, item.getContent())
                                .setText(R.id.tv_shopTime, "购买日期：" + ZhuanHuanUtil.Time2nian2(item.getBuy_time() * 1000))
                                .setText(R.id.tv_type, item.getSpec_key_name());
                        if (item.getSpec_key_name() == null || item.getSpec_key_name().isEmpty()) {
                            helper.setGone(R.id.tv_type, false);
                        } else {
                            helper.setGone(R.id.tv_type, true);
                        }
                        ImageLoader.with_head(mActivity, item.getHead_pic(), (ImageView) helper.getView(R.id.imageView3));
                        GridLayout gridLayout = helper.getView(R.id.gridLayout_imgs);
                        gridLayout.setmList(item.getImg(), mActivity);
                        SimpleRatingBar rating_rank = helper.getView(R.id.rating_rank);
                        rating_rank.setRating(item.getGoods_rank());
                    }
                });
        View view = adapter.getView(R.layout.head_comment_top);
        cbComment1 = view.findViewById(R.id.cb_comment1);
        cbComment2 = view.findViewById(R.id.cb_comment2);
        cbComment3 = view.findViewById(R.id.cb_comment3);
        cbComment4 = view.findViewById(R.id.cb_comment4);
        cbComment1.setOnClickListener(this);
        cbComment2.setOnClickListener(this);
        cbComment3.setOnClickListener(this);
        cbComment4.setOnClickListener(this);
        adapter.addHeaderView(view);
        adapter.setHeaderAndEmpty(true);
    }

    private void initTop(GoodsCommentModel.CommentrateBean commentrate) {
        cbComment1.setText("全部 " + commentrate.getRate1());
        cbComment2.setText("好评 " + commentrate.getRate2());
        cbComment3.setText("中评 " + commentrate.getRate3());
        cbComment4.setText("差评 " + commentrate.getRate4());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_comment1:
                initCb("1");
                break;
            case R.id.cb_comment2:
                initCb("2");
                break;
            case R.id.cb_comment3:
                initCb("3");
                break;
            case R.id.cb_comment4:
                initCb("4");
                break;
        }
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        adapter.onRefresh();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //默认全部
        initCb("1");
    }

    private void initCb(String type) {
        if (type.equals(this.type)) {
            return;
        }
        this.type = type;
        cbComment1.setChecked(false);
        cbComment2.setChecked(false);
        cbComment3.setChecked(false);
        cbComment4.setChecked(false);
        // 1 全部 2好评 3 中评 4差评
        switch (type) {
            case "1":
                cbComment1.setChecked(true);
                break;
            case "2":
                cbComment2.setChecked(true);
                break;
            case "3":
                cbComment3.setChecked(true);
                break;
            case "4":
                cbComment4.setChecked(true);
                break;
        }
        onClickLoadingRefresh();
    }

}
