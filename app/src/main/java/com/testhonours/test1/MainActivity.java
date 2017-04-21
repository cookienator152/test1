package com.testhonours.test1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    public List<SearchObject> searches;
    private FirebaseAuth mAuth;
    private TextView mStatus;
    //private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mCurrentUser = mAuth.getCurrentUser();
        mStatus = (TextView) findViewById(R.id.currentUser);
        //mAuth.addAuthStateListener(mAuthListener);
        if (mCurrentUser != null) {
            mStatus.setText(mCurrentUser.getEmail());
        } else {
            mStatus.setText("None.");
        }

    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser mCurrentUser = mAuth.getCurrentUser();
        if (mCurrentUser != null) {
            mStatus.setText(mCurrentUser.getEmail());
        } else {
            mStatus.setText("None.");
        }
    }
    public void changeScreen (View view){
        switch (view.getId()){
            case R.id.bLoginScreen:{
                Intent intent = new Intent(this, LoginScreen.class);
                startActivity(intent);
                break;
            }
            case R.id.bMapScreen:{
                Intent intent = new Intent(this, MapScreen.class);
                startActivity(intent);
                break;
            }
        }
    }

}
