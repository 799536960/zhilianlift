package com.duma.ld.zhilianlift.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudong on 2018/1/19.
 */

public class HouseChuZuInfoModel implements Serializable {

    /**
     * house : {"house_id":55,"house_name":null,"house_address":"西湖","house_telephone":"99999999","start_time":"2018-01-19 00:00:00","purpose":3,"house_status":0,"renovation":"1","property":8,"house_price":"1000","architecture":"90","isset":"1898","volume":"76890","green":"222","door_door":null,"sales":"售楼许可","developers":"开发商","periphery":null,"house_type":1,"cat_id1":851,"cat_id2":852,"cat_id3":853,"on_time":"1516342043","is_show":null,"years":null,"orientation":null,"floor":null,"traffic":null,"village":null,"examine":0,"reason_no":null,"user_id":null,"synopsis":"就是一个简介","premises_name":"楼盘名称","province_id":11,"city_id":123,"district":1251,"source":null,"user_name":null,"lease_type":null,"payment":null,"house_img":null,"province_code":"330000","city_code":"330100","district_code":"330105","sales_address":"售楼地址","parkinglot":"停车位","architecture_type":0,"longitude":"120.2193754157201","latitude":"30.259244461","original_img":"/public/upload/goods/2018/01-19/f787345e42279dcca6cbdac5aa34aad2.jpg","toilet":null,"office":null,"floorall":null,"allprice":null,"sex":null,"facilities":null,"read_count":0,"dele":0,"rent":"0","sales_type":3}
     * house_doorList : [{"id":3,"house_id":55,"door_img":"/public/upload/goods/2018/01-19/ccab3a74bbc747b448c3876de5cebbb4.jpg","door_price":"4","door_get":"3","door_area":"2","architecture_area":"1","door_door":"1","door_sort":null},{"id":4,"house_id":55,"door_img":"/public/upload/goods/2018/01-19/3f35ef832c65312f505c67800e947901.jpg","door_price":"8","door_get":"7","door_area":"6","architecture_area":"5","door_door":"2","door_sort":null}]
     * houseImagesList : [{"id":81,"house_id":55,"image_url":"/public/upload/goods/2018/01-19/0dd9691bb1a170ef19730262c60e5825.jpg","img_sort":0},{"id":82,"house_id":55,"image_url":"/public/upload/goods/2018/01-19/c3a929d3089ffde46392793c822cc04e.jpg","img_sort":0}]
     * houseLabel : [{"id":145,"house_id":55,"hs_id":48,"so_name":"经济住宅"},{"id":146,"house_id":55,"hs_id":49,"so_name":"品牌开发商"}]
     * collect : {"result":1}
     */

    private HouseBean house;
    private CollectBean collect;
    private List<HouseDoorListBean> house_doorList;
    private List<HouseImagesListBean> houseImagesList;
    private List<HouseLabelBean> houseLabel;

    public HouseBean getHouse() {
        return house;
    }

    public void setHouse(HouseBean house) {
        this.house = house;
    }

    public CollectBean getCollect() {
        return collect;
    }

    public void setCollect(CollectBean collect) {
        this.collect = collect;
    }

    public List<HouseDoorListBean> getHouse_doorList() {
        return house_doorList;
    }

    public void setHouse_doorList(List<HouseDoorListBean> house_doorList) {
        this.house_doorList = house_doorList;
    }

    public List<HouseImagesListBean> getHouseImagesList() {
        return houseImagesList;
    }

    public void setHouseImagesList(List<HouseImagesListBean> houseImagesList) {
        this.houseImagesList = houseImagesList;
    }

    public List<HouseLabelBean> getHouseLabel() {
        return houseLabel;
    }

    public void setHouseLabel(List<HouseLabelBean> houseLabel) {
        this.houseLabel = houseLabel;
    }

    public static class HouseBean implements Serializable {

        private int house_id;
        private String house_name;
        private String house_address;
        private String house_telephone;
        private long start_time;
        private String purpose;
        private int house_status;
        private String renovation;
        private String property;
        private String house_price;
        private String architecture;
        private String isset;
        private String volume;
        private String green;
        private String door_door;
        private String sales;
        private String developers;
        private String periphery;
        private int house_type;
        private int cat_id1;
        private int cat_id2;
        private int cat_id3;
        private String on_time;
        private String is_show;
        private String years;
        private String orientation;
        private String floor;
        private String traffic;
        private String village;
        private int examine;
        private String reason_no;
        private String user_id;
        private String synopsis;
        private String premises_name;
        private int province_id;
        private int city_id;
        private int district;
        private String source;
        private String user_name;
        private int lease_type;
        private String payment;
        private String house_img;
        private String province_code;
        private String city_code;
        private String district_code;
        private String sales_address;
        private String parkinglot;
        private String architecture_type;
        private String longitude;
        private String latitude;
        private String original_img;
        private String toilet;
        private String office;
        private String floorall;
        private String allprice;
        private int sex;
        private String facilities;
        private int read_count;
        private int dele;
        private String rent;
        private int sales_type;
        private long completed_time;
        private String purpose_name;
        private String admin_telephone;

        public String getAdmin_telephone() {
            return admin_telephone;
        }

        public void setAdmin_telephone(String admin_telephone) {
            this.admin_telephone = admin_telephone;
        }

        public String getPurpose_name() {
            return purpose_name;
        }

        public void setPurpose_name(String purpose_name) {
            this.purpose_name = purpose_name;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public long getCompleted_time() {
            return completed_time;
        }

        public void setCompleted_time(long completed_time) {
            this.completed_time = completed_time;
        }

        public int getHouse_id() {
            return house_id;
        }

        public void setHouse_id(int house_id) {
            this.house_id = house_id;
        }

        public String getHouse_name() {
            return house_name;
        }

        public void setHouse_name(String house_name) {
            this.house_name = house_name;
        }

        public String getHouse_address() {
            return house_address;
        }

        public void setHouse_address(String house_address) {
            this.house_address = house_address;
        }

        public String getHouse_telephone() {
            return house_telephone;
        }

        public void setHouse_telephone(String house_telephone) {
            this.house_telephone = house_telephone;
        }

        public long getStart_time() {
            return start_time;
        }

        public void setStart_time(long start_time) {
            this.start_time = start_time;
        }

        public int getHouse_status() {
            return house_status;
        }

        public void setHouse_status(int house_status) {
            this.house_status = house_status;
        }

        public String getRenovation() {
            return renovation;
        }

        public void setRenovation(String renovation) {
            this.renovation = renovation;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getHouse_price() {
            return house_price;
        }

        public void setHouse_price(String house_price) {
            this.house_price = house_price;
        }

        public String getArchitecture() {
            return architecture;
        }

        public void setArchitecture(String architecture) {
            this.architecture = architecture;
        }

        public String getIsset() {
            return isset;
        }

        public void setIsset(String isset) {
            this.isset = isset;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getGreen() {
            return green;
        }

        public void setGreen(String green) {
            this.green = green;
        }

        public String getDoor_door() {
            return door_door;
        }

        public void setDoor_door(String door_door) {
            this.door_door = door_door;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getDevelopers() {
            return developers;
        }

        public void setDevelopers(String developers) {
            this.developers = developers;
        }

        public String getPeriphery() {
            return periphery;
        }

        public void setPeriphery(String periphery) {
            this.periphery = periphery;
        }

        public int getHouse_type() {
            return house_type;
        }

        public void setHouse_type(int house_type) {
            this.house_type = house_type;
        }

        public int getCat_id1() {
            return cat_id1;
        }

        public void setCat_id1(int cat_id1) {
            this.cat_id1 = cat_id1;
        }

        public int getCat_id2() {
            return cat_id2;
        }

        public void setCat_id2(int cat_id2) {
            this.cat_id2 = cat_id2;
        }

        public int getCat_id3() {
            return cat_id3;
        }

        public void setCat_id3(int cat_id3) {
            this.cat_id3 = cat_id3;
        }

        public String getOn_time() {
            return on_time;
        }

        public void setOn_time(String on_time) {
            this.on_time = on_time;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getYears() {
            return years;
        }

        public void setYears(String years) {
            this.years = years;
        }

        public String getOrientation() {
            return orientation;
        }

        public void setOrientation(String orientation) {
            this.orientation = orientation;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getTraffic() {
            return traffic;
        }

        public void setTraffic(String traffic) {
            this.traffic = traffic;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public int getExamine() {
            return examine;
        }

        public void setExamine(int examine) {
            this.examine = examine;
        }

        public String getReason_no() {
            return reason_no;
        }

        public void setReason_no(String reason_no) {
            this.reason_no = reason_no;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getPremises_name() {
            return premises_name;
        }

        public String getPremises_nameNull() {
            if (premises_name == null) {
                return "";
            }
            return premises_name;
        }

        public void setPremises_name(String premises_name) {
            this.premises_name = premises_name;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getDistrict() {
            return district;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        //出租类型 0 整租 1 合租    出租才会出现
        public String getLease_type_string() {
            if (lease_type == 0) {
                return "整租";
            } else if (lease_type == 1) {
                return "合租";
            }
            return "";
        }

        public int getLease_type() {
            return lease_type;
        }

        public void setLease_type(int lease_type) {
            this.lease_type = lease_type;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getHouse_img() {
            return house_img;
        }

        public void setHouse_img(String house_img) {
            this.house_img = house_img;
        }

        public String getProvince_code() {
            return province_code;
        }

        public void setProvince_code(String province_code) {
            this.province_code = province_code;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getDistrict_code() {
            return district_code;
        }

        public void setDistrict_code(String district_code) {
            this.district_code = district_code;
        }

        public String getSales_address() {
            return sales_address;
        }

        public void setSales_address(String sales_address) {
            this.sales_address = sales_address;
        }

        public String getParkinglot() {
            return parkinglot;
        }

        public void setParkinglot(String parkinglot) {
            this.parkinglot = parkinglot;
        }

        public String getArchitecture_type() {
            return architecture_type;
        }

        public void setArchitecture_type(String architecture_type) {
            this.architecture_type = architecture_type;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }

        public String getToilet() {
            return toilet;
        }

        public void setToilet(String toilet) {
            this.toilet = toilet;
        }

        public String getOffice() {
            return office;
        }

        public void setOffice(String office) {
            this.office = office;
        }

        public String getFloorall() {
            return floorall;
        }

        public void setFloorall(String floorall) {
            this.floorall = floorall;
        }

        public String getAllprice() {
            return allprice;
        }

        public void setAllprice(String allprice) {
            this.allprice = allprice;
        }

        public int getSex() {
            return sex;
        }

        //性别要求 0 不限 1 男 2 女 出租才会出现
        public String getSex_string() {
            switch (sex) {
                case 0:
                    return "不限";
                case 1:
                    return "仅限男生";
                case 2:
                    return "仅限女生";
            }
            return "";
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getFacilities() {
            return facilities;
        }

        public void setFacilities(String facilities) {
            this.facilities = facilities;
        }

        public int getRead_count() {
            return read_count;
        }

        public void setRead_count(int read_count) {
            this.read_count = read_count;
        }

        public int getDele() {
            return dele;
        }

        public void setDele(int dele) {
            this.dele = dele;
        }

        public String getRent() {
            return rent;
        }

        public void setRent(String rent) {
            this.rent = rent;
        }

        public int getSales_type() {
            return sales_type;
        }

        public void setSales_type(int sales_type) {
            this.sales_type = sales_type;
        }
    }

    public static class CollectBean implements Serializable {
        /**
         * result : 1
         */

        private int result;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }

    public static class HouseDoorListBean implements Serializable {
        /**
         * id : 3
         * house_id : 55
         * door_img : /public/upload/goods/2018/01-19/ccab3a74bbc747b448c3876de5cebbb4.jpg
         * door_price : 4
         * door_get : 3
         * door_area : 2
         * architecture_area : 1
         * door_door : 1
         * door_sort : null
         */

        private int id;
        private int house_id;
        private String door_img;
        private String door_price;
        private String door_get;
        private String door_area;
        private String architecture_area;
        private String door_door;
        private String door_sort;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHouse_id() {
            return house_id;
        }

        public void setHouse_id(int house_id) {
            this.house_id = house_id;
        }

        public String getDoor_img() {
            return door_img;
        }

        public void setDoor_img(String door_img) {
            this.door_img = door_img;
        }

        public String getDoor_price() {
            return door_price;
        }

        public void setDoor_price(String door_price) {
            this.door_price = door_price;
        }

        public String getDoor_get() {
            return door_get;
        }

        public void setDoor_get(String door_get) {
            this.door_get = door_get;
        }

        public String getDoor_area() {
            return door_area;
        }

        public void setDoor_area(String door_area) {
            this.door_area = door_area;
        }

        public String getArchitecture_area() {
            return architecture_area;
        }

        public void setArchitecture_area(String architecture_area) {
            this.architecture_area = architecture_area;
        }

        public String getDoor_door() {
            return door_door;
        }

        public void setDoor_door(String door_door) {
            this.door_door = door_door;
        }

        public String getDoor_sort() {
            return door_sort;
        }

        public void setDoor_sort(String door_sort) {
            this.door_sort = door_sort;
        }
    }

    public static class HouseImagesListBean implements Serializable {
        /**
         * id : 81
         * house_id : 55
         * image_url : /public/upload/goods/2018/01-19/0dd9691bb1a170ef19730262c60e5825.jpg
         * img_sort : 0
         */

        private int id;
        private int house_id;
        private String image_url;
        private int img_sort;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHouse_id() {
            return house_id;
        }

        public void setHouse_id(int house_id) {
            this.house_id = house_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getImg_sort() {
            return img_sort;
        }

        public void setImg_sort(int img_sort) {
            this.img_sort = img_sort;
        }
    }

}
