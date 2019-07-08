package com.example.projectvishramapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private static final String TAG = "MainActivity";
    private String rmname;
    private String rmphonenumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmmenu);

        //calling the methods
        putDataInRMsAvaliable();
        getEURequestsData();






    }

    private void getEURequestsData(){

        DatabaseReference euref = database.getInstance().getReference("EURequests").child("");//get EURequests


        euref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //this method is called initially and when the data in this child is updated
                Log.d(TAG, "onDataChange: "+ dataSnapshot);
                parseEURequests(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void putDataInRMsAvaliable(){
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

    }

    private void parseEURequests(DataSnapshot dataSnapshot){

        Object ds = dataSnapshot.getValue();
        String dsString = ds.toString();
        Log.d(TAG, "parseEURequests: "+ dsString);
        dsString = dsString.replace("{", " ");
        dsString = dsString.replace("}", " ");
        dsString = dsString.replace(",", "");
        String[] dsChildren= dsString.split("\\s");
        for(int i = 0; i<dsChildren.length; i++){
            Log.d(TAG, "parseEURequests: "+dsChildren[i]);
        }








    }

    private void showData(DataSnapshot dataSnapshot) {


    }




}
