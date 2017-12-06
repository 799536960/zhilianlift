package com.duma.ld.zhilianlift.view.home.city;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.CityEntity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

import static com.duma.ld.zhilianlift.util.HttpUrl.getcity;
import static com.lzy.okgo.cache.CacheMode.FIRST_CACHE_THEN_REQUEST;

/**
 * 选择城市页面
 * Created by liudong on 2017/12/4.
 */

public class SelectCityActivity extends BaseMyActivity {
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.edit_search)
    EditText editSearch;
    private SupportFragment[] mFragments = new SupportFragment[2];

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_select_city);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        if (findFragment(SelectCityFragment.class) == null) {
            mFragments[0] = new SelectCityFragment();
            mFragments[1] = new SearchFragment();
            loadMultipleRootFragment(R.id.layout_fragment, 0,
                    mFragments[0],
                    mFragments[1]);
        } else {
            mFragments[0] = findFragment(SelectCityFragment.class);
            mFragments[1] = findFragment(SearchFragment.class);
        }
        initSearch();
        //请求城市
        onClickLoadingRefresh();
    }

    private void initSearch() {
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editSearch.length() > 0) {
                    showHideFragment(mFragments[1]);
                } else {
                    showHideFragment(mFragments[0]);
                }
                findFragment(SearchFragment.class).bindQueryText(editSearch.getText().toString());
            }
        });
    }


    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<List<CityEntity>>>get(getcity)
                .tag(httpTag)
                .cacheMode(FIRST_CACHE_THEN_REQUEST)
                .execute(new MyJsonCallback<HttpResModel<List<CityEntity>>>(mActivityConfig, false) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<CityEntity>>> respons, HttpResModel<List<CityEntity>> listHttpResModel) {
                        findFragment(SelectCityFragment.class).setData(listHttpResModel, mActivityConfig);
                    }
                });
    }


    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

}
