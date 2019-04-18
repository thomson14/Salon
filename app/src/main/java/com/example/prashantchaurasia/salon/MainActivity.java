package com.example.prashantchaurasia.salon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.renderscript.ScriptGroup;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Button btnlogin,btnSignup;
    EditText name,password;
    SharedPreferences.Editor loginPrefsEditor;
    SharedPreferences loginPreferences;
    private Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.edit1);
        password = (EditText) findViewById(R.id.edit2);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnsignUp);

        //sharedPreference
        loginPreferences = this.getSharedPreferences("loginprefs",MODE_PRIVATE);
        saveLogin = loginPreferences.getBoolean("saveLogin",false);

        if(saveLogin)
        {

            startActivity(new Intent(MainActivity.this,Main3Activity.class));

                name.setText(loginPreferences.getString("email",""));
                password.setText(loginPreferences.getString("password",""));
        }else {

        }




        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         //    String   name1 = name.getText().toString();
           //  String pass1 = password.getText().toString();




                Encryption encryption = new Encryption(password.getText().toString());
                String a = encryption.getEncryption();
                password.setText(a);

               // new Thread (new Task()).start();
               // loginPrefsEditor.putBoolean("saveLogin", true);
             //   loginPrefsEditor.putString("uid", name1);
               // loginPrefsEditor.putString("p", pass1);
                //loginPrefsEditor.commit();
                login();
            }


        });


        btnSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });


    }
    public void login(){


        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLHelper.iplogin ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("Response","onResponse" +response);


                        JSONObject jsonObject = null ;
                        try {
                            jsonObject = new JSONObject(response);
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        Log.d("JSON DATA","jjsonDATA" + jsonObject);
                        String error = null;
                        //String message = null;
                        try {
                            error = jsonObject.getString("error");
                          //message = jsonObject.getString("message");
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        Log.d("error", "onResponse: "+error);
                        //Log.d("message","onResponse" + message);
                        boolean flag = error.equals("false") ;
                        Log.d("error", "bool: "+flag);
                        if(flag)
                        {
                            loginPrefsEditor = loginPreferences.edit();
                            loginPrefsEditor.putBoolean("savelogin",true);
                            try {


                                loginPrefsEditor.putString("serve",jsonObject.getString("serve"));
                                loginPrefsEditor.putString("last_login",jsonObject.getString("last_login"));
                                loginPrefsEditor.putString("actdate",jsonObject.getString("actdate"));
                                loginPrefsEditor.putString("expdate",jsonObject.getString("expdate"));
                                loginPrefsEditor.putString("username",jsonObject.getString("username"));
                                loginPrefsEditor.putString("email",jsonObject.getString("email"));
                                loginPrefsEditor.putInt("uid",jsonObject.getInt("user_id"));
                                loginPrefsEditor.putInt("uid",jsonObject.getInt("user_employee"));
                                loginPrefsEditor.apply();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            loginPrefsEditor.apply();
                            Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this ,"Succesfully login!!!",Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(MainActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("on error", "onErrorResponse: "+error.getMessage());

               // Toast.makeText(MainActivity.this,"User login failed",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params =new HashMap<>();
                params.put("uid",name.getText().toString().trim());
                params.put("p",password.getText().toString().trim());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}


