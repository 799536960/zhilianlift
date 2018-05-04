package com.duma.ld.zhilianlift.view.start;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.PingPaiListModel;
import com.duma.ld.zhilianlift.model.PingPaiShaiXuanModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.view.popupWindow.OnPopListener;
import com.duma.ld.zhilianlift.view.popupWindow.PinPaiTwoListPopupWindow;
import com.duma.ld.zhilianlift.widget.CheckBoxGoodsList;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.getBrandById;
import static com.duma.ld.zhilianlift.util.HttpUrl.goodsSecAndThirdCategoryList;

/**
 * 品牌
 * Created by liudong on 2018/1/25.
 */

public class PinPaiActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_left)
    LinearLayout layoutLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cb_shaiXuan)
    CheckBoxGoodsList cbShaiXuan;
    @BindView(R.id.view_show)
    View viewShow;
    private BaseAdapter<PingPaiListModel> mAdapter;
    private PinPaiTwoListPopupWindow pinPaiTwoListPopupWindow;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_pinpai).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        tvTitle.setText("合作品牌");
        pinPaiTwoListPopupWindow = new PinPaiTwoListPopupWindow(mActivity);
        pinPaiTwoListPopupWindow.setCbText(cbShaiXuan, new OnPopListener() {
            @Override
            public void refresh() {
                onClickLoadingRefresh();
            }
        });
        mAdapter = new BaseAdapter.Builder<PingPaiListModel>(rvList, mActivity, R.layout.adapter_pinpai)
                .setLayoutManager(new GridLayoutManager(mActivity, 4))
                .buildLoad(new OnBaseLoadAdapterListener<PingPaiListModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        GetRequest<HttpResModel<List<PingPaiListModel>>> params = OkGo.<HttpResModel<List<PingPaiListModel>>>get(getBrandById)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .params(Constants.Size, 30);
                        PingPaiShaiXuanModel.SubCategoryBean selectModel = pinPaiTwoListPopupWindow.getSelectModel();
                        if (selectModel != null) {
                            params.params("id", selectModel.getId());
                            params.params("level", selectModel.getLevel());
                        }
                        params
                                .execute(new MyJsonCallback<HttpResModel<List<PingPaiListModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<PingPaiListModel>>> respons, HttpResModel<List<PingPaiListModel>> listHttpResModel) {
                                        mAdapter.setLoadData(listHttpResModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, PingPaiListModel item) {
                        helper.setText(R.id.tv_name, item.getName());
                        ImageLoader.with(item.getLogo(), (ImageView) helper.getView(R.id.img_icon));
                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtil.goMenDian(mActivity, mAdapter.getData().get(position).getId());
            }
        });
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }


    @OnClick({R.id.layout_left, R.id.cb_shaiXuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_left:
                finish();
                break;
            case R.id.cb_shaiXuan:
                if (pinPaiTwoListPopupWindow.isModel()) {
                    getShaiXuanHttp();
                } else {
                    cbShaiXuan.setChecked(true);
                    pinPaiTwoListPopupWindow.showPopupWindow(viewShow);
                }
                break;
        }
    }

    private void getShaiXuanHttp() {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<List<PingPaiShaiXuanModel>>>get(goodsSecAndThirdCategoryList)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<List<PingPaiShaiXuanModel>>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<PingPaiShaiXuanModel>>> respons, HttpResModel<List<PingPaiShaiXuanModel>> listHttpResModel) {
                        DialogUtil.getInstance().hide();
                        pinPaiTwoListPopupWindow.setHousePopModel(listHttpResModel.getResult());
                        cbShaiXuan.setChecked(true);
                        pinPaiTwoListPopupWindow.showPopupWindow(viewShow);
                    }
                });
    }
}
