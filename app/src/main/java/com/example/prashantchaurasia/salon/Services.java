package com.example.prashantchaurasia.salon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Services extends AppCompatActivity {

    Button btnAddServices;
    RecyclerView recyclerView;
    RecyclerView.Adapter serviceAdapter;
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor servicePrefsEditor;
    SharedPreferences servicesPreferences;
    Boolean saveServices;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        EditText Search = findViewById(R.id.searchText);



        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        btnAddServices = findViewById(R.id.btnAddServices);

        recyclerView = findViewById(R.id.ServiceRecycle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        servicesPreferences= this.getSharedPreferences("serviceprefs",MODE_PRIVATE);
        saveServices = servicesPreferences.getBoolean("saveServices",false);


        sharedPreferences = this.getSharedPreferences("loginprefs",MODE_PRIVATE);
        Log.d("check", "onCreate: "+String.valueOf(sharedPreferences.getInt("uid",-1)));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       JSONDATA();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        btnAddServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Services.this,AddServices.class);
                startActivity(intent);
            }
        });
        //JSON METHOD    **String Request**
        JSONDATA();

    }




    public void JSONDATA(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLHelper.URLServices, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("ResponseInteger","response"+response);

                ArrayList<GetServices> service = new ArrayList<>();
                JSONArray jsonArray = null;
                try{
                    jsonArray = new JSONArray(response);
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                try{
                    for(int i = 0 ; i < jsonArray.length();i++){

                        servicePrefsEditor = servicesPreferences.edit();
                        servicePrefsEditor.putBoolean("saveServices",true);

                        try{
                            Log.d("wow", "onResponse: wow");
                            JSONObject JsonService =jsonArray.getJSONObject(i);
                            GetServices getServices = new GetServices(
                                    JsonService.getString("name"),
                                    JsonService.getInt("sid"),
                                    JsonService.getInt("uid"),
                                    JsonService.getInt("mprice"),
                                    JsonService.getInt("mcprice"),
                                    JsonService.getInt("fcprice"),
                                    JsonService.getInt("fprice")
                            );

                            Log.d("getString","getString"+getServices.getName());
                            service.add(getServices);
                            //Log.d("ArraylistService","ArrayListService"+service);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    serviceAdapter = new ServiceAdapter(getApplicationContext(),service);
                    recyclerView.setAdapter(serviceAdapter);
                    // Log.d("serviceAdapter","serviceAdapter"+recyclerView);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("on error", "onErrorResponse: "+error.getMessage());
            }
        })
        {
            protected Map<String, String> getParams(){

                Map<String,String> params = new HashMap<>();

                params.put("uid",String.valueOf(sharedPreferences.getInt("uid",-1)));
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(Services.this);
        requestQueue.add(stringRequest);
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_services,menu);

        MenuItem searchItem  = menu.findItem(R.id.Search_Services);
         // SearchView searchViews = (SearchView) searchItem.getActionView();
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) searchItem.getActionView();

       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String s) {

            // serviceAdapter.getFilter().filter(s);
               return false;
           }
       });

        return true;
    }*/
}



