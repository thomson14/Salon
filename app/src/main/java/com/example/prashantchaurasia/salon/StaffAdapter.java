package com.example.prashantchaurasia.salon;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ProgrmmingViewHolder> {

    CardView cardView;
    TextView employeeNameDemo,InTimeStaffDemo,MobileNumberDemo,OutTimeStafDemo,WeeklyStaffDemo,DisignationDemo;
    TextView employeeName,InTimeStaff,OutTimeSatff,MobileNumber,WeeklyStaff,Designation;
    Button   btnActive,btnInActive;
    private  ArrayList<Employee> data;

    public StaffAdapter(ArrayList<Employee> data)
    {

        this.data = data;

    }


    @NonNull
    @Override
    public ProgrmmingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater layoutInflater  = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.staff_adapter,viewGroup,false);
        return new ProgrmmingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrmmingViewHolder progrmmingViewHolder, int i)
    {
        Employee title = data.get(i);
        employeeName.setText(title.getName());

        MobileNumber.setText(title.getMobile());

        Designation.setText(title.getDesignation());

        InTimeStaff.setText(title.getInTime());

        OutTimeSatff.setText(title.getOutTime());

        WeeklyStaff.setText(title.getOffDay());




    }

    @Override
    public int getItemCount()

    {
        return data.size();
    }

    public class ProgrmmingViewHolder extends RecyclerView.ViewHolder{





        public ProgrmmingViewHolder(@NonNull View itemView) {
            super(itemView);


            cardView = (CardView) itemView.findViewById(R.id.cardView);

            employeeName = (TextView) itemView.findViewById(R.id.employeeName);
            MobileNumber = (TextView) itemView.findViewById(R.id.mobileNumber);
            InTimeStaff = (TextView) itemView.findViewById(R.id.InTimeStaff);
            OutTimeSatff = (TextView) itemView.findViewById(R.id.OutTimeStaff);
            WeeklyStaff = (TextView) itemView.findViewById(R.id.WeeklyOff);
            Designation = itemView.findViewById(R.id.Designation);

            btnActive = itemView.findViewById(R.id.btnActive);
            btnInActive = itemView.findViewById(R.id.btnInActive);

        }
    }

}
