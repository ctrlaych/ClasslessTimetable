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
public class Instructor extends User {
    private int id;
    private ArrayList<Section> sectionList;

    public Instructor(int id, String name, Department dept, String userType, String username, String password) {
        super(name, dept, userType, username, password);
        this.id = id;
        this.sectionList = new ArrayList<Section>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        this.sectionList = sectionList;
    }
}
