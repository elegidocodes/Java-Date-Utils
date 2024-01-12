package com.elegido.codes.date.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class providing methods for date and time calculations.
 *
 * @author Fernando Canul Caballero
 * @version 1.0.0
 * @since 2024-01-12
 */
public class DateConverter {

    /**
     * Array containing possible date formats used in date string parsing.
     * The array includes commonly used date format patterns to accommodate different date representations.
     * Developers can extend or modify this array based on specific date formats used in their application.
     */
    private static final String[] possibleDateFormats = {
            "yyyy-MM-dd HH:mm:ss.SSSSSS",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH",
            "yyyy-MM-dd",
            "HH",
            "HH:mm",
            "HH:mm:ss",
            "HH:mm:ss.SSSSSS"
    };

    /**
     * Utility method to extract the number of hours from a given Date object.
     *
     * @param date The Date object from which to extract hours.
     * @return The number of hours represented by the provided Date.
     */
    public static long getHoursFromDate(Date date) {
        long milliseconds = date.getTime();
        return (milliseconds / (60 * 60 * 1000)); // Returns the hours
    }

    /**
     * Utility method to extract the number of hours from a given date string by attempting various date formats.
     * If the provided date string cannot be parsed using any of the specified date formats,
     * the method returns -1 to indicate parsing failure.
     *
     * @param date The date string to parse and extract hours from.
     * @return The number of hours represented by the parsed date, or -1 if parsing fails.
     * @deprecated Use {@link #getHoursFromDateWithFormat(String, String)} instead.
     */
    @Deprecated
    public static long getHoursFromDate(String date) {
        for (String format : possibleDateFormats) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                Date dateObject = simpleDateFormat.parse(date);
                if (dateObject != null) {
                    long milliseconds = dateObject.getTime();
                    return (milliseconds / (60 * 60 * 1000)); // Returns the hours
                }
            } catch (ParseException e) {
                // Date parsing failed for this format, try the next one
                //e.printStackTrace();
                System.err.println("Unparseable date");
            }
        }
        return -1;
    }

    /**
     * Utility method to extract the number of hours from a given date string using a specified date format.
     * If the provided date string cannot be parsed using the specified date format,
     * the method returns -1 to indicate parsing failure.
     *
     * @param date   The date string to parse and extract hours from.
     * @param format The date format pattern to use for parsing the provided date string.
     * @return The number of hours represented by the parsed date, or -1 if parsing fails.
     */
    public static long getHoursFromDateWithFormat(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            Date dateObject = simpleDateFormat.parse(date);
            if (dateObject != null) {
                long milliseconds = dateObject.getTime();
                return (milliseconds / (60 * 60 * 1000)); // Returns the hours
            }
        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Utility method to calculate the number of hours between two date strings using various date formats.
     * If either of the provided date strings cannot be parsed using any of the specified date formats,
     * the method returns -1 to indicate parsing failure.
     *
     * @param fromDate The starting date string.
     * @param toDate   The ending date string.
     * @return The number of hours between the two parsed dates, or -1 if parsing fails for any date.
     * @deprecated Use {@link #getHoursBetweenDatesWithFormat(String, String)} instead.
     */
    public static long getHoursBetweenDates(String fromDate, String toDate) {
        for (String format : possibleDateFormats) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                Date date1 = simpleDateFormat.parse(fromDate);
                Date date2 = simpleDateFormat.parse(toDate);

                if (date1 != null && date2 != null) {
                    long date1Hours = date1.getTime();
                    long date2Hours = date2.getTime();
                    return (Math.abs(date1Hours - date2Hours) / (60 * 60 * 1000));
                }
            } catch (ParseException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return -1;
    }

    /**
     * Utility method to calculate the number of hours between a given date string and the current date using a specified date format.
     * If the provided date string cannot be parsed using the specified date format,
     * the method returns -1 to indicate parsing failure.
     *
     * @param date   The date string to calculate hours from.
     * @param format The date format pattern to use for parsing the provided date string.
     * @return The number of hours between the parsed date and the current date, or -1 if parsing fails.
     */
    public static long getHoursBetweenDatesWithFormat(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            Date dateObject = simpleDateFormat.parse(date);
            Date currentDate = new Date();

            if (dateObject != null) {
                long dateHours = dateObject.getTime();
                long currentDateHours = currentDate.getTime();
                return (Math.abs(dateHours - currentDateHours) / (60 * 60 * 1000));
            }
        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Utility method to calculate the number of hours between a given date and the current date.
     *
     * @param date The date object to calculate hours from.
     * @return The number of hours between the provided date and the current date.
     */
    public static long getHoursBetweenDates(Date date) {
        Date currentDate = new Date();
        long dateHours = date.getTime();
        long currentDateHours = currentDate.getTime();
        return (Math.abs(dateHours - currentDateHours) / (60 * 60 * 1000));
    }

    /**
     * Utility method to calculate the number of hours between two date strings using a specified date format.
     * If either of the provided date strings cannot be parsed using the specified date format,
     * the method returns -1 to indicate parsing failure.
     *
     * @param fromDate The starting date string.
     * @param toDate   The ending date string.
     * @param format   The date format pattern to use for parsing the provided date strings.
     * @return The number of hours between the two parsed dates, or -1 if parsing fails for any date.
     */
    public static long getHoursBetweenDatesWithFormat(String fromDate, String toDate, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            Date date1 = simpleDateFormat.parse(fromDate);
            Date date2 = simpleDateFormat.parse(toDate);

            if (date1 != null && date2 != null) {
                long date1Hours = date1.getTime();
                long date2Hours = date2.getTime();
                return (Math.abs(date1Hours - date2Hours) / (60 * 60 * 1000));
            }
        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Utility method to calculate the number of hours between two Date objects.
     *
     * @param fromDate The starting date.
     * @param toDate   The ending date.
     * @return The number of hours between the two dates.
     */
    public static long getHoursBetweenDates(Date fromDate, Date toDate) {
        long date1Hours = fromDate.getTime();
        long date2Hours = toDate.getTime();
        return (Math.abs(date1Hours - date2Hours) / (60 * 60 * 1000));
    }

}
