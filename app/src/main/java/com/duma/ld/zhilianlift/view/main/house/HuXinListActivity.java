package com.duma.ld.zhilianlift.view.main.house;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HouseHuXinBean;
import com.duma.ld.zhilianlift.model.HouseHuXinListModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.getdoorinfo;

/**
 * 户型
 * Created by liudong on 2018/1/23.
 */

public class HuXinListActivity extends BaseMyActivity {
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private String houseId;
    private BaseAdapter<HouseHuXinListModel> mAdapterMenu;
    private BaseAdapter<HouseHuXinBean> mAdapterContent;
    private int positionMenu;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_huxin).setLoadingOrErrorView_A(R.id.layout_root, R.id.layout_content).setTopBar_A("户型详情");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        houseId = getIntent().getStringExtra(Constants.id);
        initAdapter();
        onClickLoadingRefresh();
    }

    private void initAdapter() {
        mAdapterMenu = new BaseAdapter.Builder<HouseHuXinListModel>(rvMenu, mActivity, R.layout.adapter_class)
                .build(new OnBaseAdapterListener<HouseHuXinListModel>() {
                    @Override
                    public void convert(BaseViewHolder helper, HouseHuXinListModel item) {
                        final TextView tv_name = helper.getView(R.id.tv_name);
                        tv_name.setText(item.getName());
                        if (helper.getLayoutPosition() == positionMenu) {
                            //选中
                            tv_name.setBackgroundColor(ZhuanHuanUtil.getColor(R.color.white));
                        } else {
                            tv_name.setBackgroundColor(ZhuanHuanUtil.getColor(R.color.hei5));
                        }
                    }
                });
        mAdapterMenu.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                positionMenu = position;
                mAdapterMenu.notifyDataSetChanged();
                setNewContent();
            }
        });
        mAdapterContent = new BaseAdapter.Builder<HouseHuXinBean>(rvList, mActivity, R.layout.adapter_house_huxin_content)
                .build(new OnBaseAdapterListener<HouseHuXinBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, HouseHuXinBean item) {
                        // TODO: 2018/1/24 缺少字段 和 数组
                        helper.setText(R.id.tv_biaoti, "没字段")
                                .setText(R.id.tv_jianZhuMianJi, item.getArchitecture_area() + "㎡")
                                .setText(R.id.tv_taoLeiMianJi, item.getDoor_area() + "㎡")
                                .setText(R.id.tv_defangLv, item.getDoor_get() + "%")
                                .setText(R.id.tv_zongJia, "¥" + item.getDoor_price() + "万/套起");
                        ImageLoader.with(item.getDoor_img(), (ImageView) helper.getView(R.id.img_icon));
                    }
                });
        mAdapterContent.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<HouseHuXinBean> data = mAdapterContent.getData();
                List<String> imgList = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    imgList.add(data.get(i).getDoor_img());
                }
                IntentUtil.goPhoto(mActivity, imgList, position);
            }
        });
    }

    private void setNewContent() {
        mAdapterContent.setNewData(mAdapterMenu.getData().get(positionMenu).getList());
    }

    private void initData(List<HouseHuXinListModel> listModels) {
        mAdapterMenu.setNewData(listModels);
        setNewContent();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<List<HouseHuXinListModel>>>get(getdoorinfo)
                .tag(httpTag)
                .params("house_id", houseId)
                .execute(new MyJsonCallback<HttpResModel<List<HouseHuXinListModel>>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<HouseHuXinListModel>>> respons, HttpResModel<List<HouseHuXinListModel>> houseHuXinListModelHttpResModel) {
                        initData(houseHuXinListModelHttpResModel.getResult());
                    }
                });
    }


}
