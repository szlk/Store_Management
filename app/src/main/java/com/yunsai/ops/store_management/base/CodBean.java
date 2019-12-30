package com.yunsai.ops.store_management.base;

import java.util.List;

public class CodBean {

    private List<RECORDSBean> rECORDS;

    public List<RECORDSBean> getRECORDS() {
        return rECORDS;
    }
	

    public void setRECORDS(List<RECORDSBean> rECORDS) {
        this.rECORDS = rECORDS;
    }

    public static class RECORDSBean {
        /**
         * shopMon : 2
         * shopNum : 92
         * shopPrice : 1.4
         * shopTime : 2019-11-06 11:50:42
         * shopName : 电脑
         * shopId : 1
         * shopAddTime : 2019-11-06 11:50:42
         * shopYu : 1254
         * shopParticulars : null
         */

        private String shopMon;
        private String shopNum;
        private String shopPrice;
        private String shopTime;
        private String shopName;
        private String shopId;
        private String shopAddTime;
        private String shopYu;
        private String shopParticulars;

        public String getShopMon() {
            return shopMon;
        }

        public void setShopMon(String shopMon) {
            this.shopMon = shopMon;
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

        public String getShopTime() {
            return shopTime;
        }

        public void setShopTime(String shopTime) {
            this.shopTime = shopTime;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopAddTime() {
            return shopAddTime;
        }

        public void setShopAddTime(String shopAddTime) {
            this.shopAddTime = shopAddTime;
        }

        public String getShopYu() {
            return shopYu;
        }

        public void setShopYu(String shopYu) {
            this.shopYu = shopYu;
        }

        public String getShopParticulars() {
            return shopParticulars;
        }

        public void setShopParticulars(String shopParticulars) {
            this.shopParticulars = shopParticulars;
        }
    }
}
