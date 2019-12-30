package com.yunsai.ops.store_management.base;

import java.util.List;

public class Listbeanss {

    private List<RECORDSBean> rECORDS;

    public List<RECORDSBean> getRECORDS() {
        return rECORDS;
    }

    public void setRECORDS(List<RECORDSBean> RECORDS) {
        this.rECORDS = RECORDS;
    }

    public static class RECORDSBean {
        /**
         * shopId : 1
         * shopName : 电脑
         * shopNum : 92
         * shopPrice : 1.4
         * shopMon : 2
         * shopYu :
         * shopTime : 19/1/2038 03:14:07
         */

        private String shopId;
        private String shopName;
        private String shopNum;
        private String shopPrice;
        private String shopMon;
        private String shopYu;
        private String shopTime;
        private String shopParticulars;
        private String shopAddTime;


        public String getShopParticulars() {
            return shopParticulars;
        }

        public void setShopParticulars(String shopParticulars) {
            this.shopParticulars = shopParticulars;
        }


        public String getShopAddTime() {
            return shopAddTime;
        }

        public void setShopAddTime(String shopAddTime) {
            this.shopAddTime = shopAddTime;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopNum() {
            return shopNum;
        }

        public void setShopNum(String shopNum) {
            this.shopNum = shopNum;
        }

        public String getShopPrice() {
            return shopPrice;
        }

        public void setShopPrice(String shopPrice) {
            this.shopPrice = shopPrice;
        }

        public String getShopMon() {
            return shopMon;
        }

        public void setShopMon(String shopMon) {
            this.shopMon = shopMon;
        }

        public String getShopYu() {
            return shopYu;
        }

        public void setShopYu(String shopYu) {
            this.shopYu = shopYu;
        }

        public String getShopTime() {
            return shopTime;
        }

        public void setShopTime(String shopTime) {
            this.shopTime = shopTime;
        }
    }
}
