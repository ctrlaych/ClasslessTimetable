/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.util.ArrayList;

/**
 *
 * @author Hammad
 */
public class Student extends User {
    private ArrayList<Section> sectionList;
    private int batchNumber;
    private int id;

    public Student(int id, String name, Department dept, String userType, String username, String password, int batchNumber) {
        super(name, dept, userType, username, password);
        this.sectionList = new ArrayList<Section>();
        this.id = id;
        this.batchNumber = batchNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Student(String name, Department dept, String userType, String username, String password) {
        super(name, dept, userType, username, password);
        this.sectionList = new ArrayList<Section>();
    }

    public ArrayList<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }
}
