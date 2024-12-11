/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Scanner;

/**
 *
 * @author Muhammad Amir Hail Bin Mohamad Hazi
 */
public class MessageUI {

    public static void dataInsertedError() {
        System.out.println("Your input data is failed to record.");
    }

    public static void wrongFormat() {
        System.out.println("Your input data is wrong format.");
    }

    public static void nullValue() {
        System.out.println("Please enter the mandatory field.");
    }

    public static void invalidChoice() {
        System.out.println("Invalid choice. Please select a valid option.");
    }

    public static void exitHome() {
        System.out.println("Back to home page...");
    }
    
    public static void notFound(){
        System.out.println("Record not found.");
    }
    
    public static void nameFormat(){
        System.out.println("Please follow this format...(e.g. AAAA1111)");
    }

    public static void pressContinue() {
        Scanner s = new Scanner(System.in);
        System.out.print("Press any key to continue...");
        s.nextLine();
    }
    
    public static void recordExist(){
        System.out.println("Your entered record is already existed.");
    }
}
