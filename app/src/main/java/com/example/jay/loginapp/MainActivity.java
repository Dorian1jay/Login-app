package com.example.jay.loginapp;

//loginapp

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // ui REFERENCES
    private EditText mEmail, mPassword;
    private Button btnSignIn, btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // declare buttons and edit text in on create
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
        btnSignOut = (Button) findViewById(R.id.email_sign_out_button);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with: "+ user.getEmail());
                    Toast.makeText(getApplicationContext(),"Successfully signed in",Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");

                }Toast.makeText(getApplicationContext(),"Successfully signed Out",Toast.LENGTH_SHORT).show();
                // ...
            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if(!email.equals("") && !password.equals(""))
                {
                    mAuth.signInWithEmailAndPassword(email,password);
                }else{
                    Toast.makeText(getApplicationContext(),"You need to fill in all the fields",Toast.LENGTH_SHORT).show();
                }
            }

           


        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(getApplicationContext(),"Signing Out",Toast.LENGTH_SHORT).show();

            }
        });
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
    // 325 0f video
    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT);

    }
}
