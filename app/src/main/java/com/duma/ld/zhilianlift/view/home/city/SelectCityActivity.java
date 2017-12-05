package com.duma.ld.zhilianlift.view.home.city;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;

import com.baidu.location.BDLocation;
import com.duma.ld.baselibrary.model.EventModel;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.CityEntity;
import com.duma.ld.zhilianlift.model.CityHeaderModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.QuModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.LocationUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.tinypinyin.lexicons.android.cncity.CnCityDict;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.indexablerv.EntityWrapper;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

import static com.duma.ld.zhilianlift.util.Constants.event_location_city;
import static com.duma.ld.zhilianlift.util.HttpUrl.get_region;
import static com.duma.ld.zhilianlift.util.HttpUrl.getcity;

/**
 * 选择城市页面
 * Created by liudong on 2017/12/4.
 */

public class SelectCityActivity extends BaseMyActivity {
    @BindView(R.id.layout_index)
    IndexableLayout layoutIndex;
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    private CityAdapter mAdapter;
    private List<CityEntity> mDatas;
    private List<CityHeaderModel> rvList;
    private CityHeaderLocationAdapter locationAdapter;
    private CityHeaderRvAdapter rvAdapter;
    private String locationString = Constants.locationString;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_select_city);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onReceiveEvent(EventModel eventModel) {
        if (eventModel.getCode() == Constants.event_location_city) {
            final BDLocation bdLocation = (BDLocation) eventModel.getData();
            locationString = bdLocation.getCity();
            addHearder();
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        layoutIndex.setLayoutManager(new LinearLayoutManager(this));
        // 多音字处理
        Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance(this)));
        // 快速排序。  排序规则设置为：只按首字母  （默认全拼音排序）  效率很高，是默认的10倍左右。  按需开启～
        layoutIndex.setCompareMode(IndexableLayout.MODE_FAST);
        mAdapter = new CityAdapter(this);
        layoutIndex.setAdapter(mAdapter);
        layoutIndex.setOverlayStyle_MaterialDesign(ZhuanHuanUtil.getColor(R.color.primary_hong));
        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<CityEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, CityEntity entity) {
                if (originalPosition >= 0) {
                    setcity(entity.getName());
                }
            }
        });
        //请求城市
        onClickLoadingRefresh();
        //开启定位
        LocationUtil.getInstance().start(mActivity, event_location_city);
    }

    private void setcity(String name) {
        if (SpDataUtil.isCity(name)) {
            finish();
            return;
        }
        SpDataUtil.setCity(name);
        EventBusUtil.sendModel(Constants.event_select_city);
        finish();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<List<CityEntity>>>get(getcity)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<List<CityEntity>>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<CityEntity>>> respons, HttpResModel<List<CityEntity>> listHttpResModel) {
                        mDatas = listHttpResModel.getResult();
                        mAdapter.setDatas(mDatas, new IndexableAdapter.IndexCallback<CityEntity>() {
                            @Override
                            public void onFinished(List<EntityWrapper<CityEntity>> datas) {
                                // 数据处理完成后回调
                            }
                        });
                        addHearder();
                    }
                });
    }

    private void addHearder() {
        if (locationAdapter != null) {
            layoutIndex.removeHeaderAdapter(locationAdapter);
        }
        if (rvAdapter != null) {
            layoutIndex.removeHeaderAdapter(rvAdapter);
        }
        //加载定位
        List<String> locationList = new ArrayList<>();
        locationList.add(locationString);
        locationAdapter = new CityHeaderLocationAdapter(mActivity, locationList);
        layoutIndex.addHeaderAdapter(locationAdapter);
        //加载rv
        if (rvList == null) {
            rvList = new ArrayList<>();
            rvList.add(new CityHeaderModel(false, null));
        }
        rvAdapter = new CityHeaderRvAdapter(mActivity, rvList, new CityHeaderRvAdapter.onHttpRes() {
            @Override
            public void httpRes() {
                DialogUtil.getInstance().show_noBack(mActivity);
                OkGo.<HttpResModel<List<QuModel>>>get(get_region)
                        .params("name", SpDataUtil.getCity())
                        .tag(httpTag)
                        .execute(new MyJsonCallback<HttpResModel<List<QuModel>>>() {
                            @Override
                            protected void onJsonSuccess(Response<HttpResModel<List<QuModel>>> respons, HttpResModel<List<QuModel>> listHttpResModel) {
                                DialogUtil.getInstance().hide();
                                rvList = new ArrayList<>();
                                rvList.add(new CityHeaderModel(true, listHttpResModel.getResult()));
                                addHearder();
                            }

                        });
            }
        });
        layoutIndex.addHeaderAdapter(rvAdapter);
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

}
