package com.mqy.mobileguard.Activities;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mqy.mobileguard.R;
import com.mqy.mobileguard.Utils.MyConstants;
import com.mqy.mobileguard.Utils.spTools;

public class Set2Activity extends SetBaseActivity {

    private android.widget.Button button;
    private android.widget.ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_set2);
        button = (Button) findViewById(R.id.bt_set2_bindsim);
        imageView = (ImageView) findViewById(R.id.iv_set2_isbind);
        if (TextUtils.isEmpty(spTools.getString(getApplicationContext(), MyConstants.SIMNUMBER, ""))){
            imageView.setImageResource(R.drawable.unlock);
        }
        else {
            imageView.setImageResource(R.drawable.lock);
        }
    }

    @Override
    public void initEvent() {
        super.initEvent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(spTools.getString(getApplicationContext(), MyConstants.SIMNUMBER, ""))) {
                    TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String simSerialNumber = telephonyManager.getSimSerialNumber();
                    spTools.putString(getApplicationContext(), MyConstants.SIMNUMBER, simSerialNumber);
                    imageView.setImageResource(R.drawable.lock);
                }
                else {
                    spTools.putString(getApplicationContext(), MyConstants.SIMNUMBER,"");
                    imageView.setImageResource(R.drawable.unlock);
                }

            }
        });
    }

    @Override
    public void nextActivity() {
        startActivity(Set3Activity.class);
    }

    @Override
    public void preActivity() {
        startActivity(Set1Activity.class);
    }

    @Override
    public void next(View v) {
        if (TextUtils.isEmpty(spTools.getString(getApplicationContext(), MyConstants.SIMNUMBER, ""))){
            Toast.makeText(this,"Please bind",Toast.LENGTH_SHORT).show();
            return;
        }
        super.next(v);
    }
}
