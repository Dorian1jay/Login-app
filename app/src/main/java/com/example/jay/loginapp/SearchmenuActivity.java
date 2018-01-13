package com.example.jay.loginapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jay on 29/12/17.
 */

public class SearchmenuActivity extends AppCompatActivity{
    private Button btninstrument,btnlocation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_menu);
        btninstrument = (Button)findViewById(R.id.BtnInstrumentsrc);
        btnlocation = (Button)findViewById(R.id.btnLocationsrc);

        btninstrument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"search by instrument",Toast.LENGTH_SHORT).show();
            }
        });
        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"search by location",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
