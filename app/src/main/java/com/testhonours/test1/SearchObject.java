package com.testhonours.test1;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchObject {

    private String searchAuthor;
    private Calendar dateAdded;
    private DogObject dog;
    private List<FirebaseUser> userList;
    private ArrayList<Marker> markerList;

    public SearchObject(){
        this.dateAdded=Calendar.getInstance();
    }

    public void setAuthor(String input){
        this.searchAuthor=input;
    }
    public String getAuthor(){
        return searchAuthor;
    }
    public void setDog(DogObject input){
        this.dog=input;
    }
    public DogObject getDog (){
        return dog;
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
