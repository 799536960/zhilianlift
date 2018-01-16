package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HouseHttpInfoModel;
import com.duma.ld.zhilianlift.model.HouseHttpModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.getALL;

/**
 * 房屋信息
 * Created by liudong on 2018/1/9.
 */

public class HouseInfoFragment extends BaseMyFragment implements OnBaseAdapterListener<HouseHttpInfoModel.FilterAttrBean.ItemBean> {
    @BindView(R.id.layout_teSe)
    LinearLayout layoutTeSe;
    @BindView(R.id.layout_leiXin)
    LinearLayout layoutLeiXin;
    @BindView(R.id.layout_zujin)
    LinearLayout layoutZujin;
    @BindView(R.id.layout_shouJia)
    LinearLayout layoutShouJia;
    @BindView(R.id.layout_fuKuanFangShi)
    LinearLayout layoutFuKuanFangShi;
    @BindView(R.id.layout_xinBie)
    LinearLayout layoutXinBie;
    @BindView(R.id.layout_sheShi)
    LinearLayout layoutSheShi;
    @BindView(R.id.rv_teSe)
    RecyclerView rvTeSe;
    @BindView(R.id.rv_sheShi)
    RecyclerView rvSheShi;
    private HouseHttpModel model;
    private BaseAdapter<HouseHttpInfoModel.FilterAttrBean.ItemBean> mAdapterTeSe;
    private BaseAdapter<HouseHttpInfoModel.FilterAttrBean.ItemBean> mAdapterSheShi;

    public static HouseInfoFragment newInstance(HouseHttpModel model) {
        HouseInfoFragment fragment = new HouseInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Model, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_house_info);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            model = (HouseHttpModel) args.getSerializable(Constants.Model);
        } else {
            TsUtils.show("获取失败!");
            mActivity.finish();
        }
        if (model == null) {
            TsUtils.show("获取失败!");
            mActivity.finish();
        }
        if (model.isRental()) {
            //出租
            layoutTeSe.setVisibility(View.GONE);
            layoutShouJia.setVisibility(View.GONE);
        } else {
            //出售
            layoutLeiXin.setVisibility(View.GONE);
            layoutZujin.setVisibility(View.GONE);
            layoutXinBie.setVisibility(View.GONE);
            layoutSheShi.setVisibility(View.GONE);
            layoutFuKuanFangShi.setVisibility(View.GONE);
        }
        mAdapterTeSe = new BaseAdapter.Builder<HouseHttpInfoModel.FilterAttrBean.ItemBean>(rvTeSe, mActivity, R.layout.adapter_house_info)
                .setLayoutManager(new GridLayoutManager(mActivity, 4))
                .isNested()
                .setNoEnpty()
                .build(this);
        mAdapterSheShi = new BaseAdapter.Builder<HouseHttpInfoModel.FilterAttrBean.ItemBean>(rvSheShi, mActivity, R.layout.adapter_house_info)
                .setLayoutManager(new GridLayoutManager(mActivity, 4))
                .isNested()
                .setNoEnpty()
                .build(this);
        initList();
        mAdapterSheShi.setNewData(model.getmListSheShi());
        onClickLoadingRefresh();
    }

    private void initList() {
        List<HouseHttpInfoModel.FilterAttrBean.ItemBean> list = new ArrayList<>();
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("宽带"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("热水器"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("洗衣机"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("冰箱"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("空调"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("衣柜"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("暖气"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("厨房"));
        model.setmListSheShi(list);
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        GetRequest<HttpResModel<HouseHttpInfoModel>> request = OkGo.<HttpResModel<HouseHttpInfoModel>>get(getALL)
                .tag(httpTag);
        if (model.isRental()) {
            //出租
            request
                    .params("cat_id", 854);
        } else {
            //出售
            request
                    .params("cat_id", 855);
        }
        request.execute(new MyJsonCallback<HttpResModel<HouseHttpInfoModel>>(mFragmentConfig) {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<HouseHttpInfoModel>> respons, HttpResModel<HouseHttpInfoModel> houseHttpInfoModelHttpResModel) {
                initData(houseHttpInfoModelHttpResModel.getResult());
            }
        });
    }

    private void initData(HouseHttpInfoModel result) {
        if (result.getFilter_attr() != null) {
            for (int i = 0; i < result.getFilter_attr().size(); i++) {
                if (result.getFilter_attr().get(i).getName().equals("房屋特色标签")) {
                    model.setmListTeSe(result.getFilter_attr().get(i).getItem());
                    mAdapterTeSe.setNewData(model.getmListTeSe());
                }
                if (result.getFilter_attr().get(i).getName().equals("物业类型")) {
                    model.setmListLeiXin(result.getFilter_attr().get(i).getItem());
                }
            }
        }
    }


    @Override
    public void convert(BaseViewHolder helper, final HouseHttpInfoModel.FilterAttrBean.ItemBean item) {
        CheckBox cb_name = helper.getView(R.id.cb_name);
        cb_name.setText(item.getSo_name());
        cb_name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setCheck(isChecked);
            }
        });
    }
}
