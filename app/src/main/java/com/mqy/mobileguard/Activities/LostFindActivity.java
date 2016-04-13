package com.mqy.mobileguard.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mqy.mobileguard.R;
import com.mqy.mobileguard.Utils.MyConstants;
import com.mqy.mobileguard.Utils.spTools;

public class LostFindActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (spTools.getBoolean(getApplicationContext(), MyConstants.ISSETUP,false)){
        initView();

        }
        else {
            Intent  intent = new Intent(this,Set1Activity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        setContentView(R.layout.activity_lost_find);

    }
}
