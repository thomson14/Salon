package com.example.prashantchaurasia.salon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class QuickBill extends AppCompatActivity implements AdapterView.OnItemSelectedListener ,View.OnClickListener {

    Spinner spinnerServices,spinnerFor,spinnerStaff,spinnerQuantity,spinnerMode,spinnerBillStatus;
    Button btnBillforSale;
    Button btnDate;
    EditText edtDate;
    private int mYear, mMonth, mDay, mHour, mMinute;


    String[] spinnerfor = {};
    String[] services = {};
    String[] staff  = {};
    String[] quantity = {};
    String[] mode = {};
    String[] billStatus = {};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_bill);

             spinnerServices = (Spinner) findViewById(R.id.spinner1);
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



        //Adapter FOR SERVICES
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,services);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServices.setAdapter(arrayAdapter);
        spinnerServices.setOnItemSelectedListener(this);


        //ADAPTER FOR GENDER
         spinnerFor = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerfor);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFor.setAdapter(arrayAdapter1);
        spinnerFor.setOnItemSelectedListener(this);

        //ADAPTER FOR STAFF
        spinnerStaff = (Spinner) findViewById(R.id.spinnerStaff);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,staff);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStaff.setAdapter(arrayAdapter2);
        spinnerStaff.setOnItemSelectedListener(this);


        //ADAPTER FOR QUANTITY
        spinnerQuantity = (Spinner) findViewById(R.id.spinnerQuantity);
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,quantity);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuantity.setAdapter(arrayAdapter3);
        spinnerQuantity.setOnItemSelectedListener(this);

        //ADAPTER FOR MODE
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
        spinnerBillStatus.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {




        Spinner spinnerServices = (Spinner)parent;
        Spinner spinnerFor = (Spinner)parent;
        Spinner spinnerStaff = (Spinner)parent;
        Spinner spinnerQuantity = (Spinner)parent;
        Spinner spinnerMode = (Spinner)parent;
        Spinner spinnerBillStatus = (Spinner)parent;


        if(spinnerServices.getId() == R.id.spinner1)
        {
            Toast.makeText(this, "Your choose :" + services[position],Toast.LENGTH_SHORT).show();
        }
        if(spinnerFor.getId() == R.id.spinner2)
        {
            Toast.makeText(this, "Your choose :" + spinnerfor[position],Toast.LENGTH_SHORT).show();
        }

        if(spinnerStaff.getId() == R.id.spinnerStaff)
        {
            Toast.makeText(this, "Your choose :" + staff[position],Toast.LENGTH_SHORT).show();
        }
        if(spinnerQuantity.getId() == R.id.spinnerQuantity)
        {
            Toast.makeText(this, "Your choose :" + quantity[position],Toast.LENGTH_SHORT).show();
        }

        if(spinnerMode.getId() == R.id.spinnerMode)
        {
            Toast.makeText(this, "Your choose :" + mode[position],Toast.LENGTH_SHORT).show();
        }

        if(spinnerBillStatus.getId() == R.id.spinnerBillStatus)
        {
            Toast.makeText(this, "Your choose :" + billStatus[position],Toast.LENGTH_SHORT).show();
        }





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
