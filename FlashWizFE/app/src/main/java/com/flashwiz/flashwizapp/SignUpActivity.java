package com.flashwiz.flashwizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.flashwiz.flashwizapp.helpers.StringHelper;


import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText name, email, password, confirm;
    Button sign_up_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Hook Edit Text Fields:
        name  = findViewById(R.id.name);
        email       = findViewById(R.id.email);
        password    = findViewById(R.id.password);
        confirm     = findViewById(R.id.confirm);

        // Hook Sign Up Button:
        sign_up_btn = findViewById(R.id.sign_up_btn);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processFormFields();
            }
        });
    }
    // End Of On Create Method.

    public void goToHome(View view){
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToSigInAct(View view){
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public void processFormFields(){
        // Check For Errors:
        if(!validateName() || !validateEmail() || !validatePasswordAndConfirm()){
            return;
        }
        // End Of Check For Errors.

        // Instantiate The Request Queue:
        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
        // The URL Posting TO:
        String url = "http://localhost:8000/user/register";



        // String Request Object:
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equalsIgnoreCase("success")){
                    name.setText(null);
                    email.setText(null);
                    password.setText(null);
                    confirm.setText(null);
                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                }
                // End Of Response If Block.

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(SignUpActivity.this, "Registration Un-Successful", Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name.getText().toString());
                params.put("email", email.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };// End Of String Request Object.

        queue.add(stringRequest);
    }
    // End Of Process Form Fields Method.


    public boolean validateName(){
        String Name = name.getText().toString();
        // Check If Name Is Empty:
        if(Name.isEmpty()){
            name.setError("Name cannot be empty!");
            return false;
        }else{
            name.setError(null);
            return true;
        }// Check If Name Is Empty.
    }
    // End Of Validate Name Field.


    public boolean validateEmail(){
        String email_e = email.getText().toString();
        // Check If Email Is Empty:
        if(email_e.isEmpty()){
            email.setError("Email cannot be empty!");
            return false;
        }else if(!StringHelper.regexEmailValidationPattern(email_e)){
            email.setError("Please enter a valid email");
            return false;
        }else{
            email.setError(null);
            return true;
        }// Check If Email Is Empty.
    }
    // End Of Validate Email Field.

    public boolean validatePasswordAndConfirm(){
        String password_p = password.getText().toString();
        String confirm_p = confirm.getText().toString();

        // Check If Password and Confirm Field Is Empty:
        if(password_p.isEmpty()){
            password.setError("Password cannot be empty!");
            return false;
        }else if (!password_p.equals(confirm_p)){
            password.setError("Passwords do not match!");
            return false;
        }else if(confirm_p.isEmpty()){
            confirm.setError("Confirm field cannot be empty!");
            return false;
        }else{
            password.setError(null);
            confirm.setError(null);
            return true;
        }// Check Password and Confirm Field Is Empty.
    }
    // End Of Validate Password and Confirm Field.

}
// End Of Sign UP Activity Class.