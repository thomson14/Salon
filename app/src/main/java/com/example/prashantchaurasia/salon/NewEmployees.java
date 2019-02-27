package com.example.prashantchaurasia.salon;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class NewEmployees extends AppCompatActivity implements AdapterView.OnItemSelectedListener ,View.OnClickListener {


        AutoCompleteTextView autoWeekly;
        Button btnSubmit,btnClose;
        EditText edtOutTime,edtInTime;

        private int mYear, mMonth, mDay, mHour, mMinute;


        String[] autoCompleteweekly = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employees);
        autoWeekly = (AutoCompleteTextView) findViewById(R.id.autoWeekly);
        btnSubmit = (Button)  findViewById(R.id.btnSubmit);
        btnClose = (Button) findViewById(R.id.btnClosed);


        edtInTime = (EditText) findViewById(R.id.edtInTime);
        edtInTime.setOnClickListener(this);
        edtOutTime = (EditText) findViewById(R.id.edtOutTime);
        edtOutTime.setOnClickListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,autoCompleteweekly);

        //Getting the instance of AutoCompleteTextView

      // autoWeekly.showDropDown();

        autoWeekly.setThreshold(1);//will start working from first character
        autoWeekly.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        autoWeekly.setTextColor(Color.WHITE);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



       btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        //IN TIME
        if (v == edtInTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            edtInTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        //OUT TIME
        if (v == edtOutTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            edtOutTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }


    }
}