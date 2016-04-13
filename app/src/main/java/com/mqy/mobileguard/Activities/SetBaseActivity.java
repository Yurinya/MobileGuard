package com.mqy.mobileguard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.View;

import com.mqy.mobileguard.R;

public abstract class SetBaseActivity extends AppCompatActivity {
    private GestureDetector gd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initGesture();
        initData();
        initEvent();
    }

    private void initGesture() {

    }

    public void initEvent() {

    }

    public void initData() {

    }

    public abstract void initView();

    public void prev(View v){
        preActivity();
        preAnimation();
    }



    public void next(View v){
        nextActivity();
        nextAnimation();
    }
    public abstract void nextActivity();
    public abstract void preActivity();
    public void startActivity(Class type){
        Intent intent = new Intent(this,type);
        startActivity(intent);
        finish();
    }
    private void preAnimation() {
        overridePendingTransition(R.anim.prev_in,R.anim.prev_out);
    }
    private void nextAnimation(){
        overridePendingTransition(R.anim.next_in,R.anim.next_out);
    }
}
