package com.duma.ld.zhilianlift.view.main.wode;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.zhilianlift.Adapter.GoodsTypeAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseSelectDelectActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.MyRecordModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.Iterator;
import java.util.List;

import static com.duma.ld.zhilianlift.util.HttpUrl.collectgoodNo;
import static com.duma.ld.zhilianlift.util.HttpUrl.collectgoodall;

/**
 * 收藏
 * Created by liudong on 2018/1/2.
 */

public class MyCollectActivity extends BaseSelectDelectActivity {
    private GoodsTypeAdapter mAdapter;

    @Override
    protected BaseAdapter initAdapter() {
        mAdapter = new GoodsTypeAdapter(rvList, mActivity).buildLoad(mActivityConfig);
        return mAdapter.getmAdapter();
    }

    @Override
    protected String getTitleName() {
        return "我的收藏";
    }

    @Override
    protected void onDeleteOnclick() {
        String visit_ids = "";
        List<MyRecordModel.VisitBean> data = mAdapter.getmAdapter().getData();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                if (visit_ids.isEmpty()) {
                    visit_ids = data.get(i).getCollect_id() + "";
                } else {
                    visit_ids = visit_ids + "," + data.get(i).getCollect_id();
                }
            }
        }
        if (visit_ids.isEmpty()) {
            TsUtils.show("请选择~");
            return;
        }
        OkGo.<HttpResModel<String>>get(collectgoodNo)
                .params("goods_collect_ids", visit_ids)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        Iterator<MyRecordModel.VisitBean> iterator = mAdapter.getmAdapter().getData().iterator();
                        while (iterator.hasNext()) {
                            MyRecordModel.VisitBean next = iterator.next();
                            if (next.isSelect()) {
                                iterator.remove();
                            }
                        }
                        mAdapter.getmAdapter().notifyDataSetChanged();
                        if (mAdapter.getmAdapter().getData().size() == 0) {
                            setEdit(false);
                        }
                        TsUtils.show("删除成功!");
                    }
                }.isDialog(mActivity));
    }


    @Override
    protected void setAllSelect(boolean select) {
        List<MyRecordModel.VisitBean> data = mAdapter.getmAdapter().getData();
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setSelect(select);
        }
    }


    @Override
    protected void removeAll() {
        OkGo.<HttpResModel<String>>get(collectgoodall)
                .tag(httpTag)
                .execute(new MyJsonCallback<HttpResModel<String>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> stringHttpResModel) {
                        mAdapter.getmAdapter().setNewData(null);
                    }
                }.isDialog(mActivity));
    }

    @Override
    protected void setEdit(boolean intEdit) {
        mAdapter.setEdit(intEdit);
        super.setEdit(intEdit);
    }
}
