package com.example.admin.vibring.googlemaps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class MyDatabase1 extends SQLiteOpenHelper {

    private Context context;

    //Define the Schema
    public static final String DATABASE_NAME = "vibring.db";
    public static final int DATABASE_VERSION = 4;
    public static final String TABLE_NAME = "AddlocationTable";
    public static final String UID = "_id";
    public static final String LOCATION = "Location";
    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE = "Longitude";
    public static final String MODE = "Mode";
    // public static final String EMAIL = "Email";

    //QUERY
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + LOCATION + " TEXT ," + LATITUDE + " DOUBLE," + LONGITUDE + " DOUBLE," + MODE + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + "";

    //private static final String ALTER_EMPLOYEE_1 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + EMAIL + " TEXT ";

    //Constructor
    public MyDatabase1(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        //     Toast.makeText(context, "Constructor called", Toast.LENGTH_LONG).show();
        //SQLiteDatabase db1 = getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db1) {
        //       Toast.makeText(context, "onCreate() called Database created", Toast.LENGTH_LONG).show();
        db1.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {

        //       Toast.makeText(context, "onCreate() called", Toast.LENGTH_LONG).show();
        db1.execSQL(DROP_TABLE);
        onCreate(db1);

        //Toast.makeText(context, "onupdate() called", Toast.LENGTH_LONG).show();
    }

}