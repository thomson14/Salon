package com.example.prashantchaurasia.salon;

import android.app.Service;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder>  {

        private Context context;
        private ArrayList<GetServices> service;
        private ArrayList<GetServices> servicesFull;

        TextView serviceName , ForMaleRate , ForFemaleRate , ForChildMaleRate  , ForchildFemaleRate;


        public ServiceAdapter(Context context , ArrayList<GetServices> service)
        {
            this.context = context;
            this.service = service;
            servicesFull = new ArrayList<>(service);

        }

    public class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(final View itemView){
                super(itemView);


                serviceName = itemView.findViewById(R.id.adapterForServiceName);
                ForMaleRate = itemView.findViewById(R.id.AdapterForMale);
                ForFemaleRate = itemView.findViewById(R.id.AdapterForFemale);
                ForChildMaleRate = itemView.findViewById(R.id.adapterForMaleChild);
                ForchildFemaleRate = itemView.findViewById(R.id.adapterForFemaleChild);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            }

        }


    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_adapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder viewHolder, int i) {

        serviceName.setText(service.get(i).getName());
        ForMaleRate.setText(String.valueOf(service.get(i).getMprice()));
        ForFemaleRate.setText(String.valueOf(service.get(i).getFprice()));
        ForChildMaleRate.setText(String.valueOf(service.get(i).getMcprice()));
        ForchildFemaleRate.setText(String.valueOf(service.get(i).getFcprice()));
        viewHolder.itemView.setTag(service.get(i));

        Log.d("serviceName","serviceName"+ serviceName);

    }

    @Override
    public int getItemCount() {
        return service.size();
    }

  /*  @Override
    public  Filter getFilter()
    {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<GetServices> filteredList  = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0 ){
                filteredList.addAll(servicesFull);
            }else {
                String filteredPattern = charSequence.toString().toLowerCase().trim();

                for (GetServices item : servicesFull){

                    if (item.getServiceName().toLowerCase().contains(filteredPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            service.clear();
            service.addAll((List) filterResults.values);
        }
    };*/


}
