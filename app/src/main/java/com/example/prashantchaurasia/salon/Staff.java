package com.example.prashantchaurasia.salon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class Staff extends AppCompatActivity {

    Button btnAddStaff;
    RecyclerView programmingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff);


        programmingList = (RecyclerView) findViewById(R.id.recyler);
        programmingList.setLayoutManager(new LinearLayoutManager(this));
        String[] languages = {"javascrip","C","java","Python","C++","C#","PHP"};
        programmingList.setAdapter(new StaffAdapter(languages));
        programmingList.setHasFixedSize(true);

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

    }
}
