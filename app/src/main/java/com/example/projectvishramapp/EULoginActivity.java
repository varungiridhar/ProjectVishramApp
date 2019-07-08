package com.example.projectvishramapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EULoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button EULoginButton;
    private EditText EUPassword, EUEmail;
    private static final String TAG = "MainActivity";
    private String eupassword, euemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eulogin);
        mAuth = FirebaseAuth.getInstance();


        EULoginButton = findViewById(R.id.EULoginButton);
        EULoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLogin();
            }
        });
    }
    private void startLogin(){

        //LOGIN

        EUPassword = findViewById(R.id.EUPasswordLogin);
        EUEmail = findViewById(R.id.EUEmailLogin);

        eupassword = EUPassword.getText().toString();
        euemail = EUEmail.getText().toString();

        Log.d(TAG, euemail);
        Log.d(TAG, eupassword);

        mAuth.signInWithEmailAndPassword(euemail, eupassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    goToEUMenu();
                    Log.d(TAG, "signInWithEmailAndPassword:successful");

                }else{
                    Log.d(TAG, "signInWithEmailAndPassword:unsuccessful");



                }
            }
        });
    }

    private void goToEUMenu(){
        Intent intent = new Intent(this, EUMenuActivity.class);
        startActivity(intent);
    }
}
