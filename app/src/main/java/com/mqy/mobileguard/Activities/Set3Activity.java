package com.mqy.mobileguard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mqy.mobileguard.R;
import com.mqy.mobileguard.Utils.MyConstants;
import com.mqy.mobileguard.Utils.spTools;

public class Set3Activity extends SetBaseActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        spTools.getString(getApplicationContext(), MyConstants.SAFENUMBER,"");
        super.initData();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_set3);
        editText = (EditText) findViewById(R.id.et_set3_safenumber);
        button = (Button) findViewById(R.id.bt_set3_choosenumber);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(Set3Activity.this,ContactActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            editText.setText(data.getStringExtra(MyConstants.SAFENUMBER));
        }
    }

    @Override
    public void next(View v) {
        String safenumber = editText.getText().toString();
        if (TextUtils.isEmpty(safenumber) ) {
            Toast.makeText(this,"Please choose safe number",Toast.LENGTH_SHORT).show();
            return;
        }else {
            spTools.putString(getApplicationContext(), MyConstants.SAFENUMBER, safenumber);
        }
        super.next(v);
    }

    @Override
    public void nextActivity() {
        spTools.putString(getApplicationContext(), MyConstants.SAFENUMBER,editText.getText().toString());
        startActivity(Set4Activity.class);
    }

    @Override
    public void preActivity() {
        startActivity(Set2Activity.class);
    }
}
