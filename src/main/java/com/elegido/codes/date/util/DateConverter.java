package com.elegido.codes.date.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Utility class providing methods for date and time calculations.
 *
 * @author Fernando Canul Caballero
 * @version 1.0.0
 * @since 2024-01-12
 */
public class DateConverter {

    public static final int MILLIS = 0;
    public static final int SECONDS = 1;
    public static final int MINUTES = 2;
    public static final int HOURS = 3;
    public static final int DAYS = 4;

    //public static void main(String[] args) {}

    /**
     * Utility method to retrieve the current date and time formatted according to the specified format.
     *
     * @param format The desired format for the output date string.
     * @return A formatted date string representing the current date and time.
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        // Return the formatted date string representing the current date and time.
        return simpleDateFormat.format(new Date());
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */

    /**
     * Utility method to calculate the number of hours between a given Date object and the current date.
     *
     * @param date The Date object from which to calculate hours.
     * @return The absolute number of hours between the provided Date and the current date.
     */
    public static long getHoursFromDate(Date date) {
        long milliseconds = Math.abs(date.getTime() - new Date().getTime());
        return TimeUnit.MILLISECONDS.toHours(milliseconds); // Returns the hours
    }

    /**
     * Utility method to calculate the number of hours between a given date string and the current date.
     * The date string is parsed using the specified format.
     *
     * @param date   The date string to calculate hours from.
     * @param format The format pattern of the input date string.
     * @return The absolute number of hours between the parsed date and the current date.
     * If parsing fails, the method returns -1.
     */
    public static long getHoursFromDate(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            Date dateObject = simpleDateFormat.parse(date);

            if (dateObject != null) {
                long milliseconds = Math.abs(dateObject.getTime() - new Date().getTime());
                return TimeUnit.MILLISECONDS.toHours(milliseconds); // Returns the hours
            }
        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Return -1 if parsing fails
        return -1;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */

    /**
     * Utility method to calculate the time difference between a given Date and the current date and time,
     * and convert it to a specified time unit.
     *
     * @param date   The Date object to calculate the time difference from.
     * @param toUnit The desired time unit to convert to.
     *               Use constants from {@link DateConverter} (e.g., {@code DateConverter.SECONDS}).
     * @return The time value in the specified unit.
     * If the specified unit is not recognized, the method returns -1.
     */
    public static long getTimeUnitFromDate(Date date, final int toUnit) {
        long timeInMillis = Math.abs(date.getTime() - new Date().getTime());
        return switch (toUnit) {
            case MILLIS -> timeInMillis;
            case SECONDS -> TimeUnit.MILLISECONDS.toSeconds(timeInMillis);
            case MINUTES -> TimeUnit.MILLISECONDS.toMinutes(timeInMillis);
            case HOURS -> TimeUnit.MILLISECONDS.toHours(timeInMillis);
            case DAYS -> TimeUnit.MILLISECONDS.toDays(timeInMillis);
            default -> -1;
        };
    }

    /**
     * Utility method to calculate the time difference between a given date string and the current date and time,
     * and convert it to a specified time unit.
     *
     * @param date   The date string to calculate the time difference from.
     * @param format The format pattern of the input date string.
     * @param toUnit The desired time unit to convert to.
     *               Use constants from {@link DateConverter} (e.g., {@code DateConverter.SECONDS}).
     * @return The time value in the specified unit.
     * If the input date string cannot be parsed or the specified unit is not recognized, the method returns -1.
     */
    public static long getTimeUnitFromDate(String date, String format, final int toUnit) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            Date dateObject = simpleDateFormat.parse(date);
            long timeInMillis = Math.abs(dateObject.getTime() - new Date().getTime());
            return switch (toUnit) {
                case MILLIS -> timeInMillis;
                case SECONDS -> TimeUnit.MILLISECONDS.toSeconds(timeInMillis);
                case MINUTES -> TimeUnit.MILLISECONDS.toMinutes(timeInMillis);
                case HOURS -> TimeUnit.MILLISECONDS.toHours(timeInMillis);
                case DAYS -> TimeUnit.MILLISECONDS.toDays(timeInMillis);
                default -> -1;
            };
        } catch (ParseException e) {
            return -1;
        }
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */

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
    public static long getHoursBetweenDates(String fromDate, String toDate, String format) {
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

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */

    /**
     * Utility method to change the format of a date string and localize it based on the specified language and region.
     *
     * @param date        The date string to be formatted.
     * @param inputFormat The format of the input date string.
     * @param language    The language code for localization (e.g., "en" for English).
     * @param region      The region code for localization (e.g., "US" for the United States).
     * @return A formatted and localized date string.
     */
    public static String changeDateFormat(String date, String inputFormat, String language, String region) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat, Locale.getDefault());
        Date dateObject = null;

        try {
            dateObject = inputDateFormat.parse(date);
        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
        }

        Locale loc = new Locale.Builder().setLanguage(language).setRegion(region).build();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, loc);

        // Return the formatted and localized date string or the current date if parsing fails.
        return dateObject != null ? dateFormat.format(dateObject) : dateFormat.format(new Date());
    }

    /**
     * Utility method to format a Date object and localize it based on the specified language and region.
     *
     * @param date     The Date object to be formatted.
     * @param language The language code for localization (e.g., "en" for English).
     * @param region   The region code for localization (e.g., "US" for the United States).
     * @return A formatted and localized date string representing the provided Date object.
     */
    public static String changeDateFormat(Date date, String language, String region) {
        Locale locale = new Locale.Builder().setLanguage(language).setRegion(region).build();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);

        // Return the formatted and localized date string.
        return dateFormat.format(date);
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */

    /**
     * Utility method to change the format of a date string and localize it based on the specified style and locale.
     *
     * @param date        The date string to be formatted.
     * @param inputFormat The format of the input date string.
     * @param style       The style for date formatting (e.g., 'DateFormat.FULL', 'DateFormat.SHORT').
     * @param locale      The locale for localization.
     * @return A formatted and localized date string.
     */
    public static String changeDateFormat(String date, String inputFormat, int style, Locale locale) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat, Locale.getDefault());
        Date dateObject = null;

        try {
            dateObject = inputDateFormat.parse(date);
        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
        }

        DateFormat dateFormat = DateFormat.getDateInstance(style, locale);

        // Return the formatted and localized date string or the current date if parsing fails.
        return dateObject != null ? dateFormat.format(dateObject) : dateFormat.format(new Date());
    }

    /**
     * Utility method to format a Date object and localize it based on the specified style and locale.
     *
     * @param date   The Date object to be formatted.
     * @param style  The style for date formatting (e.g., 'DateFormat.FULL', 'DateFormat.SHORT').
     * @param locale The locale for localization.
     * @return A formatted and localized date string.
     */
    public static String changeDateFormat(Date date, int style, Locale locale) {
        DateFormat dateFormat = DateFormat.getDateInstance(style, locale);

        // Return the formatted and localized date string.
        return dateFormat.format(date);
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */

    /**
     * Utility method to change the format of a date string.
     *
     * @param date         The date string to be formatted.
     * @param inputFormat  The format of the input date string.
     * @param outputFormat The desired format for the output date string.
     * @return A formatted date string based on the specified input and output formats.
     * If the conversion fails, the original date string is returned.
     */
    public static String changeDateFormat(String date, String inputFormat, String outputFormat) {
        try {
            SimpleDateFormat inputSdf = new SimpleDateFormat(inputFormat);
            Date dateObject = inputSdf.parse(date);

            if (dateObject != null) {
                SimpleDateFormat outputSdf = new SimpleDateFormat(outputFormat);
                return outputSdf.format(dateObject);
            }
        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Return original date if conversion fails
        return date;
    }

    /**
     * Utility method to format a Date object based on a specified output format.
     *
     * @param date         The Date object to be formatted.
     * @param outputFormat The desired format for the output date string.
     * @return A formatted date string based on the specified output format.
     */
    public static String changeDateFormat(Date date, String outputFormat) {
        SimpleDateFormat outputSdf = new SimpleDateFormat(outputFormat);

        // Return the formatted date string.
        return outputSdf.format(date);
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */

    /**
     * Utility method to format the time difference between a given date string and the current date and time.
     * The result is formatted as HH:mm:ss.
     *
     * @param date   The date string to calculate the time difference from.
     * @param format The format pattern of the input date string.
     * @return A formatted time string representing the time difference in HH:mm:ss format.
     * If the input date string cannot be parsed, the method returns "00:00:00".
     */
    public static String getFormattedTime(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            Date dateObject = simpleDateFormat.parse(date);
            long timeInMillis = Math.abs(dateObject.getTime() - new Date().getTime());

            long seconds = TimeUnit.MILLISECONDS.toSeconds(timeInMillis) % 60;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(timeInMillis) % 60;
            long hours = TimeUnit.MILLISECONDS.toHours(timeInMillis) % 24;

            // Format the time difference as HH:mm:ss
            return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        } catch (ParseException e) {
            // Return "00:00:00" if parsing fails
            return "00:00:00";
        }
    }

    /**
     * Utility method to format the time difference between a given Date object and the current date and time.
     * The result is formatted as HH:mm:ss.
     *
     * @param date The Date object to calculate the time difference from.
     * @return A formatted time string representing the time difference in HH:mm:ss format.
     */
    public static String getFormattedTime(Date date) {
        long timeInMillis = Math.abs(date.getTime() - new Date().getTime());

        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeInMillis) % 60;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeInMillis) % 60;
        long hours = TimeUnit.MILLISECONDS.toHours(timeInMillis) % 24;

        // Format the time difference as HH:mm:ss
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Utility class containing commonly used date format patterns.
     * These patterns can be used for parsing and formatting date strings.
     */
    public static class Format {
        public static final String FORMAT_1 = "yyyy-MM-dd HH:mm:ss.SSSSSS";
        public static final String FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
        public static final String FORMAT_3 = "yyyy-MM-dd HH:mm";
        public static final String FORMAT_4 = "yyyy-MM-dd HH";
        public final static String FORMAT_5 = "yyyy-MM-dd";
        public final static String FORMAT_6 = "yyyy-MM";
        public final static String FORMAT_7 = "yyyy";
        public final static String FORMAT_8 = "MM";
        public final static String FORMAT_9 = "dd";
        public final static String FORMAT_10 = "yyyy-dd";
        public final static String FORMAT_11 = "HH";
        public final static String FORMAT_12 = "HH:mm";
        public final static String FORMAT_13 = "HH:mm:ss";
        public final static String FORMAT_14 = "HH:mm:ss.SSSSSS";
        public final static String FORMAT_15 = "yyyy/MM/dd";
        public final static String FORMAT_16 = "dd/MM/yyyy";
    }

}
