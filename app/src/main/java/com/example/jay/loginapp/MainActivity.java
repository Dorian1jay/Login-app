package com.example.jay.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jay on 18/12/17.
 */

public class MainActivity extends AppCompatActivity {
    private Button loginbtn,signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstscreen);

        loginbtn = (Button) findViewById(R.id.loginbtn);
        signupbtn = (Button) findViewById(R.id.signupbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent enterlogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(enterlogin);
            }

        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent entersignup = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(entersignup);

            }

        });
    }
}
