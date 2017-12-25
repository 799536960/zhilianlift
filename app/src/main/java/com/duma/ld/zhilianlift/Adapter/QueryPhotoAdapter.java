package com.duma.ld.zhilianlift.Adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.luck.picture.lib.photoview.OnViewTapListener;
import com.luck.picture.lib.photoview.PhotoView;

import java.util.List;


public class QueryPhotoAdapter extends PagerAdapter {
    private List<String> mList;
    private Activity mActivity;

    public QueryPhotoAdapter(List<String> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {//必须实现
        return mList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {//必须实现
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.photo_view, container, false);
        PhotoView imageview = contentView.findViewById(R.id.imageView);
        ImageLoader.with(mList.get(position), imageview);
        imageview.setOnViewTapListener(new OnViewTapListener() {
            @Override
            public void onViewTap(View view, float v, float v1) {
                mActivity.finish();
            }
        });
        container.addView(contentView, 0);

        return contentView;
    }


}