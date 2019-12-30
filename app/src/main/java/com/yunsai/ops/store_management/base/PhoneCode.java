package com.yunsai.ops.store_management.base;

import java.util.List;

/***
 ** ************************************
 * Created by djl on 2019/11/18
 * com.yunsai.ops.store_management.base
 * *************************************
 * *
 ** ************************************
 */
public class PhoneCode {

    private List<RECORDSBean> rECORDS;

    public List<RECORDSBean> getRECORDS() {
        return rECORDS;
    }

    public void setRECORDS(List<RECORDSBean> rECORDS) {
        this.rECORDS = rECORDS;
    }

    public static class RECORDSBean {
        /**
         * userPassword : 123
         * userAccount : 123
         * userId : 1
         * userKey : 0
         */

        private String userPassword;
        private String userAccount;
        private String userId;
        private String userKey;

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }
    }
}
