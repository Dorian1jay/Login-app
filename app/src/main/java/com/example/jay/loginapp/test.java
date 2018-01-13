package com.example.jay.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by jay on 30/12/17.
 */

public class test extends AppCompatActivity{
    private static final String TAG = "Test";
    private Button btntest;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);
        btntest = (Button) findViewById(R.id.btndatatest);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Profiles");


        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // String a = myRef.orderByChild("name").limitToFirst(2);
               // Log.d(TAG, "onClick: "+a);

                Query query = myRef.child("name");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Log.d(TAG, "onDataChange: Inside if");

                            // dataSnapshot is the "issue" node with all children with id 0
                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"
                                String name = issue.getValue().toString();
                                Log.d(TAG, "onDataChange: inside for "+name);
                            }
                        }else{
                            Log.d(TAG, "onDataChange: else");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
    }
}
