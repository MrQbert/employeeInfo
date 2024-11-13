package com.example.employeename;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class activity_search_all extends AppCompatActivity {

    ListView list;
    SQLiteDatabase db;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> phone = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_all);

        db= openOrCreateDatabase("Wright", MODE_PRIVATE, null);
        db.execSQL("create table if not exists employee (EmpLname varchar(50),EmpFname varchar(50),EmpPhone varchar(25),EmpEmail varchar(25));");
        ViewAll();
 // populate array with db

        CustomListAdapter adapter = new CustomListAdapter(this,name,phone);
        list = findViewById(R.id.list);
        list.setEmptyView(findViewById(R.id.empty));
        list.setAdapter(adapter);
//        list.setOnItemClickListener(new gust();
    }
    public void ViewAll(){
        Cursor c = db.rawQuery("select EmpLname, EmpFname, EmpPhone from employee",null);
        if(c.getCount()==0){
            ShowMessage("No Employees Found", "Employee Table is Empty");
            return;
        }
        while(c.moveToNext()){
            name.add(c.getString(0)+" "+c.getString(1));
            phone.add(c.getString(2));
        }
    }

    public void ShowMessage(String title, String message){
        AlertDialog.Builder messBr = new AlertDialog.Builder(this);
        messBr.setCancelable(true);
        messBr.setTitle(title);
        messBr.setMessage(message);
        messBr.show();
    }

    // MAKING A PHONE CALL
    public class gust implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel: " + phone.get(i)));
            startActivity(intent);
        }

        @Override

    }

}