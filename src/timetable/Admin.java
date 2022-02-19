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
public class Admin extends User {
    private ArrayList<Student> studentList;
    private ArrayList<Instructor> instructorList;

    public Admin(String name, Department dept, String userType, String username, String password) {
        super(name, dept, userType, username, password);
        this.studentList = new ArrayList<Student>();
        this.instructorList = new ArrayList<Instructor>();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Instructor> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(ArrayList<Instructor> instructorList) {
        this.instructorList = instructorList;
    }
}
