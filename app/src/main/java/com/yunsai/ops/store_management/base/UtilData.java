package com.yunsai.ops.store_management.base;

import android.util.Log;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class UtilData {

    public static Date getNetTime(){
        String webUrl = "https://www.baidu.com/";//中国科学院国家授时中心
        try {
            URL url = new URL(webUrl);
            URLConnection uc = url.openConnection();
            uc.connect();
            long correctTime = uc.getDate();
            return new Date(correctTime);
        } catch (Exception e) {
            return new Date();
        }
    }
    public static String getinitgetData() {

        Date netTime = UtilData.getNetTime();
        long time = netTime.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String add = simpleDateFormat.format(time);
        Log.i("UtilData", "工具类: "+add);
        return add;

    }
    public static String geOLtinitgetData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String add = simpleDateFormat.format(new Date());
        Log.i("UtilData", "工具类: "+add);
        return add;

    }
}
