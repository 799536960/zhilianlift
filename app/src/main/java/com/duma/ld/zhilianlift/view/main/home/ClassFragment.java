package com.duma.ld.zhilianlift.view.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.ClassModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.duma.ld.zhilianlift.widget.LinearImageLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.classMian;
import static com.lzy.okgo.cache.CacheMode.FIRST_CACHE_THEN_REQUEST;

/**
 * 分类
 * Created by liudong on 2017/11/29.
 */

public class ClassFragment extends BaseMyFragment {
    @BindView(R.id.layout_scan)
    FrameLayout layoutScan;
    @BindView(R.id.layout_search)
    LinearLayout layoutSearch;
    @BindView(R.id.layout_message)
    LinearImageLayout layoutMessage;
    @BindView(R.id.rv_class)
    RecyclerView rvClass;
    @BindView(R.id.layout_framgment)
    FrameLayout layoutFramgment;

    private BaseAdapter<ClassModel> baseAdapter;
    private int click_position = 0;

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_class).setLoadingOrErrorView_f(R.id.layout_root, R.id.layout_class);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initAdapter();
        initFragment();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        layoutMessage.setNum(SpDataUtil.getMessageNum());
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        onClickLoadingRefresh();
    }

    private void initFragment() {
        loadRootFragment(R.id.layout_framgment, new Class_2_Fragment(), false, false);
    }

    private void initAdapter() {
        baseAdapter = new BaseAdapter<ClassModel>(R.layout.adapter_class) {
            @Override
            protected void convert(BaseViewHolder holder, ClassModel item) {
                final TextView tv_name = holder.getView(R.id.tv_name);
                tv_name.setText(item.getMobile_name());
                setChecked(tv_name, false);
                if (holder.getLayoutPosition() == click_position) {
                    setChecked(tv_name, true);
                }
            }
        };
        baseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == click_position) {
                    return;
                }
                int old = click_position;
                click_position = position;
                baseAdapter.notifyItemChanged(old);
                TextView tv_name_new = (TextView) adapter.getViewByPosition(rvClass, click_position, R.id.tv_name);
                setChecked(tv_name_new, true);
                switchClass();
            }

        });
        rvClass.setLayoutManager(new LinearLayoutManager(mActivity));
        rvClass.setAdapter(baseAdapter);
    }

    private void switchClass() {
        Class_2_Fragment fragment = findChildFragment(Class_2_Fragment.class);
        fragment.setData(baseAdapter.getItem(click_position));
    }

    private void setChecked(TextView tv_name, boolean checked) {
        if (checked) {
            tv_name.setTextColor(ContextCompat.getColor(mActivity, R.color.primary_hong));
            tv_name.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.hui_bg));
        } else {
            tv_name.setTextColor(ContextCompat.getColor(mActivity, R.color.textColor));
            tv_name.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.white));
        }
    }

    @Override
    public void onClickLoadingRefresh() {
        OkGo.<HttpResModel<List<ClassModel>>>get(classMian)
                .cacheMode(FIRST_CACHE_THEN_REQUEST)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<List<ClassModel>>>(mFragmentConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<ClassModel>>> respons, HttpResModel<List<ClassModel>> listHttpResModel) {
                        baseAdapter.setNewData(listHttpResModel.getResult());
                        switchClass();
                    }
                });
    }

    @OnClick({R.id.layout_scan, R.id.layout_search, R.id.layout_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_scan:
                // TODO: 2018/1/15 扫码
                break;
            case R.id.layout_search:
                IntentUtil.goSearch(mActivity);
                break;
            case R.id.layout_message:
                if (SpDataUtil.isLogin()) {
                    IntentUtil.goMessage(mActivity);
                } else {
                    IntentUtil.goLogin(mActivity);
                }
                break;
        }
    }
}
