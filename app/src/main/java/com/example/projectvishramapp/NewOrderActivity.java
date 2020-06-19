package com.example.projectvishramapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewOrderActivity extends AppCompatActivity {

    private EditText orderName,orderDetails,euName,paymentDetails;
    private Button doneButton;
    private TextView EUPhoneNumberTextView;
    private static final String TAG = "NewOrderActivity";
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    String uid = currentFirebaseUser.getUid();
    String phoneNum = "";
    DatabaseReference ref;
    DatabaseReference EUref;

    Order order;
    PaymentDetail paymentDetail;
    long maxid = 0;
    long maxidForEU = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        orderDetails = findViewById(R.id.EUNewOrderOrderDetails);
        orderName = findViewById(R.id.EUNewOrderOrderName);
        paymentDetails = findViewById(R.id.EUNewOrderPaymentDetails);
        doneButton = findViewById(R.id.NewOrderDoneButton);
        phoneNum = getIntent().getStringExtra("PHONENUMKEY");
        order = new Order();
        paymentDetail = new PaymentDetail();
        ref = FirebaseDatabase.getInstance().getReference().child("users").child("RMs").child(uid).child("orders");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d(TAG, "onCreate: "+phoneNum);
        EUref = FirebaseDatabase.getInstance().getReference().child("users").child("EUs").child(phoneNum).child("orders");
        EUref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxidForEU = (dataSnapshot.getChildrenCount());
                    Log.d(TAG, "onDataChange: "+ maxidForEU);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "putInEU: "+maxidForEU);
                order.setOrderDetails(orderDetails.getText().toString().trim());
                order.setOrderName(orderName.getText().toString().trim());
                order.setPaymentDetails(paymentDetails.getText().toString().trim());
                order.setPhoneNumber(phoneNum);
                ref.child(String.valueOf(maxid+1)).setValue(order);
                Toast.makeText(NewOrderActivity.this, "data inserted succesfully", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onClick: "+uid.trim());
                Log.d(TAG, "onClick:"+ paymentDetails.getText().toString().trim());

                paymentDetail.setPaymentDetails(paymentDetails.getText().toString().trim());
                paymentDetail.setOrderDetails(orderDetails.getText().toString().trim());
                paymentDetail.setOrderName(uid.trim());
                EUref.child(String.valueOf(maxidForEU+1)).setValue(paymentDetail);

            }
        });

    }

    private void putInEU(){

    }



}

