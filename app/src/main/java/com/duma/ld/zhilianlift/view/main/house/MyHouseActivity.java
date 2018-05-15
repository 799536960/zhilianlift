package com.duma.ld.zhilianlift.view.main.house;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HouseChuZuInfoModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.MyHouseModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.deleteHouse;
import static com.duma.ld.zhilianlift.util.HttpUrl.editHouseStatus;
import static com.duma.ld.zhilianlift.util.HttpUrl.getMyHouse;
import static com.duma.ld.zhilianlift.util.HttpUrl.gethoustInfo;

/**
 * 我的二手房
 * Created by liudong on 2018/1/9.
 */

public class MyHouseActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    FrameLayout layoutRoot;
    @BindView(R.id.tv_sell)
    TextView tvSell;
    private boolean isChuZu;
    private BaseAdapter<MyHouseModel> mAdapter;
    private String httpType;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_my_second_house).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        if (StringUtils.isEmpty(getIntent().getStringExtra(Constants.key))) {
            isChuZu = false;
            mActivityConfig.setTopBar_A("我的二手房");
            tvSell.setText("我要出售");
            httpType = "2";
        } else {
            isChuZu = true;
            mActivityConfig.setTopBar_A("我的出租");
            tvSell.setText("我要出租");
            httpType = "3";
        }
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<MyHouseModel>(rvList, mActivity, R.layout.adapter_my_house_list)
                .buildLoad(new OnBaseLoadAdapterListener<MyHouseModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<List<MyHouseModel>>>get(getMyHouse)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .params("house_type", httpType)
                                .execute(new MyJsonCallback<HttpResModel<List<MyHouseModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<MyHouseModel>>> respons, HttpResModel<List<MyHouseModel>> listHttpResModel) {
                                        mAdapter.setLoadData(listHttpResModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, final MyHouseModel item) {
                        PublicUtil.getViewHouse(helper, item, mActivity, isChuZu);
                        helper.addOnClickListener(R.id.tv_delete)
                                .setText(R.id.tv_time, ZhuanHuanUtil.Time2fen(item.getOn_time() * 1000))
                                .addOnClickListener(R.id.tv_change);
                        TextView tv_num = helper.getView(R.id.tv_num);
                        TextView tv_delete = helper.getView(R.id.tv_delete);
                        TextView tv_change = helper.getView(R.id.tv_change);
                        TextView tv_type = helper.getView(R.id.tv_type);
                        TextView tv_bianji = helper.getView(R.id.tv_bianji);
                        tv_num.setText(item.getRead_count() + "次浏览");
                        if (isChuZu) {
                            tv_num.setVisibility(View.VISIBLE);
                        } else {
                            tv_num.setVisibility(View.GONE);
                        }
                        if (item.getHouse_status() == 0) {
                            //下架
                            tv_bianji.setVisibility(View.VISIBLE);
                            tv_delete.setVisibility(View.VISIBLE);
                            tv_change.setText("上架");
                            tv_type.setText("已下架");
                        } else {
                            tv_bianji.setVisibility(View.GONE);
                            tv_delete.setVisibility(View.GONE);
                            tv_change.setText("下架");
                            tv_type.setText("已发布");
                        }
                        tv_bianji.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                OkGo.<HttpResModel<HouseChuZuInfoModel>>get(gethoustInfo)
                                        .tag(httpTag)
                                        .params("house_id", item.getHouse_id())
                                        .execute(new MyJsonCallback<HttpResModel<HouseChuZuInfoModel>>() {
                                            @Override
                                            protected void onJsonSuccess(Response<HttpResModel<HouseChuZuInfoModel>> respons
                                                    , HttpResModel<HouseChuZuInfoModel> houseChuZuInfoModelHttpResModel) {
                                                IntentUtil.goAddHouse(mActivity, isChuZu, houseChuZuInfoModelHttpResModel.getResult());
                                            }
                                        }.isDialog(mActivity));
                            }
                        });
                    }
                });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyHouseModel myHouseModel = mAdapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.tv_delete:
                        //删除
                        delete(myHouseModel.getHouse_id(), position);
                        break;
                    case R.id.tv_change:
                        changeType(position);
                        break;
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtil.goHouseInfo(mActivity, mAdapter.getData().get(position).getHouse_id()+"");
            }
        });
    }


    private void delete(final int house_id, final int position) {
        AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "删除", "您确定删除此条信息?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OkGo.<HttpResModel<String>>get(deleteHouse)
                                .tag(httpTag)
                                .params("house_id", house_id)
                                .execute(new MyJsonCallback<HttpResModel<String>>() {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                                        TsUtils.show("删除成功!");
                                        mAdapter.remove(position);
                                    }
                                }.isDialog(mActivity));
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false);
        builder.show();
    }

    private void changeType(final int position) {
        final int type;
        MyHouseModel myHouseModel = mAdapter.getData().get(position);
        if (myHouseModel.getHouse_status() == 0) {
            //下架 就让他上架
            type = 1;
        } else {
            type = 0;
        }
        OkGo.<HttpResModel<String>>get(editHouseStatus)
                .tag(httpTag)
                .params("house_id", myHouseModel.getHouse_id())
                .params("house_status", type)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        mAdapter.getData().get(position).setHouse_status(type);
                        mAdapter.notifyItemChanged(position);
                    }
                }.isDialog(mActivity));
    }

    @Override
    protected void onResume() {
        super.onResume();
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }

    @OnClick(R.id.tv_sell)
    public void onViewClicked() {
        IntentUtil.goAddHouse(mActivity, isChuZu, null);
    }
}
