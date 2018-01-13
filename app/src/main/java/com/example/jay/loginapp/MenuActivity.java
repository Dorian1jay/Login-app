package com.example.jay.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jay.loginapp.Helper.ValidateHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by jay on 23/12/17.
 */

public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "MenuActivity";
    private Button btnViewProfile, btnEditProfile,btnContacts,btnSearch,btntest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        // declare buttons and edit text in on create

        btnViewProfile = (Button) findViewById(R.id.btnViewProfile);
        btnEditProfile = (Button) findViewById(R.id.btnEditProfile);
        btnContacts = (Button) findViewById(R.id.btnContacts);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btntest = (Button) findViewById(R.id.btntest);





        btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ViewProfile = new Intent(MenuActivity.this, ViewProfileActivity.class);
                startActivity(ViewProfile);
                //Toast.makeText(getApplicationContext(),"View Profile Button Pressed",Toast.LENGTH_SHORT).show();
            }
        });
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent EditProfile = new Intent(MenuActivity.this, UpdateProfileActivity.class);
                startActivity(EditProfile);
            }
        });
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),"Contacts button pressed",Toast.LENGTH_SHORT).show();
                Intent Contacts = new Intent(MenuActivity.this, ContactActivity.class);
                startActivity(Contacts);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Search = new Intent(MenuActivity.this, SearchmenuActivity.class);
                startActivity(Search);

            }
        });

        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(MenuActivity.this, test.class);
                startActivity(test);

            }
        });

    }

}


