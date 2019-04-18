package com.example.prashantchaurasia.salon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddServices extends AppCompatActivity {

    EditText ServicesName,MaleRs,FemaleRs,MaleChildRs,FemaleChildRs;
    Button btnSubmit,btnClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);

        ServicesName = findViewById(R.id.ServiceName);
        MaleRs = findViewById(R.id.MaleRS);
        FemaleRs = findViewById(R.id.FemaleRs);
        MaleChildRs = findViewById(R.id.MaleChildRs);
        FemaleChildRs = findViewById(R.id.FemaleChildRs);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnClose = findViewById(R.id.btnClosed);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
