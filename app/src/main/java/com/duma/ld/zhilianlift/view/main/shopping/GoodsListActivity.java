package com.duma.ld.zhilianlift.view.main.shopping;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.GoodsBean;
import com.duma.ld.zhilianlift.model.GoodsListModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.QuModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.popupWindow.ListPopupWindow;
import com.duma.ld.zhilianlift.widget.CheckBoxGoodsList;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.basepopup.BasePopupWindow;

import static com.duma.ld.zhilianlift.util.Constants.Res;
import static com.duma.ld.zhilianlift.util.Constants.Type;
import static com.duma.ld.zhilianlift.util.HttpUrl.get_region;
import static com.duma.ld.zhilianlift.util.HttpUrl.goodsList;

/**
 * 商品列表页
 * 从搜索页面 和 类别 进来
 * Created by liudong on 2017/12/6.
 */

public class GoodsListActivity extends BaseMyActivity {
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.layout_search)
    LinearLayout layoutSearch;
    @BindView(R.id.cb_diQu)
    CheckBoxGoodsList cbDiQu;
    @BindView(R.id.cb_xiaoLiang)
    CheckBoxGoodsList cbXiaoLiang;
    @BindView(R.id.cb_jiaGe)
    CheckBoxGoodsList cbJiaGe;
    @BindView(R.id.cb_shaiXuan)
    CheckBoxGoodsList cbShaiXuan;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.tv_search_name)
    TextView tvSearchName;
    @BindView(R.id.cb_tabList)
    CheckBox cbTabList;
    @BindView(R.id.view_show)
    View viewShow;

    private String type;
    private String res;

    private BaseAdapter<GoodsBean> adapter;
    //筛选的list
    private List<Integer> list_shaiXuan;

    private ListPopupWindow listPopupWindow;
    private List<QuModel> quModelList;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_goodslist).setRefresh_A(R.id.sw_loading, R.id.layout_content, R.id.rv_list);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        type = getIntent().getStringExtra(Type);
        res = getIntent().getStringExtra(Res);
        if (!type.equals(Constants.ClassId)) {
            tvSearchName.setText(res);
        }
        list_shaiXuan = new ArrayList<>();
        //初始化pop
        listPopupWindow = new ListPopupWindow(mActivity);
        listPopupWindow.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                cbDiQu.setChecked(false);
                String res = "";
                if (SpDataUtil.getLocation().getDistrict().isEmpty()) {
                    //没有选择区
                    res = SpDataUtil.getLocation().getCity();
                } else {
                    res = SpDataUtil.getLocation().getDistrict();
                }
                if (!cbDiQu.getText().equals(res)) {
                    //不相同 说明换区了
                    cbDiQu.setText(res);
                    onClickLoadingRefresh();
                }
            }
        });
        //初始化top筛选
        topAllDefault();
        initAdapter();
    }

    private void topAllDefault() {
        cbXiaoLiang.setChecked(true);
        cbJiaGe.setChecked(false);
        cbDiQu.setChecked(false);
        if (SpDataUtil.getLocation().getDistrict().isEmpty()) {
            //没有选择区
            cbDiQu.setText(SpDataUtil.getLocation().getCity());
        } else {
            cbDiQu.setText(SpDataUtil.getLocation().getDistrict());
        }
        if (list_shaiXuan.size() == 0) {
            cbShaiXuan.setChecked(false);
        } else {
            cbShaiXuan.setChecked(true);
        }
    }

    private void initAdapter() {
        BaseAdapter.Builder<GoodsBean> builder;
        if (cbTabList.isChecked()) {
            //列表
            builder = new BaseAdapter.Builder<GoodsBean>(rvList, mActivity, R.layout.adapter_goods_list)
                    .setLayoutManager(new LinearLayoutManager(mActivity));
        } else {
            builder = new BaseAdapter.Builder<GoodsBean>(rvList, mActivity, R.layout.adapter_goods)
                    .setLayoutManager(new GridLayoutManager(mActivity, 2));
        }
        adapter = builder.buildLoad(new OnBaseLoadAdapterListener<GoodsBean>() {
            @Override
            public void onLoadHttp(int page, int httpTag) {
                GetRequest<HttpResModel<GoodsListModel>> request = OkGo.<HttpResModel<GoodsListModel>>get(goodsList)
                        .tag(httpTag)
                        .params(Constants.Page, page)
                        .params("code", SpDataUtil.getLocation().getCode());
                if (type.equals(Constants.ClassId)) {
                    //分类进来的
                    request.params("id", res);
                } else {
                    request.params("id", res);
                }
                if (cbXiaoLiang.isChecked()) {
                    //销量排序 从大到小
                    request.params("sort", "sales_sum");
                    request.params("sort_asc", "desc");
                }
                if (cbJiaGe.isChecked()) {
                    //价格排序的话
                    request.params("sort", "shop_price");
                    if (cbJiaGe.isTypeTop()) {
                        //从小到大
                        request.params("sort_asc", "asc");
                    } else {
                        request.params("sort_asc", "desc");
                    }
                }
                if (cbShaiXuan.isChecked()) {
                    //筛选
                }
                request.execute(new MyJsonCallback<HttpResModel<GoodsListModel>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<GoodsListModel>> respons, HttpResModel<GoodsListModel> listHttpResModel) {
                        adapter.setLoadData(listHttpResModel.getResult().getGoods_list());
                    }
                }.setLoadAdapter(adapter));
            }

            @Override
            public void convert(BaseViewHolder helper, GoodsBean item) {
                PublicUtil.goodsGetView(helper, item);
            }
        });
        onClickLoadingRefresh();
    }

    @OnClick({R.id.layout_back, R.id.layout_search, R.id.cb_tabList, R.id.cb_diQu, R.id.cb_xiaoLiang, R.id.cb_jiaGe, R.id.cb_shaiXuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_search:
                IntentUtil.goSearch(mActivity);
                break;
            case R.id.cb_tabList:
                //切换视图
                initAdapter();
                break;
            case R.id.cb_diQu:
                if (quModelList == null || quModelList.size() == 0) {
                    getQuHttp();
                } else {
                    showPop();
                }
                break;
            case R.id.cb_xiaoLiang:
                cbJiaGe.setChecked(false);
                cbXiaoLiang.setChecked(true);
                adapter.onRefresh();
                break;
            case R.id.cb_jiaGe:
                cbXiaoLiang.setChecked(false);
                cbJiaGe.setChecked(true, !cbJiaGe.isTypeTop());
                adapter.onRefresh();
                break;
            case R.id.cb_shaiXuan:
                //筛选开关
                break;
        }
    }

    private void getQuHttp() {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<List<QuModel>>>get(get_region)
                .params("name", SpDataUtil.getLocation().getCity())
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<List<QuModel>>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<QuModel>>> respons, HttpResModel<List<QuModel>> listHttpResModel) {
                        DialogUtil.getInstance().hide();
                        quModelList = listHttpResModel.getResult();
                        listPopupWindow.setList(quModelList);
                        showPop();
                    }
                });
    }

    private void showPop() {
        cbDiQu.setChecked(true);
        listPopupWindow.showPopupWindow(viewShow);
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        adapter.onRefresh();
    }

}
