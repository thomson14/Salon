package com.example.prashantchaurasia.salon;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ProgrmmingViewHolder> {

    private String[] data;

    public StaffAdapter(String[] data)
    {

        this.data = data;
    }


    @NonNull
    @Override
    public ProgrmmingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater  = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.staff_adapter,viewGroup,false);

        return new ProgrmmingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrmmingViewHolder progrmmingViewHolder, int i) {

        String title = data[i];
        progrmmingViewHolder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;//item count
    }

    public class ProgrmmingViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView textView;


        public ProgrmmingViewHolder(@NonNull View itemView) {
            super(itemView);


            cardView = (CardView) itemView.findViewById(R.id.cardView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
