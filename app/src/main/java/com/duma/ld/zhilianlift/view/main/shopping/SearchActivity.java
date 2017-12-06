package com.duma.ld.zhilianlift.view.main.shopping;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.SearchHotAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.CacheModel;
import com.duma.ld.zhilianlift.model.HotModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.CacheUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.ld.zhilianlift.util.HttpUrl.hot_keywords;
import static com.lzy.okgo.cache.CacheMode.FIRST_CACHE_THEN_REQUEST;

/**
 * Created by liudong on 2017/12/6.
 */

public class SearchActivity extends BaseMyActivity {
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.rv_hot)
    RecyclerView rvHot;
    @BindView(R.id.layout_hot)
    LinearLayout layoutHot;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.layout_empty)
    LinearLayout layoutEmpty;
    @BindView(R.id.layout_history)
    LinearLayout layoutHistory;
    @BindView(R.id.layout_cancel)
    FrameLayout layoutCancel;
    @BindView(R.id.tv_back)
    TextView tvBack;

    private SearchHotAdapter searchHotAdapter;
    private BaseAdapter<CacheModel> cacheAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_search, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initAdapter();
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                /*判断是否是“搜索”键*/
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String key = editSearch.getText().toString().trim();
                    if (TextUtils.isEmpty(key)) {
                        TsUtils.show("请输入关键字查询");
                        return true;
                    }
                    //  这里记得一定要将键盘隐藏了
                    KeyboardUtils.hideSoftInput(editSearch);
                    setStringToSearch(key);
                    return true;
                }
                return false;
            }
        });
        hotHttp();
    }

    private void initAdapter() {
        searchHotAdapter = new SearchHotAdapter(mActivity, rvHot);
        searchHotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                setStringToSearch(searchHotAdapter.getData().get(position));
            }
        });
        cacheAdapter = new BaseAdapter<CacheModel>(R.layout.adapter_history) {
            @Override
            protected void convert(BaseViewHolder helper, CacheModel item) {
                helper.setText(R.id.tv_title, item.getContent());
            }
        };
        cacheAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CacheModel> data = cacheAdapter.getData();
                setStringToSearch(data.get(position).getContent());
            }
        });
        rvHistory.setFocusable(false);
        rvHistory.setNestedScrollingEnabled(false);
        rvHistory.setLayoutManager(new LinearLayoutManager(mActivity));
        rvHistory.setAdapter(cacheAdapter);
        refresh();
    }

    /**
     * 点击关键词或者输入框搜索的方法
     *
     * @param s 搜索的内容
     */
    private void setStringToSearch(String s) {
        //添加到输入框
        editSearch.setText(s);
        editSearch.setSelection(s.length());
        //添加缓存
        CacheUtil.addCache(s);
        //刷新当前样式
        refresh();
        //去搜索
        TsUtils.show("去搜索:" + s);
    }

    /**
     * 刷新下样式
     */
    private void refresh() {
        //判断是否显示历史记录
        if (CacheUtil.isCache()) {
            layoutHistory.setVisibility(View.VISIBLE);
            cacheAdapter.setNewData(CacheUtil.queryAll());
        } else {
            layoutHistory.setVisibility(View.GONE);
        }
        //判断什么方式显示热搜
        searchHotAdapter.switchType();
    }

    private void hotHttp() {
        layoutHot.setVisibility(View.GONE);
        OkGo.<HttpResModel<HotModel>>get(hot_keywords)
                .tag(httpTag)
                .cacheMode(FIRST_CACHE_THEN_REQUEST)
                .execute(new MyJsonCallback<HttpResModel<HotModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<HotModel>> respons, HttpResModel<HotModel> hotModelHttpResModel) {
                        layoutHot.setVisibility(View.VISIBLE);
                        searchHotAdapter.setNewData(hotModelHttpResModel.getResult().getHot_keywords());
                    }
                });
    }


    @OnClick({R.id.layout_cancel, R.id.tv_back, R.id.layout_empty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_cancel:
                editSearch.setText("");
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.layout_empty:
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                        .setTitle("历史搜索")
                        .setMessage("确定清空历史搜索吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                CacheUtil.deleteAll();
                                refresh();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(false);
                builder.show();
                break;
        }
    }
}
