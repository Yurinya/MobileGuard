package com.mqy.mobileguard.Activities;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mqy.mobileguard.Engines.getContactList;
import com.mqy.mobileguard.R;
import com.mqy.mobileguard.Utils.MyConstants;
import com.mqy.mobileguard.domain.ContactBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MQY on 2016/4/13.
 */
public class ContactActivity extends ListActivity {
    private static final int LOADING = 0;
    private static final int FINISH = 1;
    private ListView listView;
    private List<ContactBean> datas = new ArrayList<ContactBean>();
    private Myadapter adapter;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new Myadapter();
        listView.setAdapter(adapter);
        initData();
        initEvent();

    }

    private class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(), R.layout.item_contact_listview,null);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_item_contact_lv_name);
            TextView tv_number = (TextView) view.findViewById(R.id.tv_item_contact_lv_number);
            ContactBean contactBean = datas.get(position);
            tv_name.setText(contactBean.getName());
            tv_number.setText(contactBean.getNumber());
            return view;
        }
    }
    private void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactBean contactBean = datas.get(position);
                String number = contactBean.getNumber();
                Intent intent = new Intent();
                intent.putExtra(MyConstants.SAFENUMBER,number);
                setResult(0,intent);
            }
        });

    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case LOADING:
                    pd = new ProgressDialog(ContactActivity.this);
                    pd.setTitle("Loading");
                    pd.setMessage("Please wait");
                    pd.show();
                    break;
                case FINISH:
                    if (pd != null) {
                        pd.dismiss();
                        pd = null;
                        adapter.notifyDataSetChanged();
                    }
            }
        }
    };

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Message msg = Message.obtain();
                msg.what = LOADING;
                mHandler.sendMessage(msg);
                datas = getContactList.getContactList(getApplicationContext());
                msg = Message.obtain();
                msg.what = FINISH;
                mHandler.sendMessage(msg);
            }
        }.start();

    }
}
