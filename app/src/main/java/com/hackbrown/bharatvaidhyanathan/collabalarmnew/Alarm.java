package com.hackbrown.bharatvaidhyanathan.collabalarmnew;

/**
 * Created by vaidhyanathannarayanan on 05/02/17.
 */

public class Alarm {
    public String alarm_time;
    public String alarm_name;
    boolean isSelected;

    public Alarm(){
        super();
    }

    public Alarm(String alarm_time, String alarm_name, boolean b){
        super();
        this.alarm_name = alarm_name;
        this.alarm_time = alarm_time;
        this.isSelected = b;
    }
}
