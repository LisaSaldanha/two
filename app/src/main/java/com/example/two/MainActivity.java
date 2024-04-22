package com.example.two;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageGalleryAdapter2 adapter;
    RecyclerView recyclerView;
    ClickListener listener;
    FloatingActionButton fab;
    String name;
    String date;
    String time;
    String location;

    List<announceData> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        list = getData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listener = new ClickListener() {
            @Override
            public void click(int index) {
                Toast.makeText(MainActivity.this, "clicked item index is " + index, Toast.LENGTH_LONG).show();
            }
        };
        adapter = new ImageGalleryAdapter2(list, MainActivity.this, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        //added
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.additem);
                final EditText editText1 = dialog.findViewById(R.id.editText1);
                final EditText editText2 = dialog.findViewById(R.id.editText2);
                final EditText editText3 = dialog.findViewById(R.id.editText3);
                final EditText editText4 = dialog.findViewById(R.id.editText4);
                Button btnUpload = dialog.findViewById(R.id.btnUpload);

                btnUpload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        name = editText1.getText().toString();
                        date = editText2.getText().toString();
                        time = editText3.getText().toString();
                        location = editText4.getText().toString();

                        announceData lvItem = new announceData(name, date, time, location);
                        list.add(lvItem);

                        dialog.dismiss();

                        ImageGalleryAdapter2 contactAdapter = new ImageGalleryAdapter2(list, MainActivity.this, listener);

                        recyclerView.setAdapter(contactAdapter);
                    }
                });

                dialog.show();
            }
        });


    }


    // Sample data for RecyclerView
    private List<announceData> getData() {
        List<announceData> list = new ArrayList<>();
        list.add(new announceData("Self Defense Course",
                "03 March 2024, Sunday",
                "4-6 p.m.",
                "Panjim Bus Stand"));
        list.add(new announceData("First Aid Certification Course",
                "25 February 2024, Sunday",
                "10 a.m.-12 noon",
                "Vasco Community Hall"));
        return list;
    }

}





