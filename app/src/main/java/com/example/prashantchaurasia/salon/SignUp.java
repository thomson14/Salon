package com.example.prashantchaurasia.salon;

import android.content.Intent;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {


    EditText regName , regEmail , regPassword , regMobile;
    Button regButton;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

      /*  regName = (EditText) findViewById(R.id.regName);
        regEmail = (EditText) findViewById(R.id.regEmail);
        regPassword = (EditText) findViewById(R.id.regPassword);
        regMobile = (EditText) findViewById(R.id.regMobile);
        regButton = (Button) findViewById(R.id.regButton);

        requestQueue = Volley.newRequestQueue(SignUp.this);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        UserRegistration();


            }
        });

    }

public void UserRegistration()
        {
           StringRequest stringRequest = new StringRequest(Request.Method.POST, URLHelper.ipregister, new Response.Listener<String>(){
               @Override
               public void onResponse(String response) {
                   JSONObject jsonObject = null ;
                   try {
                       jsonObject = new JSONObject(response);
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
                   String error = null;
                   String message = null;
                   try {
                       error = jsonObject.getString("error");
                       message = jsonObject.getString("message");
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
                   Log.d("error", "onResponse: "+error);
                   Log.d("message","onResponse" + message);

                   if(error == ("false")){
                       Intent intent = new Intent(SignUp.this,MainActivity.class);
                       startActivity(intent);
                       Toast.makeText(SignUp.this,"Successfully Registerd!!!!",Toast.LENGTH_SHORT).show();

                   }else {

                       Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                   }


               }
           }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {


            Log.d("on error", "onErrorResponse: "+error.getMessage());
            Toast.makeText(SignUp.this,"User registraion failed",Toast.LENGTH_SHORT).show();
        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            Map<String,String> params =new HashMap<>();
            params.put("name",regName.getText().toString().trim());
            params.put("email",regEmail.getText().toString().trim());
            params.put("password",regPassword.getText().toString().trim());
            params.put("mobile",regMobile.getText().toString().trim());
            return params;
            //return super.getParams();
        }
    };

    RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);
    requestQueue.add(stringRequest);*/
 }

}
