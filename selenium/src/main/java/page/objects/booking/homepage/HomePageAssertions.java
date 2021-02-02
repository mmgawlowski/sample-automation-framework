package page.objects.booking.homepage;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.objects.booking.BaseAssertions;

/**
 * The HomePageAssertions class provides assertion methods related to the home page, which can be used in tests.
 * It has been adapted to the use of fluent interfaces.
 */
public class HomePageAssertions extends BaseAssertions<HomePageAssertions> {

    private static final Logger log = LoggerFactory.getLogger(HomePageAssertions.class);
    private final HomePageElements homePageElements;

    public HomePageAssertions() {
        homePageElements = new HomePageElements();
    }

    /**
     * Asserts that the home page has been loaded and its title contains phrase "Booking.com".
     *
     * @return an object of this class.
     * @throws AssertionError if title doesn't contain demanded phrase.
     */
    @Step("Verifying that home page has been loaded")
    public HomePageAssertions assertThatHomePageHasBeenLoaded() {
        log.info("Verifying that home page has been loaded");
        assertThatTitleContains("Booking.com", false);
        return this;
    }

    /**
     * Asserts softly that the search field is present and enabled.
     *
     * @return an object of this class.
     * @throws AssertionError if the search field is not available or enabled but only if {@link #assertAll} has been executed.
     */
    public HomePageAssertions assertSoftlyThatSearchBoxIsAvailable() {
        assertThatElementIsVisibleAndEnabled(homePageElements.getSearchBox(), "Search box", true);
        return this;
    }

    /**
     * Asserts softly that the calendar is present and enabled.
     *
     * @return an object of this class.
     * @throws AssertionError if the calendar is not available or enabled but only if {@link #assertAll} has been executed.
     */
    @Step("Verifying that calendar is available")
    public HomePageAssertions assertSoftlyThatCalendarIsAvailable() {
        assertThatElementIsVisibleAndEnabled(homePageElements.getCalendar(), "Calendar", true);
        return this;
    }

    /**
     * Asserts softly that the search button is present and enabled.
     *
     * @return an object of this class.
     * @throws AssertionError if the calendar is not available or enabled but only if {@link #assertAll} has been executed.
     */
    @Step("Verifying that search button is available")
    public HomePageAssertions assertSoftlyThatSearchButtonIsAvailable() {
        assertThatElementIsVisibleAndEnabled(homePageElements.getSearchButton(), "Search button", true);
        return this;
    }

    /**
     * Asserts softly that warning related to a search-attempt with an empty destination has been displayed.
     *
     * @return an object of this class.
     * @throws AssertionError if the empty query alert was not displayed but only if {@link #assertAll} has been executed.
     */
    @Step("Verifying that empty query alert is displayed")
    public HomePageAssertions assertSoftlyThatEmptyQueryAlertIsDisplayed() {
        assertThatElementIsDisplayed(homePageElements.getEmptyQueryAlert(), "Empty query alert", true);
        return this;
    }

    /**
     * Asserts softly that warning related to a search-attempt with an unrecognized destination has been displayed.
     *
     * @return an object of this class.
     * @throws AssertionError if the invalid query alert was not displayed but only if {@link #assertAll} has been executed.
     */
    @Step("Verifying that invalid query alert is displayed")
    public HomePageAssertions assertSoftlyThatInvalidQueryAlertIsDisplayed() {
        assertThatElementIsDisplayed(homePageElements.getInvalidQueryAlert(), "Invalid query alert", true);
        return this;
    }

    /**
     * Asserts softly that warning related to a search-attempt within invalid date-range has been displayed.
     *
     * @return an object of this class.
     * @throws AssertionError if the invalid date range alert was not displayed but only if {@link #assertAll} has been executed.
     */
    @Step("Verifying that invalid date range alert is displayed")
    public HomePageAssertions assertSoftlyThatInvalidDateRangeAlertIsDisplayed() {
        assertThatElementIsDisplayed(homePageElements.getInvalidDateRangeAlert(), "Invalid date range alert", true);
        return this;
    }

}
