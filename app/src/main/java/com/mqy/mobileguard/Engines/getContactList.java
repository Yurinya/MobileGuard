package com.mqy.mobileguard.Engines;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.mqy.mobileguard.domain.ContactBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MQY on 2016/4/13.
 */
public class getContactList {
    public static List<ContactBean> getContactList(Context context){
        List<ContactBean> datas = new ArrayList<ContactBean>();
        Uri uriContants = Uri.parse("content://com.android.contacts/contacts");
        Uri uriDatas = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = context.getContentResolver().query(uriContants, new String[]{"_id"}, null, null, null);
        while (cursor.moveToNext()) {
            ContactBean contactBean = new ContactBean();
            String id = cursor.getString(0);
            Cursor cursor2 = context.getContentResolver()
                            .query(uriDatas, new String[]{"data1","mimetype"}," raw_contact_id = ? ",new String[]{id},null);
            while (cursor2.moveToNext()) {
                if (cursor2.getString(1).equals("vnd.android.cursor.item/name")) {
                    contactBean.setName(cursor2.getString(0));
                } else if (cursor2.getString(1).equals("vnd.android.cursor.item/phone_v2")) {
                    contactBean.setNumber(cursor2.getString(0));
                }
            }
            cursor2.close();
            datas.add(contactBean);
        }
        cursor.close();
        return datas;

    }
}
