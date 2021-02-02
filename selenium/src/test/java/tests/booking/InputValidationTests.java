package tests.booking;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import page.objects.booking.homepage.HomePageActions;
import page.objects.booking.homepage.HomePageAssertions;
import tests.TestBase;
import utils.dataproviders.DataProviders;
import utils.helper.methods.generic.DateAndTime;
import utils.listeners.RetryAnalyzer;

public class InputValidationTests extends TestBase {

    @Test(groups = "Invalid inputs")
    @TmsLink("ID-3")
    @Issue("Defect-3")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test checks if the appropriate warning is displayed when user tries to search without specifying a destination.")
    public void asUserTryToSearchWithEmptyQuery() {
        HomePageActions homePageActions = new HomePageActions();
        homePageActions.open()
                .setPlaceToSearch("")
                .clickSearchButton()
                .startAssertion(HomePageAssertions.class)
                    .assertSoftlyThatEmptyQueryAlertIsDisplayed()
                    .assertAll();
    }

    @Test(groups = "Invalid inputs", retryAnalyzer = RetryAnalyzer.class, dataProvider = "TestData", dataProviderClass = DataProviders.class)
    @TmsLink("ID-4")
    @Issue("Defect-4")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test checks if the appropriate warning is displayed when user tries to search with an invalid destination.")
    public void asUserTryToSearchWithInvalidQuery(String query) {
        HomePageActions homePageActions = new HomePageActions();
        homePageActions.open()
                .setPlaceToSearch(query)
                .clickSearchButton()
                .startAssertion(HomePageAssertions.class)
                    .assertSoftlyThatInvalidQueryAlertIsDisplayed()
                    .assertAll();
    }

    @Test(groups = "Invalid inputs")
    @TmsLink("ID-5")
    @Issue("Defect-5")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test checks if the appropriate warning is displayed when user tries to search within invalid date range (more than 30 days).")
    public void asUserTryToSearchWithInvalidDateRange() {
        HomePageActions homePageActions = new HomePageActions();
        homePageActions.open()
                .setCheckInDate(DateAndTime.dateGenerator(0))
                .setCheckOutDate(DateAndTime.dateGenerator(31))
                .clickSearchButton()
                .startAssertion(HomePageAssertions.class)
                    .assertSoftlyThatInvalidDateRangeAlertIsDisplayed()
                    .assertAll();
    }
}
