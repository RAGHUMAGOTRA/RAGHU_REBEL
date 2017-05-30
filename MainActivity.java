package com.example.admin.vibring.mainscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.vibring.about.AboutActivity;
import com.example.admin.vibring.R;
import com.example.admin.vibring.activatevibring.ActivateVibring;
import com.example.admin.vibring.addedlocations.ViewLocActivity;
import com.example.admin.vibring.googlemaps.LocationActivity;
import com.example.admin.vibring.googlemaps.SearchActivity;
import com.example.admin.vibring.googlemaps.SearchMap;

public class MainActivity extends AppCompatActivity {
    private Button btn_viewlocation,btn_searchmap,btn_addlocation,btn_about,activate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findviewbyId
        btn_viewlocation=(Button)findViewById(R.id.btnviewlocation);
        btn_searchmap=(Button)findViewById(R.id.btnsearchmap);
        btn_addlocation=(Button)findViewById(R.id.btnaddlocation);
        btn_about=(Button)findViewById(R.id.btnabout);
        activate=(Button)findViewById(R.id.activate);
        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,ActivateVibring.class);
                startActivity(intent);

            }
        });

        btn_viewlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,ViewLocActivity.class);
                startActivity(intent);
            }
        });

        btn_searchmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1  = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent1);
            }
        });
        btn_addlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,SearchMap.class);
                startActivity(intent);
            }
        });
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
