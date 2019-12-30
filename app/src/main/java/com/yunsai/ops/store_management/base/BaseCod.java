package com.yunsai.ops.store_management.base;

/***
 ** ************************************
 * Created by djl on 2019/11/3
 * com.yunsai.ops.store_management.base
 * *************************************
 * *
 ** ************************************
 */
public class BaseCod {


    /**
     * resCode : 200
     * resMsg : 成功
     * userId :
     */

    private String resCode;
    private String resMsg;
    private String userId;
    private String userKey;

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
