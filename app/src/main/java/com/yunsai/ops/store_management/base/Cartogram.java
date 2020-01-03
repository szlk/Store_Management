package com.yunsai.ops.store_management.base;

import java.util.List;

/***
 ** ************************************
 * Created by djl on 2020/1/2
 * com.yunsai.ops.store_management.base
 * *************************************
 * *
 ** ************************************
 */
public class Cartogram {

    private List<RECORDSBean> rECORDS;

    public List<RECORDSBean> getRECORDS() {
        return rECORDS;
    }

    public void setRECORDS(List<RECORDSBean> rECORDS) {
        this.rECORDS = rECORDS;
    }

    public static class RECORDSBean {
        /**
         * shopComputer : 50000
         * shopHarddisk : 4000
         * shopMemory : 3000
         * shopPhone : 30000
         * shopid : 1
         * shopCup : 200
         */

        private String shopComputer;
        private String shopHarddisk;
        private String shopMemory;
        private String shopPhone;
        private String shopid;
        private String shopCup;

        public String getShopComputer() {
            return shopComputer;
        }

        public void setShopComputer(String shopComputer) {
            this.shopComputer = shopComputer;
        }

        public String getShopHarddisk() {
            return shopHarddisk;
        }

        public void setShopHarddisk(String shopHarddisk) {
            this.shopHarddisk = shopHarddisk;
        }

        public String getShopMemory() {
            return shopMemory;
        }

        public void setShopMemory(String shopMemory) {
            this.shopMemory = shopMemory;
        }

        public String getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(String shopPhone) {
            this.shopPhone = shopPhone;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getShopCup() {
            return shopCup;
        }

        public void setShopCup(String shopCup) {
            this.shopCup = shopCup;
        }
    }
}
