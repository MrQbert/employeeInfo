package com.example.employeename;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText fname;
    EditText lname;
    EditText phone;
    EditText email;
    SQLiteDatabase db;
    StringBuilder sb = new StringBuilder();
    AlertDialog.Builder Builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.fname);
        lname= findViewById(R.id.lname);
        phone= findViewById(R.id.phone);
        email= findViewById(R.id.email);

        db = openOrCreateDatabase("Wright",MODE_PRIVATE, null);
        db.execSQL("create table if not exists employee (EmpLname varchar(50),EmpFname varchar(50),EmpPhone varchar(25),EmpEmail varchar(25));");
        sb.append("Hello World\n");
        sb.append("This is Rob");


    }
    public void Save(View v){
        String fnameString = fname.getText().toString().trim();
        String lnameString = lname.getText().toString().trim();
        String phoneString = phone.getText().toString().trim();
        String emailString= email.getText().toString().trim();

        // Checking if the name is over 50 characters if it is return an error
        if(fnameString.length() > 50 || fnameString.length() == 0 || lnameString.length() > 50 || lnameString.length() == 0
                || phoneString.length() > 25 || phoneString.length() == 0 || emailString.length() >25 || emailString.length() ==0){
            sb.delete(0,sb.length());
            sb.append("One of the field is empty\nFix the Entries");
            ShowMessage("Error",sb.toString());
        }
        db.execSQL("insert into employee(EmpLname, EmpFname, EmpPhone, EmpEmail) Values('" + lnameString + "','" + fnameString + "','" + phoneString + "','"
                + emailString + "');");
        ShowMessage("Success","You Entered a record");
        ClearText();

    }

    public void toSearch(View v){
        Intent i = new Intent(this, activity_query.class);
        startActivity(i);
    }

    public void toSearchAll(View v){
        Intent i = new Intent(this,activity_search_all.class);
        startActivity(i);
    }
    public void ClearText(){
        lname.setText("");
        fname.setText("");
        phone.setText("");
        email.setText("");
        lname.requestFocus();

    }
    public void ShowMessage(String title, String message){
        AlertDialog.Builder messBr = new AlertDialog.Builder(this);
        messBr.setCancelable(true);
        messBr.setTitle(title);
        messBr.setMessage(message);
        messBr.show();
    }
}