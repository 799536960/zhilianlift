package com.duma.ld.zhilianlift.Adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseMultiItemAdapter;
import com.duma.ld.zhilianlift.model.ShoppingCartListModel;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.PublicUtil;
import com.duma.ld.zhilianlift.widget.CheckBoxNoOnClick;
import com.duma.ld.zhilianlift.widget.NumInputLayout;

import java.util.List;

import static com.duma.ld.zhilianlift.model.ShoppingCartListModel.head;

/**
 * Created by liudong on 2017/12/28.
 */

public class ShoppingCartAdapter extends BaseMultiItemAdapter<ShoppingCartListModel> {
    //是否编辑
    private boolean isEdit;
    private OnSelectClickListener onSelectClickListener;
    private AlertDialog alertDialog;
    private NumInputLayout numInputLayout;
    private int position;

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        if (isEdit()) {
            for (int i = 0; i < getData().size(); i++) {
                ShoppingCartListModel shoppingCartListModel = getData().get(i);
                shoppingCartListModel.setId(i);
                if (shoppingCartListModel.getItemType() == head) {
                    shoppingCartListModel.setAllDelectSelect(false);
                } else {
                    shoppingCartListModel.getShoppingCartStoreGoodsModel().setSelectDelete(false);
                }
            }
        }
        notifyDataSetChanged();
    }

    public interface OnSelectClickListener {
        //商品storeId为storeId的商品全部选中为isSelect状态
        void allSelect(int storeId, boolean isSelect);

        //商品下标为position的商品选中为isSelect状态
        void selectGoods(int position, boolean isSelect);

        void changeNum(int position, int num);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ShoppingCartAdapter(List<ShoppingCartListModel> data, OnSelectClickListener onSelectClickListener, Activity activity) {
        super(data);
        this.onSelectClickListener = onSelectClickListener;
        isEdit = false;
        addItemType(head, R.layout.adapter_shopping_cart_head);
        addItemType(ShoppingCartListModel.goods, R.layout.adapter_shopping_cart_goods);
        initDialog(activity);
    }

    private void initDialog(Activity activity) {
        //初始化dialog
        AlertDialog.Builder builder = PublicUtil.getAlertDialog(activity, "修改购买数量");
        //初始化自定义dialogview
        View dialog = activity.getLayoutInflater().inflate(R.layout.dialog_shop_num, (ViewGroup) activity.findViewById(R.id.layout_dialog));
        numInputLayout = dialog.findViewById(R.id.numInput);
        alertDialog = builder.setView(dialog)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onSelectClickListener.changeNum(position, numInputLayout.getNum());
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false)
                .create();
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingCartListModel item) {
        switch (helper.getItemViewType()) {
            case head:
                helper.setText(R.id.tv_store_name, item.getStoreName());
                final CheckBoxNoOnClick cb_Select_All = helper.getView(R.id.cb_Select_All);
                cb_Select_All.setChecked(item.isAllSelect());
                cb_Select_All.setOnNewClickListener(new CheckBoxNoOnClick.OnNewClickListener() {
                    @Override
                    public void onClick() {
                        if (isEdit) {
                            boolean checked = !cb_Select_All.isChecked();
                            item.setAllDelectSelect(checked);
                            cb_Select_All.setChecked(checked);
                            onSelectClickListener.allSelect(item.getStoreId(), checked);
                        } else {
                            onSelectClickListener.allSelect(item.getStoreId(), !item.isAllSelect());
                        }
                    }
                });
                if (isEdit) {
                    //编辑状态
                    cb_Select_All.setChecked(item.isAllDelectSelect());
                } else {
                    cb_Select_All.setChecked(item.isAllSelect());
                }
                break;
            case ShoppingCartListModel.goods:
                goodsView(helper, item);
                break;
        }
    }

    private void goodsView(final BaseViewHolder helper, final ShoppingCartListModel item) {
        final CheckBoxNoOnClick cb_Select = helper.getView(R.id.cb_Select);
        LinearLayout layout_goods_type = helper.getView(R.id.layout_goods_type);
        NumInputLayout numInput = helper.getView(R.id.numInput);
        TextView tv_goods_type = helper.getView(R.id.tv_goods_type);
        ImageLoader.with(item.getShoppingCartStoreGoodsModel().getOriginal_img(), (ImageView) helper.getView(R.id.img_icon), 6);
        helper.setText(R.id.tv_goods_name, item.getShoppingCartStoreGoodsModel().getGoods_name())
                .setText(R.id.tv_goods_type2, item.getShoppingCartStoreGoodsModel().getSpec_key_name())
                .setText(R.id.tv_goods_type, item.getShoppingCartStoreGoodsModel().getSpec_key_name())
                .addOnClickListener(R.id.tv_goods_type2)
                .addOnClickListener(R.id.tv_goods_name)
                .addOnClickListener(R.id.img_icon);
        SpannableStringBuilder spannableStringBuilder = new SpanUtils()
                .append("¥")
                .setFontSize(ConvertUtils.sp2px(13))
                .append(item.getShoppingCartStoreGoodsModel().getGoods_price())
                .setFontSize(ConvertUtils.sp2px(18))
                .create();
        helper.setText(R.id.tv_goods_money, spannableStringBuilder);
        numInput.setNum(item.getShoppingCartStoreGoodsModel().getGoods_num());
        numInput.setNoInput();
        numInput.getEdit_num().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = helper.getLayoutPosition();
                numInputLayout.setNum(item.getShoppingCartStoreGoodsModel().getGoods_num());
                alertDialog.show();
            }
        });
        numInput.setOnTextClickListener(new NumInputLayout.OnTextClickListener() {
            @Override
            public void onClick(int num) {
                onSelectClickListener.changeNum(helper.getLayoutPosition(), num);
            }
        });
        if (isEdit) {
            //编辑状态
            cb_Select.setChecked(item.getShoppingCartStoreGoodsModel().isSelectDelete());
            if (item.getShoppingCartStoreGoodsModel().getSpec_key_name() != null && !item.getShoppingCartStoreGoodsModel().getSpec_key_name().isEmpty()) {
                layout_goods_type.setVisibility(View.VISIBLE);
            } else {
                layout_goods_type.setVisibility(View.GONE);
            }
            tv_goods_type.setVisibility(View.GONE);
        } else {
            cb_Select.setChecked(item.getShoppingCartStoreGoodsModel().isSelected());
            layout_goods_type.setVisibility(View.GONE);
            tv_goods_type.setVisibility(View.VISIBLE);
        }
        cb_Select.setOnNewClickListener(new CheckBoxNoOnClick.OnNewClickListener() {
            @Override
            public void onClick() {
                if (isEdit) {
                    boolean checked = !cb_Select.isChecked();
                    item.getShoppingCartStoreGoodsModel().setSelectDelete(checked);
                    cb_Select.setChecked(checked);
                    onSelectClickListener.selectGoods(helper.getLayoutPosition(), checked);
                } else {
                    onSelectClickListener.selectGoods(helper.getLayoutPosition(), !item.getShoppingCartStoreGoodsModel().isSelected());
                }
            }
        });
    }

}
