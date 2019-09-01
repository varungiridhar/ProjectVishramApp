package com.example.projectvishramapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EUMenuActivity extends AppCompatActivity {

    private Button euassistancebutton;
    private static final String TAG = "EUMenuActivity";
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eumenu);


        euassistancebutton = findViewById(R.id.EUAssistanceButton);

        euassistancebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAssistance();
            }
        });
    }

    private void getAssistance(){
        Log.d(TAG, "getAssistance: ran");
        String phoneNum = getIntent().getStringExtra("EUMenuActivityKey");
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String uid = currentFirebaseUser.getUid();

        DatabaseReference RMsAvaliableRef = database.getInstance().getReference();

        phoneNum = phoneNum+"#";

        RMsAvaliableRef.child("users").child("EUs").child("+919841660366").child("uid").setValue(uid);







    }

}

