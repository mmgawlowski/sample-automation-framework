package tests;

import configuration.ConfigurationProperties;
import configuration.PropertiesLoader;
import driver.BrowserType;
import driver.capabilities.DriverCapabilities;
import driver.capabilities.DriverCapabilitiesProvider;
import driver.manager.DriverManager;
import driver.manager.DriverUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.listeners.TestListener;

import java.util.Properties;
import java.util.logging.Level;

import static configuration.TestRunProperties.getBrowserToRun;
import static configuration.TestRunProperties.getIsHeadlessRun;
import static driver.manager.DriverManager.getCurrentBrowser;
import static utils.AllureUtils.getLogsAsAttachment;

@Listeners(TestListener.class)
public abstract class TestBase {

    @Step("Loading configuration from configuration.properties")
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties propertiesFromFile = propertiesLoader.getPropertiesFromFile("booking_com.properties");
        ConfigurationProperties.setProperties(propertiesFromFile);
    }

    @Step("Setting up browser")
    @Parameters("browserSuite")
    @BeforeMethod(alwaysRun = true)
    public void beforeTest(@Optional BrowserType browserType) {
        if (browserType == null) {
            browserType = getBrowserToRun();
        }

        DriverCapabilities<?> dc = DriverCapabilitiesProvider.getDriverCapabilities(browserType);
        dc.addDefaultCapabilities();
        dc.setHeadless(getIsHeadlessRun());
        dc.setLogging(LogType.BROWSER, Level.INFO);
        dc.setLogging(LogType.DRIVER, Level.INFO);

        DriverManager.setDriver(browserType, dc.getCapabilities());
        DriverManager.getDriver();
        DriverUtils.setInitialConfiguration();

    }

    @Step("Closing browser")
    @AfterMethod(alwaysRun = true)
    public void afterTest(ITestResult result) {
        if (BrowserType.CHROME.equals(getCurrentBrowser())){
            getLogsAsAttachment(LogType.BROWSER);
            getLogsAsAttachment(LogType.DRIVER);
        }
        DriverUtils.saveLogsToFile(result, LogType.DRIVER);
        DriverManager.quitDriver();
    }

}

