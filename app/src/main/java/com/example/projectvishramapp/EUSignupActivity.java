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

public class EUSignupActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private EditText EUName, EUPhoneNum, EUPassword, EUEmail;
    private Button EUSignupButton, EULoginActivityButton;
    private String euname, euphonenum, eupassword, euemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eusignup);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthListener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(EUSignupActivity.this, EUMenuActivity.class);
                    startActivity(intent);
                    finish();
                    return;


                }
            }
        };

        EUName = findViewById(R.id.EUEmailLogin);
        EUPhoneNum = findViewById(R.id.EUPhonenumSignup);
        EUPassword = findViewById(R.id.EUPasswordLogin);
        EUEmail = findViewById(R.id.EUEmailSignup);

        EUSignupButton = findViewById(R.id.EUSignupButton);
        EULoginActivityButton = findViewById(R.id.EULoginActivityButton);

        EUSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();


            }
        });

        EULoginActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();


            }
        });
    }
    private void startSignIn(){
        euname = EUName.getText().toString();
        euemail = EUEmail.getText().toString();
        euphonenum = EUPhoneNum.getText().toString();
        eupassword = EUPassword.getText().toString();


        mAuth.createUserWithEmailAndPassword(euemail, eupassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "createUserWithEmail:success");
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            ref.child("users").child("EUs").child(user.getUid()).child("name").setValue(euname);
                            ref.child("users").child("EUs").child(user.getUid()).child("phone_num").setValue(euphonenum);

                            goToLoginActivity();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Log.d(TAG, "onComplete: unsuccessful");

                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }
    private void goToLoginActivity(){
        Intent intent = new Intent(this, EULoginActivity.class);
        startActivity(intent);
    }
}
