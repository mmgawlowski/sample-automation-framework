package utils.helper.methods;

import java.util.ArrayList;
import java.util.List;

public class BookingCalendar {

    /**
     * Converts the specified date in "d-M-yyyy" format
     * to match the values of the options specified for the Select WebElement on the home page.
     *
     * @param date the string representing the given date.
     * @return the converted date as a list of strings, e.g. 05 January 2020: input-"5-1-2020", output-"5, 0-2020"
     * @throws IllegalStateException when the input is invalid.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
     */
    public static List<String> transformDateforSelect(String date) {
        String[] dateArr = date.split("-");
        List<String> transformedDate = new ArrayList<>();
        transformedDate.add(dateArr[0]);

        switch (dateArr[1]) {
            case "1":
                transformedDate.add("0-" + dateArr[2]);
                return transformedDate;
            case "2":
                transformedDate.add("1-" + dateArr[2]);
                return transformedDate;
            case "3":
                transformedDate.add("2-" + dateArr[2]);
                return transformedDate;
            case "4":
                transformedDate.add("3-" + dateArr[2]);
                return transformedDate;
            case "5":
                transformedDate.add("4-" + dateArr[2]);
                return transformedDate;
            case "6":
                transformedDate.add("5-" + dateArr[2]);
                return transformedDate;
            case "7":
                transformedDate.add("6-" + dateArr[2]);
                return transformedDate;
            case "8":
                transformedDate.add("7-" + dateArr[2]);
                return transformedDate;
            case "9":
                transformedDate.add("8-" + dateArr[2]);
                return transformedDate;
            case "10":
                transformedDate.add("9-" + dateArr[2]);
                return transformedDate;
            case "11":
                transformedDate.add("10-" + dateArr[2]);
                return transformedDate;
            case "12":
                transformedDate.add("11-" + dateArr[2]);
                return transformedDate;
            default:
                throw new IllegalStateException(
                        String.format("Unexpected value: %s. Specify the month using only numbers from 1 to 12", dateArr[1]));
        }
    }
}
