package com.example.lab_vijaybharathreddy_c0872418_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerview;
    ArrayList<LocationList> location_list = new ArrayList<>();
    MyAdapter MyAdapter;

    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveBtn =findViewById(R.id.savebtn);
        recyclerview = findViewById(R.id.Listview);
        MyAdapter = new MyAdapter(MainActivity.this, location_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(MyAdapter);
        InsertData();




    }

    private void InsertData() {
        LocationList location = new LocationList("new",27.2,28.4);
        location_list.add(location);
        MyAdapter adapter = new MyAdapter( this, location_list);

        recyclerview.setAdapter(adapter);
    }
    public void uponClick(View view){

        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
        startActivity(intent);

    }



}


