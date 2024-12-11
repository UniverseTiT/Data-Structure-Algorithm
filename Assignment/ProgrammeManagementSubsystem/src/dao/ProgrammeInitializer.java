/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.ListInterface;
import adt.SortedArrayList;
import entity.Programme;

/**
 *
 * @author Tan Chee Fung
 */
public class ProgrammeInitializer {
    
    public ListInterface<Programme> initializer() {
    ListInterface<Programme> pList = new SortedArrayList<>();
    pList.addEntry(new Programme("DFT1", "Diploma in Information Technology"));
    pList.addEntry(new Programme("DIS1", "Diploma in Information Systems"));
    pList.addEntry(new Programme("DCS1", "Diploma in Computer Science"));
    pList.addEntry(new Programme("RSE1", "Bachelor of Software Engineering"));
    pList.addEntry(new Programme("RSD1", "Bachelor of Software Development"));
    pList.addEntry(new Programme("ACC1", "Diploma in Accounting"));
    pList.addEntry(new Programme("DGD1", "Diploma in Graphical Design"));
    pList.addEntry(new Programme("RSF1", "Bachelor of Science (Hons) in Food Science"));
    return pList;
  }
}
