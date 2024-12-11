package entity;

import java.io.Serializable;
import java.util.Objects;
import adt.*;
/**
 *
 * @author Muhammad Amir Hail Bin Mohamad Hazi
 */
public class TutorialGroupEntity implements Serializable, Comparable<TutorialGroupEntity> {

    private String groupID;
    private int numberOfStudents=0;
    private ListInterface<Student> studentsList = new SortedArrayList<>();

    public TutorialGroupEntity() {
    }

    public TutorialGroupEntity(String groupID) {
        this.groupID = groupID;
    }
        
    public TutorialGroupEntity(String groupID,int numberOfStudents,ListInterface<Student> studentsList) {
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
        if(studentsList.addEntry(student));
        return true;
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
        final TutorialGroupEntity other = (TutorialGroupEntity) obj;
        return Objects.equals(this.groupID, other.groupID);
    }

    @Override
    public String toString() {
        return String.format("%-7s", groupID);
    }

    @Override
    public int compareTo(TutorialGroupEntity c) {
        return this.groupID.compareTo(c.groupID);
    }
}
