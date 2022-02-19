/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.time.LocalTime;

/**
 *
 * @author Hammad
 */
public class Section {
    private String name;
    private String day;
    private LocalTime time;
    private String room;
    private int roomLimit;
    private Course course;
    private Instructor instructor;
    private int status;

    public Section(String name, String day, LocalTime time, String room, int roomLimit, Course course, Instructor instructor) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.room = room;
        this.roomLimit = roomLimit;
        this.course = course;
        this.instructor = instructor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

    public Section(String name, String day, LocalTime time, String room, int roomLimit, Course course) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.room = room;
        this.roomLimit = roomLimit;
        this.course = course;
    }
    
    public Section(String name, String day, LocalTime time, String room, int roomLimit, int status) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.room = room;
        this.roomLimit = roomLimit;
        this.status = status;
    }   
    
    public Section(String name, String day, LocalTime time, String room, int roomLimit) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.room = room;
        this.roomLimit = roomLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getRoomLimit() {
        return roomLimit;
    }

    public void setRoomLimit(int roomLimit) {
        this.roomLimit = roomLimit;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
}
