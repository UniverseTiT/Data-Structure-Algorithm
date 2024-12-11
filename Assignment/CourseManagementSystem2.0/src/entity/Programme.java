/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Ng Jun Yu
 */
public class Programme implements Serializable, Comparable<Programme> {

    private String programmeCode;

    public Programme() {
    }

    public Programme(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.programmeCode);
        return hash;
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
        return Objects.equals(this.programmeCode, other.programmeCode);
    }

    @Override
    public String toString() {
        return programmeCode;
    }

    
    
    @Override
    public int compareTo(Programme o) {
        return this.programmeCode.compareTo(o.programmeCode);
    }

}
