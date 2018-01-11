package com.duma.ld.zhilianlift.util.imageSelect;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
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
    private OnSelectFileListener onSelectFileListener;
    private int code;
    private boolean isSave;
    private OnClickAdapterListener onClickAdapterListener;

    public void setOnClickAdapterListener(OnClickAdapterListener onClickAdapterListener) {
        this.onClickAdapterListener = onClickAdapterListener;
    }

    public interface OnClickAdapterListener {
        void onClick();
    }

    public interface OnActivityListener {
        void onFileList(List<LocalMedia> mList);
    }

    public void setOnSelectFileListener(OnSelectFileListener onSelectFileListener) {
        this.onSelectFileListener = onSelectFileListener;
    }

    public static ImageSelectManager create(Activity mActivity) {
        return new ImageSelectManager(mActivity);
    }

    private ImageSelectManager(Activity mActivity) {
        this.mActivity = mActivity;
        mList = new ArrayList<>();
        mDialog = new PaiZhaoDialog(mActivity);
        mDialog.setClicklistener(this);
        isSave = true;
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
//                    imageView.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.imagephoto1));
                    Glide.with(mActivity)
                            .load(ZhuanHuanUtil.getDrawable(R.drawable.imagephoto1))
                            .into(imageView);
                    layout.setVisibility(View.GONE);
                } else {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(mActivity)
                            .load(localMedia.getPath())
                            .into(imageView);
//                    ImageLoader.with(mActivity,localMedia.getPath(), imageView);
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
                            if (onClickAdapterListener != null) {
                                onClickAdapterListener.onClick();
                            }
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
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if (mAdapter != null) {
                        adapterRefresh();
                    }
                    if (onSelectFileListener != null) {
                        File file = null;
                        if (getmList() != null && getmList().size() > 0) {
                            if (getmList().get(0).isCompressed() || (getmList().get(0).isCut() && getmList().get(0).isCompressed())) {
                                file = new File(getmList().get(0).getCompressPath());
                            } else {
                                file = new File(getmList().get(0).getPath());
                            }
                        }
                        onSelectFileListener.getFile(file, code);
                    }
                    break;
            }
        }
    }

    public void setListImg(List<LocalMedia> list) {
        if (mAdapter != null) {
            mList = list;
            adapterRefresh();
        }
    }

    public void onActivityResultLister(int requestCode, int resultCode, Intent data, OnActivityListener onActivityListener) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    onActivityListener.onFileList(PictureSelector.obtainMultipleResult(data));
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                    if (mAdapter != null) {
//                        adapterRefresh();
//                    }
                    break;
            }
        }
    }

    private void openGallery() {
        PictureSelectionModel pictureSelectionModel = PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(maxNum)
                .compress(isCompress)
                .minimumCompressSize(100);
        if (isSave) {
            pictureSelectionModel
                    .selectionMedia(mList);
        }
        pictureSelectionModel.forResult(PictureConfig.CHOOSE_REQUEST);
    }

    private void openCamera() {
        PictureSelectionModel pictureSelectionModel = PictureSelector.create(mActivity)
                .openCamera(PictureMimeType.ofImage())
                .maxSelectNum(maxNum)
                .compress(isCompress)
                .minimumCompressSize(100);
        if (isSave) {
            pictureSelectionModel
                    .selectionMedia(mList);
        }
        pictureSelectionModel.forResult(PictureConfig.CHOOSE_REQUEST);
    }

    public void dialog_show() {
        if (isDialog) {
            mDialog.show();
        } else {
            xiangce();
        }
    }

    public void dialog_show(int code) {
        this.code = code;
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

    public ImageSelectManager setIsSave(boolean isSave) {
        this.isSave = isSave;
        return this;
    }

    public List<LocalMedia> getmList() {
        return mList;
    }

    public List<File> getFileList() {
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < getmList().size(); i++) {
            File file;
            if (getmList().get(i).isCompressed() || (getmList().get(i).isCut() && getmList().get(i).isCompressed())) {
                file = new File(getmList().get(i).getCompressPath());
            } else {
                file = new File(getmList().get(i).getPath());
            }
            fileList.add(file);
        }
        return fileList;
    }

    public static List<File> getFileList(List<LocalMedia> mList) {
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            File file;
            if (mList.get(i).isCompressed() || (mList.get(i).isCut() && mList.get(i).isCompressed())) {
                file = new File(mList.get(i).getCompressPath());
            } else {
                file = new File(mList.get(i).getPath());
            }
            fileList.add(file);
        }
        return fileList;
    }
}
