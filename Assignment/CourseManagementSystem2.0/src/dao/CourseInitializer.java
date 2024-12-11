/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.*;

/**
 *
 * @author Ng Jun Yu
 */
public class CourseInitializer {

    public SortedArrayInterface<Course> initializer() {
        SortedArrayInterface<Course> cList = new SortedArrayList<>();
        cList.addEntry(new Course("BACS2053", "OBJECT-ORIENTED ANALYSIS AND DESIGN"));
        cList.addEntry(new Course("BACS2042", "RESEARCH METHODS"));
        cList.addEntry(new Course("BACS2063", "DATA STRUCTURES AND ALGORITHMS"));
        cList.addEntry(new Course("BAIT2004", "FUNDAMENTALS OF COMPUTER NETWORKS"));
        cList.addEntry(new Course("BACS2163", "SOFTWARE ENGINEERING"));
        cList.addEntry(new Course("BAIT2203", "HUMAN COMPUTER INTERACTION"));
        cList.addEntry(new Course("MPU-3322", "CONTEMPORARY MALAYSIAN ISSUES"));
        cList.addEntry(new Course("BAIT2023", "INTRODUCTION TO INTERNET SECURITY"));
        cList.addEntry(new Course("BAIT2113", "WEB APPLICATION DEVELOPMENT"));
        cList.addEntry(new Course("BMIT2154", "SWITCHING AND ROUTING TECHNOLOGIES"));
        return cList;
    }

}
