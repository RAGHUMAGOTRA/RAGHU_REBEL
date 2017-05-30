package com.example.admin.vibring.loginregistration;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.vibring.mainscreen.MainActivity;
import com.example.admin.vibring.R;

public class LoginActivity extends AppCompatActivity {
    private EditText edtName, edtPass;
    private TextView txtleft, error;
    private Button btn_Login;
    private Button btn_Register;
    String name,phone,password;
    private MyDatabase myDatabase;
    private SQLiteDatabase db;
    int attemptscounter=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //findviewbyId
        edtName = (EditText) findViewById(R.id.edtName);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btn_Login = (Button) findViewById(R.id.btnLogin);
        btn_Register = (Button) findViewById(R.id.btnRegister);
            error=(TextView)findViewById(R.id.counter);
        myDatabase = new MyDatabase(LoginActivity.this);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= edtName.getText().toString().trim();
                if(username.isEmpty())
                {
                    edtName.setError("Enter UserName");
                    edtName.setText(" ");
                    edtName.setFocusable(true);
                }
                String userpassword= edtPass.getText().toString().trim();
                if(userpassword.isEmpty()){
                        edtPass.setError("Enter Password");
                    edtPass.setText(" ");
                     edtPass.setFocusable(true);
                }
                db = myDatabase.getReadableDatabase();
                //String[] columns = {MyDatabase.UID, MyDatabase.NAME, MyDatabase.PASSWORD, MyDatabase.PHONE};
                String[] columns = {MyDatabase.NAME, MyDatabase.PASSWORD};
                Cursor cursor = db.query(MyDatabase.TABLE_NAME, columns, null, null, null, null, null);
                //StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    //int index1 = cursor.getColumnIndex(MyDatabase.UID);
                    int index2 = cursor.getColumnIndex(MyDatabase.NAME);
                    int index3 = cursor.getColumnIndex(MyDatabase.PASSWORD);
                    //int index4 = cursor.getColumnIndex(MyDatabase.PHONE);
                    // int index5 = cursor.getColumnIndex(MyDatabase.EMAIL);

                    //int uid = cursor.getInt(index1);
                    name = cursor.getString(index2);
                    password = cursor.getString(index3);
                    //phone = cursor.getString(index4);
                    //email = cursor.getString(index5);

                            if ((username.equals(name))&&(userpassword.equals(password))) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                error.setText(String.valueOf(attemptscounter));
                                attemptscounter--;
                                edtName.setText("");
                                error.setError("Enter Username");
                                error.setError("Enter Username");
                                edtPass.setText("");
                                edtName.setFocusable(true);
                                if(attemptscounter<0)
                                {
                                    error.setText("BLOCKED... Wait for some time");

                                }

                            }
                        }


            }
        });


        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(), RegistrationActivity.class);
                startActivity(in);
            }
        });
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//
//            case R.id.btnView:
//                Toast.makeText(LoginActivity.this, "View clicked", Toast.LENGTH_LONG).show();
//                db = myDatabase.getReadableDatabase();
//                String[] columns = {MyDatabase.UID, MyDatabase.NAME, MyDatabase.PASSWORD, MyDatabase.PHONE};
//                Cursor cursor = db.query(MyDatabase.TABLE_NAME, columns, null, null, null, null, null);
//                StringBuffer buffer = new StringBuffer();
//                while (cursor.moveToNext()) {
//                    int index1 = cursor.getColumnIndex(MyDatabase.UID);
//                    int index2 = cursor.getColumnIndex(MyDatabase.NAME);
//                    int index3 = cursor.getColumnIndex(MyDatabase.PASSWORD);
//                    int index4 = cursor.getColumnIndex(MyDatabase.PHONE);
//                    // int index5 = cursor.getColumnIndex(MyDatabase.EMAIL);
//
//                    int uid = cursor.getInt(index1);
//                    name = cursor.getString(index2);
//                    password = cursor.getString(index3);
//                    phone = cursor.getString(index4);
//                    //email = cursor.getString(index5);
//
//                    buffer.append(uid + " " + name + " " + password + " " + phone + "\n");
//                }
//                Toast.makeText(LoginActivity.this, buffer.toString(), Toast.LENGTH_LONG).show();
//
//                break;
//        }


    }
