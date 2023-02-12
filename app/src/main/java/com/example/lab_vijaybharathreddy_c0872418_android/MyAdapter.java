package com.example.lab_vijaybharathreddy_c0872418_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<LocationList> locationlist;
    Context context;


    public MyAdapter(Context context, List<LocationList> loctionslist) {
        this.context = context ;

        this.locationlist =  loctionslist;

    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylceview_cell ,null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LocationList LoList = locationlist.get(position);
        holder.locationName.setText(LoList.getLocation_name());


    }

    @Override
    public int getItemCount() {
        return locationlist.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView locationName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           locationName  = itemView.findViewById(R.id.locationcell);

        }

    }

}
