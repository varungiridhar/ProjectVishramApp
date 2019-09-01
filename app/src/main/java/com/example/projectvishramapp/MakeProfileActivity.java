package com.example.projectvishramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class MakeProfileActivity extends AppCompatActivity {

    private CheckBox mNewOrderCheck, mCompletedOrdersCheck, mPendingOrdersCheck, mAllOrdersCheck;
    private Button sendButton;
    private ArrayList<String> mResult;
    private static final String TAG = "MakeProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);

        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");


        mNewOrderCheck = findViewById(R.id.check_newOrder);
        mCompletedOrdersCheck = findViewById(R.id.check_completedOrders);
        mPendingOrdersCheck = findViewById(R.id.check_pendingOrders);
        mAllOrdersCheck = findViewById(R.id.check_allOrders);

        sendButton = findViewById(R.id.makeProfileButton);
        mResult = new ArrayList<>();

        mNewOrderCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNewOrderCheck.isChecked()){
                    mResult.add("newOrders");
                }else{
                    mResult.remove("newOrders");
                }
            }
        });

        mCompletedOrdersCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNewOrderCheck.isChecked()){
                    mResult.add("completedOrders");
                }else{
                    mResult.remove("completedOrders");
                }
            }
        });

        mAllOrdersCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNewOrderCheck.isChecked()){
                    mResult.add("allOrders");
                }else{
                    mResult.remove("allOrders");
                }
            }
        });

        mPendingOrdersCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNewOrderCheck.isChecked()){
                    mResult.add("pendingOrders");
                }else{
                    mResult.remove("pendingOrders");
                }
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String compareString = new String();
                for (String s : mResult){
                    compareString = s;
                    Log.d(TAG, "onClick: "+ compareString);
                    
                    if(compareString.equals("newOrders")){
                        Intent intent = new Intent(MakeProfileActivity.this, NewOrderActivity.class);
                        String phoneNum = getIntent().getStringExtra("key");
                        intent.putExtra("newOrderKey", phoneNum);
                        startActivity(intent);




                    }else{
                        if(compareString.equals("newOrders")){
                            Intent intent = new Intent(MakeProfileActivity.this, NewOrderActivity.class);
                            String phoneNum = getIntent().getStringExtra("key");
                            intent.putExtra("newOrderKey", phoneNum);
                            startActivity(intent);




                        }
                    }

                    //all compare statements here:










                }

            }
        });
    }
}
