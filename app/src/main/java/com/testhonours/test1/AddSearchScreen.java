package com.testhonours.test1;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSearchScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private EditText mDogName;
    private EditText mDogRace;
    private Spinner mBehaviour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_search_screen);

        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference dbRefSearches = mDatabase.getReference("searches");

        mDogName = (EditText) findViewById(R.id.bDogName);
        mDogRace = (EditText) findViewById(R.id.bDogRace);
        mBehaviour = (Spinner) findViewById(R.id.bBehaviourSpinner);
        String behaviourChoice;
        mBehaviour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                behaviourChoice=mBehaviour.getItemAtPosition(position).toString();
            }
        });
    }
//    @Override
//    protected void onStop(Bundle savedInstanceState){
//        super.onStop();
//
//    }

    public void addNewSearch(View v) {
        if (!validateForm()) {
            return;
        }
        SearchObject currentSearch = new SearchObject();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        currentSearch.setAuthor(currentUser);
        currentSearch.setDateAdded();

        DogObject currentDog = new DogObject();
        currentDog.setName(mDogName.getText().toString());
        currentDog.setRace(mDogRace.getText().toString());
        currentSearch.setDog(currentDog);
    }

    private boolean validateForm() {
        boolean valid = true;

        String dogname = mDogName.getText().toString();
        if (TextUtils.isEmpty(dogname)) {
            mDogName.setError("Required.");
            valid = false;
        } else {
            mDogName.setError(null);
        }

        String dograce = mDogRace.getText().toString();
        if (TextUtils.isEmpty(dograce)) {
            mDogRace.setError("Required.");
            valid = false;
        } else {
            mDogRace.setError(null);
        }

        return valid;
    }

}
