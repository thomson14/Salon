package com.example.prashantchaurasia.salon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Staff extends AppCompatActivity {

    Button btnAddStaff;
    RecyclerView programmingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff);


        programmingList = (RecyclerView) findViewById(R.id.recyler);
        programmingList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Employee> employees = new ArrayList<>();
        Bundle bundle = getIntent().getBundleExtra("bundle");
        Employee employee =  bundle.getParcelable("employee");
        employees.add(employee);

        programmingList.setAdapter(new StaffAdapter(employees));

        programmingList.setHasFixedSize(true);

        btnAddStaff = (Button)  findViewById(R.id.btnAddSTAFF);

        btnAddStaff.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(Staff.this,NewEmployees.class);
                startActivity(intent);


           /*  Bundle bundle = getIntent().getExtras();

             String EmployeeName = bundle.getString("employeename");
             String ContactMobile = bundle.getString("contactmobile");
             String disgnation = bundle.getString("disignation");


             employeeName.getText(String.valueOf(EmployeeName));
             MoblieNumber.getText(String.valueOf(ContactMobile));
             Designation.getText(String.valueOf(disgnation));*/

            }
        });

    }
}
