package com.KCR;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetInput {

    // Returns a integer between minInt..maxInt
    public static int getOptionFromUser(int minInt, int maxInt) {
        Scanner scan = new Scanner(System.in);
        int retNumber;
        boolean isNotValid = false;

        System.out.printf("%nSelect: ");
        do {
            if (isNotValid) {
                System.out.printf("The number must be between %d and %d%n", minInt, maxInt);
                System.out.printf("Select: ");
            }
            while (!scan.hasNextInt()) {
                System.out.printf("That is not a number! Please try again.%n");
                System.out.printf("Select: ");
                scan.next();
            }
            retNumber = scan.nextInt();
            isNotValid = retNumber < minInt || retNumber > maxInt;
        } while (isNotValid);

        return retNumber;
    }

    // Gets a phone number from user
    public static int getPhoneNumberFromUser() {
        Scanner scan = new Scanner(System.in);

        while (!scan.hasNextInt()) {
            System.out.printf("The entered phone number %s is not a number! Please try again%n", scan.nextLine());
        }
        return scan.nextInt();
    }

    // Gets an int from user
    public static int getIntFromUser(String title) {
        Scanner scanInput = new Scanner(System.in);
        System.out.printf("%n%s: ", title);

        while (!scanInput.hasNextInt()) {
            System.out.printf("The input '%s' is not a number. Please try again.%n", scanInput.nextLine());
            System.out.printf("%s: ", title);
        }
        return scanInput.nextInt();
    }

    // Gets a double from user
    public static double getDoubleFromUser() {
        Scanner scanInput = new Scanner(System.in);

        while (!scanInput.hasNextDouble()) {
            System.out.printf("The input '%s' is not a number. Please try again.%n", scanInput.nextLine());
            System.out.printf("Select: ");
        }
        return scanInput.nextDouble();
    }

    // Gets a string from user without numbers in it
    public static String getStringFromUser(String title) {
        Scanner scanInput = new Scanner(System.in);

        System.out.printf("Please enter %s%n", title);
        String name = scanInput.next();
        while (name.matches(".*\\d.*")) {
            System.out.printf(title + " cannot contain a number! Please try again%n");
            name = scanInput.next();
        }
        return name;
    }

    // Gets a string from user below or at a certain length
    public static String getStringOfLength(int length, String title) {
        Scanner scanInput = new Scanner(System.in);
        System.out.printf("%n%s: ", title);

        String name = scanInput.next();
        while (name.length() > length) {
            System.out.printf("%s cannot contain more than %d characters! Please try again%n", title, length);
            System.out.printf("%s: ", title);
            name = scanInput.next();
        }
        return name;
    }

    // Gets a string without whitespace. Useful for usernames
    public static String getUsernameFromUser() {
        Scanner scanInput = new Scanner(System.in);
        String userName = scanInput.nextLine();
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(userName);
        boolean found = matcher.find();
        while (found) {
            System.out.printf("A username cannot contain whitespace! Please try again %n");
            userName = scanInput.nextLine();
            found = matcher.find();
        }

        return userName;
    }
}