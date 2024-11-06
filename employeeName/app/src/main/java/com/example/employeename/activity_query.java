package com.example.employeename;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class activity_query extends AppCompatActivity {

    SQLiteDatabase db;
    EditText lname, fname,phone, email, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        db= openOrCreateDatabase("Wright", MODE_PRIVATE, null);
        db.execSQL("create table if not exists employee (EmpLname varchar(50),EmpFname varchar(50),EmpPhone varchar(25),EmpEmail varchar(25));");
        lname = findViewById(R.id.lname);
        fname = findViewById(R.id.fname);
        phone = findViewById(R.id.phone);
        email= findViewById(R.id.email);
        id = findViewById(R.id.empID);
    }

    public void findEmployee(View v){
        Cursor c = db.rawQuery("select * from employee where ROWID="+id.getText().toString()+";",null);
        if(c.getCount()==0){
            ShowMessage("Not Found","Employee ID Not Found");
            return;
        }
        c.moveToNext();

        lname.setText(c.getString(0));
        fname.setText(c.getString(1));
        phone.setText(c.getString(2));
        email.setText(c.getString(3));
    }

    public void ShowMessage(String title, String message){
        AlertDialog.Builder messBr = new AlertDialog.Builder(this);
        messBr.setCancelable(true);
        messBr.setTitle(title);
        messBr.setMessage(message);
        messBr.show();
    }

}