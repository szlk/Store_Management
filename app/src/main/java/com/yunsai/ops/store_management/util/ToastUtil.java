package com.yunsai.ops.store_management.util;

import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

/***
 ** ************************************
 * Created by djl on 2019/11/10
 * com.yunsai.ops.store_management.util
 * *************************************
 * *
 ** ************************************
 */
public class ToastUtil {
    private static Toast toast = null;
    public static void showToast(Context context, String text) {
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            Looper.prepare();
            myLooper = Looper.myLooper();
        }

        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.show();
        if ( myLooper != null) {
            Looper.loop();
            myLooper.quit();
        }
    }
}
