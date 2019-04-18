package com.example.prashantchaurasia.salon;

import android.content.Context;
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

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder> {


    private TextView employeeName,InTimeStaff,OutTimeSatff,MobileNumber,WeeklyStaff,Designation;
    private  ArrayList<Employee> data;
    private Context context;

    public StaffAdapter( Context context,ArrayList<Employee> data) {
        this.context = context;
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(final View itemView){
            super(itemView);


            employeeName = itemView.findViewById(R.id.employeeName1);
            MobileNumber = itemView.findViewById(R.id.mobileNumber);
            Designation = itemView.findViewById(R.id.Designation);
            InTimeStaff = itemView.findViewById(R.id.InTimeStaff);
            OutTimeSatff = itemView.findViewById(R.id.OutTimeStaff);
            WeeklyStaff = itemView.findViewById(R.id.WeeklyOff);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

    }

    @NonNull
    @Override
    public StaffAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.staff_adapter,viewGroup,false);
        return new StaffAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffAdapter.ViewHolder viewHolder, int i) {

        employeeName.setText(data.get(i).getName());
        MobileNumber.setText(data.get(i).getMobile());
        Designation.setText(data.get(i).getDesignation());
        InTimeStaff.setText(data.get(i).getIntime());
        OutTimeSatff.setText(data.get(i).getOuttime());
        WeeklyStaff.setText(data.get(i).getWeekoff());
        viewHolder.itemView.setTag(data.get(i));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
