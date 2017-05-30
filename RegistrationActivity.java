package com.example.admin.vibring.loginregistration;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.admin.vibring.R;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName, edtPassword, edtPhone;
    private Button btnSave, btnView, btncancel;
    private MyDatabase myDatabase;
    private SQLiteDatabase db;
    String name, password, phone;

    //defining AwesomeValidation object
    //private AwesomeValidation awesomeValidation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.registrationfile);


        //findviewbyId
        edtName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        btnSave = (Button) findViewById(R.id.btnuserregister);
        btncancel = (Button) findViewById(R.id.btnusercancel);
        myDatabase = new MyDatabase(RegistrationActivity.this);

        //adding validation to edittexts
        //awesomeValidation.addValidation(this, R.id.edtUserName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        //awesomeValidation.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        //awesomeValidation.addValidation(this, R.id.edtPassword, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passworderror);
        //awesomeValidation.addValidation(this, R.id.edtPhone, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);

btncancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = myDatabase.getWritableDatabase();
                ContentValues cv = new ContentValues();
                // Toast.makeText(RegistrationActivity.this, "Save clicked", Toast.LENGTH_LONG).show();
                if(edtName.getText().toString().isEmpty())
                {
                    edtName.setError("Enter UserName");
                    edtName.setText(" ");
                    edtName.setFocusable(true);
                }
                else
                {
                    name = edtName.getText().toString().trim();
                    cv.put(MyDatabase.NAME, name);
                }
                if(edtPassword.getText().toString().isEmpty())
                {
                    edtPassword.setError("Enter Password");
                    edtPassword.setText(" ");
                    edtPassword.setFocusable(true);
                }
                else
                {
                    password = edtPassword.getText().toString().trim();
                    cv.put(MyDatabase.PASSWORD, password);
                }
                //phone = edtPhone.getText().toString().trim();
                if(edtPhone.getText().toString().isEmpty())
                {
                    edtPhone.setError("Enter Phone");
                    edtPhone.setText(" ");
                    edtPhone.setFocusable(true);
                }
                else
                {
                    phone = edtPhone.getText().toString().trim();
                    cv.put(MyDatabase.PHONE, phone);

                }

                //email = edtEmail.getText().toString().trim();



                // cv.put(MyDatabase.EMAIL, email);
if(name=="")
{
    if(password=="")
    {
        if(phone=="")
            Toast.makeText(RegistrationActivity.this,"Empty values...Please Enter Valid Values",Toast.LENGTH_LONG).show();
    }
}
                else {

    long id = db.insert(MyDatabase.TABLE_NAME, null, cv);

    if (id < 0) {
        //Toast.makeText(RegistrationActivity.this, "Insertion unsuccessful", Toast.LENGTH_LONG).show();
    } else {
        //  Toast.makeText(RegistrationActivity.this, "Insertion Successful", Toast.LENGTH_LONG).show();
        edtName.setText(" ");
        edtPassword.setText(" ");
        edtPhone.setText(" ");
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                break;
            case R.id.btnView:
                Toast.makeText(RegistrationActivity.this, "View clicked", Toast.LENGTH_LONG).show();
                db = myDatabase.getReadableDatabase();
                String[] columns = {MyDatabase.UID, MyDatabase.NAME, MyDatabase.PASSWORD, MyDatabase.PHONE};
                Cursor cursor = db.query(MyDatabase.TABLE_NAME, columns, null, null, null, null, null);
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    int index1 = cursor.getColumnIndex(MyDatabase.UID);
                    int index2 = cursor.getColumnIndex(MyDatabase.NAME);
                    int index3 = cursor.getColumnIndex(MyDatabase.PASSWORD);
                    int index4 = cursor.getColumnIndex(MyDatabase.PHONE);
                    // int index5 = cursor.getColumnIndex(MyDatabase.EMAIL);

                    int uid = cursor.getInt(index1);
                    name = cursor.getString(index2);
                    password = cursor.getString(index3);
                    phone = cursor.getString(index4);
                    //email = cursor.getString(index5);

                    buffer.append(uid + " " + name + " " + password + " " + phone + "\n");
                }
                Toast.makeText(RegistrationActivity.this, buffer.toString(), Toast.LENGTH_LONG).show();

                break;
            case R.id.btnUpdate:
                Toast.makeText(RegistrationActivity.this, "Update clicked", Toast.LENGTH_LONG).show();
                db = myDatabase.getWritableDatabase();
                ContentValues cv1 = new ContentValues();
                cv1.put(MyDatabase.NAME, "Vishal Kumar");
                String whereClause1 = MyDatabase.NAME + "=?";
                String[] whereArgs1 = {"vishal"};
                int u = db.update(MyDatabase.TABLE_NAME, cv1, whereClause1, whereArgs1);
                Toast.makeText(RegistrationActivity.this, "No.of rows updated = " + u, Toast.LENGTH_LONG).show();
                break;
            case R.id.btnDelete:
                Toast.makeText(RegistrationActivity.this, "Delete clicked", Toast.LENGTH_LONG).show();
                db = myDatabase.getWritableDatabase();
                String whereClause = MyDatabase.NAME + "=?";
                String[] whereArgs = {"Ravi"};
                int d = db.delete(MyDatabase.TABLE_NAME, whereClause, whereArgs);
                Toast.makeText(RegistrationActivity.this, "No. of Rows deleted = " + d, Toast.LENGTH_LONG).show();
                break;
        }
        //Intent intent = new Intent(RegistrationActivity.this, LocationActivity.class);
        // startActivity(intent);

    }
}