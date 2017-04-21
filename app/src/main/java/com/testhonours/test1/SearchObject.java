package com.testhonours.test1;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchObject {

    private FirebaseUser author;
    private Calendar dateAdded;
    private DogObject dog;
    private List<FirebaseUser> userList;
    private ArrayList<Marker> markerList;

    public SearchObject(){
    }

    public void setAuthor(FirebaseUser input){
        this.author =input;
    }
    public FirebaseUser getAuthor(){
        return author;
    }
    public void setDog(DogObject input){
        this.dog=input;
    }
    public DogObject getDog (){
        return dog;
    }
    public void setDateAdded(){
        this.dateAdded=Calendar.getInstance();
    }
    public Calendar getDateAdded(){
        return dateAdded;
    }
    public void addUser (FirebaseUser user){
        this.userList.add(user);
    }
    public List<FirebaseUser> getUserList (){
        return userList;
    }
    public void addMarker(Marker marker){
        this.markerList.add(marker);
    }
    public ArrayList<Marker> getMarkers(){
        return markerList;
    }
}
