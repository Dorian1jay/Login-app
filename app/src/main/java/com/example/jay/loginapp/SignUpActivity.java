package com.example.jay.loginapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.jay.loginapp.Helper.ValidateHelper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by jay on 19/12/17.
 */

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    private EditText mEmail, mPassword, mPasswordConfirm;
    private Button btnSignIn;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        // declare buttons and edit text in on create
        mEmail = (EditText) findViewById(R.id.remail);
        mPassword = (EditText) findViewById(R.id.rpassword);
        mPasswordConfirm = (EditText) findViewById(R.id.rpassword2);
        btnSignIn = (Button) findViewById(R.id.register_sign_in_button);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String password2 = mPasswordConfirm.getText().toString();
                mAuth = FirebaseAuth.getInstance();

                ValidateHelper VH = new ValidateHelper();


                if(VH.checkEmpty(email,password,password2) == false)
                {
                    Toast.makeText(getApplicationContext(),"All fields must be filled in.",Toast.LENGTH_SHORT).show();
                }else if(VH.validatePassword(password,password2) == false)
                {
                    Toast.makeText(getApplicationContext(),"Password confirmation must match password.",Toast.LENGTH_SHORT).show();
                }else if(VH.isValidEmail(email) == false){
                    Toast.makeText(getApplicationContext(),"Invaild email entered",Toast.LENGTH_SHORT).show();
                }else{

                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                                Toast.makeText(getApplicationContext(),"User Successfully created",Toast.LENGTH_SHORT).show();

                        }
                    });

                }



                //Toast.makeText(getApplicationContext(),"Sign Up Button pressed "+email+" "+password,Toast.LENGTH_SHORT).show();
            }
       });


    }
}
