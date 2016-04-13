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
    public static void putBoolean(Context context,String key,Boolean value){
        SharedPreferences sp = context.getSharedPreferences(MyConstants.SPFILE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();//保存数据
    }

    public static boolean getBoolean(Context context,String key,boolean defValue){
        SharedPreferences sp = context.getSharedPreferences(MyConstants.SPFILE, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }
}
