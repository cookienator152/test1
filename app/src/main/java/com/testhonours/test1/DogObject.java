package com.testhonours.test1;

public class DogObject {
    private String name;
    private String race;
    private String behaviour;

    public DogObject(){
    }

    public void setName(String input){
        this.name=input;
    }
    public String getName(){
        return this.name;
    }
    public void setRace(String input){
        this.race = input;
    }
    public String getRace(){
        return this.race;
    }
    public void setBehaviour(String input){
        this.behaviour=input;
    }
    public String getBehaviour(){
        return behaviour;
    }
}
