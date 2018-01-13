package com.example.jay.loginapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jay.loginapp.Helper.ValidateHelper;
import com.example.jay.loginapp.Models.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jay on 21/12/17.
 */

public class UpdateProfileActivity extends AppCompatActivity {

    private Button mAddtoDB;
    private EditText nametxt;
    private EditText biotxt;
    private EditText stylestext;
    private EditText linkstext;
    private Spinner countiesspin;
    private Spinner instrumentspin;
    private TextView locatationtxt;
    private TextView instrumenttxt;


    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    public void textreset(){
        nametxt.setText("");
        biotxt.setText("");
        stylestext.setText("");
        linkstext.setText("");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        final String TAG = "AddToDatabase";
        setTitle("Edit Profile");

        mAddtoDB = (Button) findViewById(R.id.btnSavetoDB);
        nametxt =(EditText) findViewById(R.id.username);
        biotxt =(EditText) findViewById(R.id.bio);
        stylestext = (EditText) findViewById(R.id.Styles);
        linkstext = (EditText) findViewById(R.id.Links);
        locatationtxt = (TextView) findViewById(R.id.locationtxt) ;
        instrumenttxt = (TextView) findViewById(R.id.Instrumenttxt) ;




        instrumentspin =(Spinner) findViewById(R.id.Instrumentspinner);
        ArrayAdapter<CharSequence> instrumentadapter = ArrayAdapter.createFromResource(this,
                R.array.Instrument_array, android.R.layout.simple_spinner_item);
        instrumentadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instrumentspin.setAdapter(instrumentadapter);
        final String instrument = instrumentspin.getSelectedItem().toString();

         countiesspin = (Spinner) findViewById(R.id.locationspinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Counties_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
       countiesspin.setAdapter(adapter);
       countiesspin.setPrompt("Choose a location");
      final String location = countiesspin.getSelectedItem().toString();

        UserProfile james = new UserProfile("James Byrne","meath","Guitar",
                "Acoustic","im a person","Youtube");




        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();

        mAddtoDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Attempting to add object to database.");


                String name = nametxt.getText().toString();
                String bio = biotxt.getText().toString();
                String Styles = stylestext.getText().toString();
                String Links = linkstext.getText().toString();
                String location = countiesspin.getSelectedItem().toString();
                String instrument = instrumentspin.getSelectedItem().toString();
                Log.d(TAG, "onClick: "+name+Styles+bio+instrument+location);

                ValidateHelper VH = new ValidateHelper();

                if (VH.checkEmpty(name,location,instrument,bio,Styles)== false)
                {

                Toast.makeText(getApplicationContext(),"All all fields must be filled in ",Toast.LENGTH_SHORT).show();
                }else{

                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child("Profiles").child(userID).setValue(userID);
                    myRef.child("Profiles").child(userID).child("name").setValue(name);
                    myRef.child("Profiles").child(userID).child("Location").setValue(location);
                    myRef.child("Profiles").child(userID).child("Instruments").setValue(instrument);
                    myRef.child("Profiles").child(userID).child("Styles").setValue(Styles);
                    myRef.child("Profiles").child(userID).child("Links").setValue(Links);
                    myRef.child("Profiles").child(userID).child("Bio").setValue(bio);
                    Toast.makeText(getApplicationContext(),name+"'s profile details were updated ",Toast.LENGTH_SHORT).show();
                    textreset();

                }




//
//                DatabaseReference usersRef = myRef.child("Profiles");
//                Map<String, UserProfile> users = new HashMap<>();
//                users.put(userID, new UserProfile("James Byrne","meath","Guitar",
//                        "Acoustic","im a person","Youtube"));
//                usersRef.setValue(users);




            }
        });
    }

}
