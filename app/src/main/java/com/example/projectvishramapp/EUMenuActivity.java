package com.example.projectvishramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EUMenuActivity extends AppCompatActivity {

    private Button payTMButton, cabButton, grofersButton, practoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eumenu);

        payTMButton = findViewById(R.id.PayTMButton);
        cabButton = findViewById(R.id.CabButton);
        grofersButton = findViewById(R.id.GrofersButton);
        practoButton = findViewById(R.id.PractoButton);

        payTMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUberMenuActivity();
            }
        });

        grofersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        practoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void goToUberMenuActivity(){
        Intent intent = new Intent(this, UberMenuActivity.class);
        startActivity(intent);
    }

}

