package com.duma.ld.zhilianlift.view.home.city;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.tinypinyin.lexicons.android.cncity.CnCityDict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.indexablerv.EntityWrapper;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

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
    private CityHeaderLocationAdapter adapter;
    private List<String> locationList;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_select_city);
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
        mDatas = initDatas();
        mAdapter.setDatas(mDatas, new IndexableAdapter.IndexCallback<CityEntity>() {
            @Override
            public void onFinished(List<EntityWrapper<CityEntity>> datas) {
                // 数据处理完成后回调
            }
        });
        layoutIndex.setOverlayStyle_MaterialDesign(ZhuanHuanUtil.getColor(R.color.primary_hong));
        updateLoaction("定位中");
        List<String> list = new ArrayList<>();
        list.add("");
        layoutIndex.addHeaderAdapter(new CityHeaderTopAdapter(mActivity, list));
    }

    private List<CityEntity> initDatas() {
        List<CityEntity> list = new ArrayList<>();
        List<String> cityStrings = Arrays.asList(getResources().getStringArray(R.array.city_array));
        for (String item : cityStrings) {
            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(item);
            list.add(cityEntity);
        }
        return list;
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        updateLoaction("杭州");
    }

    private void updateLoaction(String city) {
        List<String> list = new ArrayList<>();
        list.add(city);
        if (adapter != null) {
            layoutIndex.removeHeaderAdapter(adapter);
        }
        adapter = new CityHeaderLocationAdapter(mActivity, list);
        layoutIndex.addHeaderAdapter(adapter);
    }
}
