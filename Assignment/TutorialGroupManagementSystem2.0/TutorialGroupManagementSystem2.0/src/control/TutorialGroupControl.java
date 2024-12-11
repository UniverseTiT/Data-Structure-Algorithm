package control;

import java.util.*;
import entity.*;
import adt.*;
import java.util.regex.*;
import utility.*;
import dao.*;
import java.io.File;
/**
 *
 * @author Muhammad Amir Hail Bin Mohamad Hazi
 */
public class TutorialGroupControl {

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

    public TutorialGroupControl() {
        File f = new File("TutorialGroup.dat");
        if (f.exists() && !f.isDirectory()) {
            groupList = TutorialGroupDAO.retrieveTutorialGroupFrom("TutorialGroup.dat");
        } else {
            file.createFile("TutorialGroup.dat");
            groupList = tg.initializer();
            file.saveTutorialGroupToFile(groupList, "TutorialGroup.dat");
        }
        File f2 = new File("Student.dat");
        if (f2.exists() && !f2.isDirectory()) {
            sList = TutorialGroupDAO.retrieveStudentFrom("Student.dat");
        } else {
            file.createFile("Student.dat");
            sList = ss.initializer();
            file.saveStudentToFile(sList, "Student.dat");
        }
    }

    public void addStudent() {
        System.out.println("\n\nPlease enter the following information to add student into a tutorial group.");
        do {
            valid = false;
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
                    TutorialGroupEntity newTutorialGroup = new TutorialGroupEntity(tutorialGroup.getGroupID() , tutorialGroup.getNumberOfStudents(), tutorialGroup.getStudentsList());

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
                                        newTutorialGroup.increaseNumberOfStudents();
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
                                                System.out.println("Group ID: " + tutorialGroup.getGroupID());
                                                System.out.println("Current number of students in the group: " + newTutorialGroup.getNumberOfStudents());
                                                valid = true;
                                                break;
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
                }
            }
            if (valid == false && valid2 == false) {
                System.out.println("\nTHE GROUP DOES NOT EXIST! PLEASE TRY AGAIN..\n");
            }
        } while (valid == false);
    }

    public void removeStudent() {
        System.out.println("\n\nPlease enter the following information to remove student from a tutorial group.");
        valid = false;
        String more;
        do {
            System.out.print("\nEnter Group ID: (Enter x to exit): ");
            groupID = s.nextLine().toUpperCase();
            valid = exit = AdditionalFuntion.breakLoop(groupID);
            if (exit) {
                MessageUI.exitHome();
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
                    ListInterface<Student> ssList = tutorialGroup.getStudentsList();
                    do {
                        valid = false;
                        valid2 = false;
                        Iterator<Student> iterator2 = ssList.getIterator();
                        while (iterator2.hasNext()) {
                            Student student = iterator2.next();
                            System.out.printf("\nID: %-10s Name: %-100s\n", student.getStudentID(), student.getStudentName());
                            valid = true;
                            valid2 = true;
                        }
                        if (valid == false && valid2 == false) {
                            System.out.println("\nSTUDENT DOES NOT EXIST!.\n");
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
                                Iterator<Student> iterator3 = ssList.getIterator();
                                while (iterator3.hasNext()) {
                                    Student student = iterator3.next();
                                    if (studentID.equals(student.getStudentID())) {
                                        System.out.println("\n\nResult ---> ");
                                        System.out.println("Student ID: " + student.getStudentID());
                                        System.out.println("Student Name: " + student.getStudentName());

                                        Student newStudent = new Student(student.getStudentID(), student.getStudentName(), student.getGender(), student.getPhoneNum());
                                        TutorialGroupEntity newTutorialGroup = new TutorialGroupEntity(tutorialGroup.getGroupID(), tutorialGroup.getNumberOfStudents(), tutorialGroup.getStudentsList());
                                        boolean success = newTutorialGroup.removeStudent(student);
                                        if (success) {
                                            boolean editStudent = sList.amendEntry(student, newStudent);
                                            boolean editTutorialGroup = groupList.amendEntry(tutorialGroup, newTutorialGroup);
                                            file.saveTutorialGroupToFile(groupList, "TutorialGroup.dat");
                                            file.saveStudentToFile(sList, "Student.dat");
                                            if (!editStudent && !editTutorialGroup) {
                                                System.out.println("\nAMEND FAIL\n");
                                            } else {
                                                System.out.println("\nStudent have been successfully removed.\n");
                                            }
                                        } else {
                                            System.out.println("\nREMOVE FAIL, STUDENT IS NOT IN THE GROUP\n");
                                        }
                                        valid = true;
                                        valid2 = true;
                                        break;
                                    }
                                }
                                if (valid == false && valid2 == false) {
                                    System.out.println("\nTHE STUDENT DOES NOT EXIST! PLEASE TRY AGAIN.\n");
                                }
                            } while (valid == false);
                        }
                    } while (valid == false);

                    valid = true;
                    valid2 = true;
                    break;
                }
            }

            if (valid == false && valid2 == false) {
                System.out.println("\nTHE GROUP DOES NOT EXIST! PLEASE TRY AGAIN..\n");
            }
            if (valid == true && valid2 == true) {
                System.out.println("\nDo you want to remove more? (Enter x to exit):\n");
                more = s.nextLine();
                valid = exit = AdditionalFuntion.breakLoop(more);
                if (exit) {
                    MessageUI.exitHome();
                    break;
                }
            }

        } while (valid == false);
    }

    public void listStudents() {
        System.out.println("\n\nPlease enter the following information to list all students in a tutorial group.");
        valid = false;
        String more;
        do {
            System.out.print("\nEnter Group ID: (Enter x to exit): ");
            groupID = s.nextLine().toUpperCase();
            valid = exit = AdditionalFuntion.breakLoop(groupID);
            if (exit) {
                MessageUI.exitHome();
                break;
            }
            Iterator<TutorialGroupEntity> iterator = groupList.getIterator();
            while (iterator.hasNext()) {
                TutorialGroupEntity tutorialGroup = iterator.next();
                if (groupID.equals(tutorialGroup.getGroupID())) {
                    System.out.println("\n\nResult ---> ");
                    System.out.println("Group ID: " + tutorialGroup.getGroupID());
                    System.out.println("Current number of students in the group: " + tutorialGroup.getNumberOfStudents());

                    ListInterface<Student> ssList = tutorialGroup.getStudentsList();

                    do {
                        valid = false;
                        Iterator<Student> iterator2 = ssList.getIterator();
                        while (iterator2.hasNext()) {
                            Student student = iterator2.next();
                            System.out.printf("\nID: %-10s Name: %-100s\n", student.getStudentID(), student.getStudentName());
                            valid = true;
                        }
                        if (valid == false) {
                            System.out.println("\nSTUDENT DOES NOT EXIST!.\n");
                            break;
                        }
                    } while (valid == false);
                    valid = true;
                    break;
                }
            }

            if (valid == false) {
                System.out.println("\nTHE GROUP DOES NOT EXIST! PLEASE TRY AGAIN..\n");
            }
            if (valid == true) {
                System.out.println("\nDo you want to see more? (Enter x to exit):\n");
                more = s.nextLine();
                valid = exit = AdditionalFuntion.breakLoop(more);
                if (exit) {
                    MessageUI.exitHome();
                    break;
                }
            }
        } while (valid == false);
    }

    public void findGroup() {
        System.out.println("\n\nPlease enter the following information to find the group of a student.");
        valid = false;
        String more;
        do {
            System.out.print("\nEnter Student ID: (Enter x to exit): ");
            studentID = s.nextLine();
            valid = exit = AdditionalFuntion.breakLoop(studentID);
            if (exit) {
                MessageUI.exitHome();
                break;
            }
            Iterator<Student> iterator = sList.getIterator();
            while (iterator.hasNext()) {
                Student student = iterator.next();
                if (studentID.equals(student.getStudentID())) {
                    System.out.println("\n\nResult ---> ");
                    System.out.println("Student ID: " + student.getStudentID());
                    System.out.println("Student Name: " + student.getStudentName());

                    if (student.getTutorialGroup() != null) {
                        System.out.println("\nStudent Tutorial Group: " + student.getTutorialGroup());
                        System.out.println("\nThe student already has a group.\n");
                        valid = true;
                        break;

                    } else {
                        System.out.println("\nThe student does not have a group.\n");
                        valid = true;
                        break;
                    }
                }
            }
            if (valid == false) {
                System.out.println("\nTHE STUDENT DOES NOT EXIST! PLEASE TRY AGAIN.\n");
            }
            if (valid == true) {
                System.out.println("\nDo you want to see more? (Enter x to exit):\n");
                more = s.nextLine();
                valid = exit = AdditionalFuntion.breakLoop(more);
                if (exit) {
                    MessageUI.exitHome();
                    break;
                }
            }

        } while (valid == false);
    }

    public void filterGroup() {
        System.out.println("\n\nPlease enter the following information to find the groups based on the programme code.");
        valid = false;
        String more;
        do {
            System.out.print("\nEnter Programme ID (Enter x to exit): ");
            programmeID = s.nextLine().toUpperCase();
            valid = exit = AdditionalFuntion.breakLoop(programmeID);
            if (exit) {
                MessageUI.exitHome();
                break;
            }
            Iterator<TutorialGroupEntity> iterator = groupList.getIterator();
            while (iterator.hasNext()) {
                TutorialGroupEntity tutorialGroup = iterator.next();
                String slice = tutorialGroup.getGroupID().substring(0, 3);
                if (slice.equals(programmeID)) {
                    System.out.println("\nGroup ID: " + tutorialGroup.getGroupID());
                    System.out.println("Current number of students in the group: " + tutorialGroup.getNumberOfStudents());
                    valid = true;
                }
            }
            if (valid == false) {
                System.out.println("\nGROUP DOES NOT EXIST! PLEASE TRY AGAIN.\n");
            }
            if (valid == true) {
                System.out.println("\nDo you want to see more? (Enter x to exit):\n");
                more = s.nextLine();
                valid = exit = AdditionalFuntion.breakLoop(more);
                if (exit) {
                    MessageUI.exitHome();
                    break;
                }
            }
        } while (valid == false);
    }

    public void changeGroup() {
        System.out.println("\n\nPlease enter the following information to change the tutorial group of a student.");
        valid = false;
        String more;
        do {
            System.out.print("\nEnter Student ID: (Enter x to exit): ");
            studentID = s.nextLine();
            valid = exit = AdditionalFuntion.breakLoop(studentID);
            if (exit) {
                MessageUI.exitHome();
                break;
            }
            Iterator<Student> iterator = sList.getIterator();
            valid2 = false;
            while (iterator.hasNext()) {
                Student student = iterator.next();
                if (studentID.equals(student.getStudentID())) {
                    System.out.println("\n\nResult ---> ");
                    System.out.println("Student ID: " + student.getStudentID());
                    System.out.println("Student Name: " + student.getStudentName());
                    if (student.getTutorialGroup() != null) {
                        System.out.println("\nCurrent Tutorial Group: " + student.getTutorialGroup());
                        do {
                            valid = false;
                            valid2 = false;
                            System.out.print("\nEnter New Group ID (Enter x to exit):  ");
                            groupID = s.nextLine().toUpperCase();
                            valid = exit = AdditionalFuntion.breakLoop(groupID);
                            if (exit) {
                                break;
                            }
                            Iterator<TutorialGroupEntity> iterator2 = groupList.getIterator();
                            while (iterator2.hasNext()) {
                                TutorialGroupEntity tutorialGroup = iterator2.next();
                                if (groupID.equals(tutorialGroup.getGroupID())) {
                                    System.out.println("\n\nResult ---> ");
                                    System.out.println("Group ID: " + tutorialGroup.getGroupID());
                                    System.out.println("Current number of students in the group: " + tutorialGroup.getNumberOfStudents());

                                    if (tutorialGroup.getNumberOfStudents() >= 20) {
                                        System.out.println("\nTHE GROUP HAS REACH THE LIMIT OF 20 STUDENTS. PLEASE TRY ANOTHER GROUP.\n");
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
                                                System.out.println("\nStudent have been successfully changed to this group.\n");
                                            }
                                        } else {
                                            System.out.println("\nCHANGE FAIL\n");
                                        }
                                        valid = true;
                                        valid2 = true;
                                        break;
                                    }
                                }
                            }
                            if (valid == false && valid2 == false) {
                                System.out.println("\nTHE GROUP DOES NOT EXIST! PLEASE TRY AGAIN.\n");
                            }
                        } while (valid == false);
                        valid = true;
                        valid2 = true;
                        break;
                    } else {
                        System.out.println("\nSTUDENT DOES NOT HAVE A GROUP YET. ");
                        valid = true;
                        valid2 = true;
                        break;
                    }
                }
            }
            if (valid == false && valid2 == false) {
                System.out.println("\nTHE STUDENT DOES NOT EXIST! PLEASE TRY AGAIN.\n");
            }
            if (valid == true && valid2 == true) {
                System.out.println("\nDo you want to change more? (Enter x to exit):\n");
                more = s.nextLine();
                valid = exit = AdditionalFuntion.breakLoop(more);
                if (exit) {
                    MessageUI.exitHome();
                    break;
                }
            }

        } while (valid == false);
    }

    public void generateReport() {
        int dft = 0, dcs = 0;
        int dft1 = 0, dft2 = 0, dcs1 = 0, dcs2 = 0;
        int sdft1 = 0, sdft2 = 0, sdcs1 = 0, sdcs2 = 0;
        int sdft = 0, sdcs = 0;
        int totalGroup = 0;
        double avgdft = 0;
        double avgdcs = 0;
        double avgdft1 = 0;
        double avgdft2 = 0;
        double avgdcs1 = 0;
        double avgdcs2 = 0;
        Iterator<TutorialGroupEntity> iterator2 = groupList.getIterator();
        while (iterator2.hasNext()) {
            totalGroup++;
            TutorialGroupEntity tutorialGroup = iterator2.next();
            String slice = tutorialGroup.getGroupID().substring(0, 4);
            switch (slice) {
                case "DFT1":
                    dft++;
                    dft1++;
                    sdft1 += tutorialGroup.getNumberOfStudents();
                    sdft += tutorialGroup.getNumberOfStudents();
                    break;
                case "DFT2":
                    dft++;
                    dft2++;
                    sdft2 += tutorialGroup.getNumberOfStudents();
                    sdft += tutorialGroup.getNumberOfStudents();
                    break;
                case "DCS1":
                    dcs++;
                    dcs1++;
                    sdcs1 += tutorialGroup.getNumberOfStudents();
                    sdcs += tutorialGroup.getNumberOfStudents();
                    break;
                case "DCS2":
                    dcs++;
                    dcs2++;
                    sdcs2 += tutorialGroup.getNumberOfStudents();
                    sdcs += tutorialGroup.getNumberOfStudents();
                    break;
            }
        }
        System.out.println("\n\n========================================================");
        System.out.println("REPORTS ON TUTORIAL GROUP OF FACULTY OF COMPUTER SCIENCE");
        System.out.println("========================================================\n");
        System.out.println("\nTotal Group in FOCS: " + totalGroup);
        System.out.println("\n\nTotal Group in DFT: " + dft);
        System.out.println("\nTotal Group in DCS: " + dcs);
        System.out.println("\n\nTotal Group in DFT Year 1: " + dft1);
        System.out.println("\nTotal Group in DFT Year 2: " + dft2);
        System.out.println("\nTotal Group in DCS Year 1: " + dcs1);
        System.out.println("\nTotal Group in DCS Year 2: " + dcs2);
        System.out.println("\n\nTotal Students in DFT: " + sdft);
        System.out.println("\nTotal Students in DCS: " + sdcs);
        System.out.println("\n\nTotal Students in DFT Year 1: " + sdft1);
        System.out.println("\nTotal Students in DFT Year 2: " + sdft2);
        System.out.println("\nTotal Students in DCS Year 1: " + sdcs1);
        System.out.println("\nTotal Students in DCS Year 2: " + sdcs2);
        if (dft > 0) {
            avgdft = (double) sdft / dft;
        }
        if (dcs > 0) {
            avgdcs = (double) sdcs / dcs;
        }
        if (dft1 > 0) {
            avgdft1 = (double) sdft1 / dft1;
        }
        if (dft2 > 0) {
            avgdft2 = (double) sdft2 / dft2;
        }
        if (dcs1 > 0) {
            avgdcs1 = (double) sdcs1 / dcs1;
        }
        if (dcs2 > 0) {
            avgdcs2 = (double) sdcs2 / dcs2;
        }
        System.out.printf("\n\nAverage number of Students in DFT groups: %.2f", avgdft);
        System.out.printf("\nAverage number of Students in DCS groups: %.2f", avgdcs);
        System.out.printf("\n\nAverage number of Students in DFT Year 1 groups: %.2f", avgdft1);
        System.out.printf("\nAverage number of Students in DFT Year 2 groups: %.2f", avgdft2);
        System.out.printf("\nAverage number of Students in DCS Year 1 groups: %.2f", avgdcs1);
        System.out.printf("\nAverage number of Students in DCS Year 2 groups: %.2f\n\n\n", avgdcs2);
    }
}
