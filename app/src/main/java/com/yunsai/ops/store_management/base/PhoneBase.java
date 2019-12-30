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
public class PhoneBase {

    private List<RECORDSBean> RECORDS;

    public List<RECORDSBean> getRECORDS() {
        return RECORDS;
    }

    public void setRECORDS(List<RECORDSBean> RECORDS) {
        this.RECORDS = RECORDS;
    }

    public static class RECORDSBean {
        /**
         * userId : 1
         * userAccount : 123
         * userPassword : 123
         * userKey : 0
         * userName :
         */

        private String userId;
        private String userAccount;
        private String userPassword;
        private String userKey;
        private String userName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
