package com.example.prashantchaurasia.salon;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class QuickBill extends AppCompatActivity implements AdapterView.OnItemSelectedListener ,View.OnClickListener {

    Spinner spinnerServices,spinnerFor,spinnerStaff,spinnerQuantity,spinnerMode,spinnerBillStatus;
    Button btnBillforSale,onAddFieldButton,QuickBillSubmit;
    EditText edtDate;
    Spinner ForSecond, ServicesSecond;
    TextView RateSecond;
    SharedPreferences loginPreferences,servicesPreferences;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private LinearLayout linearLayout;
    SharedPreferences sharedPreferences;
    TextView RateService;
    RecyclerView QuickRecycler;

    Spinner DialogService,  DialogFor, DialogStaff,DialogQuantity;
    TextView DialogRATE,DialogTotal;
    EditText DialogDiscount;


    ArrayList<String> str = new ArrayList<>();
    ArrayList<Integer> Mprice= new ArrayList<>();
    ArrayList<Integer> Fprice = new ArrayList<>();
    ArrayList<String> Staffname = new ArrayList<>();


    //String[] spinnerfor = {};
   // String[] services = {};
   // String[] staff  = {};
    String[] quantity = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    String[] mode = {};
    String[] billStatus = {};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_bill);



        QuickRecycler = (RecyclerView) findViewById(R.id.QuickBillRecycle);
        onAddFieldButton = findViewById(R.id.addServices);


        sharedPreferences = this.getSharedPreferences("loginprefs",MODE_PRIVATE);
        Log.d("check", "onCreate: "+String.valueOf(sharedPreferences.getInt("uid",-1)));


        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
       loginPreferences = this.getSharedPreferences("loginprefs",MODE_PRIVATE);
        String[] spinnerfor = loginPreferences.getString("serve"," ").split(",");
        final ArrayList<String> Fora = new ArrayList<>() ;
        for(String s:spinnerfor){
            Fora.add(s);
        }


             btnBillforSale = (Button)  findViewById(R.id.btnBillforSale);
             edtDate = (EditText)  findViewById(R.id.edtdate);
             edtDate.setOnClickListener(this);

             //  BUTTON FOR BILL FOR SALE
        btnBillforSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuickBill.this,BillForSale.class);
                startActivity(intent);
            }
        });

        final ArrayList<GetServices> quickArraylist = new ArrayList<>();
        final QuickBillAdapter quickBillAdapter = new QuickBillAdapter(this,quickArraylist);
        QuickRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        QuickRecycler.setAdapter(quickBillAdapter);

        onAddFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(QuickBill.this);
                dialog.setContentView(R.layout.quick_bill_second);
                dialog.setTitle("Custom Dialog");
                 DialogService = dialog.findViewById(R.id.Servicespinner2);
                 DialogFor = dialog.findViewById(R.id.Forspinner2);
                 DialogStaff = dialog.findViewById(R.id.spinnerStaff);
                 DialogQuantity = dialog.findViewById(R.id.spinnerQuantity);
                 DialogRATE = dialog.findViewById(R.id.RateService2);
                 DialogDiscount = dialog.findViewById(R.id.Discount);
                 DialogTotal =dialog.findViewById(R.id.Total);
                 QuickBillSubmit = dialog.findViewById(R.id.QuickBill_Submit);
                QuickBillSubmit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        GetServices getServices = new GetServices(DialogService.getSelectedItem().toString(),
                                DialogFor.getSelectedItem().toString(),
                                DialogStaff.getSelectedItem().toString(),1,
                                //Integer.valueOf(DialogQuantity.getSelectedItem().toString()),
                                Integer.valueOf(DialogRATE.getText().toString()),
                                100,//Integer.valueOf(DialogTotal.getText().toString()),
                                23.0);//Double.valueOf(DialogDiscount.getText().toString()));
                        quickArraylist.add(getServices);
                        quickBillAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                ArrayAdapter arrayAdapter = new ArrayAdapter(QuickBill.this, android.R.layout.simple_spinner_item, str);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                DialogService.setAdapter(arrayAdapter);
                DialogService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (!Mprice.get(i).equals(str.get(i))) {
                            DialogRATE.setText(String.valueOf(Mprice.get(i)));
                            Log.d("mpricessss", "mpricess" + Mprice.get(i));
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(QuickBill.this,android.R.layout.simple_spinner_item,Fora);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                DialogFor.setAdapter(arrayAdapter2);
                DialogFor.setOnItemSelectedListener(QuickBill.this);



                ArrayAdapter arrayAdapterStaff = new ArrayAdapter(QuickBill.this,android.R.layout.simple_spinner_item,Staffname);
                arrayAdapterStaff.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                DialogStaff.setAdapter(arrayAdapterStaff);
                DialogStaff.setOnItemSelectedListener(QuickBill.this);


                ArrayAdapter arrayAdapter3 = new ArrayAdapter(QuickBill.this,android.R.layout.simple_spinner_item,quantity);
                arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                DialogQuantity.setAdapter(arrayAdapter3);
                DialogQuantity.setOnItemSelectedListener(QuickBill.this);
                dialog.show();
            }
        });

        /* ****************************************************************************************** */
        //Service Data(JSONDATA)

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLHelper.URLServices, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("QuickBillResponse","hurrreeeee"+response);
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
                        try{
                            Log.d("wow", "onResponse: wow");
                            JSONObject JsonService =jsonArray.getJSONObject(i);
                            str.add(JsonService.getString("name"));
                            Mprice.add(JsonService.getInt("mprice"));
                            Fprice.add(JsonService.getInt("fprice"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                        //Adapter FOR SERVICES
                        ArrayAdapter arrayAdapter = new ArrayAdapter(QuickBill.this, android.R.layout.simple_spinner_item, str);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerServices.setAdapter(arrayAdapter);
                        spinnerServices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                TextView textView = (TextView)adapterView.getChildAt(0);
                                textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                                if (!Mprice.get(i).equals(str.get(i))) {
                                    RateService.setText(String.valueOf(Mprice.get(i)));
                                    Log.d("mpricessss", "mpricess" + Mprice.get(i));
                                }

                                else if (!Fprice.get(i).equals(Fora.get(i))){
                                    RateService.setText(String.valueOf(Fprice.get(i)));
                                }
                                if (str.equals(Fora)){

                                    RateService.setText(String.valueOf(Fprice.get(i)));
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    Log.d("RateServices","RateServices"+RateService);
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
        RequestQueue requestQueue = Volley.newRequestQueue(QuickBill.this);
        requestQueue.add(stringRequest);

        //END OF SERVICE DATA

        /* ****************************************************************************************** */

        //STAFF DATA (EMPLOYEE)

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URLHelper.URLEmployee, new Response.Listener<String>() {
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
                            // str.add(JsonService.getString("name"));
                            Staffname.add(jsonObject.getString("name"));

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    //ADAPTER FOR STAFF
                     spinnerStaff = (Spinner) findViewById(R.id.spinnerStaff);
                    ArrayAdapter arrayAdapterStaff = new ArrayAdapter(QuickBill.this,android.R.layout.simple_spinner_item,Staffname);
                    arrayAdapterStaff.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerStaff.setAdapter(arrayAdapterStaff);
                    spinnerStaff.setOnItemSelectedListener(QuickBill.this);
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

        RequestQueue requestQueue1 = Volley.newRequestQueue(QuickBill.this);
        requestQueue1.add(stringRequest1);

        //END OF STAFF DATA
        /* ****************************************************************************************** */



        //ADAPTER FOR GENDER




     /*   //ADAPTER FOR MODE
        spinnerMode = (Spinner) findViewById(R.id.spinnerMode);
        ArrayAdapter arrayAdapter4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mode);
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMode.setAdapter(arrayAdapter4);
        spinnerMode.setOnItemSelectedListener(this);

        //ADAPTER FOR BillStatus
        spinnerBillStatus = (Spinner) findViewById(R.id.spinnerBillStatus);
        ArrayAdapter arrayAdapter5 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,billStatus);
        arrayAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBillStatus.setAdapter(arrayAdapter5);
        spinnerBillStatus.setOnItemSelectedListener(this);*/

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView)parent.getChildAt(0);
        textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    //FOR DATE IN EDITTEXT
    @Override
    public void onClick(View v)
    {
        if (v == edtDate)
        {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            edtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}
