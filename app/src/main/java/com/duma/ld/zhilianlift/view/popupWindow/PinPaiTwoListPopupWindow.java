package com.duma.ld.zhilianlift.view.popupWindow;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseAdapter.OnBaseAdapterListener;
import com.duma.ld.zhilianlift.model.PinPaiClass12Model;
import com.duma.ld.zhilianlift.widget.CheckBoxGoodsList;

import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * 合作品牌的pop
 * Created by liudong on 2017/12/21.
 */

public class PinPaiTwoListPopupWindow extends BasePopupWindow {
    private Activity mActivity;
    private BaseAdapter<PinPaiClass12Model> mAdapterTitle;
    private BaseAdapter<PinPaiClass12Model.ListBean> mAdapterContent;
    private int mPositionTitle;
    private int mPositionContent;


    public int getmPositionContent() {
        return mPositionContent;
    }

    public BaseAdapter<PinPaiClass12Model> getmAdapterTitle() {
        return mAdapterTitle;
    }

    public BaseAdapter<PinPaiClass12Model.ListBean> getmAdapterContent() {
        return mAdapterContent;
    }

    public PinPaiTwoListPopupWindow(Activity context) {
        super(context);
        this.mActivity = context;
        initData();
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.pop_list_two);
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    protected void initData() {
        RecyclerView rv_list_title = getPopupWindowView().findViewById(R.id.rv_list_title);
        RecyclerView rv_list_content = getPopupWindowView().findViewById(R.id.rv_list_content);
        mAdapterTitle = new BaseAdapter.Builder<PinPaiClass12Model>(rv_list_title, mActivity, R.layout.adapter_pop_list_title)
                .setNoEnpty()
                .build(new OnBaseAdapterListener<PinPaiClass12Model>() {
                    @Override
                    public void convert(BaseViewHolder helper, PinPaiClass12Model item) {
                        TextView tv_name = helper.getView(R.id.tv_name);
                        tv_name.setText(item.getMobile_name());
                        if (mPositionTitle == helper.getLayoutPosition()) {
                            tv_name.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                            tv_name.setBackgroundColor(ZhuanHuanUtil.getColor(R.color.white));
                        } else {
                            tv_name.setTextColor(ZhuanHuanUtil.getColor(R.color.hei1));
                            tv_name.setBackgroundColor(ZhuanHuanUtil.getColor(R.color.hei5));
                        }
                    }
                });
        mAdapterContent = new BaseAdapter.Builder<PinPaiClass12Model.ListBean>(rv_list_content, mActivity, R.layout.adapter_pop_list)
                .setNoEnpty()
                .build(new OnBaseAdapterListener<PinPaiClass12Model.ListBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, PinPaiClass12Model.ListBean item) {
                        TextView tv_name = helper.getView(R.id.tv_name);
                        tv_name.setText(item.getMobile_name());
                        if (mPositionContent == helper.getLayoutPosition()) {
                            tv_name.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                        } else {
                            tv_name.setTextColor(ZhuanHuanUtil.getColor(R.color.hei1));
                        }
                    }
                });
        mAdapterTitle.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPositionContent = 0;
                mPositionTitle = position;
                setContentData(mAdapterTitle.getData().get(position).getList());
                mAdapterTitle.notifyDataSetChanged();
            }
        });
        mAdapterContent.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPositionContent = position;
                mAdapterContent.notifyDataSetChanged();
                dismiss();
            }
        });
    }

    public PinPaiClass12Model.ListBean getSelectModel() {
        if (isModel()) return null;
        return mAdapterTitle.getData().get(mPositionTitle).getList().get(mPositionContent);
    }

    public boolean isModel() {
        if (mAdapterTitle.getData().size() == 0) {
            return true;
        }
        return false;
    }


    public void setHousePopModel(List<PinPaiClass12Model> list) {
        mAdapterTitle.setNewData(list);
        setContentData(list.get(0).getList());
    }

    private void setContentData(List<PinPaiClass12Model.ListBean> item) {
        mAdapterContent.setNewData(item);
    }

    public void setCbText(final CheckBoxGoodsList cbText, final OnPopListener onPopListener) {
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                cbText.setChecked(false);
                String name = getmAdapterContent().getData().get(getmPositionContent()).getMobile_name();
                if (!name.equals(cbText.getText())) {
                    cbText.setText(name);
                    onPopListener.refresh();
                }
            }
        });
    }

}
