package com.testhonours.test1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText mEmailField;
    private EditText mPasswordField;
    private static final String TAG = "login system";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mEmailField= (EditText) findViewById(R.id.loginEmail);
        mPasswordField= (EditText) findViewById(R.id.loginPassword);

        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
    }

    private void createAccount(String email, String password) {
        Log.e(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                if (task.isSuccessful()){
                    Log.e(TAG, "createUserWithEmail:success");
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    Toast.makeText(LoginScreen.this, "Register successful.",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(LoginScreen.this, "Register failed."+task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    Log.e(TAG, currentUser.getEmail());
                    Toast.makeText(LoginScreen.this, "Sign in successful.",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Log.e(TAG, "signInWithEmail:failed");
                    Toast.makeText(LoginScreen.this, R.string.auth_failed,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bRegister) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        }
        else if (i == R.id.bLogIn) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        }
        else if (i== R.id.bLogOut){
            mAuth.signOut();
            Toast.makeText(LoginScreen.this, "Logged out.",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
