package driver.manager;

import driver.BrowserFactory;
import driver.BrowserType;
import driver.listeners.WebDriverEventListenerRegister;
import io.qameta.allure.Step;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static configuration.TestRunProperties.getIsRemoteRun;

/**
 * {@code DriverManager} is the class responsible for managing of an instance of {@code WebDriver}.
 * It uses the Singleton pattern extended with support for multithreading.
 */
public class DriverManager {
    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> webDriverTL = new ThreadLocal<>();
    private static final ThreadLocal<BrowserType> browserTypeTL = new ThreadLocal<>();

    private DriverManager() {
    }

    /**
     * Gets a new instance of {@code WebDriver} for the given browser
     * with the given set of capabilities and sets it as a ThreadLocal variable.
     * <p>
     * Whether the instance obtained is a local or remote {@code WebDriver}
     * depends on the value assigned to the "is.remote.run" property key in the .properties file.
     * <p>
     * That instance is then wrapped in {@code EventFiringWebDriver}
     * using the {@link WebDriverEventListenerRegister#registerWebDriverEventListener} method,
     * providing extended logging.
     * This extended logging will be visible in the console when the TRACE level is selected in the logback.xml file.
     * <p>
     * The specified type of the browser is also set as ThreadLocal variable to be used in other methods.
     *
     * @param browserType the given browser type as the {@link BrowserType} enum constant.
     * @param capabilities an object of the class extending {@code MutableCapabilities}
     *                     that represents a set of requested capabilities.
     */
    @Step("Setting browser to: {browserType}")
    public static void setDriver(BrowserType browserType, MutableCapabilities capabilities) {
        WebDriver driver;

        log.info("Setting browser to: {}", browserType);

        driver = BrowserFactory.getBrowser(browserType, getIsRemoteRun(), capabilities);
        driver = WebDriverEventListenerRegister.registerWebDriverEventListener(driver);

        browserTypeTL.set(browserType);
        webDriverTL.set(driver);
    }

    /**
     * Returns an instance of {@code Webdriver} assigned to the current thread if any is available.
     *
     * @return an instance of {@code Webdriver} assigned to the ThreadLocal variable.
     * @throws IllegalStateException if the ThreadLocal variable is set to null.
     */
    public static WebDriver getDriver() {
        if (webDriverTL.get() == null) {
            throw new IllegalStateException("WebDriver Instance was null! Please create instance of WebDriver using setWebDriver!");
        }
        return webDriverTL.get();
    }

    /**
     * Closes the open browser, quits WebDriver and removes it from the ThreadLocal variable.
     * For all WebDrivers except GeckoDriver, the {@code close} method is called first, followed by {@code quit}.
     * <p>
     * At this point, the browser type assigned to the ThreadLocal variable is also removed.
     */
    public static void quitDriver(){
        log.info("Closing browser");
        webDriverTL.get().close();
        if (!browserTypeTL.get().equals(BrowserType.FIREFOX)){
            webDriverTL.get().quit();
        }
        webDriverTL.remove();
        browserTypeTL.remove();
    }

    /**
     *
     * @return the type of the browser assigned to the ThreadLocal variable as a {@link BrowserType} enum constant.
     */
    public static BrowserType getCurrentBrowser() {
        return browserTypeTL.get();
    }
}
