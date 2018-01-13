package com.example.jay.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jay.loginapp.Models.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by jay on 01/01/18.
 */

public class ViewOtherProfileActivity extends AppCompatActivity {

    private static final String TAG = "ViewOtherProfile";
    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String CurrentUserID;
    private String userID;
    private ListView mListView;
    private Button btnsend;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewotherprofile);

        mListView = (ListView) findViewById(R.id.listviewotherprofile);
        btnsend = (Button) findViewById(R.id.btnsendmessage);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = getIntent().getExtras().getString("uid");



        final String CurrentUserID = user.getUid();


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                   // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with: "+ user.getEmail());
                   // Toast.makeText(getApplicationContext(),"Successfully signed in",Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                   // Log.d(TAG, "onAuthStateChanged:signed_out");

                }//Toast.makeText(getApplicationContext(),"Successfully signed Out",Toast.LENGTH_SHORT).show();
                // ...
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                Object value = dataSnapshot.getValue();
//                Log.d(TAG, "Value is: " + value);

                showData(dataSnapshot);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent test = new Intent(ViewOtherProfileActivity.this, ChatActivity.class);
                test.putExtra("otheruid",userID);
                startActivity(test);

            }
        });



    }
    private void showData(DataSnapshot dataSnapshot) {


        dataSnapshot = dataSnapshot.child("Profiles");
        UserProfile uInfo = new UserProfile();
        uInfo.setName(dataSnapshot.child(userID).getValue(UserProfile.class).getName()); //set the name

        uInfo.setLocation(dataSnapshot.child(userID).getValue(UserProfile.class).getLocation());//set the email
        uInfo.setInstruments(dataSnapshot.child(userID).getValue(UserProfile.class).getInstruments()); //set the phone_num

        uInfo.setStyles(dataSnapshot.child(userID).getValue(UserProfile.class).getStyles());
        uInfo.setBio(dataSnapshot.child(userID).getValue(UserProfile.class).getBio());
        uInfo.setLinks(dataSnapshot.child(userID).getValue(UserProfile.class).getLinks());



        //display all the information

        ArrayList<String> array  = new ArrayList<>();
        array.add("Name : "+uInfo.getName());
        array.add("Location : "+uInfo.getLocation());
        array.add("Instrument : "+uInfo.getInstruments());
        array.add("Styles : "+uInfo.getStyles());
        array.add("Bio : "+uInfo.getBio());
        array.add("Links :"+uInfo.getLinks());
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        mListView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //add a toast to show when successfully signed in
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
