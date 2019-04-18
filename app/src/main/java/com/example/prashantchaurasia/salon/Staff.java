package com.example.prashantchaurasia.salon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Staff extends AppCompatActivity {

    Button btnAddStaff;
    RecyclerView staffRecycler;
    SharedPreferences sharedPreferences;
    RecyclerView.LayoutManager layoutManager;
    EditText SearchStaff;
    SwipeRefreshLayout StaffRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff);

        staffRecycler = findViewById(R.id.recycler_unique);
            sharedPreferences = this.getSharedPreferences("loginprefs",MODE_PRIVATE);
        Log.d("check", "onCreate: "+String.valueOf(sharedPreferences.getInt("uid",-1)));

        SearchStaff = findViewById(R.id.searchView);


        StaffRefresh = findViewById(R.id.swipeToRefreshStaff);

        StaffRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        JsonEmployee();
                        StaffRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });


        btnAddStaff = (Button)  findViewById(R.id.btnAddSTAFF);
        btnAddStaff.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Staff.this,NewEmployees.class);
                startActivity(intent);
            }
        });

        JsonEmployee();
    }


    //JSONDATA FETCH>>>>>>>>
    public void JsonEmployee(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLHelper.URLEmployee, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("ResponseEmployee","response"+response);
                ArrayList<Employee> ArrayEmployee = new ArrayList<>();
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                try{
                    for (int i=0 ; i < jsonArray.length() ; i++ ){
                        try{

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Employee employee = new Employee(
                                    jsonObject.getString("name"),
                                    jsonObject.getString("designation"),
                                    jsonObject.getString("intime"),
                                    jsonObject.getString("outtime"),
                                    jsonObject.getString("weekoff"),
                                    jsonObject.getString("mobile"),
                                    jsonObject.getInt("eid"),
                                    jsonObject.getInt("status"),
                                    jsonObject.getInt("uid")
                            );

                            ArrayEmployee.add(employee);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    staffRecycler.setLayoutManager(new LinearLayoutManager(Staff.this,LinearLayoutManager.VERTICAL,false));
                    StaffAdapter staffAdapter = new StaffAdapter(Staff.this,ArrayEmployee);
                    staffRecycler.setAdapter(staffAdapter);
                    Log.d("serviceAdapter","serviceAdapter"+staffAdapter);

                }catch (Exception e){

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("on error", "onErrorResponse: "+error.getMessage());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("uid",String.valueOf(sharedPreferences.getInt("uid",-1)));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Staff.this);
        requestQueue.add(stringRequest);
    }
}
