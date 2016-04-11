package com.mqy.mobileguard.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.mqy.mobileguard.R;

public class SplashActivity extends Activity {
    private RelativeLayout mRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        initAnimation();
        waiting();

    }

    private void waiting() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(3000);
                toHome();
            }
        }.start();
    }

    private void toHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void initAnimation() {
        AlphaAnimation alphaAnimation =new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        AnimationSet animationSet = new AnimationSet(true);
        mRelativeLayout.setAnimation(animationSet);
    }
}
