package com.testhonours.test1;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSearchScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private EditText mDogName;
    private EditText mDogRace;
    private String mDogBehaviour;
    private Spinner mBehaviour;
    private TextView desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_search_screen);
        mAuth = FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();
        setup();
    }
//        mDatabase = FirebaseDatabase.getInstance();
    public void setup(){

        mDogName = (EditText) findViewById(R.id.bDogName);
        mDogRace = (EditText) findViewById(R.id.bDogRace);

        mBehaviour = (Spinner) findViewById(R.id.bBehaviourSpinner);
        desc = (TextView) findViewById(R.id.mDescription);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.behaviours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBehaviour.setAdapter(adapter);

        mBehaviour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object temp = parent.getSelectedItemId();
                temp=temp.toString();
                if (temp=="0"){
                    desc.setText(R.string.desc1);
                } else if (temp=="1"){
                    desc.setText(R.string.desc2);
                } else if (temp=="2"){
                    desc.setText(R.string.desc3);
                }
                Toast.makeText(AddSearchScreen.this, temp.toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddSearchScreen.this, "NothingSelected", Toast.LENGTH_SHORT).show();

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

        DatabaseReference myRef = mDatabase.getReference();
        DatabaseReference searchRef = myRef.child("searches");
        DatabaseReference newRef = searchRef.push();
        newRef.setValue(currentSearch);
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
