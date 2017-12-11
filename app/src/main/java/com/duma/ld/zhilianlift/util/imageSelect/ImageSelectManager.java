package com.duma.ld.zhilianlift.util.imageSelect;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by liudong on 2017/8/28.
 */

public class ImageSelectManager implements PaiZhaoDialog.ClickListenerInterface {
    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private BaseQuickAdapter<LocalMedia, BaseViewHolder> mAdapter;
    //rv的列
    private int row;
    //---------------------------------
    //是否显示dialog
    private boolean isDialog = true;
    //是否压缩
    private boolean isCompress = true;
    // 最大图片选择数量
    private int maxNum = 8;
    //---------------------------------
    //当前选中的图片
    private List<LocalMedia> mList;

    private PaiZhaoDialog mDialog;

    public static ImageSelectManager create(Activity mActivity) {
        return new ImageSelectManager(mActivity);
    }

    private ImageSelectManager(Activity mActivity) {
        this.mActivity = mActivity;
        mList = new ArrayList<>();
        mDialog = new PaiZhaoDialog(mActivity);
        mDialog.setClicklistener(this);
    }


    /**
     * rv样式
     *
     * @param mRecyclerView
     * @param row           几列 默认4列
     */
    public void starRvStyle(RecyclerView mRecyclerView, int row) {
        this.mRecyclerView = mRecyclerView;
        this.row = row;
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, row));
        mRecyclerView.setFocusable(false);
        mRecyclerView.setNestedScrollingEnabled(false);
        initAdapter();
        mRecyclerView.setAdapter(mAdapter);
        adapterRefresh();
    }

    private void adapterRefresh() {
        List<LocalMedia> mList_adapter = new ArrayList<>();
        mList_adapter.addAll(mList);
        if (mList.size() != 0 && mList.size() == maxNum) {
            //满了
        } else {
            LocalMedia e = new LocalMedia();
            e.setPath("0");
            mList_adapter.add(e);
        }
        mAdapter.setNewData(mList_adapter);
    }


    public ImageSelectManager starRvStyle(RecyclerView mRecyclerView) {
        starRvStyle(mRecyclerView, 4);
        return this;
    }

    private void initAdapter() {
        mAdapter = new BaseQuickAdapter<LocalMedia, BaseViewHolder>(R.layout.rv_photo, null) {
            @Override
            protected void convert(final BaseViewHolder holder, final LocalMedia localMedia) {
                final ImageView imageView = holder.getView(R.id.img_photo);
                FrameLayout layout = holder.getView(R.id.layout_finsh);

                if (localMedia.getPath().equals("0")) {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imageView.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.img_19));
                    layout.setVisibility(View.GONE);
                } else {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    ImageLoader.with(localMedia.getPath(), imageView);
                    layout.setVisibility(View.VISIBLE);
                }

                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mList.remove(holder.getLayoutPosition());
                        adapterRefresh();
                    }
                });

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (localMedia.getPath().equals("0")) {
                            dialog_show();
                        } else {
                            PictureSelector.create(mActivity).externalPicturePreview(holder.getLayoutPosition(), mList);
                        }
                    }
                });
            }
        };
    }

    /**
     * activity 回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    mList = PictureSelector.obtainMultipleResult(data);
                    Log.e("11", "onActivityResult: " + mList.toString());
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if (mAdapter != null) {
                        adapterRefresh();
                    }
                    break;
            }
        }
    }

    private void openGallery() {
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(maxNum)
                .compress(isCompress)
                .minimumCompressSize(100)
                .selectionMedia(mList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    private void openCamera() {
        PictureSelector.create(mActivity)
                .openCamera(PictureMimeType.ofImage())
                .maxSelectNum(maxNum)
                .compress(isCompress)
                .minimumCompressSize(100)
                .selectionMedia(mList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    public void dialog_show() {
        if (isDialog) {
            mDialog.show();
        } else {
            xiangce();
        }
    }

    @Override
    public void paiZhao() {
        openCamera();
    }

    @Override
    public void xiangce() {
        openGallery();
    }

    public ImageSelectManager setDialog(boolean dialog) {
        isDialog = dialog;
        return this;
    }

    public ImageSelectManager setCompress(boolean compress) {
        isCompress = compress;
        return this;
    }

    public ImageSelectManager setMaxNum(int maxNum) {
        this.maxNum = maxNum;
        return this;
    }


    public List<LocalMedia> getmList() {
        return mList;
    }
}
