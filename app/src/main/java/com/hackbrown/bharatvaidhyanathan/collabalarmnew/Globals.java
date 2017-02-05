package com.hackbrown.bharatvaidhyanathan.collabalarmnew;

import java.util.ArrayList;

/**
 * Created by vaidhyanathannarayanan on 05/02/17.
 */
public class Globals {
    private static ArrayList<Alarm> alarms;
    private static ArrayList<String> users;

    private static Globals ourInstance;

    public static Globals getInstance() {
        if(ourInstance == null){
            ourInstance = new Globals();
        }
        return ourInstance;
    }

    private Globals() {
        alarms = new ArrayList<>();
        users = new ArrayList<>();
    }

    public ArrayList<Alarm> getAlarms(){
        return this.alarms;
    }

    public Alarm getAlarm(int i){
        return this.alarms.get(i);
    }

    public void setAlarm(int i, Alarm a){
        this.alarms.set(i,a);
    }

    public void addToAlarms(Alarm a){
        this.alarms.add(a);
    }

    public String[] getUsers(){
        return (String[]) this.users.toArray();
    }

}
