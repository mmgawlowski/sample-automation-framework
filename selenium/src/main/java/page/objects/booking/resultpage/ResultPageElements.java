package page.objects.booking.resultpage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.objects.booking.BasePage;

/**
 * PageFactory class, which provides WebElements from the Booking.com result page.
 */
public class ResultPageElements extends BasePage {

    /**
     * This XPath is compatible with String.format() as it has to be completed with the given destination.
     * 
     * @see ResultPageAssertions#assertSoftlyThatFoundPlaceEqualsToExpected 
     */
    private static final String ACCOMMODATION_LIST_ELEMENT_XPATH = "//span[contains(@class, 'sr-hotel__name') and contains(text(), '%s')]";

    @FindBy(xpath = "//div[contains(@class, 'sr_header')]//h1")
    private WebElement defaultHeaderWithFoundRegion;

    @FindBy(className = "ski-accommodation__title")
    private WebElement skiTypeHeaderWithFoundRegion;

    @FindBy(xpath = "//div[@data-mode='checkin']//div[@data-date-format='date_with_weekday']")
    private WebElement CheckInDateFromResultPage;

    @FindBy(xpath = "//div[@data-mode='checkout']//div[@data-date-format='date_with_weekday']")
    private WebElement CheckOutDateFromResultPage;

    public static String getAccommodationListElementXpath() {
        return ACCOMMODATION_LIST_ELEMENT_XPATH;
    }

    public WebElement getDefaultHeaderWithFoundRegion() {
        return defaultHeaderWithFoundRegion;
    }

    public WebElement getSkiTypeHeaderWithFoundRegion() {
        return skiTypeHeaderWithFoundRegion;
    }

    public WebElement getCheckInDateFromResultPage() {
        return CheckInDateFromResultPage;
    }

    public WebElement getCheckOutDateFromResultPage() {
        return CheckOutDateFromResultPage;
    }

}
