package com.example.projectvishramapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EUMenuActivity extends AppCompatActivity {
    private static final String TAG = "EUMenuActivity";
    String phoneNum;


//
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eumenu);

        phoneNum = "+918861958661";
        Log.d(TAG, "onCreate: "+ phoneNum);
        sendDataToHomeFragment();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_eu);
        bottomNav.setOnNavigationItemSelectedListener(navListener);




    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_help:
                            selectedFragment = new HelpFragment();
                            sendDataToHelpFragment();
                            break;
                        case R.id.nav_money:
                            selectedFragment = new MoneyFragment();
                            sendDataToMoneyFragment(phoneNum);
                            break;
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            sendDataToHomeFragment();


                            break;
                    }



                    return true;
                }
            };
//
//    private void getAssistance(){
//        Log.d(TAG, "getAssistance: ran");
//        String phoneNum = getIntent().getStringExtra("EUMenuActivityKey");
//        String euName = getIntent().getStringExtra("EUNAMEKEY");
//        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
//        String uid = currentFirebaseUser.getUid();
//
//        DatabaseReference EURequests = database.getInstance().getReference();
//
//
//        EURequests.child("users").child("EUs").child("+9999999999").child("name").setValue(euName);
//
//
//
//
//
//
//
//
//
//    }
    private void sendDataToMoneyFragment(String phoneNum){

        Log.d(TAG, "sendDataToFragment: "+phoneNum);

        Bundle bundle = new Bundle();
        bundle.putString("key", phoneNum);

        MoneyFragment moneyFragment = new MoneyFragment();
        moneyFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, moneyFragment).commit();
    }
    private void sendDataToHomeFragment(){


        Bundle bundle = new Bundle();
        bundle.putString("key", phoneNum);

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
    }
    private void sendDataToHelpFragment(){


        Bundle bundle = new Bundle();
        HelpFragment helpFragment = new HelpFragment();
        helpFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, helpFragment).commit();
    }

}

