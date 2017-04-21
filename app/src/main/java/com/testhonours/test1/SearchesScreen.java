package com.testhonours.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchesScreen extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    private List<SearchObject> mList;
    private ListView mSearchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searches_screen);

        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference dbRef = mDatabase.getReference();
        DatabaseReference dbSearches = dbRef.child("searches");
        dbSearches.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                SearchObject temp = snapshot.getChildren().iterator().next().getValue(SearchObject.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void newSearch(View v){
        Intent intent = new Intent (this, AddSearchScreen.class);
        startActivity(intent);

    }



}
