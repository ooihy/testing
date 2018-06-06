package com.example.ooikk.testing;

//This is the sign up page. Upon completing sign_up, users will be directed to main page

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//alt+enter to import automatically

public class SignupActivity extends AppCompatActivity {

    private EditText mEditTextPw;
    private EditText mEditTextEmail;
    private EditText mEditTextName;
    private EditText mEditTextPhone;
    private EditText mEditTextUsername;
    private Button mBtnNext;
    private Button mBtnUpload;
    private FirebaseAuth auth;
    public static final int GET_FROM_GALLERY = 3;

    String fullName;
    String userName;
    String phoneNum;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); //set layout [activity_signup] for this activity

        //Initialise widgets
        mEditTextEmail = findViewById(R.id.emailEditText);
        mEditTextPw = findViewById(R.id.passwordEditText);
        mEditTextName = findViewById(R.id.nameEditText);
        mEditTextUsername = findViewById(R.id.usernameEditText);
        mEditTextPhone = findViewById(R.id.phoneEditText);
        mBtnNext = findViewById(R.id.mBtnNext);

        // Firebase Auth Instance
        auth = FirebaseAuth.getInstance();

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = mEditTextName.getText().toString().trim(); //get from textbox
                userName = mEditTextUsername.getText().toString().trim(); //get from textbox
                phoneNum = mEditTextPhone.getText().toString().trim(); //get from textbox
                email = mEditTextEmail.getText().toString().trim(); //get from textbox
                password = mEditTextPw.getText().toString().trim(); //get from textbox
                // Check if email is empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignupActivity.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                // check if password is empty
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignupActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //check if password is alphanumeric
                if(!password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])[a-zA-Z0-9]+$")){
                    Toast.makeText(SignupActivity.this, "Password must contain one upper and lower case letter and one number", Toast.LENGTH_LONG).show();
                    return;
                }

                // Create a new user
                auth.createUserWithEmailAndPassword(email, password).
                        addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Signup successful, got to main activity
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    // End the activity
                                    finish();
                                }
                            }
                        });
            }
        });

    }
}
