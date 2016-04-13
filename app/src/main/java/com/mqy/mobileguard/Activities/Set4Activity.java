package com.mqy.mobileguard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.mqy.mobileguard.R;
import com.mqy.mobileguard.Services.LostFindService;
import com.mqy.mobileguard.Utils.MyConstants;
import com.mqy.mobileguard.Utils.ServiceUtils;
import com.mqy.mobileguard.Utils.spTools;

public class Set4Activity extends SetBaseActivity {

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_set4);
        checkBox = (CheckBox) findViewById(R.id.cb_set4_protected);
    }

    @Override
    public void initData() {
        super.initData();
        if (ServiceUtils.isServiceRunning(getApplicationContext(),"com.mqy.mobileguard.Services.LostFindService")) {
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }
    }

    @Override
    public void initEvent() {
        super.initEvent();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(Set4Activity.this, LostFindService.class);
                    startService(intent);
                }
                else {
                    Intent intent = new Intent(Set4Activity.this, LostFindService.class);
                    stopService(intent);
                }
            }
        });
    }

    @Override
    public void nextActivity() {
        spTools.putBoolean(getApplicationContext(), MyConstants.ISSETUP,true);
        startActivity(LostFindActivity.class);
    }

    @Override
    public void preActivity() {
        startActivity(Set3Activity.class);
    }
}
