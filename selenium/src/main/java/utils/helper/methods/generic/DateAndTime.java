package utils.helper.methods.generic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * {@code DateAndTime} class contains a set of useful tools for date and time management.
 */
public class DateAndTime {

    /**
     * A method analogous to {@link #dateGenerator(String, int)} but with the default format: "yyyy-MM-dd"
     *
     * @param daysFromToday the number of days to add to the current date.
     * @return a string with the generated date.
     */
    public static String dateGenerator(int daysFromToday) {
        return dateGenerator("yyyy-MM-dd", daysFromToday);
    }

    /**
     * Generates the date in the given format.
     * The generated date is based on the current date and the number of days added.
     *
     * @param format the string representing the desired date format compatible with {@code SimpleDateFormat}.
     * @param daysFromToday the number of days to add to the current date.
     * @return a string with the generated date.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
     */
    public static String dateGenerator(String format, int daysFromToday) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, daysFromToday);
        return dateFormat.format(c.getTime());
    }


    /**
     * Converts the given date from one format to another.
     *
     * @param inputFormat the string that represents the initial format of the given date
     *                    in accordance with {@code SimpleDateFormat}
     * @param outputFormat the string that represents the requested output format of the given date
     *                     in accordance with {@code SimpleDateFormat}
     * @param date the string that represents the given date.
     * @param locale the locale with which the generated date should match.
     *               This is particularly important for formats where the date is expressed in words.
     * @return a string with the given date converted to the output format.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
     */
    public static String convertDateFormat(String inputFormat, String outputFormat, String date, Locale locale) {
        DateFormat dateFormat = new SimpleDateFormat(outputFormat, locale);
        try {
            Date date1 = new SimpleDateFormat(inputFormat).parse(date);
            return dateFormat.format(date1);
        } catch (ParseException e) {
            throw new IllegalStateException("Couldn't parse the given date");
        }
    }

    /**
     * {@link #convertDateFormat(String, String, String, Locale)}, which is set to use the default locale.
     */
    public static String convertDateFormat(String inputFormat, String outputFormat, String date) {
        return convertDateFormat(inputFormat, outputFormat, date, Locale.getDefault());
    }
}
