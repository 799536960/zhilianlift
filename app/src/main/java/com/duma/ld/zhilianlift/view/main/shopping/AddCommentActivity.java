package com.duma.ld.zhilianlift.view.main.shopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.base.OnTopBarRightListener;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.OrderCommentModel;
import com.duma.ld.zhilianlift.model.OrderEventModel;
import com.duma.ld.zhilianlift.model.OrderModel;
import com.duma.ld.zhilianlift.model.UpDataCommentModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.DialogUtil;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.util.imageSelect.ImageSelectManager;
import com.duma.ld.zhilianlift.widget.SimpleRatingBar;
import com.google.gson.Gson;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.addComment;
import static com.duma.ld.zhilianlift.util.HttpUrl.comment_list;
import static com.duma.ld.zhilianlift.util.HttpUrl.upload_comment_img;

/**
 * 订单所有商品评价
 * Created by liudong on 2018/1/11.
 */

public class AddCommentActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private OrderEventModel eventModel;
    private String order_id;
    private BaseAdapter<OrderCommentModel.OrderInfoBean.NoCommentGoodsListBean> mAdapter;
    private List<ImageSelectManager> mList;
    private int position;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_add_comment).setTopBar_A("评价", new OnTopBarLeftListener() {
            @Override
            public void onClick() {
                onBack();
            }
        }, "提交", new OnTopBarRightListener() {
            @Override
            public void onClick() {
                sendHttp();
            }
        });
    }

    /**
     * 提交请求
     * 成功发送事件
     */
    private void sendHttp() {
        List<UpDataCommentModel> upDataList = new ArrayList<>();
        List<OrderCommentModel.OrderInfoBean.NoCommentGoodsListBean> data = mAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            OrderCommentModel.OrderInfoBean.NoCommentGoodsListBean bean = data.get(i);
            UpDataCommentModel model = new UpDataCommentModel();
            model.setGoods_id(bean.getGoods_id());
            model.setContent(bean.getComment());
            model.setImgList(bean.getmImageList());
            model.setRank(bean.getComment_star());
            model.setSpec_key_name(bean.getSpec_key_name());
            model.setImgList(bean.getmImageList());
            model.setHide_username(bean.getUser());
            model.setRec_id(bean.getRec_id());
            upDataList.add(model);
        }
        Logger.e(new Gson().toJson(upDataList));
        OkGo.<HttpResModel<OrderModel>>post(addComment)
                .tag(httpTag)
                .params("order_id", order_id)
                .params("comment_form_data", new Gson().toJson(upDataList))
                .execute(new MyJsonCallback<HttpResModel<OrderModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<OrderModel>> respons, HttpResModel<OrderModel> orderModelHttpResModel) {
                        TsUtils.show("评价成功!");
                        eventModel.setModel(orderModelHttpResModel.getResult());
                        EventBusUtil.sendModel(Constants.event_refresh_order_comment, eventModel);
                        finish();
                    }
                }.isDialog(mActivity));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mList = new ArrayList<>();
        order_id = getIntent().getStringExtra(Constants.key);
        eventModel = new OrderEventModel(getIntent().getIntExtra(Constants.position, 0), getIntent().getStringExtra(Constants.Type));
        initAdapter();
        onClickLoadingRefresh();
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<OrderCommentModel.OrderInfoBean.NoCommentGoodsListBean>(rvList, mActivity, R.layout.adapter_add_comment)
                .build(new OnBaseAdapterListener<OrderCommentModel.OrderInfoBean.NoCommentGoodsListBean>() {
                    @Override
                    public void convert(final BaseViewHolder helper, final OrderCommentModel.OrderInfoBean.NoCommentGoodsListBean item) {
                        ImageLoader.with(item.getOriginal_img(), (ImageView) helper.getView(R.id.img_icon));
                        final SimpleRatingBar rating_rank = helper.getView(R.id.rating_rank);
                        RecyclerView rv_photo = helper.getView(R.id.rv_photo);
                        EditText edit_comment_content = helper.getView(R.id.edit_comment_content);
                        CheckBox cb_comment = helper.getView(R.id.cb_comment);
                        //星星
                        rating_rank.setOnRatingBarChangeListener(new SimpleRatingBar.OnRatingBarChangeListener() {
                            @Override
                            public void onRatingChanged(SimpleRatingBar simpleRatingBar, float rating, boolean fromUser) {
                                item.setComment_star((int) rating);
                            }
                        });
                        //内容
                        edit_comment_content.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                item.setComment(s.toString());
                            }
                        });
                        //是否匿名
                        cb_comment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                item.setUser(isChecked);
                            }
                        });

                        //照片
                        ImageSelectManager imageSelectManager = ImageSelectManager.create(mActivity)
                                .setMaxNum(9)
                                .starRvStyle(rv_photo);
                        imageSelectManager.setOnClickAdapterListener(new ImageSelectManager.OnClickAdapterListener() {
                            @Override
                            public void onClick() {
                                position = helper.getLayoutPosition();
                            }
                        });
                        mList.add(imageSelectManager);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mList.get(position).onActivityResultLister(requestCode, resultCode, data, new ImageSelectManager.OnActivityListener() {
            @Override
            public void onFileList(List<LocalMedia> mList) {
                upDataPhoto(mList);
            }
        });
    }

    private void upDataPhoto(final List<LocalMedia> list) {
        DialogUtil.getInstance().show_noBack(mActivity, "图片上传中");
        OkGo.<HttpResModel<List<String>>>post(upload_comment_img)
                .tag(httpTag)
                .addFileParams("idcard_img[]", ImageSelectManager.getFileList(list))
                .execute(new MyJsonCallback<HttpResModel<List<String>>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<String>>> respons, HttpResModel<List<String>> stringHttpResModel) {
                        DialogUtil.getInstance().hide();
                        mAdapter.getData().get(position).setmImageList(stringHttpResModel.getResult());
                        mList.get(position).setListImg(list);
                    }
                });
    }


    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<OrderCommentModel>>get(comment_list)
                .tag(httpTag)
                .params("order_id", order_id)
                .execute(new MyJsonCallback<HttpResModel<OrderCommentModel>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<OrderCommentModel>> respons, HttpResModel<OrderCommentModel> orderCommentModelHttpResModel) {
                        List<OrderCommentModel.OrderInfoBean.NoCommentGoodsListBean> no_comment_goods_list = orderCommentModelHttpResModel.getResult().getOrder_info().getNo_comment_goods_list();
                        if (no_comment_goods_list == null || no_comment_goods_list.size() == 0) {
                            TsUtils.show("该订单已评价请刷新最新订单信息!");
                            finish();
                            return;
                        }
                        mAdapter.setNewData(no_comment_goods_list);
                    }
                });
    }

    @Override
    protected void onBack() {
        AlertDialog.Builder builder = PublicUtil.getAlertDialog_nessage(mActivity, "确定要退出评价页面嘛?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false);
        builder.show();
    }
}
