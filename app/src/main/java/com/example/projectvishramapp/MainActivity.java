package com.example.projectvishramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {

    private Button rmmenuButton;
    private Button eumenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rmmenuButton = findViewById(R.id.RMMenuButton);
        eumenuButton = findViewById(R.id.EUMenuButton);

        rmmenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRMMenuActivity();
            }
        });

        eumenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEUMenuActivity();
            }
        });
    }

    public void openRMMenuActivity(){
        Intent intent = new Intent(this, RMSignupActivity.class);
        startActivity(intent);
    }

    public void openEUMenuActivity(){
        Intent intent = new Intent(this, EUMenuActivity.class);
        startActivity(intent);
    }

}
