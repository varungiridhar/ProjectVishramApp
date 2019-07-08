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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RMLoginActivity extends AppCompatActivity {

    private Button RMLoginbutton;
    private EditText RMPassword, RMEmail;
    private static final String TAG = "MainActivity";
    private String rmpassword, rmemail;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmlogin);

        mAuth = FirebaseAuth.getInstance();

        RMLoginbutton = findViewById(R.id.RMLoginButton);



        RMLoginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLogin();
            }
        });
    }
    private void startLogin(){
        RMPassword = findViewById(R.id.RMPasswordLogin);
        RMEmail = findViewById(R.id.RMEmailLogin);

        rmpassword = RMPassword.getText().toString();
        rmemail = RMEmail.getText().toString();
        Log.d(TAG, "startLogin: started login");



        mAuth.signInWithEmailAndPassword(rmemail, rmpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            goToRMMenu();
                            Log.d(TAG, "signInWithEmailAndPassword:successful");

                        }else{
                            Log.d(TAG, "signInWithEmailAndPassword:unsuccessful");



                        }
                    }
                });
    }
    private void goToRMMenu(){

        Intent intent = new Intent(this, RMMenuActivity.class);
        startActivity(intent);
    }
}
