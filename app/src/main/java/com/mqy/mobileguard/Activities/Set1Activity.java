package com.mqy.mobileguard.Activities;

import android.os.Bundle;

import com.mqy.mobileguard.R;

public class Set1Activity extends SetBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_set1);
    }

    @Override
    public void nextActivity() {
        startActivity(Set2Activity.class);
    }

    @Override
    public void preActivity() {

    }
}
