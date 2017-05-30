package com.example.admin.vibring.addedlocations;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.vibring.R;
import com.example.admin.vibring.googlemaps.MyDatabase1;

import java.util.ArrayList;

import static java.lang.System.exit;


public class ViewLocActivity extends AppCompatActivity {

    private SingleRow singlerow;
    private ArrayList<SingleRow> singleRowArrayList;
    private ListView lv;
    private MyAdapter myadapter;
    private MyDatabase1 myDatabase1;
    private SQLiteDatabase db2;
    String locationName, mode;
    double latitude, longitude;
    Button btncancel,btndelete,btnselectall;
    int uid;
    CheckBox checkBox;
    ArrayList arrayList= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_loc);
        myDatabase1 = new MyDatabase1(this);
        lv = (ListView) findViewById(R.id.locationlist);
        btnselectall = (Button) findViewById(R.id.btnselectalllist);
        btncancel = (Button) findViewById(R.id.btncancelloc);
        btndelete = (Button) findViewById(R.id.btnDeletelist);
        //checkBox = (CheckBox) findViewById(R.id.deleteCheck);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnselectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewLocActivity.this, "Selected", Toast.LENGTH_LONG).show();
            }
        });


        db2 = myDatabase1.getReadableDatabase();
        String[] columns = {MyDatabase1.UID, MyDatabase1.LOCATION, MyDatabase1.LATITUDE, MyDatabase1.LONGITUDE, MyDatabase1.MODE};
        Cursor cursor = db2.query(MyDatabase1.TABLE_NAME, columns, null, null, null, null, null);
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

            singlerow = new SingleRow(uid, locationName, latitude, longitude, mode);
            singleRowArrayList.add(singlerow);
            //singlerow= new SingleRow(uid[index1],locationName[index2],latitude[index3],longitude[index4],mode[index5]);

        }
//        for (int i = 0; i < locationName.length; i++) {
//            singlerow = new SingleRow(locationName, desc[i]);
//            singleRowArrayList.add(singlerow);
//        }

        for (int i = 0; i < singleRowArrayList.size(); i++) {
            singlerow = singleRowArrayList.get(i);
            locationName = singlerow.getLocationName();
        }
        myadapter = new MyAdapter(this, singleRowArrayList);

        lv.setAdapter(myadapter);


//            String[] name = {"Raj", "Ravi", "Rajat", "Ankita", "Arun", "Divya", "Diksha", "Rakesh", "Rahul"};
//            String[] mode = {"Android", "PHP", "IOS", "PHP", "Android", "Android", "PHP", "Android", "PHP"};


//        //  int[] image = {R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user};
//        singleRowArrayList = new ArrayList<>();
//        for (int i = 0; i < name.length; i++) {
//            singlerow = new SingleRow(name[i], desc[i]);
//            singleRowArrayList.add(singlerow);
//        }
//        myadapter = new MyAdapter(this, singleRowArrayList);
//        lv.setAdapter(myadapter);
//
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(ViewLocActivity.this, "Delete Clicked", Toast.LENGTH_LONG).show();
                db2 = myDatabase1.getWritableDatabase();
               // String whereClause = MyDatabase1.LOCATION;
              //  String[] whereArgs = {};
                int d = db2.delete(MyDatabase1.TABLE_NAME, null, null);
                if (d > 0) {
                    Toast.makeText(ViewLocActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ViewLocActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                }

            }
        });
        //final ArrayList arrayList= null;

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exit(0);
    }
}
