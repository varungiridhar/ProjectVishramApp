package com.example.projectvishramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewOrderActivity extends AppCompatActivity {

    private EditText orderName;
    private EditText orderDetails;
    private EditText euName;
    private EditText paymentDetails;
    private Button doneButton;
    private DatabaseReference mDatabase;
    private TextView EUPhoneNumberTextView;
// ...
    private static final String TAG = "NewOrderActivity";
    FirebaseDatabase database = FirebaseDatabase.getInstance();


// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        EUPhoneNumberTextView = findViewById(R.id.EUPhoneNumberTextView);



        doneButton = findViewById(R.id.NewOrderDoneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putNewOrder();
            }
        });

    }

    private void putNewOrder(){
        String phoneNum = getIntent().getStringExtra("newOrderKey");

        euName = findViewById(R.id.EUNewOrderEUName);
        orderDetails = findViewById(R.id.EUNewOrderOrderDetails);
        orderName = findViewById(R.id.EUNewOrderOrderName);
        paymentDetails = findViewById(R.id.EUNewOrderPaymentDetails);


        //recording order in EUs
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child("EUs").child(phoneNum).child("Orders");

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String uid = currentFirebaseUser.getUid();

        String EUPhoneNumber = getIntent().getStringExtra("newOrderKey");


        DatabaseReference RMsAvaliableRef = database.getInstance().getReference().child("users").child("RMs").child(uid).child("Orders").child(euName.getText().toString());
        RMsAvaliableRef.child("OrderDetails").setValue(orderDetails.getText().toString());
        RMsAvaliableRef.child("orderName").setValue(orderName.getText().toString());
        RMsAvaliableRef.child("EUPhoneNumber").setValue(EUPhoneNumber);
        RMsAvaliableRef.child("PaymentDetails").setValue(paymentDetails.getText().toString());






        //recording order in RM


    }

}
