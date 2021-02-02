package tests.booking;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import page.objects.booking.homepage.HomePageActions;
import page.objects.booking.homepage.HomePageAssertions;
import page.objects.booking.resultpage.ResultPageAssertions;
import tests.TestBase;
import utils.dataproviders.DataProviders;
import utils.helper.methods.generic.DateAndTime;

public class SearchEngineTests extends TestBase {

    @Test(dataProvider = "TestData", dataProviderClass = DataProviders.class)
    @TmsLink("ID-2")
    @Issue("Defect-2")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The purpose of this test is to confirm that the results displayed on the result page match the given input.")
    public void asUserTryToSearchForGivenDestinationAndDataRange(String query) {
        HomePageActions homePageActions = new HomePageActions();
        homePageActions.open()
                .startAssertion(HomePageAssertions.class)
                    .assertThatHomePageHasBeenLoaded()
                .endAssertion(HomePageActions.class)
                .setPlaceToSearch(query)
                .setCheckInDate(DateAndTime.dateGenerator(1))
                .setCheckOutDate(DateAndTime.dateGenerator(14))
                .clickSearchButton()
                .startAssertion(ResultPageAssertions.class)
                    .assertSoftlyThatFoundPlaceEqualsToExpected(query)
                    .assertSoftlyThatShownCheckInDateEqualsToSelected(DateAndTime.dateGenerator(1))
                    .assertSoftlyThatShownCheckOutDateEqualsToSelected(DateAndTime.dateGenerator(14))
                    .assertAll();
    }
}
