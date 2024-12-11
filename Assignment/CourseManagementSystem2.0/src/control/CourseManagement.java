package control;

/**
 *
 * @author Ng Jun Yu
 */
import java.util.*;
import entity.*;
import adt.*;
import java.util.regex.*;
import utility.*;
import dao.*;

public class CourseManagement {

    private final Scanner s = new Scanner(System.in);
    private String courseCode;
    private String courseName;
    private final String regexCode = ".{4}\\d{4}";
    private final Pattern p = Pattern.compile(regexCode);
    private Matcher m;
    private boolean valid = true;
    private int choice;
    private boolean exit;
    private SortedArrayInterface<String> programmeCode = new SortedArrayList<>();
    private SortedArrayInterface<Course> cList = new SortedArrayList<>();

    public CourseManagement() {
        cList = FileManagement.retrieveFromFile("Course.dat");
    }

    public void addCourse() {
        do {
            System.out.println("Please enter the following information about the new course.");
            System.out.print("Course code(Enter x to exit): ");
            courseCode = s.nextLine().toUpperCase();
            exit = AdditionalFuntion.breakLoop(courseCode);
            if (exit) {
                MessageUI.exitHome();
                break;
            }
            System.out.print("Course name: ");
            courseName = s.nextLine();
            Course course = new Course(courseCode.toUpperCase(), courseName.toUpperCase());
            m = p.matcher(courseCode);
            if (courseCode != null || courseName != null) {
                if (m.matches() == true) {
                    boolean foundCourse = cList.findEntry(course);
                    if (foundCourse) {
                        cList.addEntry(course);
                        valid = true;
                    } else {
                        MessageUI.recordExist();
                        valid = false;
                    }
                } else {
                    MessageUI.wrongFormat();
                    MessageUI.nameFormat();
                    MessageUI.pressContinue();
                    valid = false;
                }
            } else {
                MessageUI.nullValue();
                valid = false;
            }
        } while (!valid);
    }

    public void searchDeleteEdit() {
        Iterator<Course> iterator = cList.getIterator();
        do {
            System.out.println("Search Course");
            System.out.print("Enter the course code(Enter x to exit): ");
            courseCode = s.nextLine().toUpperCase();
            exit = AdditionalFuntion.breakLoop(courseCode);
            if (exit) {
                MessageUI.exitHome();
                break;
            }
            Course findCourse = new Course(courseCode);
            boolean found = cList.findEntry(findCourse);
            if (found) {
                if (courseCode != null) {
                    while (iterator.hasNext()) {
                        Course c = iterator.next();
                        if (courseCode.equals(c.getCourseCode())) {
                            System.out.println("\nResult ---> ");
                            System.out.println("Course Code: " + c.getCourseCode());
                            System.out.println("Course Name: " + c.getCourseName());
                            System.out.println("What you want to do next?");
                            System.out.println("1. Edit course detail");
                            System.out.println("2. Delete course");
                            System.out.println("3. Back to home");
                            System.out.print("--->");
                            choice = s.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("Enter 'X' to maintain old record.");
                                    System.out.print("Enter the new course code: ");
                                    s.next();
                                    String newCode = s.next().toUpperCase();
                                    if (newCode.equals("X")) {
                                        newCode = c.getCourseCode();
                                    }
                                    System.out.print("Enter the new course name: ");
                                    s.nextLine();
                                    String newName = s.nextLine().toUpperCase();
                                    if (newName.equals("X")) {
                                        newName = c.getCourseName();
                                    }
                                    Course editedCourse = new Course(newCode, newName);
                                    boolean editCourse = cList.amendEntry(c, editedCourse);
                                    if (!editCourse) {
                                        System.out.println("Edit fail");
                                    } else {
                                        System.out.println("Selected record has been edited.");
                                    }
                                    break;
                                case 2:
                                    boolean deleteCourse = cList.removeEntry(c);
                                    if (!deleteCourse) {
                                        System.out.println("Delete fail");
                                    } else {
                                        System.out.println("Selected record has been deleted.");
                                    }
                                    break;
                                case 3:
                                    MessageUI.exitHome();
                                    MessageUI.pressContinue();
                                    break;
                                default:
                                    MessageUI.wrongFormat();
                                    break;
                            }
                        }
                    }
                } else {
                    MessageUI.notFound();
                    valid = false;
                }
            } else {
                MessageUI.nullValue();
                valid = false;
            }
        } while (!valid);
    }

    public void listCourse() {
        System.out.println("Course code \tCourse Name \t\t\t Programme");
        System.out.print(cList);
        MessageUI.pressContinue();
    }

    public void addProgramme() {
        Iterator<Course> it = cList.getIterator();
        do {
            System.out.println("Please the course code to insert the programme.");
            System.out.print("Course code(Enter x to exit): ");
            courseCode = s.nextLine().toUpperCase();
            exit = AdditionalFuntion.breakLoop(courseCode);
            if (exit) {
                MessageUI.exitHome();
                break;
            }
            Course course = new Course(courseCode);
            boolean foundCourse = cList.findEntry(course);
            if (foundCourse) {
                while (it.hasNext()) {
                    Course c = it.next();
                    if (courseCode.equals(c.getCourseCode())) {
                        System.out.println("Result -->");
                        System.out.println("Course code: " + c.getCourseCode());
                        System.out.println("Course name: " + c.getCourseName());
                        System.out.println("Course programme: " + c.getCourseProgramme());
                        System.out.print("Enter the programme code:");
                        String pCode = s.nextLine().toUpperCase();
                        programmeCode.addEntry(pCode);
                        c.setCourseProgramme(programmeCode);
                        cList.amendEntry(course, c);
                        break;
                    }
                }
                valid = true;
            } else {
                MessageUI.recordExist();
                valid = false;
            }

        } while (!valid);
    }

    public void deleteProgramme() {
        Iterator<Course> it = cList.getIterator();
        do {
            System.out.println("Please the course code to delete the programme.");
            System.out.print("Course code(Enter x to exit): ");
            courseCode = s.nextLine().toUpperCase();
            exit = AdditionalFuntion.breakLoop(courseCode);
            if (exit) {
                MessageUI.exitHome();
                break;
            }
            Course course = new Course(courseCode);
            boolean foundCourse = cList.findEntry(course);
            if (foundCourse) {
                while (it.hasNext()) {
                    Course c = it.next();
                    if (courseCode.equals(c.getCourseCode())) {
                        System.out.println("Result -->");
                        System.out.println("Course code: " + c.getCourseCode());
                        System.out.println("Course name: " + c.getCourseName());
                        System.out.println("Course programme: " + c.getCourseProgramme());
                        System.out.print("Enter the programme code:");
                        String pCode = s.nextLine().toUpperCase();
                        programmeCode.removeEntry(pCode);
                        c.setCourseProgramme(programmeCode);
                        cList.amendEntry(course, c);
                        break;
                    }
                }
                valid = true;
            } else {
                MessageUI.recordExist();
                valid = false;
            }

        } while (!valid);
    }
}
