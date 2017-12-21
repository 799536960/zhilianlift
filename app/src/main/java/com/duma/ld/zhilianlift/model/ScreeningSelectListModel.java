package com.duma.ld.zhilianlift.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudong on 2017/12/21.
 */

public class ScreeningSelectListModel {
    private String key;
    private List<GoodsListModel.FilterAttrBean.ItemBean> list;

    @Override
    public String toString() {
        return "ScreeningSelectListModel{" +
                "key='" + key + '\'' +
                ", list=" + list +
                '}';
    }

    public ScreeningSelectListModel(String key, List<GoodsListModel.FilterAttrBean.ItemBean> list) {
        this.key = key;
        this.list = list;
    }

    public static void addModel(GoodsListModel.FilterAttrBean.ItemBean bean, List<ScreeningSelectListModel> listModelList) {
        //啥都没有 创建一个新的
        if (listModelList.size() == 0) {
            List<GoodsListModel.FilterAttrBean.ItemBean> list = new ArrayList<>();
            list.add(bean);
            listModelList.add(new ScreeningSelectListModel(bean.getKey(), list));
            return;
        }

        //查询是否有key相同的
        for (int i = 0; i < listModelList.size(); i++) {
            if (listModelList.get(i).getKey().equals(bean.getKey())) {
                //相同 就添加
                listModelList.get(i).getList().add(bean);
                return;
            }
        }
        //没查询到 说明是新的
        List<GoodsListModel.FilterAttrBean.ItemBean> list = new ArrayList<>();
        list.add(bean);
        listModelList.add(new ScreeningSelectListModel(bean.getKey(), list));
    }

    public static void removeModel(GoodsListModel.FilterAttrBean.ItemBean bean, List<ScreeningSelectListModel> listModelList) {
        //查询key 查询到key后再查询value 查到后删除
        for (int i = 0; i < listModelList.size(); i++) {
            if (listModelList.get(i).getKey().equals(bean.getKey())) {
                //查value
                for (int i1 = 0; i1 < listModelList.get(i).getList().size(); i1++) {
                    if (listModelList.get(i).getList().get(i1).getValue().equals(bean.getValue())) {
                        //删除
                        if (listModelList.get(i).getList().size() == 1) {
                            //如果刚好就一个 必须把整个给删了 不然会留下一个空数组
                            listModelList.remove(i);
                            return;
                        } else {
                            listModelList.get(i).getList().remove(i1);
                            return;
                        }

                    }
                }
            }
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<GoodsListModel.FilterAttrBean.ItemBean> getList() {
        return list;
    }

    public void setList(List<GoodsListModel.FilterAttrBean.ItemBean> list) {
        this.list = list;
    }
}
