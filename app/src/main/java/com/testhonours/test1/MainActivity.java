package com.testhonours.test1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
