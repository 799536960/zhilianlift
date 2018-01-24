package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
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
import com.duma.ld.zhilianlift.model.GoodsListModel;
import com.duma.ld.zhilianlift.model.HouseListModel;
import com.duma.ld.zhilianlift.model.HousePopModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.MyHouseModel;
import com.duma.ld.zhilianlift.model.QuModel;
import com.duma.ld.zhilianlift.model.ScreeningModel;
import com.duma.ld.zhilianlift.model.ScreeningSelectListModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.ShaiXuanUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.view.popupWindow.HouseQuYuPopupWindow;
import com.duma.ld.zhilianlift.view.popupWindow.HouseTwoListPopupWindow;
import com.duma.ld.zhilianlift.view.popupWindow.OnPopListener;
import com.duma.ld.zhilianlift.widget.CheckBoxGoodsList;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.basepopup.BasePopupWindow;

import static com.duma.ld.zhilianlift.util.HttpUrl.getALL1;
import static com.duma.ld.zhilianlift.util.HttpUrl.getALL2;
import static com.duma.ld.zhilianlift.util.HttpUrl.getALL3;
import static com.duma.ld.zhilianlift.util.HttpUrl.getList1;
import static com.duma.ld.zhilianlift.util.HttpUrl.getList2;
import static com.duma.ld.zhilianlift.util.HttpUrl.getList3;
import static com.duma.ld.zhilianlift.util.HttpUrl.get_region;

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
    //pop
    //区域pop
    private HouseQuYuPopupWindow houseQuYuPopupWindow;
    private List<QuModel> quModelList;
    private String city;
    //价格pop
    private HouseTwoListPopupWindow houseTwoListPopupWindow;
    //区域
    private HouseTwoListPopupWindow houseTwoListPopupWindowHuXin;

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
        //默认是当前的城市
        city = SpDataUtil.getLocation().getCity();
        //初始化pop
        initPop();
        //初始化筛选
        initScreen();
        //初始化房产列表设配器
        initAdapter();
        onClickLoadingRefresh();
    }

    //初始化pop
    private void initPop() {
        /**
         * 区域pop
         */
        houseQuYuPopupWindow = new HouseQuYuPopupWindow(mActivity);
        houseQuYuPopupWindow.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                cbDiQu.setChecked(false);
                String name;
                if (houseQuYuPopupWindow.getmPosition() != 0) {
                    name = quModelList.get(houseQuYuPopupWindow.getmPosition()).getName();
                } else {
                    name = "区域";
                }
                if (!name.equals(cbDiQu.getText())) {
                    cbDiQu.setText(name);
                    onClickLoadingRefresh();
                }
            }
        });
        /**
         * 价格
         */
        houseTwoListPopupWindow = new HouseTwoListPopupWindow(mActivity);
        houseTwoListPopupWindow.setCbText(cbJiaGe, "价格", new OnPopListener() {
            @Override
            public void refresh() {
                onClickLoadingRefresh();
            }
        });
        houseTwoListPopupWindowHuXin = new HouseTwoListPopupWindow(mActivity);
        houseTwoListPopupWindowHuXin.setCbText(cbHuXin, "户型", new OnPopListener() {
            @Override
            public void refresh() {
                onClickLoadingRefresh();
            }
        });

//        houseTwoListPopupWindow.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                cbJiaGe.setChecked(false);
//                String name;
//                if (houseTwoListPopupWindow.getmPositionContent() != 0) {
//                    name = houseTwoListPopupWindow.getmAdapterContent().getData().get(houseTwoListPopupWindow.getmPositionContent()).getSo_name();
//                } else {
//                    name = "价格";
//                }
//                if (!name.equals(cbJiaGe.getText())) {
//                    cbJiaGe.setText(name);
//                    onClickLoadingRefresh();
//                }
//            }
//        });
    }

    private void initAdapter() {
        int house_layout;
        switch (type) {
            case 0:
                //找新房
                house_layout = R.layout.adapter_new_house_list;
                break;
            default:
                house_layout = R.layout.adapter_house_list;
                break;
        }
        mAdapter = new BaseAdapter.Builder<MyHouseModel>(rvList, mActivity, house_layout)
                .buildLoad(new OnBaseLoadAdapterListener<MyHouseModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        GetRequest<HttpResModel<HouseListModel>> request = null;
                        switch (type) {
                            case 0:
                                //找新房
                                request = OkGo.get(getList1);
                                break;
                            case 1:
                                request = OkGo.get(getList3);
                                break;
                            default:
                                request = OkGo.get(getList2);
                                break;
                        }
                        if (cbShaiXuan.isChecked()) {
                            //筛选
                            List<ScreeningSelectListModel> list_shaiXuan = shaiXuanUtil.getList_shaiXuan();
                            for (int i = 0; i < list_shaiXuan.size(); i++) {
                                String value = "";
                                for (int i1 = 0; i1 < list_shaiXuan.get(i).getList().size(); i1++) {
                                    if (i1 == 0) {
                                        value = list_shaiXuan.get(i).getList().get(i1).getValue();
                                    } else {
                                        value = value + "," + list_shaiXuan.get(i).getList().get(i1).getValue();
                                    }
                                }
                                Logger.e("筛选 Key: " + list_shaiXuan.get(i).getKey() + " value: " + value);
                                request.params(list_shaiXuan.get(i).getKey(), value);
                            }
                        }
                        HousePopModel.FilterAttrBean.ItemBean selectModel = houseTwoListPopupWindow.getSelectModel();
                        if (selectModel != null) {
                            Logger.e("价格 Key: " + selectModel.getSo_key() + " value: " + selectModel.getSo_value());
                            request.params(selectModel.getSo_key(), selectModel.getSo_value());
                        }
                        selectModel = houseTwoListPopupWindowHuXin.getSelectModel();
                        if (selectModel != null) {
                            Logger.e("户型 Key: " + selectModel.getSo_key() + " value: " + selectModel.getSo_value());
                            request.params(selectModel.getSo_key(), selectModel.getSo_value());
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
                        switch (type) {
                            case 0:
                                //找新房
                                PublicUtil.getViewHouseNew(helper, item, mActivity);
                                break;
                            case 1:
                                helper.setGone(R.id.tv_num, false);
                                //租房
                                PublicUtil.getViewHouse(helper, item, mActivity, false);
                                break;
                            default:
                                helper.setGone(R.id.tv_num, false);
                                PublicUtil.getViewHouse(helper, item, mActivity, true);
                                break;
                        }

                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtil.goHouseInfo(mActivity, mAdapter.getData().get(position).getHouse_id());
            }
        });
    }


    @OnClick({R.id.tv_cancel, R.id.tv_ok, R.id.tv_city, R.id.cb_diQu, R.id.cb_jiaGe, R.id.cb_huXin, R.id.cb_shaiXuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                break;
            case R.id.cb_diQu:
                if (quModelList == null || quModelList.size() == 0) {
                    getQuHttp();
                } else {
                    showPop();
                }
                break;
            case R.id.cb_jiaGe:
                if (houseTwoListPopupWindow.isModel()) {
                    jiaGeHttp();
                } else {
                    showPopJiaGe();
                }
                break;
            case R.id.cb_huXin:
                if (houseTwoListPopupWindowHuXin.isModel()) {
                    huXinHttp();
                } else {
                    showPopHuXin();
                }
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

    private void jiaGeHttp() {
        String url = getALL1;
        String cat_id = "";
        switch (type) {
            case 0:
                cat_id = "853";
                break;
            case 1:
                cat_id = "855";
                break;
            case 2:
                cat_id = "854";
                url = getALL3;
                break;
        }
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<HousePopModel>>get(url)
                .params("cat_id", cat_id)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<HousePopModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<HousePopModel>> respons, HttpResModel<HousePopModel> listHttpResModel) {
                        DialogUtil.getInstance().hide();
                        houseTwoListPopupWindow.setHousePopModel(listHttpResModel.getResult());
                        showPopJiaGe();
                    }
                });
    }

    private void huXinHttp() {
        String cat_id = "";
        switch (type) {
            case 0:
                cat_id = "853";
                break;
            case 1:
                cat_id = "855";
                break;
            case 2:
                cat_id = "854";
                break;
        }
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<HousePopModel>>get(getALL2)
                .tag(httpTag)
                .params("cat_id", cat_id)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<HousePopModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<HousePopModel>> respons, HttpResModel<HousePopModel> listHttpResModel) {
                        DialogUtil.getInstance().hide();
                        houseTwoListPopupWindowHuXin.setHousePopModel(listHttpResModel.getResult());
                        showPopHuXin();
                    }
                });
    }

    private void showPopJiaGe() {
        cbJiaGe.setChecked(true);
        houseTwoListPopupWindow.showPopupWindow(viewShow);
    }

    private void showPopHuXin() {
        cbHuXin.setChecked(true);
        houseTwoListPopupWindowHuXin.showPopupWindow(viewShow);
    }

    private void getQuHttp() {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<List<QuModel>>>get(get_region)
                .params("name", city)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<List<QuModel>>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<QuModel>>> respons, HttpResModel<List<QuModel>> listHttpResModel) {
                        DialogUtil.getInstance().hide();
                        quModelList = listHttpResModel.getResult();
                        quModelList.add(0, new QuModel("不限"));
                        houseQuYuPopupWindow.setList(quModelList);
                        showPop();
                    }
                });
    }

    private void showPop() {
        cbDiQu.setChecked(true);
        houseQuYuPopupWindow.showPopupWindow(viewShow);
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
