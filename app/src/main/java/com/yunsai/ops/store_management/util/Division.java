package com.yunsai.ops.store_management.util;

import java.text.DecimalFormat;

/***
 ** ************************************
 * Created by djl on 2020/1/3
 * com.yunsai.ops.store_management.util
 * *************************************
 * *
 ** ************************************
 */
public class Division {
    /**
     * TODO 除法运算，保留小数
     * @author 袁忠明
     * @date 2018-4-17下午2:24:48
     * @param a 被除数
     * @param b 除数
     * @return 商
     */
    public static String txfloat(int a,int b) {
        // TODO 自动生成的方法存根

        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数

        return df.format((float)a/b);

    }
}
