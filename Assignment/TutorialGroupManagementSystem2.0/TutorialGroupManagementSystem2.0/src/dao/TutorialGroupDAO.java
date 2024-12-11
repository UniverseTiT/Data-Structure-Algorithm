/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.*;
import java.io.*;
import utility.*;
/**
 *
 * @author Muhammad Amir Hail Bin Mohamad Hazi
 */
public class TutorialGroupDAO {

    public static ListInterface<TutorialGroupEntity> retrieveTutorialGroupFrom(String fileName) {
        File file = new File(fileName);
        ListInterface<TutorialGroupEntity> groupList = new SortedArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            groupList = (SortedArrayList<TutorialGroupEntity>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return groupList;
        }
    }

    public void saveTutorialGroupToFile(ListInterface<TutorialGroupEntity> groupList, String fileName) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(groupList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Cannot save to file");
        }
    }

    public void createFile(String fileName) {
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static ListInterface<Student> retrieveStudentFrom(String fileName) {
        File file = new File(fileName);
        ListInterface<Student> sList = new SortedArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            sList = (SortedArrayList<Student>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return sList;
        }
    }

    public void saveStudentToFile(ListInterface<Student> sList, String fileName) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(sList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Cannot save to file");
        }
    }

}
