package UI;

import adt.*;
import control.*;
import dao.*;
import entity.Course;
import java.util.Scanner;
import utility.*;

/**
 *
 * @author Ng Jun Yu
 */
public class CourseUI {

    public static void main(String[] args) {
        CourseInitializer c = new CourseInitializer();
        SortedArrayInterface<Course> cList = c.initializer();
        FileManagement file = new FileManagement();
        file.createCourseFile("Course.dat");
        file.saveToCourseFile(cList, "Course.dat");
        AdditionalFuntion af = new AdditionalFuntion();
        CourseManagement cm = new CourseManagement();
        Scanner s = new Scanner(System.in);
        String choice;
        do {
            af.dash();
            System.out.println("Course Management System\n");
            System.out.println("1. Add Course");
            System.out.println("2. Search to delete/edit Course");
            System.out.println("3. List Courses");
            System.out.println("4. Add Programme to Course");
            System.out.println("5. Delete Programme from Course");
            System.out.println("6. Report");
            System.out.println("00. Exit");
            System.out.print("\nEnter your choice-->");
            choice = s.nextLine();
            switch (choice) {
                case "1":
                    af.dash();
                    cm.addCourse();
                    break;
                case "2":
                    af.dash();
                    cm.searchDeleteEdit();
                    break;
                case "3":
                    af.dash();
                    cm.listCourse();
                    break;
                case "4":
                    af.dash();
                    cm.addProgramme();
                    break;
                case "5":
                    af.dash();
                    cm.deleteProgramme();
                    break;
                case "6":
                    af.dash();
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
}
