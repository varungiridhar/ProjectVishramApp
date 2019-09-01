package com.example.projectvishramapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RMMenuActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private TextView EUData;

    private static final String TAG = "RMMenuActivity";
    private ArrayList<String>  mPhoneNumbers = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmmenu);

        //calling the methods
/*
        putDataInRMsAvaliable();
*/
        getEUPhoneNumbers();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        Log.d(TAG, "onCreate: started");
        initPhoneNumbers();


        putInRMs();

        Log.d(TAG, "onCreate: ");



    }

    private void getEUPhoneNumbers(){

        DatabaseReference euref = database.getInstance().getReference("EURequests").child("phoneNumber");//get EURequests


        euref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //this method is called initially and when the data in this child is updated
                Log.d(TAG, "onDataChange: "+ dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /*private void putDataInRMsAvaliable(){
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String uid = currentFirebaseUser.getUid();
        Log.d(TAG, "putDataInRMsAvaliable: "+uid);

        final DatabaseReference RMsAvaliableRef = database.getInstance().getReference("RMsAvaliable").child(uid);

        //GETTING NAME AND PUTTING IT IN RMsAvaliable

        DatabaseReference rmnameref = database.getInstance().getReference("users").child("RMs").child(uid).child("name");

        rmnameref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: "+dataSnapshot.getValue());

                //set value in RMsAvaliable
                RMsAvaliableRef.child("name").setValue(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //GETTING PHONENUM AND PUTTING IT IN RMsAvalaible

        DatabaseReference rmphoennumref = database.getInstance().getReference("users").child("RMs").child(uid).child("phone_num");

        rmphoennumref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: "+dataSnapshot.getValue());

                //set value in RMsAvaliable
                RMsAvaliableRef.child("phone_num").setValue(dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //FUNCTION COMPLETED

    }*/

    private void initPhoneNumbers(){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users").child("EUs");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //validation check for deleted values shoud occur.
                //$When value is changed on database, the app crashes$
                Log.d(TAG, "onDataChange: "+ dataSnapshot.getValue().toString());
                addPhoneNumbers(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



    }

    private void addPhoneNumbers(String phoneListString) {
        Log.d(TAG, "addPhoneNumbers:started ");

        String[] phoneListArr = phoneListString.split(",");

        for(int i = 0; i<phoneListArr.length; i++){
            phoneListArr[i] = phoneListArr[i].replace("{", "");
            String[] phoneNum = phoneListArr[i].split("=");
            String phoneNumString = phoneNum[0];
            Log.d(TAG, "addPhoneNumbers: "+ phoneNumString);
            mPhoneNumbers.add(phoneNumString);
        }




        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mPhoneNumbers, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void putInRMs(){


        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String uid = currentFirebaseUser.getUid();

        DatabaseReference RMsRef = database.getInstance().getReference();
        String phoneNum = getIntent().getStringExtra("RMMenuActivityKey");


        RMsRef.child("users").child("RMs").child(uid).child("phonenumber").setValue(phoneNum);


    }






}
