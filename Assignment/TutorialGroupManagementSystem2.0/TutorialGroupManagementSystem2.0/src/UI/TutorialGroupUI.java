package UI;

import adt.*;
import control.*;
import dao.*;
import entity.*;
import java.util.Iterator;
import  java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utility.*;
/**
 *
 * @author Muhammad Amir Hail Bin Mohamad Hazi
 */
public class TutorialGroupUI {
    private final Scanner s = new Scanner(System.in);
    private String studentID;
    private String groupID;
    private String programmeID;
    private final String regexCode = ".{4}\\d{4}";
    private final Pattern p = Pattern.compile(regexCode);
    private Matcher m;
    private int choice;
    private boolean exit;
    private boolean valid;
    private boolean valid2;
    private ListInterface<TutorialGroupEntity> groupList = new SortedArrayList<>();
    StudentInitializer ss = new StudentInitializer();
    TutorialGroupInitializer tg = new TutorialGroupInitializer();
    private ListInterface<Student> sList = new SortedArrayList<>();
    TutorialGroupDAO file = new TutorialGroupDAO();
    
    
    public static void main(String[] args) {
        AdditionalFuntion af = new AdditionalFuntion();
        TutorialGroupControl tgm = new TutorialGroupControl();
        Scanner s = new Scanner(System.in);
        String choice;
        do {
            af.dash();
            System.out.println("Tutorial Group Management System\n");
            System.out.println("1. Add Student into Tutorial Group");
            System.out.println("2. Remove Student from a Tutorial Group");
            System.out.println("3. Transfer Student into another Tutorial Group");
            System.out.println("4. Find the Group of a Student");
            System.out.println("5. List all Students inside a Tutorial Group");
            System.out.println("6. Filter Group based on Criteria");
            System.out.println("7. Report");
            System.out.println("00. Exit");
            System.out.print("\nEnter your choice-->");
            choice = s.nextLine();
            switch (choice) {
                case "1":
                    af.dash();
                    tgm.addStudent();
                    break;
                case "2":
                    af.dash();
                    tgm.removeStudent();
                    break;
                case "3":
                    af.dash();
                    tgm.changeGroup();
                    break;
                case "4":
                    af.dash();
                    tgm.findGroup();
                    break;
                case "5":
                    af.dash();
                    tgm.listStudents();
                    break;
                case "6":
                    af.dash();
                    tgm.filterGroup();
                    break;
                case "7":
                    af.dash();
                    tgm.generateReport();
                    break;
                case "00":
                    af.dash();
                    System.out.println("Exiting the program...");
                    break;
                default:
                    af.dash();
                    MessageUI.invalidChoice();
                    break;
            }
            af.dash();
        } while (!"00".equals(choice));
        System.exit(0);
    }
        public void addStudent() {
        System.out.println("\n\nPlease enter the following information to add student into a tutorial group.");
        valid = false;
        String more;
        do {
            System.out.print("\nEnter Group ID: (Enter x to exit): ");
            groupID = s.nextLine().toUpperCase();
            valid = exit = AdditionalFuntion.breakLoop(groupID);
            if (exit) {
                break;
            }
            Iterator<TutorialGroupEntity> iterator = groupList.getIterator();
            valid2 = false;
            while (iterator.hasNext()) {
                TutorialGroupEntity tutorialGroup = iterator.next();
                if (groupID.equals(tutorialGroup.getGroupID())) {
                    System.out.println("\n\nResult ---> ");
                    System.out.println("Group ID: " + tutorialGroup.getGroupID());
                    System.out.println("Current number of students in the group: " + tutorialGroup.getNumberOfStudents());
                    if (tutorialGroup.getNumberOfStudents() >= 20) {
                        System.out.println("\nTHE GROUP HAS REACH THE LIMIT OF 20 STUDENTS. PLEASE TRY ANOTHER GROUP.\n");
                        valid2 = true;
                        break;

                    } else {
                        
                        do {
                            valid = false;
                            valid2 = false;
                            System.out.print("\nEnter Student ID (Enter x to exit):  ");
                            studentID = s.nextLine();
                            valid = exit = AdditionalFuntion.breakLoop(studentID);
                            if (exit) {
                                break;
                            }
                            Iterator<Student> iterator2 = sList.getIterator();
                            while (iterator2.hasNext()) {
                                Student student = iterator2.next();
                                if (studentID.equals(student.getStudentID())) {
                                    System.out.println("\n\nResult ---> ");
                                    System.out.println("Student ID: " + student.getStudentID());
                                    System.out.println("Student Name: " + student.getStudentName());
                                    if (student.getTutorialGroup() != null) {
                                        System.out.println("Student Tutorial Group: " + student.getTutorialGroup());
                                        System.out.println("\nTHIS STUDENT ALREADY HAVE TUTORIAL GROUP! ADD FAILED.");
                                        valid2 = true;
                                        break;
                                    } else {
                                        Student newStudent = new Student(student.getStudentID(), student.getStudentName(), student.getGender(), student.getPhoneNum());
                                        newStudent.setTutorialGroup(tutorialGroup.getGroupID());
                                        TutorialGroupEntity newTutorialGroup = new TutorialGroupEntity(tutorialGroup.getGroupID(), tutorialGroup.getNumberOfStudents(), tutorialGroup.getStudentsList());
                                        boolean success = newTutorialGroup.addStudent(newStudent);
                                        if (success) {
                                            boolean editStudent = sList.amendEntry(student, newStudent);
                                            boolean editTutorialGroup = groupList.amendEntry(tutorialGroup, newTutorialGroup);
                                            file.saveTutorialGroupToFile(groupList, "TutorialGroup.dat");
                                            file.saveStudentToFile(sList, "Student.dat");
                                            if (!editStudent && !editTutorialGroup) {
                                                System.out.println("\nAMEND FAIL\n");
                                            } else {
                                                System.out.println("\nStudent have been successfully added.\n");
                                            }
                                        } else {
                                            System.out.println("\nADD FAIL\n");
                                        }
                                        valid = true;
                                        valid2 = true;
                                        break;
                                    }
                                }
                            }
                            if (valid == false && valid2 == false) {
                                System.out.println("\nTHE STUDENT DOES NOT EXIST! PLEASE TRY AGAIN.\n");
                            }
                        } while (valid2 == false);
                        
                        
                    }
                    if (valid == true && valid2 == true) {
                        System.out.println("\nDo you want to add more? (Enter x to exit):\n");
                        more = s.nextLine();
                        valid = exit = AdditionalFuntion.breakLoop(more);
                        if (exit) {
                            MessageUI.exitHome();
                            break;
                        }
                    }

                }

            }
            if (valid == false && valid2 == false) {
                System.out.println("\nTHE GROUP DOES NOT EXIST! PLEASE TRY AGAIN..\n");
            }
        } while (valid == false);
    }
}
