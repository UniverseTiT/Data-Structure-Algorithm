/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.Programme;
import java.io.*;

/**
 *
 * @author Tan Chee Fung
 */
public class ProgrammeDAO {
       
  public void saveToProgrammeFile(ListInterface<Programme> pList,String fileName) {
    fileName = "programme.dat";
      File file = new File(fileName);
    try {
      ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
      ooStream.writeObject(pList);
      ooStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nFile not found");
    } catch (IOException ex) {
      System.out.println("\nCannot save to file");
    }
  }

  public ListInterface<Programme> retrieveFromProgrammeFile(String fileName) {
    fileName = "programme.dat";
      File file = new File(fileName);
    ListInterface<Programme> pList = new SortedArrayList<>();
    try {
      ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
      pList = (SortedArrayList<Programme>) (oiStream.readObject());
      oiStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nNo such file.");
    } catch (IOException ex) {
      System.out.println("\nCannot read from file.");
    } catch (ClassNotFoundException ex) {
      System.out.println("\nClass not found.");
    } finally {
      return pList;
    }
  }
}
