/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Ng Jun Yu
 */
public class Student implements Serializable, Comparable<Student> {
    private String studentID;
    private String studentName;
    private String gender;
    private String phoneNum;
    private String tutorialGroup;

    public Student() {
    }

    //add student to a tg
    public Student(String studentID, String tutorialGroup) {
        this.studentID = studentID;
        this.tutorialGroup = tutorialGroup;
    }

    public Student(String studentID, String studentName, String gender, String phoneNum) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.gender = gender;
        this.phoneNum = phoneNum;
    } 

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    

    public String getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(String tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    @Override
    public int compareTo(Student o) {
        return this.studentID.compareTo(o.studentID);
    }

    
  
    
}
