package com.mqy.mobileguard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mqy.mobileguard.R;
import com.mqy.mobileguard.Utils.MD5Utils;
import com.mqy.mobileguard.Utils.MyConstants;
import com.mqy.mobileguard.Utils.spTools;

public class HomeActivity extends AppCompatActivity {
    private GridView gv_menu;
    private int icons[] = {R.drawable.safe, R.drawable.callmsgsafe,R.drawable.app,
            R.drawable.taskmanager, R.drawable.netmanager,R.drawable.trojan,
            R.drawable.sysoptimize,R.drawable.atools, R.drawable.settings};
    private String names[]={"手机防盗","通讯卫士","软件管家","进程管理","流量统计","病毒查杀","缓存清理","高级工具","设置中心"};
    private MyAdapter adapter;
    private AlertDialog alertDialog_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        gv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //手机防盗
                    case 0:
                        if(TextUtils.isEmpty(spTools.getString(getApplicationContext(), MyConstants.PASSWORD,""))){
                            passwordSettingDialog();
                        }
                        else {
                            passwordCheckingDialog();
                        }
                        break;
                }
            }
        });

    }

    private void passwordCheckingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(getApplicationContext(), R.layout.dialog_checking_password, null);
        final EditText et_password_enter = (EditText) view.findViewById(R.id.tv_dialog_set_password_enter);
        Button bt_set_password_yes = (Button) view.findViewById(R.id.bt_dialog_set_password_yes);
        Button bt_set_password_no = (Button) view.findViewById(R.id.bt_dialog_set_password_no);
        builder.setView(view);
        alertDialog_password = builder.create();
        alertDialog_password.show();
        bt_set_password_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password_enter = et_password_enter.getText().toString();
                String MD5_enter_password = MD5Utils.md5(MD5Utils.md5(password_enter));
                if(TextUtils.isEmpty(password_enter)){
                    Toast.makeText(getApplicationContext(),"Don`t be empty",Toast.LENGTH_SHORT).show();
                    return;
                }else if(!MD5_enter_password.equals(spTools.getString(getApplicationContext(),MyConstants.PASSWORD,""))){
                    Toast.makeText(getApplicationContext(), "WRONG",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    //密码相同
                    Intent intent = new Intent(HomeActivity.this,LostFindActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
                    alertDialog_password.dismiss();
                }
            }
        });
        bt_set_password_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog_password.dismiss();
            }
        });
    }

    private void passwordSettingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(getApplicationContext(), R.layout.dialog_set_password, null);
        final EditText et_password_one = (EditText) view.findViewById(R.id.tv_dialog_set_password_one);
        final EditText et_password_two = (EditText) view.findViewById(R.id.tv_dialog_set_password_two);
        Button bt_set_password_yes = (Button) view.findViewById(R.id.bt_dialog_set_password_yes);
        Button bt_set_password_no = (Button) view.findViewById(R.id.bt_dialog_set_password_no);
        builder.setView(view);
        alertDialog_password = builder.create();
        alertDialog_password.show();
        bt_set_password_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password_one = et_password_one.getText().toString();
                String password_two = et_password_two.getText().toString();
                if(TextUtils.isEmpty(password_one)||TextUtils.isEmpty(password_two)){
                    Toast.makeText(getApplicationContext(),"Don`t be empty",Toast.LENGTH_SHORT).show();
                    return;
                }else if(!password_one.equals(password_two)){
                    Toast.makeText(getApplicationContext(), "Passwords arn`t same ",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    String saved_password = MD5Utils.md5(MD5Utils.md5(password_one));
                    spTools.putString(getApplicationContext(), MyConstants.PASSWORD,saved_password);
                    Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
                    alertDialog_password.dismiss();
                }
            }
        });
        bt_set_password_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog_password.dismiss();
            }
        });

    }

    private void initData() {
        adapter = new MyAdapter();
        gv_menu.setAdapter(adapter);
    }

    private void initView() {
        setContentView(R.layout.activity_home);
        gv_menu = (GridView) findViewById(R.id.gv_home_menu);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return icons.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //为何不用mainActivity.this?
            View view = View.inflate(getApplicationContext(),R.layout.item_home_gridview,null);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_item_home_gv_icon);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_item_home_gv_name);
            iv_icon.setImageResource(icons[position]);
            tv_name.setText(names[position]);
            return view;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }
}
