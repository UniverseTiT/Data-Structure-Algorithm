/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;


import control.ProgrammeMaintenance;

/**
 *
 * @author Tan Chee Fung
 */
public class ProgrammeManagementUI {

    public static void main(String[] args) {
        ProgrammeMaintenance programmeMaintenance = new ProgrammeMaintenance();
        int choice;

        do {
            choice = programmeMaintenance.getMenuChoice();
            programmeMaintenance.runProgrammeMenu(choice);
        } while (choice != 0 && choice != 6);

    }
}
