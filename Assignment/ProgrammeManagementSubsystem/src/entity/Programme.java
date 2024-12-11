/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ListInterface;
import adt.SortedArrayList;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Tan Chee Fung
 */
public class Programme implements Serializable, Comparable<Programme> {

    private String code;
    private String name;
    private ListInterface<TutorialGroup> tutorialGroupProgramme = new SortedArrayList<>();

    public Programme(String code, String name, ListInterface<TutorialGroup> tutorialGroupProgramme) {
        this.code = code;
        this.name = name;
        this.tutorialGroupProgramme = tutorialGroupProgramme;
    }
    
    public Programme() {
    }

    public Programme(String code) {
        this.code = code;
    }

    public Programme(String code, String name) {
        this.code = code;
        this.name = name;
        this.tutorialGroupProgramme = new SortedArrayList<>();
    }

    public ListInterface<TutorialGroup> getTutorialGroupProgramme() {
        return tutorialGroupProgramme;
    }

    public void setTutorialGroupProgramme(ListInterface<TutorialGroup> tutorialGroupProgramme) {
        this.tutorialGroupProgramme = tutorialGroupProgramme;
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
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.code);
        return hash;
    }

     public void addTutorialGroup(TutorialGroup tutorialgroup) {
        tutorialGroupProgramme.addEntry(tutorialgroup);
    }

    public void removeProgramme(TutorialGroup tutorialgroup) {
        tutorialGroupProgramme.removeEntry(tutorialgroup);
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Programme other = (Programme) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return String.format("%-8s\t%-8s ", code, name);
    }

    @Override
    public int compareTo(Programme o) {
        return this.code.compareTo(o.code);
    }

}
