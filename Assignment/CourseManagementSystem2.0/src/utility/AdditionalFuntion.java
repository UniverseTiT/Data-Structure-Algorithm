/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Ng Jun Yu
 */
public class AdditionalFuntion {

    public static boolean breakLoop(String s) {
        boolean b = false;
        if (s.equalsIgnoreCase("x") || s.equalsIgnoreCase("X")) {
            return true;
        }
        return b;
    }

    public void dash() {
        for (int i = 0; i <= 30; i++) {
            System.out.print("=");
        }
        System.out.println();
    }
}
