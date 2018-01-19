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
import com.duma.ld.zhilianlift.model.HousePopModel;
import com.duma.ld.zhilianlift.widget.CheckBoxGoodsList;

import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * 房产价格和户型的pop
 * Created by liudong on 2017/12/21.
 */

public class HouseTwoListPopupWindow extends BasePopupWindow {
    private Activity mActivity;
    private BaseAdapter<HousePopModel.FilterAttrBean> mAdapterTitle;
    private BaseAdapter<HousePopModel.FilterAttrBean.ItemBean> mAdapterContent;
    private int mPositionTitle;
    private int mPositionContent;
    private HousePopModel housePopModel;
    //cb
    private CheckBoxGoodsList cbText;
    private OnPopListener onPopListener;
    private String defaultText;


    public int getmPositionContent() {
        return mPositionContent;
    }

    public BaseAdapter<HousePopModel.FilterAttrBean> getmAdapterTitle() {
        return mAdapterTitle;
    }

    public BaseAdapter<HousePopModel.FilterAttrBean.ItemBean> getmAdapterContent() {
        return mAdapterContent;
    }

    public HouseTwoListPopupWindow(Activity context) {
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
        mAdapterTitle = new BaseAdapter.Builder<HousePopModel.FilterAttrBean>(rv_list_title, mActivity, R.layout.adapter_pop_list_title)
                .setNoEnpty()
                .build(new OnBaseAdapterListener<HousePopModel.FilterAttrBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, HousePopModel.FilterAttrBean item) {
                        TextView tv_name = helper.getView(R.id.tv_name);
                        tv_name.setText(item.getName());
                        if (mPositionTitle == helper.getLayoutPosition()) {
                            tv_name.setTextColor(ZhuanHuanUtil.getColor(R.color.huang2));
                            tv_name.setBackgroundColor(ZhuanHuanUtil.getColor(R.color.white));
                        } else {
                            tv_name.setTextColor(ZhuanHuanUtil.getColor(R.color.hei1));
                            tv_name.setBackgroundColor(ZhuanHuanUtil.getColor(R.color.hei5));
                        }
                    }
                });
        mAdapterContent = new BaseAdapter.Builder<HousePopModel.FilterAttrBean.ItemBean>(rv_list_content, mActivity, R.layout.adapter_pop_list)
                .setNoEnpty()
                .build(new OnBaseAdapterListener<HousePopModel.FilterAttrBean.ItemBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, HousePopModel.FilterAttrBean.ItemBean item) {
                        TextView tv_name = helper.getView(R.id.tv_name);
                        tv_name.setText(item.getSo_name());
                        if (mPositionContent == helper.getLayoutPosition()) {
                            tv_name.setTextColor(ZhuanHuanUtil.getColor(R.color.huang2));
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
                setContentData(housePopModel.getFilter_attr().get(position).getItem());
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

    public HousePopModel.FilterAttrBean.ItemBean getSelectModel() {
        if (isModel()) return null;
        return housePopModel.getFilter_attr().get(mPositionTitle).getItem().get(mPositionContent);
    }

    public boolean isModel() {
        if (housePopModel == null) {
            return true;
        }
        return false;
    }


    public void setHousePopModel(HousePopModel housePopModel) {
        this.housePopModel = housePopModel;
        mAdapterTitle.setNewData(housePopModel.getFilter_attr());
        setContentData(housePopModel.getFilter_attr().get(0).getItem());
    }

    private void setContentData(List<HousePopModel.FilterAttrBean.ItemBean> item) {
        mAdapterContent.setNewData(item);
    }

    public void setCbText(final CheckBoxGoodsList cbText, final String defaultText, final OnPopListener onPopListener) {
        this.cbText = cbText;
        this.onPopListener = onPopListener;
        this.defaultText = defaultText;
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                cbText.setChecked(false);
                String name;
                if (getmPositionContent() != 0) {
                    name = getmAdapterContent().getData().get(getmPositionContent()).getSo_name();
                } else {
                    name = defaultText;
                }
                if (!name.equals(cbText.getText())) {
                    cbText.setText(name);
                    onPopListener.refresh();
                }
            }
        });
    }

}
