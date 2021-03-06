package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.AddresModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.widget.CheckBoxNoOnClick;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.Constants.event_addresList_add;
import static com.duma.ld.zhilianlift.util.Constants.event_addresList_edit;
import static com.duma.ld.zhilianlift.util.Constants.event_address;
import static com.duma.ld.zhilianlift.util.HttpUrl.del_address;
import static com.duma.ld.zhilianlift.util.HttpUrl.getAddressList;
import static com.duma.ld.zhilianlift.util.HttpUrl.setDefaultAddress;

/**
 * 地址管理
 * Created by liudong on 2017/12/11.
 */

public class AddresListActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.tv_addres)
    TextView tvAddres;
    private BaseAdapter<AddresModel> adapter;
    //点击编辑的positong
    private int positionEdit;
    //当前是默认地址的position;
    private int positionDefault;

    //传过来的 如果不为null 就把id传过去
    private String keyId;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        switch (eventModel.getCode()) {
            case event_addresList_add:
                adapter.addData((AddresModel) eventModel.getData());
                break;
            case event_addresList_edit:
                adapter.setData(positionEdit, (AddresModel) eventModel.getData());
                break;
        }
    }

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_addres_list)
                .setTopBar_A("地址管理", new OnTopBarLeftListener() {
                    @Override
                    public void onClick() {
                        onBack();
                    }
                })
                .setRefresh_A(R.id.sw_loading, R.id.layout_content, R.id.rv_list);
    }

    @Override
    protected void onBack() {
        if (keyId != null) {
            TsUtils.show("请点击选择一个收货地址,或者新建一个收货地址!");
        } else {
            finish();
        }
    }

    @OnClick(R.id.tv_addres)
    public void onViewClicked() {
        IntentUtil.goAdd_Addres(mActivity);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        keyId = getIntent().getStringExtra(Constants.key);
        adapter = new BaseAdapter.Builder<AddresModel>(rvList, mActivity, R.layout.rv_dizhi_list)
                .buildLoad(new OnBaseLoadAdapterListener<AddresModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<List<AddresModel>>>get(getAddressList)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .execute(new MyJsonCallback<HttpResModel<List<AddresModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<AddresModel>>> respons, HttpResModel<List<AddresModel>> listHttpResModel) {
                                        adapter.setLoadData(listHttpResModel.getResult());
                                    }
                                }.setLoadAdapter(adapter));
                    }

                    @Override
                    public void convert(final BaseViewHolder helper, AddresModel item) {
                        helper.setText(R.id.tv_name, item.getConsignee())
                                .setText(R.id.tv_dianhua, item.getMobile())
                                .setText(R.id.tv_xiangxidizhi, item.getProvince_city_district() + " " + item.getAddress())
                                .addOnClickListener(R.id.tv_edit)
                                .addOnClickListener(R.id.tv_remove);
                        CheckBoxNoOnClick checkBoxNoOnClick = helper.getView(R.id.cb_moren);
                        if (item.getIs_default() == 1) {
                            positionDefault = helper.getLayoutPosition();
                            checkBoxNoOnClick.setChecked(true);
                        } else {
                            checkBoxNoOnClick.setChecked(false);
                        }
                        checkBoxNoOnClick.setOnNewClickListener(new CheckBoxNoOnClick.OnNewClickListener() {
                            @Override
                            public void onClick() {
                                //设置默认
                                if (helper.getLayoutPosition() != positionDefault) {
                                    setDefault(helper.getLayoutPosition());
                                }
                            }
                        });

                    }
                });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (keyId != null) {
                    AddresModel model = AddresListActivity.this.adapter.getData().get(position);
                    EventBusUtil.sendModel(event_address, model.getAddress_id() + "", model);
                    mActivity.finish();
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.tv_edit:
                        positionEdit = position;
                        IntentUtil.goAddOrChange(mActivity, (AddresModel) adapter.getData().get(position));
                        break;
                    case R.id.tv_remove:
                        AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "删除收货地址", "是否删除该收货地址?")
                                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        removeHttp(position);
                                    }
                                })
                                .setNegativeButton("否", null)
                                .setCancelable(false);
                        builder.show();
                        break;
                }
            }
        });
        onClickLoadingRefresh();
    }

    private void setDefault(final int layoutPosition) {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<String>>get(setDefaultAddress)
                .tag(httpTag)
                .params("address_id", adapter.getData().get(layoutPosition).getAddress_id())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        //更改旧的数据集为未选中
                        AddresModel modelold = adapter.getData().get(positionDefault);
                        modelold.setIs_default(0);
                        adapter.setData(positionDefault, modelold);
                        //更改新的为选中
                        CheckBoxNoOnClick checkBoxNoOnClick = (CheckBoxNoOnClick) adapter.getViewByPosition(rvList, layoutPosition, R.id.cb_moren);
                        checkBoxNoOnClick.setChecked(true);
                        adapter.getData().get(layoutPosition).setIs_default(1);
                        positionDefault = layoutPosition;
                        Logger.e(adapter.getData().get(layoutPosition).getIs_default() + "");
                    }
                });
    }

    private void removeHttp(final int position) {
        DialogUtil.getInstance().show_noBack(mActivity);
        OkGo.<HttpResModel<String>>get(del_address)
                .tag(httpTag)
                .params("id", adapter.getData().get(position).getAddress_id())
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        //查看删除的是不是默认地址
                        if (positionDefault == position) {
                            //删除的是默认地址 就把第一个改为默认
                            if (adapter.getData().size() > 0) {
                                AddresModel model = adapter.getData().get(0);
                                model.setIs_default(1);
                                adapter.setData(0, model);
                            }
                        }
                        //删除
                        adapter.remove(position);
                    }
                });
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        adapter.onRefresh();
    }


}
