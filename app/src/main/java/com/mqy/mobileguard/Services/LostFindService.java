package com.mqy.mobileguard.Services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;

public class LostFindService extends Service {
    private SmsReceiver mSmsReceiver;
    public LostFindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public class SmsReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            Object[] datas = (Object[]) extras.get("pdus");
            for (Object data:datas) {
                SmsMessage sm = SmsMessage.createFromPdu((byte[])data);

            }
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mSmsReceiver = new SmsReceiver();
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(mSmsReceiver, filter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSmsReceiver);
    }
}
