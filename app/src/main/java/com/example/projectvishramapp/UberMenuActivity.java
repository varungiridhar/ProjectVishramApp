package com.example.projectvishramapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UberMenuActivity extends AppCompatActivity {

    private EditText destinationAddressEditText;
    private Button requestCabButton;
    private static final String TAG = "MainActivity";
    private String destinationAddress;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uber_menu);
        requestCabButton = findViewById(R.id.RequestCabButton);
        requestCabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                putEUReuqest();


            }
        });
    }

    private void putEUReuqest(){
        destinationAddressEditText = findViewById(R.id.DestinationAddressEditText);
        destinationAddress =  destinationAddressEditText.getText().toString();

        /* DATABASE STRUCTURE:
           EURequests
           ---UID
           ------email
           ------name
           ------phone_num
           ------request_type(ex: cabRequest)
           ------destination_name(ex: Casio Hypershopee)
        */

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String uid = currentFirebaseUser.getUid();
        Log.d(TAG, "putDataInRMsAvaliable: "+uid);

        final DatabaseReference EURequestsRef = database.getInstance().getReference("EURequests").child(uid);

        //GETTING name AND PUTTING IT IN EURequests

        DatabaseReference eunameref = database.getInstance().getReference("users").child("EUs").child(uid).child("name");

        eunameref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: "+dataSnapshot.getValue());

                //set value in RMsAvaliable
                EURequestsRef.child("name").setValue(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //GETTING phone_num AND PUTTING IT IN EURequests

        DatabaseReference euphoennumref = database.getInstance().getReference("users").child("EUs").child(uid).child("phone_num");

        euphoennumref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: "+dataSnapshot.getValue());

                //set value in RMsAvaliable
                EURequestsRef.child("phone_num").setValue(dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //PUTTING request_type IN EURequests
        EURequestsRef.child("request_type").setValue("cabRequest");
        //PUTTING destination_name IN EURequests
        EURequestsRef.child("destination_address").setValue(destinationAddress);




        //FUNCTIONS COMPLETED


    }





}
