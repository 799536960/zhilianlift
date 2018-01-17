package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
import com.duma.ld.zhilianlift.view.dialog.HouseListDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.getALL;

/**
 * 房屋信息
 * Created by liudong on 2018/1/9.
 */

public class HouseInfoFragment extends BaseMyFragment implements OnBaseAdapterListener<HouseHttpInfoModel.FilterAttrBean.ItemBean>, RadioGroup.OnCheckedChangeListener {
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
    @BindView(R.id.group_leiXin)
    RadioGroup groupLeiXin;
    @BindView(R.id.group_xinBie)
    RadioGroup groupXinBie;
    @BindView(R.id.group_laiYuan)
    RadioGroup groupLaiYuan;
    @BindView(R.id.tv_wuYeleiXin)
    TextView tvWuYeleiXin;
    @BindView(R.id.layout_wuYeleiXin)
    LinearLayout layoutWuYeleiXin;
    @BindView(R.id.tv_diQu)
    TextView tvDiQu;
    @BindView(R.id.layout_diQu)
    LinearLayout layoutDiQu;
    @BindView(R.id.radio_zhenZu)
    RadioButton radioZhenZu;
    @BindView(R.id.radio_heZu)
    RadioButton radioHeZu;
    @BindView(R.id.tv_fuKuanFangShi)
    TextView tvFuKuanFangShi;
    @BindView(R.id.radio_buxian)
    RadioButton radioBuxian;
    @BindView(R.id.radio_nan)
    RadioButton radioNan;
    @BindView(R.id.radio_nv)
    RadioButton radioNv;
    @BindView(R.id.radio_yeZhu)
    RadioButton radioYeZhu;
    @BindView(R.id.radio_jiJiRen)
    RadioButton radioJiJiRen;
    private HouseHttpModel model;
    //房屋特色
    private BaseAdapter<HouseHttpInfoModel.FilterAttrBean.ItemBean> mAdapterTeSe;
    //房屋设施
    private BaseAdapter<HouseHttpInfoModel.FilterAttrBean.ItemBean> mAdapterSheShi;
    private HouseListDialog leiXinListDialog, fuKuanFangShiDialog;

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
        //初始化adapter
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
        //单选监听选择
        groupLaiYuan.setOnCheckedChangeListener(this);
        groupLeiXin.setOnCheckedChangeListener(this);
        groupXinBie.setOnCheckedChangeListener(this);
        groupLeiXin.check(R.id.radio_zhenZu);
        groupXinBie.check(R.id.radio_buxian);
        groupLaiYuan.check(R.id.radio_yeZhu);
        //初始化付款方式
        fuKuanFangShiDialog = new HouseListDialog(mActivity, getInitFuKuanFangShiList(), new HouseListDialog.OnStringClickListener() {
            @Override
            public void onItemClick(HouseHttpInfoModel.FilterAttrBean.ItemBean itemBean, int position) {
                model.setFuKuanFangShiModel(itemBean);
                tvFuKuanFangShi.setText(itemBean.getSo_name());
            }
        });
        onClickLoadingRefresh();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_zhenZu:
                //整租
                layoutXinBie.setVisibility(View.GONE);
                layoutSheShi.setVisibility(View.GONE);
                break;
            case R.id.radio_heZu:
                //合租
                layoutXinBie.setVisibility(View.VISIBLE);
                layoutSheShi.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_buxian:
                //性别 不限
                break;
            case R.id.radio_nan:
                //性别 男
                break;
            case R.id.radio_nv:
                //性别 女
                break;
            case R.id.radio_yeZhu:
                //来源 业主房源
                break;
            case R.id.radio_jiJiRen:
                //来源 经纪人
                break;
        }
    }


    @OnClick({R.id.layout_wuYeleiXin, R.id.layout_diQu, R.id.layout_fuKuanFangShi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_wuYeleiXin:
                leiXinListDialog.show();
                leiXinListDialog.setName("物业类型");
                break;
            case R.id.layout_diQu:
                break;
            case R.id.layout_fuKuanFangShi:
                fuKuanFangShiDialog.show();
                fuKuanFangShiDialog.setName("付款方式");
                break;
        }
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


    //初始化http请求数据
    private void initData(HouseHttpInfoModel result) {
        if (result.getFilter_attr() != null) {
            for (int i = 0; i < result.getFilter_attr().size(); i++) {
                if (result.getFilter_attr().get(i).getName().equals("房屋特色标签")) {
                    model.setmListTeSe(result.getFilter_attr().get(i).getItem());
                    mAdapterTeSe.setNewData(model.getmListTeSe());
                }
                if (result.getFilter_attr().get(i).getName().equals("物业类型")) {
                    //物业类型
                    leiXinListDialog = new HouseListDialog(mActivity, result.getFilter_attr().get(i).getItem(), new HouseListDialog.OnStringClickListener() {
                        @Override
                        public void onItemClick(HouseHttpInfoModel.FilterAttrBean.ItemBean itemBean, int position) {
                            model.setWuYeLeiXinModel(itemBean);
                            tvWuYeleiXin.setText(itemBean.getSo_name());
                        }
                    });
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

    //初始化房屋设施数据
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

    //初始化付款方式数据
    private List<HouseHttpInfoModel.FilterAttrBean.ItemBean> getInitFuKuanFangShiList() {
        List<HouseHttpInfoModel.FilterAttrBean.ItemBean> list = new ArrayList<>();
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("押一付一"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("押一付二"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("押一付三"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("押一付四"));
        list.add(new HouseHttpInfoModel.FilterAttrBean.ItemBean("无押金"));
        return list;
    }
}
