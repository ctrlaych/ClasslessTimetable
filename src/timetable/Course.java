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
public class Course {

    private int id;
    private String code;
    private String name;
    private int creditHours;
    private int batchNumber;
    private ArrayList<Section> sectionList;
    private Department dept;
    private int labStatus;

    public Course() {
    }
    
    public Course(Course c) {
        this.id = c.id;
        this.code = c.code;
        this.name = c.name;
        this.creditHours = c.creditHours;
        this.batchNumber = c.batchNumber;
        this.dept = c.dept;
        this.labStatus = c.labStatus;
        this.sectionList = c.sectionList;
    }
    
    public Course(int id, String code, String name, int creditHours, int batchNumber, Department dept, int ls) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.creditHours = creditHours;
        this.batchNumber = batchNumber;
        this.dept = dept;
        this.labStatus = ls;
        this.sectionList = new ArrayList<Section>();
    }

    public int getLabStatus() {
        return labStatus;
    }

    public void setLabStatus(int labStatus) {
        this.labStatus = labStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public ArrayList<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        this.sectionList = sectionList;
    }

}
