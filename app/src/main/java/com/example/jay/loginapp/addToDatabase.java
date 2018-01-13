package com.example.jay.loginapp;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;






/**
 * Created by jay on 15/10/17.
 */

public class addToDatabase extends AppCompatActivity {

    private Button mAddtoDB;
    private EditText madd_new_first_name;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additemtodatabaselayout);

        final String TAG = "AddToDatabase";

        mAddtoDB = (Button) findViewById(R.id.btnAddtoDB);
        madd_new_first_name =(EditText) findViewById(R.id.add_new_first_name);



        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
            }

        };
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
//
//                DataSnapshot UIDSnapshot = dataSnapshot.child("UserIDs");
//                Iterable<DataSnapshot> UIDChildren = UIDSnapshot.getChildren();
//                for (DataSnapshot uid : UIDChildren) {
//                    String s = uid.getValue(String.class);
//                    Log.d("uid","uid is "+ s);
//                    DataSnapshot uName = dataSnapshot.child(s);
//
//
//                    Log.d("uid_name_test",uName.child("name").getValue().toString() );
//                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        mAddtoDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Attempting to add object to database.");
                String newName = madd_new_first_name.getText().toString();
                if(!newName.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child("Profiles").child(userID).setValue(userID);
                    myRef.child("Profiles").child(userID).child("name").setValue(newName);
                    myRef.child("Profiles").child(userID).child("Location").setValue("Dublin");
                    myRef.child("Profiles").child(userID).child("Instrument").setValue("guitar");
                    myRef.child("Profiles").child(userID).child("Style").setValue("Blues");
                    myRef.child("Profiles").child(userID).child("Experience").setValue("5 years");



                    //reset the text
                    madd_new_first_name.setText("");



                }
            }
        });


// ...
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
