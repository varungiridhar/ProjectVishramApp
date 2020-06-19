package com.example.projectvishramapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MoneyFragment extends Fragment{
    String phoneNum;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    int noOfChildren = 0;
    public ArrayList<String> mRMName = new ArrayList<>();
    public ArrayList<String> mRMPaymentDetails = new ArrayList<>();
    public ArrayList<String> mRMOrderDetails = new ArrayList<>();
    RecyclerView recyclerView;
    ListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_money, container, false);
        phoneNum = this.getArguments().getString("key").toString();

//        OurData.paymentDetails.clear();

        recyclerView = (RecyclerView) view.findViewById(R.id.RecycleView_EU);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);


        getNoOfChildren(view);


        return view;
    }


    private void getNoOfChildren(final View view) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.d(TAG, "getNoOfChildren: "+phoneNum);
        DatabaseReference noOfChildrenRef = database.getReference("users").child("EUs").child(phoneNum).child("orders");
        noOfChildrenRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildAdded: "+dataSnapshot);
                noOfChildren = (int) dataSnapshot.getChildrenCount();
                initRMDetails(noOfChildren, view);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initRMDetails(int noOfChildren,final View view){
        Log.d(TAG, "initRMDetails: run");
        for(int i = 1; i<=noOfChildren; i++) {

            final DatabaseReference ref = database.getReference().child("users").child("EUs").child(phoneNum).child("orders").child(String.valueOf(i));
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot!=null) {
                        String dataSnapshotStr = (String) dataSnapshot.getValue().toString();
                        parseDatasnapshot(dataSnapshotStr, view);
                    }else{
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }
    }

    private void parseDatasnapshot(String dataSnapshotStr,View view){
        Log.d(TAG, "parseDatasnapshot: run");

        dataSnapshotStr = dataSnapshotStr.replace("{", "");
        dataSnapshotStr = dataSnapshotStr.replace("}", "");

        String[] elements = dataSnapshotStr.split(",");
        Log.d(TAG, "parseDatasnapshot: "+elements.length);
        for (int a = 0; a < elements.length; a++) {
            if (a == 0) {
                String paymentDetails = (elements[a].split("="))[1];
//                OurData.paymentDetails.add(paymentDetails);
                Log.d(TAG, "parseDatasnapshot: "+paymentDetails);
            } else {
                if (a == 1) {
                    String RMuid = (elements[a].split("="))[1];
//                    OurData.RMNames.add(RMuid);
                    Log.d(TAG, "parseDatasnapshot: "+RMuid);
                }
            }
        }

//        Log.d(TAG, "parseDatasnapshot: OurData = "+OurData.paymentDetails);
        setupRecyclerView(view);
    }

    private void setupRecyclerView(View view){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }


}
