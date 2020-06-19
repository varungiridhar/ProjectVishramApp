package com.example.projectvishramapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ListAdapter extends RecyclerView.Adapter {


    private Context mcontext;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_itemeu,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return OurData.RMNames.length;
    }


    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView RMNameET;
        private TextView RMPaymentDetailsET;
        private TextView RMOrderDetailsET;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            RMNameET = itemView.findViewById(R.id.RMNameET);
            RMPaymentDetailsET = itemView.findViewById(R.id.RMPaymentDetailsET);
            RMOrderDetailsET = itemView.findViewById(R.id.RMOrderDetailsET);
            itemView.setOnClickListener(this);


        }

        public void bindView(int position){
            RMNameET.setText(OurData.RMNames[position]);
            RMPaymentDetailsET.setText(OurData.PaymentDetails[position]);
            RMOrderDetailsET.setText(OurData.OrderDetails[position]);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
