package com.duma.ld.zhilianlift.view.main.wode;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.WoDeBaoBeiModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.editPreparationStatus;
import static com.duma.ld.zhilianlift.util.HttpUrl.myPreparation;

/**
 * 我的报备
 * Created by liudong on 2018/1/24.
 */

public class WoDeBaoBeiActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;
    private BaseAdapter<WoDeBaoBeiModel> mAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_wode_baobei).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading).setTopBar_A("我的报备");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mAdapter = new BaseAdapter.Builder<WoDeBaoBeiModel>(rvList, mActivity, R.layout.adapter_wode_baobei)
                .buildLoad(new OnBaseLoadAdapterListener<WoDeBaoBeiModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<List<WoDeBaoBeiModel>>>get(myPreparation)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .execute(new MyJsonCallback<HttpResModel<List<WoDeBaoBeiModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<WoDeBaoBeiModel>>> respons, HttpResModel<List<WoDeBaoBeiModel>> listHttpResModel) {
                                        mAdapter.setLoadData(listHttpResModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, WoDeBaoBeiModel item) {
                        helper.addOnClickListener(R.id.tv_cancel)
                                .setText(R.id.tv_houseName, "" + item.getPremises_name())
                                .setText(R.id.tv_beiTuiJianRen_name, "" + item.getRecommended_name())
                                .setText(R.id.tv_beiTuiJianRen_phone, "" + item.getRecommended_mobile())
                                .setText(R.id.tv_beiZhu, "" + item.getRemarksNull())
                                .setText(R.id.tv_name, "" + item.getRecommend_name())
                                .setText(R.id.tv_phone, "" + item.getRecommend_mobile());
                    }
                });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "取消报备", "确定要取消报备嘛?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cancelHttp(position);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(false);
                builder.show();
            }
        });
        onClickLoadingRefresh();
    }

    private void cancelHttp(final int position) {
        OkGo.<HttpResModel<String>>post(editPreparationStatus)
                .tag(httpTag)
                .params("status", 0)
                .params("id", mAdapter.getData().get(position).getId())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        TsUtils.show("取消成功!");
                        mAdapter.remove(position);
                    }
                }.isDialog(mActivity));
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }
}
