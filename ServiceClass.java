package com.example.admin.vibring.service;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.example.admin.vibring.addedlocations.SingleRow;
import com.example.admin.vibring.googlemaps.MyDatabase1;
import com.example.admin.vibring.googlemaps.SearchActivity;
import com.example.admin.vibring.loginregistration.MyDatabase;
import com.example.admin.vibring.loginregistration.RegistrationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 5/10/2017.
 */

public class ServiceClass extends Service {
    private ArrayList<SingleRow> singleRowArrayList;
    String locationName, mode;
    int uid;
    double latitude, longitude;
    SearchActivity searchActivity= new SearchActivity();

    MyDatabase1 myDatabase1;
    private SQLiteDatabase db;

      @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Should check for database change here?
        myDatabase1 = new MyDatabase1(ServiceClass.this);
        // fetches data from database


        db = myDatabase1.getReadableDatabase();
        String[] columns = {MyDatabase1.UID, MyDatabase1.LOCATION, MyDatabase1.LATITUDE, MyDatabase1.LONGITUDE, MyDatabase1.MODE};
        Cursor cursor = db.query(MyDatabase1.TABLE_NAME, columns, null, null, null, null, null);
        //StringBuffer buffer = new StringBuffer();
        singleRowArrayList = new ArrayList<SingleRow>();
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(MyDatabase1.UID);
            int index2 = cursor.getColumnIndex(MyDatabase1.LOCATION);
            int index3 = cursor.getColumnIndex(MyDatabase1.LATITUDE);
            int index4 = cursor.getColumnIndex(MyDatabase1.LONGITUDE);
            int index5 = cursor.getColumnIndex(MyDatabase1.MODE);
            // int index5 = cursor.getColumnIndex(MyDatabase.EMAIL);

            uid = cursor.getInt(index1);
            locationName = cursor.getString(index2);
            latitude = cursor.getDouble(index3);
            longitude = cursor.getDouble(index4);
            mode = cursor.getString(index5);


        }
        return Service.START_NOT_STICKY;
    }
    public class MyBinder extends Binder {
        ServiceClass getService() {
            return ServiceClass.this;
        }
    }


}
