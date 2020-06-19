package com.example.projectvishramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MakeProfileActivity extends AppCompatActivity {

    private CheckBox mNewOrderCheck, mCompletedOrdersCheck, mPendingOrdersCheck, mAllOrdersCheck;
    private Button sendButton;
    private ArrayList<String> mResult;
    private static final String TAG = "MakeProfileActivity";
    String phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);

        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        phoneNum = getIntent().getStringExtra("key");

        Log.d(TAG, "onCreate: "+ phoneNum);

        mNewOrderCheck = findViewById(R.id.check_newOrder);
        mCompletedOrdersCheck = findViewById(R.id.check_completedOrders);
        mPendingOrdersCheck = findViewById(R.id.check_pendingOrders);
        mAllOrdersCheck = findViewById(R.id.check_allOrders);

        sendButton = findViewById(R.id.makeProfileButton);
        mResult = new ArrayList<>();

        mNewOrderCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNewOrderCheck.isChecked()) {
                    mResult.add("newOrders");

                    mCompletedOrdersCheck.setChecked(false);
                    mPendingOrdersCheck.setChecked(false);
                    mAllOrdersCheck.setChecked(false);


                } else {
                    mResult.remove("newOrders");
                }
            }
        });

        mCompletedOrdersCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCompletedOrdersCheck.isChecked()) {
                    mResult.add("completedOrders");

                    mNewOrderCheck.setChecked(false);
                    mPendingOrdersCheck.setChecked(false);
                    mAllOrdersCheck.setChecked(false);
                } else {
                    mResult.remove("completedOrders");
                }
            }
        });

        mAllOrdersCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAllOrdersCheck.isChecked()) {
                    mResult.add("allOrders");

                    mCompletedOrdersCheck.setChecked(false);
                    mPendingOrdersCheck.setChecked(false);
                    mNewOrderCheck.setChecked(false);
                } else {
                    mResult.remove("allOrders");
                }
            }
        });

        mPendingOrdersCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPendingOrdersCheck.isChecked()) {
                    mResult.add("pendingOrders");

                    mCompletedOrdersCheck.setChecked(false);
                    mNewOrderCheck.setChecked(false);
                    mAllOrdersCheck.setChecked(false);
                } else {
                    mResult.remove("pendingOrders");
                }
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String compareString = new String();
                for (String s : mResult) {
                    compareString = s;

                    if (compareString.equals("newOrders")) {


                        Intent intent = new Intent(MakeProfileActivity.this, NewOrderActivity.class);
                        intent.putExtra("PHONENUMKEY", phoneNum);
                        startActivity(intent);


                    } else {
                        if (compareString.equals("completedOrders")) {


                        } else {
                            if (compareString.equals("allOrders")) {
                                Intent intent = new Intent(MakeProfileActivity.this, NewOrderActivity.class);
                                String phoneNum = getIntent().getStringExtra("key");
                                intent.putExtra("", phoneNum);

                                Log.d(TAG, "onClick: allOrders");


                            } else {
                                if (compareString.equals("pendingOrders")) {
                                    Intent intent = new Intent(MakeProfileActivity.this, NewOrderActivity.class);
                                    String phoneNum = getIntent().getStringExtra("key");
                                    intent.putExtra("", phoneNum);
                                    startActivity(intent);

                                    Log.d(TAG, "onClick: pendingOrders");


                                }
                            }
                        }
                    }
                }

                //all compare statements here:


            }

        });
    }
    private void intentFunction_NewOrderActivity(){

    }
}
