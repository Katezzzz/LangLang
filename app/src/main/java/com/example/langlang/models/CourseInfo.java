package com.example.langlang.models;

import android.os.Parcel;

import java.io.Serializable;

public class CourseInfo implements Serializable {
    int id;
    String name;
    String language;
    String teacher;
    String time;
    int seats;



    public CourseInfo() {

    }

    protected CourseInfo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        language = in.readString();
        teacher = in.readString();
        time = in.readString();
        seats = in.readInt();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }


}
