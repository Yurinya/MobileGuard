package com.mqy.mobileguard.Utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by MQY on 2016/4/13.
 */
public class ServiceUtils {
    public static boolean isServiceRunning(Context context, String serviceName){
        boolean isRunning = false;
        ActivityManager systemService = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = systemService.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo runningService:runningServices) {
            if (runningService.service.getClassName().equals(serviceName)){
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
