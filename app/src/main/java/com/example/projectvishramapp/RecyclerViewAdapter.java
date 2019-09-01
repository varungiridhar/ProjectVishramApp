package com.example.projectvishramapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mPhoneNumbers = new ArrayList<>();
    private Context mcontext;


    public RecyclerViewAdapter(ArrayList<String> mPhoneNumbers, Context mcontext) {
        this.mPhoneNumbers = mPhoneNumbers;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.phone_number.setText(mPhoneNumbers.get(position));

        holder.phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCallingView(mPhoneNumbers.get(position));
            }
        });
        holder.makeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMakeProfileActivity(mPhoneNumbers.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPhoneNumbers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView phone_number;
        RelativeLayout parent_layout;
        TextView makeProfileButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            phone_number = itemView.findViewById(R.id.phone_number);
            parent_layout = itemView.findViewById(R.id.parent_layout);
            makeProfileButton = itemView.findViewById(R.id.makeProfileButton);

        }
    }
    private void getCallingView(String phoneNum){
        Intent i = new Intent(Intent.ACTION_DIAL);
        String p = "tel:" + phoneNum;
        i.setData(Uri.parse(p));
        mcontext.startActivity(i);
    }

    private void getMakeProfileActivity(String phoneNum){


        Intent intent = new Intent(mcontext, MakeProfileActivity.class);

        intent.putExtra("key", phoneNum);
        mcontext.startActivity(intent);
    }

}
