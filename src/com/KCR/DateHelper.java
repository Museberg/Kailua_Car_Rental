package com.KCR;

import java.time.*;
import java.time.format.*;
import java.util.Scanner;

public class DateHelper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Returns true if date can be parsed
    private static boolean isValid(String dateStr) {
        try {
            LocalDate retDate = LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    public static long getDays(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate).getDays();
    }

    public static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }

    // Returns the date as dd/mm yyyy - e.g. 28/05 2019
    public static String dateToString(LocalDate date) {
        return date.format(formatter);
    }

    // Asks the user to input a date. Only returns once date in correct format is given
    public static LocalDate getValidDateFromUser(String title) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("%n%s: ", title);

        String date = scan.nextLine(); // Getting input from user

        boolean validDate = isValid(date);

        while (!validDate) {
            System.out.printf("%s does not match with the format yyyy-MM-dd! Please try again.", date);
            System.out.printf("%n%s: ", title);
            date = scan.nextLine();
            validDate = isValid(date);
        }
        return parseDate(date);
    }


}
