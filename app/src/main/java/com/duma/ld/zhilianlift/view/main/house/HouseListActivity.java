package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.GoodsListModel;
import com.duma.ld.zhilianlift.model.HouseListModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.MyHouseModel;
import com.duma.ld.zhilianlift.model.ScreeningModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.ShaiXuanUtil;
import com.duma.ld.zhilianlift.widget.CheckBoxGoodsList;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.getAddressList;
import static com.duma.ld.zhilianlift.util.HttpUrl.getList2;
import static com.duma.ld.zhilianlift.util.HttpUrl.getList3;

/**
 * 房产列表 0找新房 1二手房(售房) 2租房
 * Created by liudong on 2018/1/18.
 */

public class HouseListActivity extends BaseMyActivity {
    @BindView(R.id.cb_diQu)
    CheckBoxGoodsList cbDiQu;
    @BindView(R.id.cb_jiaGe)
    CheckBoxGoodsList cbJiaGe;
    @BindView(R.id.cb_huXin)
    CheckBoxGoodsList cbHuXin;
    @BindView(R.id.cb_shaiXuan)
    CheckBoxGoodsList cbShaiXuan;
    @BindView(R.id.view_show)
    View viewShow;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_content)
    FrameLayout layoutContent;
    @BindView(R.id.rv_screen)
    RecyclerView rvScreen;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.layout_drawerLayout)
    DrawerLayout layoutDrawerLayout;
    @BindView(R.id.tv_city)
    TextView tvCity;

    private int type;
    private BaseAdapter<MyHouseModel> mAdapter;
    //筛选
    private ShaiXuanUtil shaiXuanUtil;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_house_list).setRefresh_A(R.id.sw_loading, R.id.layout_content, R.id.rv_list);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        type = getIntent().getIntExtra(Constants.Type, 0);
        switch (type) {
            case 0:
                mActivityConfig.setTopBar_A("找新房");
                break;
            case 1:
                mActivityConfig.setTopBar_A("二手房");
                break;
            case 2:
                mActivityConfig.setTopBar_A("租房");
                break;
        }
        //初始化筛选
        initScreen();
        //初始化房产列表设配器
        initAdapter();
        onClickLoadingRefresh();
    }


    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<MyHouseModel>(rvList, mActivity, R.layout.adapter_house_list)
                .buildLoad(new OnBaseLoadAdapterListener<MyHouseModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        GetRequest<HttpResModel<HouseListModel>> request = null;
                        switch (type) {
                            case 0:
                                //找新房
                                request = OkGo.get(getAddressList);
                                break;
                            case 1:
                                request = OkGo.get(getList3);
                                break;
                            default:
                                request = OkGo.get(getList2);
                                break;
                        }
                        request
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .execute(new MyJsonCallback<HttpResModel<HouseListModel>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<HouseListModel>> respons, HttpResModel<HouseListModel> listHttpResModel) {
                                        mAdapter.setLoadData(listHttpResModel.getResult().getHouseList());
                                        //初始化筛选数据
                                        if (shaiXuanUtil.getListScreen().size() == 0) {
                                            initData(listHttpResModel.getResult());
                                        }
                                    }
                                }.setLoadAdapter(mAdapter));
                    }


                    @Override
                    public void convert(BaseViewHolder helper, MyHouseModel item) {
                        helper.setGone(R.id.tv_num, false);
                        if (type == 1) {
                            PublicUtil.getViewHouse(helper, item, mActivity, false);
                        } else {
                            PublicUtil.getViewHouse(helper, item, mActivity, true);
                        }

                    }
                });
    }


    @OnClick({R.id.tv_cancel, R.id.tv_ok, R.id.tv_city, R.id.cb_diQu, R.id.cb_jiaGe, R.id.cb_huXin, R.id.cb_shaiXuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                break;
            case R.id.cb_diQu:
                break;
            case R.id.cb_jiaGe:
                break;
            case R.id.cb_huXin:
                break;
            case R.id.cb_shaiXuan:
                //筛选开关
                shaiXuanUtil.openDrawer();
                break;
            case R.id.tv_cancel:
                //列表所有未选中
                shaiXuanUtil.cancelDrawer();
                break;
            case R.id.tv_ok:
                shaiXuanUtil.closeDrawer();
                break;
        }
    }

    //初始化筛选数据
    private void initData(HouseListModel result) {
        List<HouseListModel.FilterAttrBean> filter_attr = result.getFilter_attr();
        if (filter_attr == null) {
            return;
        }
        for (int i = 0; i < filter_attr.size(); i++) {
            //添加head
            shaiXuanUtil.getListScreen().add(ScreeningModel.newHeadModel(filter_attr.get(i).getName()));
            for (int i1 = 0; i1 < filter_attr.get(i).getItem().size(); i1++) {
                //添加content
                GoodsListModel.FilterAttrBean.ItemBean itemBean = new GoodsListModel.FilterAttrBean.ItemBean();
                itemBean.setName(filter_attr.get(i).getItem().get(i1).getSo_name());
                itemBean.setValue(filter_attr.get(i).getItem().get(i1).getSo_value());
                itemBean.setKey(filter_attr.get(i).getItem().get(i1).getSo_key());
                shaiXuanUtil.getListScreen().add(ScreeningModel.newContentModel(itemBean));
            }
            //添加线
            shaiXuanUtil.getListScreen().add(ScreeningModel.newViewModel());
        }
        shaiXuanUtil.getScreeningAdapter().notifyDataSetChanged();
    }

    //初始化筛选
    private void initScreen() {
        shaiXuanUtil = new ShaiXuanUtil(layoutDrawerLayout, rvScreen, mActivity, cbShaiXuan, new ShaiXuanUtil.OnShaiXuanUtilListener() {
            @Override
            public void onRefresh() {
                onClickLoadingRefresh();
            }
        });
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }

}
