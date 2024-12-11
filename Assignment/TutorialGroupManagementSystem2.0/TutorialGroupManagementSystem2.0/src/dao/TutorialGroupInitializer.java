/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.*;
/**
 *
 * @author Muhammad Amir Hail Bin Mohamad Hazi
 */
public class TutorialGroupInitializer {

    public ListInterface<TutorialGroupEntity> initializer() {
        ListInterface<TutorialGroupEntity> gList = new SortedArrayList<>();
      
        gList.addEntry(new TutorialGroupEntity("DFT1G1"));
        gList.addEntry(new TutorialGroupEntity("DFT1G2"));
        gList.addEntry(new TutorialGroupEntity("DFT1G3"));
        gList.addEntry(new TutorialGroupEntity("DFT1G4"));
        gList.addEntry(new TutorialGroupEntity("DFT1G5"));
        gList.addEntry(new TutorialGroupEntity("DFT2G1"));
        gList.addEntry(new TutorialGroupEntity("DFT2G2"));
        gList.addEntry(new TutorialGroupEntity("DFT2G3"));
        gList.addEntry(new TutorialGroupEntity("DFT2G4"));
        gList.addEntry(new TutorialGroupEntity("DFT2G5"));
        gList.addEntry(new TutorialGroupEntity("DCS2G1"));
        gList.addEntry(new TutorialGroupEntity("DCS2G2"));
        gList.addEntry(new TutorialGroupEntity("DCS2G3"));
        gList.addEntry(new TutorialGroupEntity("DCS2G4"));
        gList.addEntry(new TutorialGroupEntity("DCS2G5"));
        return gList;
    }

}
