package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.PCDAddresModel;
import com.duma.ld.zhilianlift.model.ProvinceModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.Constants.event_addres_add;
import static com.duma.ld.zhilianlift.util.Constants.event_addres_refresh;
import static com.duma.ld.zhilianlift.util.HttpUrl.getprovince;

/**
 * 选择和显示县市区的fragment
 * 由选择地址dialog页面viewpager初始化
 * Created by liudong on 2017/12/12.
 */

public class SelectAddresFragment extends BaseMyFragment {

    @BindView(R.id.tv_list)
    RecyclerView tvList;

    private PCDAddresModel model;
    private BaseAdapter<ProvinceModel> adapter;
    private int select = -1;

    public static SelectAddresFragment newInstance(PCDAddresModel model) {
        SelectAddresFragment fragment = new SelectAddresFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.key, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        Bundle args = getArguments();
        if (args != null) {
            model = (PCDAddresModel) args.getSerializable(Constants.key);
        }
        return initConfig.setLayoutIdByFragment(R.layout.fragment_select_addres);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        adapter = new BaseAdapter<ProvinceModel>(R.layout.adapter_select_addres) {
            @Override
            protected void convert(BaseViewHolder helper, ProvinceModel item) {
                helper.setText(R.id.tv_name, item.getName());
                if (select == helper.getLayoutPosition()) {
                    helper.setTextColor(R.id.tv_name, ZhuanHuanUtil.getColor(R.color.hong));
                } else {
                    helper.setTextColor(R.id.tv_name, ZhuanHuanUtil.getColor(R.color.hei1));
                }
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                select = position;
                adapter.notifyItemChanged(position);

                List<ProvinceModel> data = adapter.getData();
                PCDAddresModel sendModel = new PCDAddresModel();
                sendModel.setProvinceModel(model.getProvinceModel());
                sendModel.setCityModel(model.getCityModel());
                sendModel.setDistrictModel(model.getDistrictModel());
                if (sendModel.getProvinceModel() == null) {
                    sendModel.setProvinceModel(data.get(position));
                    EventBusUtil.sendModel(event_addres_refresh, sendModel);
                    return;
                }
                if (sendModel.getProvinceModel() != null && sendModel.getCityModel() == null) {
                    sendModel.setCityModel(data.get(position));
                    EventBusUtil.sendModel(event_addres_refresh, sendModel);
                    return;
                }
                if (sendModel.getProvinceModel() != null && sendModel.getCityModel() != null && sendModel.getDistrictModel() == null) {
                    sendModel.setDistrictModel(data.get(position));
                    EventBusUtil.sendModel(event_addres_add, sendModel);
                    EventBusUtil.sendModel(event_addres_refresh);
                    return;
                }
            }
        });
        tvList.setLayoutManager(new LinearLayoutManager(mActivity));
        tvList.setAdapter(adapter);
        onClickLoadingRefresh();
    }


    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        GetRequest<HttpResModel<List<ProvinceModel>>> httpResModelGetRequest = OkGo.<HttpResModel<List<ProvinceModel>>>get(getprovince);
        if (model.getCityModel() != null) {
            httpResModelGetRequest.params("parent_id", model.getCityModel().getId());
        } else {
            if (model.getProvinceModel() != null) {
                httpResModelGetRequest.params("parent_id", model.getProvinceModel().getId());
            }
        }
        httpResModelGetRequest
                .execute(new MyJsonCallback<HttpResModel<List<ProvinceModel>>>(mFragmentConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<ProvinceModel>>> respons, HttpResModel<List<ProvinceModel>> listHttpResModel) {
                        adapter.setNewData(listHttpResModel.getResult());
                    }
                });
    }

}
