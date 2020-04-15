package com.KCR;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.*;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateHelper {

    private static final DateTimeFormatter localDateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Returns true if local date can be parsed
    private static boolean localDateIsValid(String dateStr) {
        try {
            LocalDate retDate = LocalDate.parse(dateStr, localDateformatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    // Returns true if date can be parsed
    private static boolean dateIsValid(String dateStr) {
        try {
            Date retDate = dateformatter.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }


    public static long getDays(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate).getDays();
    }

    public static long getDays(Date startDate, Date endDate){
        return TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);
    }

    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, localDateformatter);
    }
    public static Date parseDate(String dateStr) throws ParseException {
        return dateformatter.parse(dateStr);
    }

    // Returns the date as dd/mm yyyy - e.g. 28/05 2019
    public static String dateToString(LocalDate date) {
        return date.format(localDateformatter);
    }
    public static String dateToString(Date date) {
        date = new Date(date.getTime() - 3600 * 1000);
        return dateformatter.format(date); }

    // Asks the user to input a date. Only returns once date in correct format is given
    public static LocalDate getValidLocalDateFromUser(String title) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("%n%s: ", title);

        String date = scan.nextLine(); // Getting input from user

        boolean validDate = localDateIsValid(date);

        while (!validDate) {
            System.out.printf("%s does not match with the format yyyy-MM-dd! Please try again.", date);
            System.out.printf("%n%s: ", title);
            date = scan.nextLine();
            validDate = localDateIsValid(date);
        }
        return parseLocalDate(date);
    }

    public static Date getValidDateFromUser(String title) throws ParseException {
        Scanner scan = new Scanner(System.in);
        System.out.printf("%n%s: ", title);

        String date = scan.nextLine(); // Getting input from user

        boolean validDate = dateIsValid(date);

        while (!validDate) {
            System.out.printf("%s does not match with the format yyyy-MM-dd HH:mm:ss! Please try again.", date);
            System.out.printf("%n%s: ", title);
            date = scan.nextLine();
            validDate = dateIsValid(date);
        }
        return parseDate(date);
    }


}
