package com.duma.ld.zhilianlift.base.baseAdapter;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

/**
 * 能自动添加控制的空白页
 * 能够开启关闭下拉刷新 并且处理好了分页
 * Created by liudong on 2017/12/4.
 */

public class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> implements BaseQuickAdapter.RequestLoadMoreListener {
    private RecyclerView mRecyclerView;
    private Activity mActivity;
    private int page;
    private int mOldItemSize;
    private boolean isPage;
    private OnBaseLoadAdapterListener<T> onBaseLoadAdapterListener;
    private OnBaseAdapterListener<T> onBaseAdapterListener;

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        if (isPage) {
            onBaseLoadAdapterListener.convert(helper, item);
        } else {
            onBaseAdapterListener.convert(helper, item);
        }
    }

    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }

    //普通的构造
    public BaseAdapter(Builder builder, OnBaseAdapterListener<T> onBaseAdapterListener) {
        super(builder.layoutResId);
        this.onBaseAdapterListener = onBaseAdapterListener;
        isPage = false;
        init(builder);
    }

    //分页的构造
    public BaseAdapter(Builder builder, OnBaseLoadAdapterListener<T> onBaseAdapterListener) {
        super(builder.layoutResId);
        this.onBaseLoadAdapterListener = onBaseAdapterListener;
        isPage = true;
        //打开分页的监听
        setOnLoadMoreListener(this, mRecyclerView);
        init(builder);
    }

    private void init(Builder builder) {
        this.mRecyclerView = builder.mRecyclerView;
        this.mActivity = builder.mActivity;
        page = 1;
        mOldItemSize = 0;
        mRecyclerView.setLayoutManager(builder.layoutManager);
        mRecyclerView.setAdapter(this);
        if (builder.isEmptyView) {
            setEmptyLayout(builder.title, builder.drawableId);
        }
    }


    public void setEmptyLayout() {
        setEmptyLayout("哦~该列表空空的~", R.drawable.img_81);
    }

    public void setEmptyLayout(String title) {
        setEmptyLayout(title, R.drawable.img_81);
    }

    public void setEmptyLayout(@DrawableRes int drawableId) {
        setEmptyLayout("哦~该列表空空的~", drawableId);
    }

    public void setEmptyLayout(String title, @DrawableRes int drawableId) {
        View view = getView(R.layout.include_no_data);
        TextView textView = view.findViewById(R.id.tv_adapter_name);
        ImageView imageView = view.findViewById(R.id.img_adapter_nodata);
        textView.setText(title);
        imageView.setImageDrawable(ZhuanHuanUtil.getDrawable(drawableId));
        setEmptyView(view);
    }

    //获得view
    public View getView(@LayoutRes int layout) {
        return mActivity.getLayoutInflater().inflate(layout, (ViewGroup) mRecyclerView.getParent(), false);
    }

    //加载更多
    @Override
    public void onLoadMoreRequested() {
        if (getData().size() > mOldItemSize) {
            onRefresh(page);
        } else {
            /**
             * 这一页和上一页的数据一样 说明已经加载完毕
             */
            loadMoreEnd();
        }
    }


    //初始加载 下拉刷新
    public void onRefresh() {
        setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        page = 1;
        onRefresh(1);
    }

    private void onRefresh(int page) {
        OkGo.getInstance().cancelTag(-1);
        onBaseLoadAdapterListener.onLoadHttp(page, -1);
    }

    /**
     * 分页加载的起点
     * 请求的数据到这里后再决定 是否加载下一页 是否显示空页面 是否加载结束
     *
     * @param data 这次的数据
     */
    public void setLoadData(List data) {
        setEnableLoadMore(true);
        if (data == null) {
            data = new ArrayList();
        }
//        if (data.size() == 0 && page == 1) {
//            //第一页的时候是空数据
//
//        } else
        if (data.size() == 0 && page != 1) {
            //不是第一页 是空数据 说明加载到最后一页了
            loadMoreEnd();
        } else {
            //正常加载 页数才加1
            page++;
            if (page == 2) {
                //说明是第一次加载数据
                mOldItemSize = 0;
                setNewData(data);
            } else {
                //上一页的item的个数
                mOldItemSize = getData().size();
                //添加最新数据
                addData(data);
                //这一页还有数据 说明还没加载完  这个貌似要放在最后 这个会预加载
                loadMoreComplete();
            }
        }
    }

    public int getPage() {
        return page;
    }

    //建造器
    public static class Builder<T> {
        private RecyclerView mRecyclerView;
        private Activity mActivity;
        private int layoutResId;
        private String title;//空页面的title
        @DrawableRes
        private int drawableId;//空页面的图片
        private boolean isEmptyView;//是否需要空页面
        private RecyclerView.LayoutManager layoutManager;

        public Builder(RecyclerView mRecyclerView, Activity mActivity, int layoutResId) {
            this.mRecyclerView = mRecyclerView;
            this.mActivity = mActivity;
            this.layoutResId = layoutResId;
            //默认
            isEmptyView = true;
            title = "哦~该列表空空的~";
            drawableId = R.drawable.img_81;
            layoutManager = new LinearLayoutManager(mActivity);
        }

        public Builder<T> setLayoutManager(RecyclerView.LayoutManager layoutManager) {
            this.layoutManager = layoutManager;
            return this;
        }

        //设置空页面的title
        public Builder<T> setTitle(String title) {
            this.title = title;
            return this;
        }

        //设置空页面的图片
        public Builder<T> setrawableId(@DrawableRes int drawableId) {
            this.drawableId = drawableId;
            return this;
        }

        //设置空页面图片和title
        public Builder<T> setTitleOrDrawableId(String title, @DrawableRes int drawableId) {
            this.title = title;
            this.drawableId = drawableId;
            return this;
        }

        //不需要空页面
        public Builder<T> setNoEnpty() {
            isEmptyView = false;
            return this;
        }

        //不带分页
        public BaseAdapter<T> build(OnBaseAdapterListener<T> onBaseAdapterListener) {
            return new BaseAdapter<T>(this, onBaseAdapterListener);
        }

        //带分页
        public BaseAdapter<T> buildLoad(OnBaseLoadAdapterListener<T> onBaseLoadAdapterListener) {
            return new BaseAdapter<T>(this, onBaseLoadAdapterListener);
        }
    }
}
