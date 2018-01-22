package com.duma.ld.zhilianlift.model;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * 添加房屋的表单信息
 * Created by liudong on 2018/1/16.
 */

public class HouseHttpModel implements Serializable {

    //是否出租房
    private boolean isRental;
    /**
     * 多选
     */
    //房屋特色 都有
    private List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListTeSe;
    //房屋设施  出租才会出现
    private List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListSheShi;
    /**
     * 多数据单选
     */
    //物业类型
    private HouseHttpInfoModel.FilterAttrBean.ItemBean wuYeLeiXinModel;
    //付款方式 出租才会出现
    private HouseHttpInfoModel.FilterAttrBean.ItemBean fuKuanFangShiModel;
    /**
     * 图片
     */
    private List<File> list;
    /**
     * 单选 默认有
     */
    //出租类型 0 整租 1 合租    出租才会出现
    private int chuZuleiXin;
    //性别要求 0 不限 1 男 2 女 出租才会出现
    private int xinBieYaoQiu;
    //来源     0 业主房源 1 经纪人
    private int laiYuan;
    /**
     * 配套补充中的
     */
    //小区概况(选填)
    private String xiaoQuGaiKuang;
    //交通状况(选填)
    private String jiaoTongZhuangKuang;
    //周边配套(选填)
    private String zhouBianPeiTao;
    /**
     * 房屋数据
     */
    //地区
    private PCDAddresModel addresModel;
    //房屋名称
    private String fangWuMinCheng;
    //房屋简介
    private String fangWuJianJie;
    //详细地址
    private String xiangXiDiZhi;
    //几室
    private String jiShi;
    //几厅
    private String jiTing;
    //几卫
    private String jiWei;
    //几楼
    private String jiLou;
    //共几层
    private String gongJiCeng;
    //建筑面积
    private String jianZhuMianJi;
    //租金  出租才会出现
    private String zuJing;
    //售价  出售才会出现
    private String shouJia;
    //房屋装修 (选填)
    private String fangWuZhuangXiu;
    //年代 (选填)
    private String nianDai;
    //房屋朝向 (选填)
    private String fangWuChaoXiang;
    //姓名
    private String xinMing;
    //联系电话
    private String lianXiDianHua;

    /**
     * 楼盘信息
     */
    //楼盘名称
    private String louPanMinCheng;
    //建筑类别
    private String jianZhuLieBie;
    //产权年限
    private String chanQuanNianXian;
    //停车位
    private String tingCheWei;
    //容积率
    private String rongJiLv;
    //绿化率
    private String lvHuaLv;
    //竣工时间
    private String junGongShiJian;
    //开发商
    private String kaiFaShang;


    public String getJianZhuLieBie() {
        return jianZhuLieBie;
    }

    public void setJianZhuLieBie(String jianZhuLieBie) {
        this.jianZhuLieBie = jianZhuLieBie;
    }

    public String getChanQuanNianXian() {
        return chanQuanNianXian;
    }

    public void setChanQuanNianXian(String chanQuanNianXian) {
        this.chanQuanNianXian = chanQuanNianXian;
    }

    public String getTingCheWei() {
        return tingCheWei;
    }

    public void setTingCheWei(String tingCheWei) {
        this.tingCheWei = tingCheWei;
    }

    public String getRongJiLv() {
        return rongJiLv;
    }

    public void setRongJiLv(String rongJiLv) {
        this.rongJiLv = rongJiLv;
    }

    public String getLvHuaLv() {
        return lvHuaLv;
    }

    public void setLvHuaLv(String lvHuaLv) {
        this.lvHuaLv = lvHuaLv;
    }

    public String getJunGongShiJian() {
        return junGongShiJian;
    }

    public void setJunGongShiJian(String junGongShiJian) {
        this.junGongShiJian = junGongShiJian;
    }

    public String getKaiFaShang() {
        return kaiFaShang;
    }

    public void setKaiFaShang(String kaiFaShang) {
        this.kaiFaShang = kaiFaShang;
    }

    public List<File> getList() {
        return list;
    }

    public void setList(List<File> list) {
        this.list = list;
    }

    public String getFangWuJianJie() {
        return fangWuJianJie;
    }

    public void setFangWuJianJie(String fangWuJianJie) {
        this.fangWuJianJie = fangWuJianJie;
    }

    public String getLouPanMinCheng() {
        return louPanMinCheng;
    }

    public void setLouPanMinCheng(String louPanMinCheng) {
        this.louPanMinCheng = louPanMinCheng;
    }

    public String getXiangXiDiZhi() {
        return xiangXiDiZhi;
    }

    public void setXiangXiDiZhi(String xiangXiDiZhi) {
        this.xiangXiDiZhi = xiangXiDiZhi;
    }

    public String getJiShi() {
        return jiShi;
    }

    public void setJiShi(String jiShi) {
        this.jiShi = jiShi;
    }

    public String getJiTing() {
        return jiTing;
    }

    public void setJiTing(String jiTing) {
        this.jiTing = jiTing;
    }

    public String getJiWei() {
        return jiWei;
    }

    public void setJiWei(String jiWei) {
        this.jiWei = jiWei;
    }

    public String getJiLou() {
        return jiLou;
    }

    public void setJiLou(String jiLou) {
        this.jiLou = jiLou;
    }

    public String getGongJiCeng() {
        return gongJiCeng;
    }

    public void setGongJiCeng(String gongJiCeng) {
        this.gongJiCeng = gongJiCeng;
    }

    public String getJianZhuMianJi() {
        return jianZhuMianJi;
    }

    public void setJianZhuMianJi(String jianZhuMianJi) {
        this.jianZhuMianJi = jianZhuMianJi;
    }

    public String getZuJing() {
        return zuJing;
    }

    public void setZuJing(String zuJing) {
        this.zuJing = zuJing;
    }

    public String getShouJia() {
        return shouJia;
    }

    public void setShouJia(String shouJia) {
        this.shouJia = shouJia;
    }

    public String getFangWuZhuangXiu() {
        return fangWuZhuangXiu;
    }

    public void setFangWuZhuangXiu(String fangWuZhuangXiu) {
        this.fangWuZhuangXiu = fangWuZhuangXiu;
    }

    public String getNianDai() {
        return nianDai;
    }

    public void setNianDai(String nianDai) {
        this.nianDai = nianDai;
    }

    public String getFangWuChaoXiang() {
        return fangWuChaoXiang;
    }

    public void setFangWuChaoXiang(String fangWuChaoXiang) {
        this.fangWuChaoXiang = fangWuChaoXiang;
    }

    public String getXinMing() {
        return xinMing;
    }

    public void setXinMing(String xinMing) {
        this.xinMing = xinMing;
    }

    public String getLianXiDianHua() {
        return lianXiDianHua;
    }

    public void setLianXiDianHua(String lianXiDianHua) {
        this.lianXiDianHua = lianXiDianHua;
    }

    public String getFangWuMinCheng() {
        return fangWuMinCheng;
    }

    public void setFangWuMinCheng(String fangWuMinCheng) {
        this.fangWuMinCheng = fangWuMinCheng;
    }

    public int getChuZuleiXin() {
        return chuZuleiXin;
    }

    public void setChuZuleiXin(int chuZuleiXin) {
        this.chuZuleiXin = chuZuleiXin;
    }

    public int getXinBieYaoQiu() {
        return xinBieYaoQiu;
    }

    public void setXinBieYaoQiu(int xinBieYaoQiu) {
        this.xinBieYaoQiu = xinBieYaoQiu;
    }

    public int getLaiYuan() {
        return laiYuan;
    }

    public void setLaiYuan(int laiYuan) {
        this.laiYuan = laiYuan;
    }

    public String getXiaoQuGaiKuang() {
        return xiaoQuGaiKuang;
    }

    public void setXiaoQuGaiKuang(String xiaoQuGaiKuang) {
        this.xiaoQuGaiKuang = xiaoQuGaiKuang;
    }

    public String getJiaoTongZhuangKuang() {
        return jiaoTongZhuangKuang;
    }

    public void setJiaoTongZhuangKuang(String jiaoTongZhuangKuang) {
        this.jiaoTongZhuangKuang = jiaoTongZhuangKuang;
    }

    public String getZhouBianPeiTao() {
        return zhouBianPeiTao;
    }

    public void setZhouBianPeiTao(String zhouBianPeiTao) {
        this.zhouBianPeiTao = zhouBianPeiTao;
    }

    public PCDAddresModel getAddresModel() {
        return addresModel;
    }

    public void setAddresModel(PCDAddresModel addresModel) {
        this.addresModel = addresModel;
    }

    public HouseHttpInfoModel.FilterAttrBean.ItemBean getWuYeLeiXinModel() {
        return wuYeLeiXinModel;
    }

    public void setWuYeLeiXinModel(HouseHttpInfoModel.FilterAttrBean.ItemBean wuYeLeiXinModel) {
        this.wuYeLeiXinModel = wuYeLeiXinModel;
    }

    public HouseHttpInfoModel.FilterAttrBean.ItemBean getFuKuanFangShiModel() {
        return fuKuanFangShiModel;
    }

    public void setFuKuanFangShiModel(HouseHttpInfoModel.FilterAttrBean.ItemBean fuKuanFangShiModel) {
        this.fuKuanFangShiModel = fuKuanFangShiModel;
    }

    public List<HouseHttpInfoModel.FilterAttrBean.ItemBean> getmListSheShi() {
        return mListSheShi;
    }

    public void setmListSheShi(List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListSheShi) {
        this.mListSheShi = mListSheShi;
    }

    public List<HouseHttpInfoModel.FilterAttrBean.ItemBean> getmListTeSe() {
        return mListTeSe;
    }

    public void setmListTeSe(List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListTeSe) {
        this.mListTeSe = mListTeSe;
    }

    public boolean isRental() {
        return isRental;
    }

    public void setRental(boolean rental) {
        isRental = rental;
    }


}
