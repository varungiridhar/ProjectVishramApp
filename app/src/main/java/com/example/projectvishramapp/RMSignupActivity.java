package com.example.projectvishramapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RMSignupActivity extends AppCompatActivity {

    private EditText RMName, RMPhoneNum, RMPassword, RMEmail;
    private Button RMSignupButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private static final String TAG = "MainActivity";
    private Button RMLoginActivityButton;
    private String rmname, rmphonenum, rmpassword, rmemail;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmsignup);


        mAuth = FirebaseAuth.getInstance();
        firebaseAuthListener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(RMSignupActivity.this, RMMenuActivity.class);
                    startActivity(intent);
                    finish();
                    return;


                }
            }
        };
        RMName = findViewById(R.id.RMEmailLogin);
        RMPhoneNum = findViewById(R.id.RMPhonenumSignup);
        RMPassword = findViewById(R.id.RMPasswordLogin);
        RMEmail = findViewById(R.id.RMEmailSignup);

        RMSignupButton = findViewById(R.id.RMSignupButton);
        RMLoginActivityButton = findViewById(R.id.RMLoginActivityButton);

        RMSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
                Log.d(TAG, "Signup button clicked");


            }
        });

        RMLoginActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();
                Log.d(TAG, "Login button clicked");


            }
        });
    }
    private void startSignIn(){
        rmname = RMName.getText().toString();
        rmemail = RMEmail.getText().toString();
        rmphonenum = RMPhoneNum.getText().toString();
        rmpassword = RMPassword.getText().toString();


        mAuth.createUserWithEmailAndPassword(rmemail, rmpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            ref.child("users").child("RMs").child(user.getUid()).child("name").setValue(rmname);
                            ref.child("users").child("RMs").child(user.getUid()).child("phone_num").setValue(rmphonenum);

                            goToLoginActivity();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RMSignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }
    private void goToLoginActivity(){
        Intent intent = new Intent(this, RMLoginActivity.class);
        startActivity(intent);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Log.d(TAG, "user signed in already");
        }else{
            Log.d(TAG, "user not signed in");
        }
    }



}
