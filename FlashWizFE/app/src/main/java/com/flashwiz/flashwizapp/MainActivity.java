package com.flashwiz.flashwizapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button sign_in, sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void goToSignIn(View view){
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        MainActivity.this.startActivity(intent);
        finish();
    }
    public void goToSignUp(View view){
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        MainActivity.this.startActivity(intent);
        finish();
    }




}