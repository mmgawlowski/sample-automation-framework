package page.objects.booking.homepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.objects.booking.BasePage;

/**
 * PageFactory class, which provides WebElements from the Booking.com home page.
 */
public class HomePageElements extends BasePage {

    /**
     * This XPath is compatible with String.format() as it has to be completed with the given check-in date.
     *
     * @see HomePageActions#setCheckInDateByClicking
     */
    private static final String CHECK_IN_DATE_XPATH = "//td[@data-date='%s']";

    /**
     * This XPath is compatible with String.format() as it has to be completed with the given check-out date.
     *
     * @see HomePageActions#setCheckInDateByClicking
     */
    private static final String CHECK_OUT_DATE_XPATH = "//td[@data-date='%s']";

    @FindBy(id = "ss")
    private WebElement searchBox;

    @FindBy(className = "calendar-restructure-sb")
    private WebElement calendar;

    @FindBy(xpath = "//div[@data-bui-ref='calendar-next']")
    private WebElement calendarButtonNext;

    @FindBy(className = "sb-searchbox__button")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='fe_banner fe_banner__accessible fe_banner__red -visible']")
    private WebElement emptyQueryAlert;

    @FindBy(className = "sb-searchbox__error")
    private WebElement invalidQueryAlert;

    @FindBy(xpath = "//div[@data-component='search/dates/dates-errors']/div")
    private WebElement invalidDateRangeAlert;

    @FindBy(xpath = "//div[@data-mode='checkin']//div[@data-type='month-year']//select")
    private WebElement checkInMonthAndYearDropDwn;

    @FindBy(xpath = "//div[@data-mode='checkin']//select[@name='checkin_monthday']")
    private WebElement checkInDayDropDwn;

    @FindBy(xpath = "//div[@data-mode='checkout']//div[@data-type='month-year']//select")
    private WebElement checkOutMonthAndYearDropDwn;

    @FindBy(xpath = "//div[@data-mode='checkout']//select[@name='checkout_monthday']")
    private WebElement checkOutDayDropDwn;

    public static String getCheckInDateXpath() {
        return CHECK_IN_DATE_XPATH;
    }

    public static String getCheckOutDateXpath() {
        return CHECK_OUT_DATE_XPATH;
    }

    public WebElement getSearchBox() {
        return searchBox;
    }

    public WebElement getCalendar() {
        return calendar;
    }

    public WebElement getCalendarButtonNext() {
        return calendarButtonNext;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getInvalidQueryAlert() {
        return invalidQueryAlert;
    }

    public WebElement getEmptyQueryAlert() {
        return emptyQueryAlert;
    }

    public WebElement getInvalidDateRangeAlert() {
        return invalidDateRangeAlert;
    }

    public WebElement getCheckInMonthAndYearDropDwn() {
        return checkInMonthAndYearDropDwn;
    }

    public WebElement getCheckInDayDropDwn() {
        return checkInDayDropDwn;
    }

    public WebElement getCheckOutMonthAndYearDropDwn() {
        return checkOutMonthAndYearDropDwn;
    }

    public WebElement getCheckOutDayDropDwn() {
        return checkOutDayDropDwn;
    }

}
