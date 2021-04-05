package tests;

import configuration.TestRunConfig;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.listeners.TestListener;

@Listeners(TestListener.class)
public class TestBase {

    @Step("Setting environment")
    @BeforeSuite
    public void beforeSuite() {
        TestRunConfig environmentConfig = ConfigFactory.create(TestRunConfig.class);

        RestAssured.baseURI = environmentConfig.baseUri();
        RestAssured.basePath = environmentConfig.basePath();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
    }
}
