package com.flashwiz.flashwizapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.flashwiz.flashwizapp.helpers.StringHelper;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private static final int MY_SOCKET_TIMEOUT_MS = 60000;
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

//    public void processFormFields(){
//        Log.d(TAG, "processFormFields: Validating form fields...");
//        // Check For Errors:
//        if(!validateName() || !validateEmail() || !validatePasswordAndConfirm()){
//            return;
//        }
//        // End Of Check For Errors.
//        Log.d(TAG, "processFormFields: Sending registration request...");
//        // Instantiate The Request Queue:
//
//        Log.d(TAG, "processFormFields: Instantiating RequestQueue...");
//
//
//        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
//        // The URL Posting TO:
//        String url = "http://192.168.1.229:8000/user/save";
//        Log.d(TAG, "processFormFields: Creating StringRequest...");
//        // String Request Object:
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                if(response.equalsIgnoreCase("success")){
//                    name.setText(null);
//                    email.setText(null);
//                    password.setText(null);
//                    confirm.setText(null);
//                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
//                }
//                // End Of Response If Block.
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                System.out.println(error.getMessage());
//                Toast.makeText(SignUpActivity.this, "Registration Un-Successful", Toast.LENGTH_LONG).show();
//                Log.e(TAG, "Error: " + error.getMessage());
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type", "application/json");
//                return headers;
//            }
//
//
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email", email.getText().toString());
//                params.put("password", password.getText().toString());
//                params.put("name", name.getText().toString());
//                Log.d(TAG, params.toString());
//                return params;
//            }
//        };
//
//        // Set the timeout for the request (e.g., 30 seconds):
//        int MY_SOCKET_TIMEOUT_MS = 60000; // 30 seconds
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                MY_SOCKET_TIMEOUT_MS,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        Log.d(TAG, "processFormFields: Adding StringRequest to queue...");
//
//        // End Of String Request Object.
//        queue.add(stringRequest);
//    }


    // End Of Process Form Fields Method.

    public void processFormFields(){
        Log.d(TAG, "processFormFields: Validating form fields...");
        // Check for errors (same logic as before)
        if(!validateName() || !validateEmail() || !validatePasswordAndConfirm()){
            return;
        }

        Log.d(TAG, "processFormFields: Sending registration request...");
        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
        String url = "http://192.168.1.12:8000/user/save"; // Update URL if needed

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, getJsonObjectParams(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.optString("status");
                            if(status != null && status.equalsIgnoreCase("success")){
                                Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(SignUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e(TAG, "Error: " + error.getMessage());
                        Toast.makeText(SignUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };




        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonObjectRequest);
    }

    private JSONObject getJsonObjectParams() {
        JSONObject params = new JSONObject();
        try {
            params.put("email", email.getText().toString());
            params.put("password", password.getText().toString());
            params.put("name", name.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }


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