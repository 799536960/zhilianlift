package com.duma.ld.zhilianlift.view.main.wode;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.Adapter.GoodsTypeAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseLoadAdapterListener;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseSelectDelectActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.MyRecordModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.duma.ld.zhilianlift.util.HttpUrl.clear_visit_log;
import static com.duma.ld.zhilianlift.util.HttpUrl.del_visit_log;
import static com.duma.ld.zhilianlift.util.HttpUrl.visit_log;

/**
 * 我的记录
 * Created by liudong on 2018/1/3.
 */

public class MyRecordActivity extends BaseSelectDelectActivity {
    private BaseAdapter<MyRecordModel> mAdapter;

    @Override
    protected BaseAdapter initAdapter() {
        mAdapter = new BaseAdapter.Builder<MyRecordModel>(rvList, mActivity, R.layout.adapter_my_record)
                .setTitleOrDrawableId("您还没有浏览记录哦~", R.drawable.zuji)
                .buildLoad(new OnBaseLoadAdapterListener<MyRecordModel>() {
                    @Override
                    public void onLoadHttp(int page, int size) {
                        OkGo.<HttpResModel<List<MyRecordModel>>>get(visit_log)
                                .tag(httpTag)
                                .params(Constants.Page, page)
                                .execute(new MyJsonCallback<HttpResModel<List<MyRecordModel>>>(mActivityConfig) {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<List<MyRecordModel>>> respons, HttpResModel<List<MyRecordModel>> myRecordModel) {
                                        mAdapter.setLoadData(myRecordModel.getResult());
                                    }
                                }.setLoadAdapter(mAdapter));
                    }

                    @Override
                    public void convert(BaseViewHolder helper, MyRecordModel item) {
                        String time = item.getDate();
                        if (time.equals(ZhuanHuanUtil.Time2nian(new Date().getTime()))) {
                            time = "今天";
                        }
                        helper.setText(R.id.tv_time, time);
                        RecyclerView rv_goods = helper.getView(R.id.rv_goods);
                        GoodsTypeAdapter adapter = new GoodsTypeAdapter(rv_goods, mActivity).build();
                        adapter.setEdit(isEdit);
                        adapter.getmAdapter().setNewData(item.getVisit());
                    }
                });
        return mAdapter;
    }


    @Override
    protected void setAllSelect(boolean select) {
        List<MyRecordModel> data = mAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            for (int i1 = 0; i1 < data.get(i).getVisit().size(); i1++) {
                data.get(i).getVisit().get(i1).setSelect(select);
            }
        }
    }

    @Override
    protected String getTitleName() {
        return "我的记录";
    }

    @Override
    protected void onDeleteOnclick() {
        String visit_ids = "";
        List<MyRecordModel> data = mAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            List<MyRecordModel.VisitBean> visit = data.get(i).getVisit();
            for (int j = 0; j < visit.size(); j++) {
                if (visit.get(j).isSelect()) {
                    if (visit_ids.isEmpty()) {
                        visit_ids = visit.get(j).getVisit_id() + "";
                    } else {
                        visit_ids = visit_ids + "," + visit.get(j).getVisit_id();
                    }
                }
            }
        }
        if (visit_ids.isEmpty()) {
            TsUtils.show("请选择~");
            return;
        }
        OkGo.<HttpResModel<String>>get(del_visit_log)
                .params("visit_ids", visit_ids)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        List<MyRecordModel> list = mAdapter.getData();
                        for (int i = 0; i < list.size(); i++) {
                            Iterator<MyRecordModel.VisitBean> iterator = list.get(i).getVisit().iterator();
                            while (iterator.hasNext()) {
                                MyRecordModel.VisitBean next = iterator.next();
                                if (next.isSelect()) {
                                    iterator.remove();
                                }
                            }
                        }
                        Iterator<MyRecordModel> iterator = mAdapter.getData().iterator();
                        while (iterator.hasNext()) {
                            MyRecordModel next = iterator.next();
                            if (next.getVisit().isEmpty()) {
                                iterator.remove();
                            }
                        }
                        if (mAdapter.getData().size() == 0) {
                            setEdit(false);
                        }
                        mAdapter.notifyDataSetChanged();
                        TsUtils.show("删除成功!");
                    }
                }.isDialog(mActivity));
    }

    @Override
    protected void removeAll() {
        OkGo.<HttpResModel<String>>get(clear_visit_log)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        mAdapter.setNewData(null);
                    }
                }.isDialog(mActivity));
    }

}
