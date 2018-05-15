package com.duma.ld.zhilianlift.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.PublicConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.MyRecordModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.widget.CheckBoxNoOnClick;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import static com.duma.ld.zhilianlift.util.HttpUrl.collectgoodlist;

/**
 * Created by liudong on 2018/1/3.
 */

public class GoodsTypeAdapter implements BaseQuickAdapter.OnItemClickListener {
    private BaseAdapter<MyRecordModel.VisitBean> mAdapter;
    private RecyclerView mRecyclerView;
    private Activity mActivity;
    private boolean isEdit;

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public GoodsTypeAdapter(RecyclerView mRecyclerView, Activity mActivity) {
        this.mRecyclerView = mRecyclerView;
        this.mActivity = mActivity;
        isEdit = false;
    }

    public GoodsTypeAdapter build() {
        mAdapter = new BaseAdapter.Builder<MyRecordModel.VisitBean>(mRecyclerView, mActivity, R.layout.adapter_my_record_goods)
                .setNoEnpty()
                .isNested()
                .build(new OnBaseAdapterListener<MyRecordModel.VisitBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, MyRecordModel.VisitBean item) {
                        getView(helper, item);
                    }
                });
        mAdapter.setOnItemClickListener(this);
        return this;
    }

    public GoodsTypeAdapter buildLoad(final PublicConfig config) {
        mAdapter = new BaseAdapter.Builder<MyRecordModel.VisitBean>(mRecyclerView, mActivity, R.layout.adapter_my_record_goods)
                .setTitleOrDrawableId("您还没有收藏的物品哦~", R.drawable.sc)
                .buildLoad(new OnBaseLoadAdapterListener<MyRecordModel.VisitBean>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<List<MyRecordModel.VisitBean>>>get(collectgoodlist)
                                .tag(mActivity)
                                .params(Constants.Page, page)
                                .execute(new MyJsonCallback<HttpResModel<List<MyRecordModel.VisitBean>>>(config) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<MyRecordModel.VisitBean>>> respons, HttpResModel<List<MyRecordModel.VisitBean>> myRecordModel) {
                                        mAdapter.setLoadData(myRecordModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, MyRecordModel.VisitBean item) {
                        getView(helper, item);
                    }
                });
        mAdapter.setOnItemClickListener(this);
        return this;
    }


    private void getView(BaseViewHolder helper, final MyRecordModel.VisitBean item) {
        ImageLoader.with(item.getOriginal_img(), (ImageView) helper.getView(R.id.img_icon));
        helper.setText(R.id.tv_title, item.getGoods_name());
        TextView tv_price = helper.getView(R.id.tv_price);
        if (item.getType() == 0) {

        } else {

        }
        //        type = 0 是商品  goods_id
//        type = 1 是新房  house_id
//        type = 2 是二手房 house_id
//        type = 3 是租房   house_id
        switch (item.getType()) {
            case 0:
                SpannableStringBuilder spannableStringBuilder = new SpanUtils()
                        .append("¥")
                        .setFontSize(ConvertUtils.sp2px(13))
                        .append(item.getGoods_price() + "")
                        .setFontSize(ConvertUtils.sp2px(18))
                        .create();
                tv_price.setText(spannableStringBuilder);
                tv_price.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                break;
//            case 1:
//                tv_price.setText(item.getGoods_price() + "元/平");
//                tv_price.setTextColor(ZhuanHuanUtil.getColor(R.color.huang1));
//                break;
//            case 2:
//                tv_price.setText(item.getGoods_price() + "万元");
//                tv_price.setTextColor(ZhuanHuanUtil.getColor(R.color.huang1));
//                break;
//            case 3:
//                tv_price.setText(item.getGoods_price() + "元/月");
//                tv_price.setTextColor(ZhuanHuanUtil.getColor(R.color.huang1));
//                break;
            default:
                tv_price.setText(item.getGoods_price() + "");
                tv_price.setTextColor(ZhuanHuanUtil.getColor(R.color.huang1));
                break;
        }
        final CheckBoxNoOnClick cb_select = helper.getView(R.id.cb_select);
        if (isEdit) {
            cb_select.setVisibility(View.VISIBLE);
        } else {
            cb_select.setVisibility(View.GONE);
        }
        cb_select.setChecked(item.isSelect());
        cb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setSelect(cb_select.isChecked());
            }
        });
    }


    public BaseAdapter<MyRecordModel.VisitBean> getmAdapter() {
        return mAdapter;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        type = 0 是商品  goods_id
//        type = 1 是新房  house_id
//        type = 2 是二手房 house_id
//        type = 3 是租房   house_id
        MyRecordModel.VisitBean visitBean = mAdapter.getData().get(position);
        switch (visitBean.getType()) {
            case 0:
                IntentUtil.goGoodsDetails(mActivity, visitBean.getGoods_id()+"");
                break;
            default:
                IntentUtil.goHouseInfo(mActivity, visitBean.getHouse_id()+"");
                break;
        }
    }
}
