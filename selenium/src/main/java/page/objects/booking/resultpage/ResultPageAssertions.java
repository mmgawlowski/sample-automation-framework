package page.objects.booking.resultpage;

import driver.waits.Waits;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.objects.booking.BaseAssertions;
import utils.helper.methods.generic.DateAndTime;

import java.util.Locale;

/**
 * The ResultPageAssertions class provides assertion methods related to the result page, which can be used in tests.
 * It has been adapted to the use of fluent interfaces.
 */
public class ResultPageAssertions extends BaseAssertions<ResultPageAssertions> {

    private static final Logger log = LoggerFactory.getLogger(ResultPageAssertions.class);
    private final ResultPageElements resultPageElements;

    private static final String DATE_INPUT_FORMAT = "yyyy-MM-dd";
    private static final String DATE_OUTPUT_FORMAT_PL = "EEEE d LLLL yyyy";
    private static final String DATE_OUTPUT_FORMAT_EN = "EEEE, LLLL d, yyyy";

    /**
     * A class constructor.
     * Creation of a new instance depends on the simple assertion that redirection to the result page occurred.
     *
     * @throws AssertionError if there was no redirection to the result page.
     */
    public ResultPageAssertions() {
        assertRedirectionToResultPage();
        resultPageElements = new ResultPageElements();
    }

    /**
     * Asserts that user has been redirected to the result page.
     */
    private void assertRedirectionToResultPage() {
        assertThatUrlContains("searchresults", false);
    }

    /**
     * Asserts softly that the chosen destination e.g.&nbsp;city/region or accommodation e.g.&nbsp;hotel has been displayed on the result page.
     * At first this method searches in the heading, then in the list of accommodations.
     *
     * @param query the chosen destination.
     * @return an object of this class.
     * @throws AssertionError if the chosen destination couldn't be found on the result page but only if {@link #assertAll()} has been executed.
     */
    @Step("Verifying the found place")
    public ResultPageAssertions assertSoftlyThatFoundPlaceEqualsToExpected(String query) {
        log.info("Verifying that chosen place is displayed on result page");
        String placeFoundMessage = "Chosen place found";
        String failMessage = "Expected place couldn't be found on result page";

        if (defaultHeaderContainsQuery(query)) {
            log.info(placeFoundMessage);
        } else if (skiTypeHeaderContainsQuery(query)) {
            log.info(placeFoundMessage);
        } else if (accommodationsListContainsQuery(query)) {
            log.info(placeFoundMessage);
        } else {
            softly.fail(failMessage);
        }
        return this;
    }

    /**
     * Checks if the default header (h1) on the result page contains the given destination.
     * This is a helper method for {@link #assertSoftlyThatFoundPlaceEqualsToExpected}.
     *
     * @param query the chosen destination.
     * @return true if the header is present and contains the destination in it, otherwise false.
     */
    private Boolean defaultHeaderContainsQuery(String query) {
        try {
            WebElement defaultHeaderWithFoundRegion = resultPageElements.getDefaultHeaderWithFoundRegion();
            Waits.waitUntilElementIsVisible(defaultHeaderWithFoundRegion, 5);
            return StringUtils.containsIgnoreCase(defaultHeaderWithFoundRegion.getText(), query);
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Checks if the special type of header that is displayed for ski-related regions contains the given destination.
     * This is a helper method for {@link #assertSoftlyThatFoundPlaceEqualsToExpected}.
     *
     * @param query the chosen destination.
     * @return true if the header is present and contains the destination in it, otherwise false.
     */
    private Boolean skiTypeHeaderContainsQuery(String query) {
        try {
            return StringUtils.containsIgnoreCase(resultPageElements.getSkiTypeHeaderWithFoundRegion().getText(), query);
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    /**
     * Checks if the list of accommodations displayed on the result page contains the given destination.
     * This is a helper method for {@link #assertSoftlyThatFoundPlaceEqualsToExpected}.
     *
     * @param query the chosen destination.
     * @return true if the list contains the destination, otherwise false.
     */
    private Boolean accommodationsListContainsQuery(String query) {
        try {
            Waits.waitUntilLocatedElementIsVisible(By.xpath(String.format(ResultPageElements.getAccommodationListElementXpath(), query)), 3);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Asserts softly that the check-in date displayed on the result page corresponds to the given input.
     *
     * @param date the chosen check-in date in the format: "yyyy-MM-dd".
     *             It is then converted to the format in which it appears on the results page.
     *             IMPORTANT: the result may vary depending on the locale of the browser.
     * @return an object of this class.
     * @throws AssertionError if the displayed check-in date is different than expected but only if {@link #assertAll} has been executed.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
     */
    @Step("Verifying that check-in date equals to selected")
    public ResultPageAssertions assertSoftlyThatShownCheckInDateEqualsToSelected(String date) {
        log.info("Verifying that check-in date equals to selected");
        String checkInDateFromResultPage = resultPageElements.getCheckInDateFromResultPage().getText();
        String expectedDatePl = DateAndTime.convertDateFormat(DATE_INPUT_FORMAT, DATE_OUTPUT_FORMAT_PL, date, new Locale("pl", "PL"));
        String expectedDateEn = DateAndTime.convertDateFormat(DATE_INPUT_FORMAT, DATE_OUTPUT_FORMAT_EN, date, Locale.ENGLISH);

        if (checkInDateFromResultPage.equalsIgnoreCase(expectedDatePl) || checkInDateFromResultPage.equalsIgnoreCase(expectedDateEn) ) {
            log.info("Displayed check-in date is equal");
        } else {
            softly.fail("Displayed check-in date is not equal to expected!");
        }
        return this;
    }

    /**
     * Asserts softly that the check-out date displayed on the result page corresponds to the given input.
     *
     * @param date the chosen check-out date in the format: "yyyy-MM-dd".
     *             It is then converted to the format in which it appears on the results page.
     *             IMPORTANT: the result may vary depending on the locale of the browser.
     * @return an object of this class.
     * @throws AssertionError if the displayed check-out date is different than expected but only if {@link #assertAll} has been executed.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
     */
    @Step("Verifying that check-out date equals to selected")
    public ResultPageAssertions assertSoftlyThatShownCheckOutDateEqualsToSelected(String date) {
        log.info("Verifying that check-out date equals to selected");
        String checkOutDateFromResultPage = resultPageElements.getCheckOutDateFromResultPage().getText();
        String expectedDatePl = DateAndTime.convertDateFormat(DATE_INPUT_FORMAT, DATE_OUTPUT_FORMAT_PL, date, new Locale("pl", "PL"));
        String expectedDateEn = DateAndTime.convertDateFormat(DATE_INPUT_FORMAT, DATE_OUTPUT_FORMAT_EN, date, Locale.ENGLISH);

        if (checkOutDateFromResultPage.equalsIgnoreCase(expectedDatePl) || checkOutDateFromResultPage.equalsIgnoreCase(expectedDateEn)) {
            log.info("Displayed check-out date is equal");
        } else {
            softly.fail("Displayed check-out date is not equal to expected!");
        }
        return this;
    }

}
