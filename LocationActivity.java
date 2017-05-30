package com.example.admin.vibring.googlemaps;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.vibring.R;
import com.example.admin.vibring.addedlocations.ViewLocActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.vibring.R.id.edtLongitude;
import static java.lang.System.exit;


public class LocationActivity extends AppCompatActivity {
    private TextView LocationName, Latitude, Longitude;
    String locationName;
    Double longitude, latitude;
    private Spinner spinner;
    private Button btnAddLocation, btnCancel;
    private MyDatabase1 myDatabase1;
    private SQLiteDatabase db1;
    String item;
    AudioManager am;
    TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        myDatabase1 = new MyDatabase1(LocationActivity.this);
        //findviewbyid
        LocationName = (TextView) findViewById(R.id.edtLocationName);
        Longitude = (TextView) findViewById(edtLongitude);
        Latitude = (TextView) findViewById(R.id.edtLatitude);
        counter=(TextView)findViewById(R.id.counter);
        //mode = (Spinner) findViewById(R.id.spinner);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnAddLocation = (Button) findViewById(R.id.btnaddlocation);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        //Intent i = getIntent();
        //GETTING DATA FROM PREVIOUS ACTIVITY SEARCH MAP
        Bundle bundle = getIntent().getExtras();
        locationName = bundle.getString("locationsearch");
        latitude = bundle.getDouble("latitude");
        longitude = bundle.getDouble("longitude");
        LocationName.setText(" LOCATION NAME : " +locationName);
        Latitude.setText(" LATITUDE   : " +latitude);
        Longitude.setText(" LONGITUDE  : " +longitude);
         //String[] modes= getResources().getStringArray(R.array.modes);
        List<String> list = new ArrayList<String>();

        list.add("RingTone");
        list.add("Vibrate");
        list.add("Silent");
                ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);

          adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            item=spinner.getSelectedItem().toString();
                      if(position==0)
                      {
                         am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                      }else if(position==1)
                      {
                     am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                      }
                      else{
                          am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                      }

        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
        btnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db1 = myDatabase1.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put(MyDatabase1.LOCATION, locationName);
                cv.put(MyDatabase1.LATITUDE, latitude);
                cv.put(MyDatabase1.LONGITUDE, longitude);
                cv.put(MyDatabase1.MODE, item);
                // cv.put(MyDatabase.EMAIL, email);

                long id = db1.insert(MyDatabase1.TABLE_NAME, null, cv);
                if (id < 0) {

                    Toast.makeText(LocationActivity.this, "Data Not SAved", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(LocationActivity.this,ViewLocActivity.class);

                    startActivity(intent);
                    //Toast.makeText(LocationActivity.this, "Insertion Successful", Toast.LENGTH_LONG).show();
                    LocationName.setText(" ");
                    Latitude.setText(" ");
                    Longitude.setText(" ");

                }


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exit(0);
    }
}
