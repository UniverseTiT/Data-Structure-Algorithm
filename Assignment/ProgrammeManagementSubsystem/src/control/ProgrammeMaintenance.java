/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ListInterface;
import adt.SortedArrayList;
import dao.ProgrammeDAO;
import entity.Programme;
import entity.TutorialGroup;
import java.util.*;
import utility.MessageUI;

/**
 *
 * @author Tan Chee Fung
 */
public class ProgrammeMaintenance {

    private ListInterface<Programme> pList = new SortedArrayList<>();
    private String fileName = "programme.dat";
    ProgrammeDAO programmeDAO = new ProgrammeDAO();
    Scanner scanner = new Scanner(System.in);
    private String code, name;

    public ProgrammeMaintenance() {
        pList = programmeDAO.retrieveFromProgrammeFile(fileName);
    }

    public int getMenuChoice() {
        int choice = -1;
        while (choice < 0 || choice > 6) {
            try {
                System.out.println("-----------------Programme Management------------------\n");
                System.out.println("   1. Programme List                                   \n");
                System.out.println("   2. Add new programme                                \n");
                System.out.println("   3. Find a programme to edit or delete               \n");
                System.out.println("   4. Management of assign tutorial group to programme \n");
                System.out.println("   5. Programme Report                                 \n");
                System.out.println("   6. Back to Main Menu                                \n");
                System.out.println("   0. Quit                                             \n");
                System.out.println("-------------------------------------------------------\n");
                System.out.print("Enter choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
            } catch (InputMismatchException e) {
                MessageUI.displayInvalidChoiceMessage();
                scanner.nextLine();
            }
        }

        return choice;
    }

    public void runProgrammeMenu(int choice) {

        switch (choice) {
            case 0:
                MessageUI.displayExitMessage();
                System.exit(0);
                break;
            case 1:
                listAllProgramme();
                break;
            case 2:
                addNewProgramme();
                break;
            case 3:
                searchAndEditProgramme();
                break;
            case 4:
                manageTutorialGroupsForProgramme();
                break;
            case 5:
                generateProgrammeReport();
                break;
            case 6:
                break;
            default:
                MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void listAllProgramme() {
        System.out.println("Programme code\tProgramme name");
        System.out.println(pList);
    }

    public String inputProgrammeCode() {
        System.out.print("Enter programme code: ");
        String code = scanner.nextLine().toUpperCase();
        return code;
    }

    public String inputProgrammeName() {
        System.out.print("Enter programme name: ");
        String name = scanner.nextLine();
        return name;
    }

    public Programme inputProgrammeDetails() {
        String programmeCode;
        String programmeName;

        do {
            programmeCode = inputProgrammeCode();
            if (programmeCode.trim().isEmpty()) {
                MessageUI.displayInvalidNullMessage();
            }
        } while (programmeCode.trim().isEmpty());

        do {
            programmeName = inputProgrammeName();
            if (programmeName.trim().isEmpty()) {
                MessageUI.displayInvalidNullMessage();
            }
        } while (programmeName.trim().isEmpty());

        System.out.println();
        return new Programme(programmeCode.toUpperCase(), programmeName);
    }

    public void addNewProgramme() {
        Programme newProgramme = inputProgrammeDetails();
        pList.addEntry(newProgramme);
        programmeDAO.saveToProgrammeFile(pList, fileName);
    }

    public void searchAndEditProgramme() {
        Iterator<Programme> iterator = pList.getIterator();
        boolean valid = true;

        do {
            System.out.print("Enter the programme code to search: ");
            String code = scanner.nextLine().toUpperCase();
            Programme findProgramme = new Programme(code);
            boolean match = pList.findEntry(findProgramme);

            if (match) {
                valid = processMatchedProgramme(code, iterator);
            } else {
                MessageUI.displayNotFoundMessage();
                valid = false;
            }
        } while (!valid);
    }

    private boolean processMatchedProgramme(String code, Iterator<Programme> iterator) {
        boolean valid = true;

        if (code != null) {
            while (iterator.hasNext()) {
                Programme matchedProgramme = iterator.next();
                if (code.equals(matchedProgramme.getCode())) {
                    displayProgrammeInfo(matchedProgramme);
                    int choice = getUserChoice();

                    switch (choice) {
                        case 1:
                            valid = editProgramme(matchedProgramme);
                            break;
                        case 2:
                            boolean deleteProgramme = pList.removeEntry(matchedProgramme);
                            programmeDAO.saveToProgrammeFile(pList, fileName);
                            MessageUI.displayEditMessage(deleteProgramme);
                            break;
                        case 3:
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                            break;
                    }
                }
            }
        } else {
            MessageUI.displayNotFoundMessage();
            valid = false;
        }

        return valid;
    }

    private void displayProgrammeInfo(Programme programme) {
        System.out.println("Result:\n");
        System.out.println("Programme Code: " + programme.getCode());
        System.out.println("Course Name: " + programme.getName());
        System.out.println("Options:");
        System.out.println("1. Edit");
        System.out.println("2. Delete");
        System.out.println("3. Return to programme menu");
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private boolean editProgramme(Programme programme) {
        Programme editProgramme = inputProgrammeDetails();
        boolean changeProgramme = pList.amendEntry(programme, editProgramme);
        programmeDAO.saveToProgrammeFile(pList, fileName);
        MessageUI.displayEditMessage(changeProgramme);
        return changeProgramme;
    }

    public void manageTutorialGroupsForProgramme() {
        Iterator<Programme> iterator = pList.getIterator();
        boolean valid = true;
        do {

            System.out.println("Available Programmes:");
            listAllProgramme();

            String programmeCode = inputProgrammeCode();
            Programme findProgramme = new Programme(programmeCode);
            boolean found = pList.findEntry(findProgramme);
            if (found) {
                if (programmeCode != null) {
                    while (iterator.hasNext()) {
                        Programme c = iterator.next();
                        if (programmeCode.equals(c.getCode())) {
                            // Display the program's current tutorial groups
                            System.out.println("Tutorial Groups for " + c.getName() + ":");
                            ListInterface<TutorialGroup> tutorialGroups = c.getTutorialGroupProgramme();
                            if (tutorialGroups.getNumberOfEntries() == 0) {
                                System.out.println("None.");
                            } else {
                                int tutorialGroupCounter = 1;

                                Iterator<TutorialGroup> tutorialGroupIterator = tutorialGroups.getIterator();
                                while (tutorialGroupIterator.hasNext()) {
                                    TutorialGroup tutorialGroup = tutorialGroupIterator.next();
                                    System.out.println(tutorialGroupCounter + ". " + tutorialGroup.toString());
                                    tutorialGroupCounter++;
                                }
                            }

                            int choice;
                            // Prompt the user to add or remove tutorial groups
                            System.out.println("Options:");
                            System.out.println("1. Add a Tutorial Group");
                            System.out.println("2. Remove Tutorial Group(The latest group)");
                            System.out.println("3. Back to Programme Menu");
                            do {
                                System.out.print("Enter your choice: ");
                                choice = scanner.nextInt();
                                scanner.nextLine();

                                switch (choice) {
                                    case 1:
                                        String newTutorialGroupName = generateNewTutorialGroupName(c);
                                        TutorialGroup newTutorialGroup = new TutorialGroup(newTutorialGroupName);
                                        c.addTutorialGroup(newTutorialGroup);
                                        programmeDAO.saveToProgrammeFile(pList, fileName);
                                        System.out.println(newTutorialGroupName + " added successfully.");
                                        break;
                                    case 2:
                                        if (tutorialGroups.getNumberOfEntries() == 0) {
                                            System.out.println("No tutorial group,error to remove!");
                                        } else {
                                            boolean removedLast = removeLastTutorialGroup(c);
                                            programmeDAO.saveToProgrammeFile(pList, fileName);
                                            if (removedLast) {
                                                System.out.println("Last tutorial group removed successfully.");
                                            }
                                        }
                                        break;
                                    case 3:
                                        break;
                                    default:
                                        MessageUI.displayInvalidChoiceMessage();
                                        break;
                                }
                            } while (choice != 3);
                        }
                    }
                } else {
                    MessageUI.displayInvalidNullMessage();
                    valid = false;
                }
            } else {
                MessageUI.displayNotFoundMessage();
                valid = false;
            }
        } while (!valid);

    }

    private String generateNewTutorialGroupName(Programme programme) {
        int nextGroupNumber = programme.getTutorialGroupProgramme().getNumberOfEntries() + 1;
        return programme.getCode() + "G" + nextGroupNumber;
    }

    private boolean removeLastTutorialGroup(Programme programme) {
        ListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupProgramme();
        int size = tutorialGroups.getNumberOfEntries();
        if (size > 0) {
            TutorialGroup lastTutorialGroup = tutorialGroups.getEntry(size);
            return tutorialGroups.removeEntry(lastTutorialGroup);
        }
        return false;
    }

    public void generateProgrammeReport() {
        System.out.println("------------------- Programme Report ------------------\n");
        int totalProgrammes = pList.getNumberOfEntries();
        int totalTutorialGroups = 0;

        Iterator<Programme> iterator = pList.getIterator();
        while (iterator.hasNext()) {
            Programme programme = iterator.next();
            String programmeCode = programme.getCode();
            String programmeName = programme.getName();
            ListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupProgramme();
            int tutorialGroupCount = tutorialGroups.getNumberOfEntries();

            System.out.println("Programme Code: " + programmeCode);
            System.out.println("Programme Name: " + programmeName);
            System.out.println("Number of Tutorial Groups: " + tutorialGroupCount);

            if (tutorialGroupCount > 0) {
                System.out.println("Tutorial Groups:");
                int tutorialGroupCounter = 1;
                Iterator<TutorialGroup> tutorialGroupIterator = tutorialGroups.getIterator();
                while (tutorialGroupIterator.hasNext()) {
                    TutorialGroup tutorialGroup = tutorialGroupIterator.next();
                    System.out.println(tutorialGroupCounter + ". " + tutorialGroup.toString());
                    tutorialGroupCounter++;
                }
            }

            System.out.println();
            totalTutorialGroups += tutorialGroupCount;
        }
        System.out.println("\n-------------------------------------------------------\n");
        System.out.println("Total Programmes: " + totalProgrammes);
        System.out.println("Total Tutorial Groups: " + totalTutorialGroups);
        System.out.println("\n-------------------------------------------------------\n");
    }

}
