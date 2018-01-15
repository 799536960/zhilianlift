package com.duma.ld.zhilianlift.view.main.wode;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.base.OnTopBarLeftListener;
import com.duma.ld.baselibrary.base.OnTopBarRightListener;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.MessageModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.clear_new;
import static com.duma.ld.zhilianlift.util.HttpUrl.del_new;
import static com.duma.ld.zhilianlift.util.HttpUrl.getNews;

/**
 * 消息中心
 * Created by liudong on 2018/1/15.
 */

public class MessageActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_root)
    ConstraintLayout layoutRoot;
    private BaseAdapter<MessageModel> mAdapter;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_message).setRefresh_A(R.id.sw_loading, R.id.layout_root, R.id.sw_loading).setTopBar_A("消息中心", new OnTopBarLeftListener() {
            @Override
            public void onClick() {
                onBack();
            }
        }, R.drawable.lajitong, new OnTopBarRightListener() {
            @Override
            public void onClick() {
                AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "清空消息", "您确定要清空消息中心嘛?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteAll();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(false);
                builder.show();
            }
        });
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mAdapter = new BaseAdapter.Builder<MessageModel>(rvList, mActivity, R.layout.adapter_message)
                .buildLoad(new OnBaseLoadAdapterListener<MessageModel>() {
                    @Override
                    public void onLoadHttp(int page, int httpTag) {
                        OkGo.<HttpResModel<List<MessageModel>>>get(getNews)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .execute(new MyJsonCallback<HttpResModel<List<MessageModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<MessageModel>>> respons, HttpResModel<List<MessageModel>> myRecordModel) {
                                        mAdapter.setLoadData(myRecordModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, MessageModel item) {
                        helper.setText(R.id.tv_type_name, item.getType_name())
                                .setText(R.id.tv_time, ZhuanHuanUtil.Time2fen(item.getAdd_time() * 1000))
                                .setText(R.id.tv_content, item.getContext());
                        ImageView img_type = helper.getView(R.id.img_type);
                        switch (item.getType()) {
                            case 1:
                                //通知
                                img_type.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.balance));
                                break;
                            case 2:
                                //系统
                                img_type.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.system));
                                break;
                            case 3:
                                //物流
                                img_type.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.trasportation));
                                break;
                        }
                    }
                });
        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                AlertDialog.Builder builder = PublicUtil.getAlertDialog(mActivity, "删除消息", "您确定要删除该消息嘛?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteHttp(position);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(false);
                builder.show();
                return true;
            }
        });
        onClickLoadingRefresh();
    }

    private void deleteHttp(final int position) {
        OkGo.<HttpResModel<List<MessageModel>>>get(del_new)
                .params("ids", mAdapter.getData().get(position).getId())
                .execute(new MyJsonCallback<HttpResModel<List<MessageModel>>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<MessageModel>>> respons, HttpResModel<List<MessageModel>> myRecordModel) {
                        TsUtils.show("删除成功!");
                        mAdapter.remove(position);
                    }
                }.isDialog(mActivity));
    }

    private void deleteAll() {
        OkGo.<HttpResModel<List<MessageModel>>>get(clear_new)
                .execute(new MyJsonCallback<HttpResModel<List<MessageModel>>>(mActivityConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<MessageModel>>> respons, HttpResModel<List<MessageModel>> myRecordModel) {
                        TsUtils.show("清空成功!");
                        mAdapter.setNewData(null);
                    }
                }.isDialog(mActivity));
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        mAdapter.onRefresh();
    }

}
