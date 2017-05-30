package com.example.admin.vibring.activatevibring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.vibring.R;

public class ActivateVibring extends AppCompatActivity {
Button activate,deactivate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_vibring);
        activate=(Button)findViewById(R.id.activatevibring);
        deactivate=(Button)findViewById(R.id.deactivatevibring);
        deactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivateVibring.this,"Deactivated",Toast.LENGTH_SHORT).show();
            }
        });
        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivateVibring.this,"Activated",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
