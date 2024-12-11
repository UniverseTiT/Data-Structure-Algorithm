package entity;

import java.io.Serializable;
import java.util.Objects;
import adt.*;

public class TutorialGroup implements Serializable, Comparable<TutorialGroup> {

    private String groupID;
    private int numberOfStudents=0;
    private ListInterface<Student> studentsList = new SortedArrayList<>();

    public TutorialGroup() {
    }

    public TutorialGroup(String groupID) {
        this.groupID = groupID;
    }
        
    public TutorialGroup(String groupID,int numberOfStudents,ListInterface<Student> studentsList) {
        this.groupID = groupID;
        this.numberOfStudents=numberOfStudents;
        this.studentsList=studentsList;
    }
    
    public void increaseNumberOfStudents() {
        this.numberOfStudents++;
    }
    
        public void decreaseNumberOfStudents() {
        this.numberOfStudents--;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getGroupID() {
        return groupID;
    }

    public ListInterface<Student> getStudentsList() {
        return studentsList;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }


    public void setStudentsList(ListInterface<Student> studentsList) {
        this.studentsList = studentsList;
    }
    
    public boolean addStudent(Student student){
        boolean success=studentsList.addEntry(student);
        if (success){
        increaseNumberOfStudents();
        return true;
        }
        return false;
    }
    

    public boolean removeStudent(Student student){
        boolean success=studentsList.removeEntry(student);
        if (success){
        decreaseNumberOfStudents();
        return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final TutorialGroup other = (TutorialGroup) obj;
        return Objects.equals(this.groupID, other.groupID);
    }

    @Override
    public String toString() {
        return String.format("%-7s", groupID);
    }

    @Override
    public int compareTo(TutorialGroup c) {
        return this.groupID.compareTo(c.groupID);
    }
}
