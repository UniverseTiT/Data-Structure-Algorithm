package entity;

import java.io.Serializable;
import java.util.Objects;
import adt.*;

/**
 *
 * @author Ng Jun Yu
 */
public class Course implements Serializable, Comparable<Course> {

    private String courseCode;
    private String courseName;
    private SortedArrayInterface<String> courseProgramme = new SortedArrayList<>();

    public Course() {
    }

    public Course(String courseCode) {
        this.courseCode = courseCode;
    }  

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }
    
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public SortedArrayInterface<String> getCourseProgramme() {
        return courseProgramme;
    }

    public void setCourseProgramme(SortedArrayInterface<String> courseProgramme) {
        this.courseProgramme = courseProgramme;
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
        final Course other = (Course) obj;
        return Objects.equals(this.courseCode, other.courseCode);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-40s %-20s", courseCode, courseName, courseProgramme);
    }

    @Override
    public int compareTo(Course c) {
        return this.courseCode.compareTo(c.courseCode);
    }
}
