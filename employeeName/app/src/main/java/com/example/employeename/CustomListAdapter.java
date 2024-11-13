package com.example.employeename;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> name;
    private final ArrayList<String> phone;

    public CustomListAdapter(@NonNull Activity context, ArrayList name, ArrayList phone) {
        super(context, R.layout.mylist, name);

        this.context =context;
        this.name = name;
        this.phone = phone;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.mylist,null,true);

        ImageView imgV = rowView.findViewById(R.id.icon);
        TextView textname= rowView.findViewById(R.id.name);
        TextView textphone= rowView.findViewById(R.id.phone);

        imgV.setImageResource(R.drawable.half);
        textname.setText(name.get(position));
        textphone.setText(phone.get(position));

        return rowView;
    }
}
