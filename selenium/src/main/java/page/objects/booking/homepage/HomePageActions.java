package page.objects.booking.homepage;

import driver.BrowserType;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import configuration.TestRunProperties;
import custom.assertions.FluentAssertions;
import driver.manager.DriverManager;
import driver.manager.DriverUtils;
import driver.waits.Waits;
import utils.helper.methods.BookingCalendar;
import utils.helper.methods.generic.DateAndTime;

import java.util.List;

/**
 * The HomePageActions class provides methods which allow to interact with the booking.com home page.
 * It has been adapted to the use of fluent interfaces.
 */
public class HomePageActions implements FluentAssertions {

    private static final Logger log = LoggerFactory.getLogger(HomePageActions.class);
    private final HomePageElements homePageElements;

    private static final String DATE_INPUT_FORMAT = "yyyy-MM-dd";
    private static final String DATE_OUTPUT_FORMAT = "d-M-yyyy";
    private int i;

    public HomePageActions() {
        homePageElements = new HomePageElements();
    }

    /**
     * Navigates to booking.com page.
     * The actual address is read from the properties file.
     *
     * @return an object of this class.
     */
    @Step("Opening home page")
    public HomePageActions open(){
        DriverUtils.navigateToPage(TestRunProperties.getAppUrl());
        return this;
    }

    /**
     * Checks if the search box on the home page is present
     * and then inserts text from the given query imitating keyboard input.
     *
     * @param query a destination.
     * @return an object of this class.
     */
    @Step("Setting the destination: {0}")
    public HomePageActions setPlaceToSearch(String query) {
        log.info("Setting the destination: {}", query);
        WebElement searchBox = homePageElements.getSearchBox();
        Waits.waitUntilElementIsVisible(searchBox, 10);
        searchBox.clear();
        searchBox.sendKeys(query);
        return this;
    }

    /**
     * Uses the calendar to select the chosen check-in date through Select WebElement.
     * <p>
     * Exceptionally for Firefox to simplify multi-browser tests {@link #setCheckInDateByClicking} is used instead
     * as interaction with Select WebElement leads to {@link ElementClickInterceptedException}.
     *
     * @param checkInDate a check-in date in the format "yyyy-MM-dd".
     *                    Calendar does not allow any past dates or future dates that are more than 15 months away.
     * @return an object of this class.
     * @throws NoSuchElementException when provided date cannot be applied.
     */
    @Step("Setting check-in date: {0}")
    public HomePageActions setCheckInDate(String checkInDate) {
        log.info("Setting check-in date: {}", checkInDate);

        if(BrowserType.FIREFOX.equals(DriverManager.getCurrentBrowser())) {
            setCheckInDateByClicking(checkInDate);
        }

        Select checkInMonthAndYearDropDwn = new Select(homePageElements.getCheckInMonthAndYearDropDwn());
        Select checkInDayDropDwn = new Select(homePageElements.getCheckInDayDropDwn());
        String dateInProperFormat = DateAndTime.convertDateFormat(DATE_INPUT_FORMAT, DATE_OUTPUT_FORMAT, checkInDate);
        List<String> transformedDate = BookingCalendar.transformDateforSelect(dateInProperFormat);

        try {
            checkInMonthAndYearDropDwn.selectByValue(transformedDate.get(1));
            checkInDayDropDwn.selectByValue(transformedDate.get(0));
        } catch (NoSuchElementException e) {
            log.error("Incorrect date");
            throw e;
        }
        return this;
    }

    /**
     * Uses the calendar to select the chosen check-out date through Select WebElement.
     * <p>
     * Exceptionally for Firefox to simplify multi-browser tests {@link #setCheckInDateByClicking} is used instead
     * as interaction with Select WebElement leads to {@link ElementClickInterceptedException}.
     *
     * @param checkOutDate a check-out date in the format "yyyy-MM-dd".
     *                     Calendar does not allow past dates or future dates that are more than 15 months away.
     * @return an object of this class.
     * @throws NoSuchElementException when provided date cannot be applied.
     */
    @Step("Setting check-out date: {0}")
    public HomePageActions setCheckOutDate(String checkOutDate) {
        log.info("Setting check-out date: {}", checkOutDate);

        if(BrowserType.FIREFOX.equals(DriverManager.getCurrentBrowser())) {
            setCheckInDateByClicking(checkOutDate);
        }

        Select checkOutMonthAndYearDropDwn = new Select(homePageElements.getCheckOutMonthAndYearDropDwn());
        Select checkOutDayDropDwn = new Select(homePageElements.getCheckOutDayDropDwn());
        String dateInProperFormat = DateAndTime.convertDateFormat(DATE_INPUT_FORMAT, DATE_OUTPUT_FORMAT, checkOutDate);
        List<String> transformedDate = BookingCalendar.transformDateforSelect(dateInProperFormat);

        try {
            checkOutMonthAndYearDropDwn.selectByValue(transformedDate.get(1));
            checkOutDayDropDwn.selectByValue(transformedDate.get(0));
        } catch (NoSuchElementException e) {
            log.error("Incorrect date");
            throw e;
        }
        return this;
    }

    /**
     * Uses the calendar to select the chosen check-in date.
     * It simulates user behavior in a more literal way - it clicks on elements of the calendar.
     * <p>
     * This method has been created mostly for Firefox as using GeckoDriver with Select WebElement
     * results in {@link ElementClickInterceptedException}.
     *
     * @param checkInDate check-in date in the format "yyyy-MM-dd".
     *                    Calendar does not allow past dates or future dates that are more than 15 months away.
     * @return an object of this class.
     * @throws NoSuchElementException when provided date cannot be applied.
     */
    @Step("Setting check-in date: {0}")
    public HomePageActions setCheckInDateByClicking(String checkInDate) {
        log.info("Setting check-in date: {}", checkInDate);
        WebElement calendar = homePageElements.getCalendar();
        calendar.click();
        i = 0;

        while (i < 15) {
            if (i == 14) {
                log.error("Incorrect date");
                throw new NoSuchElementException("Unable to locate element: " + checkInDate);
            }
            try {
                DriverManager.getDriver().findElement(By.xpath(String.format(HomePageElements.getCheckInDateXpath(), checkInDate))).isDisplayed();
                DriverManager.getDriver().findElement(By.xpath(String.format(HomePageElements.getCheckInDateXpath(), checkInDate))).click();
                break;
            } catch (NoSuchElementException | ElementNotInteractableException e) {
                try {
                    homePageElements.getCalendarButtonNext().click();
                    i++;
                } catch (ElementNotInteractableException e1) {
                    calendar.click();
                }
            }
        }
        return this;
    }

    /**
     * Uses the calendar to select the chosen check-out date.
     * It simulates user behavior in a more literal way - it clicks on elements of the calendar.
     * <p>
     * This method has been created mostly for Firefox as using GeckoDriver with Select WebElement
     * results in {@link ElementClickInterceptedException}.
     *
     * @param checkOutDate check-out date in the format "yyyy-MM-dd".
     *                     Calendar does not allow past dates or future dates that are more than 15 months away.
     * @return an object of this class.
     * @throws NoSuchElementException when provided date cannot be applied.
     */
    @Step("Setting check-out date: {0}")
    public HomePageActions setCheckOutDateByClicking(String checkOutDate) {
        log.info("Setting check-out date: {}", checkOutDate);
        while (i < 15) {
            if (i == 14) {
                log.error("Incorrect date");
                throw new NoSuchElementException("Unable to locate element: " + checkOutDate);
            }
            try {
                DriverManager.getDriver().findElement(By.xpath(String.format(HomePageElements.getCheckOutDateXpath(), checkOutDate))).isDisplayed();
                DriverManager.getDriver().findElement(By.xpath(String.format(HomePageElements.getCheckOutDateXpath(), checkOutDate))).click();
                break;
            } catch (NoSuchElementException e) {
                homePageElements.getCalendarButtonNext().click();
                i++;
            }
        }
        return this;
    }

    /**
     * Clicks on the search button.
     *
     * @return an object of this class.
     */
    @Step("Clicking on search button")
    public HomePageActions clickSearchButton() {
        log.info("Clicking on search button");
        homePageElements.getSearchButton().click();
        return this;
    }

}
