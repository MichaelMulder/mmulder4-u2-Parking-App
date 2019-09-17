package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        while (choice != 3) {
            String input = keyboard.nextLine();
            choice = Integer.parseInt(input);
            // Display Menu
            switch (choice) {

                case 1:
                    // CheckIn
                    // Submit to CheckOut
                    // Display CheckOut Menu
                case 3:
                    // Close Garage
                    // Display summary of activity
                    // Exit program
                default:
                    System.exit(0);

            }
        }
    }
}
