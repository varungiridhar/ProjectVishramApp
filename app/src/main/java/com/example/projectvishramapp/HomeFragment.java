package com.example.projectvishramapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomeFragment extends Fragment{
    private Button euassistancebutton;
    private static final String TAG = "HomeFragment";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    View view;
    String phoneNum;
    String euName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        phoneNum = this.getArguments().getString("key").toString();

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button euassistancebutton = (Button) view.findViewById(R.id.EUAssistanceButton);
        euassistancebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "getAssistance: ran");


                DatabaseReference EURequests = database.getInstance().getReference();

                try
                {
                    EURequests.child("users").child("EUs").child(phoneNum).child("name").setValue("varun");
                }
                // doesn't matches with ArithmeticException
                catch(Exception ex)
                {
                    Log.d(TAG, "onClick: exception: "+ ex);
                }
            }
        });


        return view;
    }



}
