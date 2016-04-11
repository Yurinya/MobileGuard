package com.mqy.mobileguard.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MQY on 2016/4/11.
 */
public class spTools {
    public static void putString(Context context, String key,String value){
        SharedPreferences  sharedPreferences = context.getSharedPreferences(MyConstants.SPFILE,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,value).commit();
    }
    public static String getString(Context context, String key,String default_value){
        SharedPreferences  sharedPreferences = context.getSharedPreferences(MyConstants.SPFILE,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,default_value);
    }
}
