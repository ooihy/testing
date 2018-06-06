package com.example.ooikk.testing;

//This is the login page
//User can key in login details or choose to sign up to create new account

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText mEditTextEmail;
    private EditText mEditTextPw;
    private Button mButtonLogin;
    private TextView mTextViewSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Firebase Auth Instance
        auth = FirebaseAuth.getInstance();

        //Initialise widgets
        mButtonLogin = findViewById(R.id.mButtonLogin);
        mEditTextEmail = findViewById(R.id.mEditTextEmail);
        mEditTextPw = findViewById(R.id.mEditTextPw);
        mTextViewSignup = findViewById(R.id.mTextViewSignup);

        mTextViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditTextEmail.getText().toString().trim();
                String password = mEditTextPw.getText().toString().trim();
                // Check if email is empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(loginActivity.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }


                // check if password is empty
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(loginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }


                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    // error occurred
                                    Toast.makeText(loginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });


    }
}
