package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/17.
 */

public class MyHouseModel {
    private int house_id;
    private String house_name;
    private String house_address;
    private String house_telephone;
    private long start_time;
    private int purpose;
    private int house_status;
    private String renovation;
    private String property;
    private String house_price;
    private String architecture;
    private String isset;
    private String volume;
    private String green;
    private int door_door;
    private String sales;
    private String developers;
    private String periphery;
    private int house_type;
    private int cat_id1;
    private int cat_id2;
    private int cat_id3;
    private long on_time;
    private int is_show;
    private String years;
    private String orientation;
    private String floor;
    private String traffic;
    private String village;
    private int examine;
    private String reason_no;
    private int user_id;
    private String synopsis;
    private String premises_name;
    private int province_id;
    private int city_id;
    private int district;
    private String source;
    private String user_name;
    private String lease_type;
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
    private int toilet;
    private int office;
    private int floorall;
    private String allprice;
    private int sex;
    private String facilities;
    private String company_district_name;
    private List<HouseImagesListBean> houseImagesList;
    private List<HouseLabelBean> houseLabel;
    private String read_count;
    private String rent;

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getRead_count() {
        return read_count;
    }

    public void setRead_count(String read_count) {
        this.read_count = read_count;
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

    public int getPurpose() {
        return purpose;
    }

    public void setPurpose(int purpose) {
        this.purpose = purpose;
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

    public int getDoor_door() {
        return door_door;
    }

    public void setDoor_door(int door_door) {
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

    public long getOn_time() {
        return on_time;
    }

    public void setOn_time(long on_time) {
        this.on_time = on_time;
    }

    public int getIs_show() {
        return is_show;
    }

    public void setIs_show(int is_show) {
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

    public String getOrientationNoNull() {
        if (orientation == null) {
            return "";
        }
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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

    public String getLease_type() {
        return lease_type;
    }

    public void setLease_type(String lease_type) {
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

    public int getToilet() {
        return toilet;
    }

    public void setToilet(int toilet) {
        this.toilet = toilet;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public int getFloorall() {
        return floorall;
    }

    public void setFloorall(int floorall) {
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

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }


    public List<HouseImagesListBean> getHouseImagesList() {
        return houseImagesList;
    }

    public void setHouseImagesList(List<HouseImagesListBean> houseImagesList) {
        this.houseImagesList = houseImagesList;
    }

    public String getCompany_district_name() {
        return company_district_name;
    }

    public void setCompany_district_name(String company_district_name) {
        this.company_district_name = company_district_name;
    }

    public List<HouseLabelBean> getHouseLabel() {
        return houseLabel;
    }

    public void setHouseLabel(List<HouseLabelBean> houseLabel) {
        this.houseLabel = houseLabel;
    }


    public static class HouseImagesListBean {
        /**
         * id : 8
         * house_id : 31
         * image_url : /public/upload/house_img/20180117/f3494b17536d961fb9d4ed9cdd320c96.jpg
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

    public static class HouseLabelBean {
        /**
         * id : 90
         * house_id : 31
         * hs_id : 53
         * so_name : 满两年
         */

        private int id;
        private int house_id;
        private int hs_id;
        private String so_name;

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

        public int getHs_id() {
            return hs_id;
        }

        public void setHs_id(int hs_id) {
            this.hs_id = hs_id;
        }

        public String getSo_name() {
            return so_name;
        }

        public void setSo_name(String so_name) {
            this.so_name = so_name;
        }
    }
}
