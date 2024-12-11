package utility;

/**
 *
 * @author Kat Tan
 */
public class MessageUI {

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice,please enter one of the number 0 to 6 only!");
    }

    public static void displayExitMessage() {
        System.out.println("\nExiting system");
    }

    public static void displayInvalidNullMessage() {
        System.out.println("\nYou haven't enter data!");
    }

    public static boolean displayEditMessage(boolean dataEntered) {
        if (dataEntered) {
            System.out.println("\nYour edits have been successfully updated!");
        } else {
            System.out.println("\nFailed to update your edits!");
        }

        return dataEntered;
    }

    public static void displayNotFoundMessage() {
        System.out.println("\nYou don't have this data!");
    }
}
