package tests.booking;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import page.objects.booking.homepage.HomePageActions;
import page.objects.booking.homepage.HomePageAssertions;
import tests.TestBase;

public class HomePageTests extends TestBase {

    @Test
    @TmsLink("ID-1")
    @Issue("Defect-1")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test checks if the home page has been properly loaded and its all necessary elements are enabled")
    public void checkAvailabilityOfTheHomePageAndItsElements(){
        HomePageActions homePageActions = new HomePageActions();
        homePageActions.open()
                .startAssertion(HomePageAssertions.class)
                    .assertThatHomePageHasBeenLoaded()
                    .assertSoftlyThatSearchBoxIsAvailable()
                    .assertSoftlyThatSearchButtonIsAvailable()
                    .assertSoftlyThatCalendarIsAvailable()
                    .assertAll();
    }
}
