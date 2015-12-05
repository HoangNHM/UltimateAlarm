package com.hoangnhm.ultimatealarm.data.model;



public class Alarm {

    public String id;
    public String title;
    public String description;
    public String timePoint;

    public Alarm() {
    }

    public Alarm(String id, String title, String description, String timePoint) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timePoint = timePoint;
    }
}
