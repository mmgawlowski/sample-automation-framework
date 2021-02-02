package driver;

import configuration.TestRunProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple factory class that provides a new instance of the {@code WebDriver} with the given set of capabilities
 * that corresponds to the selected browser.
 */
public class BrowserFactory {
    private static final String UNKNOWN_BROWSER_MESSAGE = "Unknown browser type! Please check your configuration";

    /**
     * Returns a new {@code WebDriver} instance for the selected browser with the given set of capabilities.
     * <p>
     * Depending on the settings, a local or remote {@code WebDriver} instance from the list of available options
     * may be returned.
     * <p>
     * The configuration of local drivers is self-managing through the use of the {@code WebDriverManager} library.
     *
     * @param browserType the requested browser as the {@link BrowserType} enum constant.
     * @param isRemoteRun if true, a new instance of {@code RemoteWebDriver} will be returned.
     * @param capabilities an object of the class extending {@code MutableCapabilities}
     *                     that represents a set of requested capabilities.
     * @return a new, local or remote, {@code WebDriver} instance for the selected browser
     *         with the given set of capabilities.
     * @throws IllegalStateException if you are trying to perform tests locally
     *                               on a browser outside the list of available ones.
     * @see <a href="https://github.com/bonigarcia/webdrivermanager">WebDriverManager</a>
     * @see <a href="https://www.selenium.dev/documentation/en/grid/">Selenium Grid</a>
     */
    public static WebDriver getBrowser(BrowserType browserType, Boolean isRemoteRun, MutableCapabilities capabilities) {

        if (isRemoteRun) {
            switch (browserType) {
                case CHROME:
                    return getRemoteWebDriver((ChromeOptions)capabilities);
                case FIREFOX:
                    return getRemoteWebDriver((FirefoxOptions)capabilities);
                case IE:
                    return getRemoteWebDriver((InternetExplorerOptions)capabilities);
                default:
                    throw new IllegalStateException(UNKNOWN_BROWSER_MESSAGE);
            }
        }

        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver((ChromeOptions)capabilities);
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver((FirefoxOptions)capabilities);
            case IE:
                WebDriverManager.iedriver().arch32().setup();
                return new InternetExplorerDriver((InternetExplorerOptions)capabilities);
            default:
                throw new IllegalStateException(UNKNOWN_BROWSER_MESSAGE);
        }

    }

    /**
     * Creates and returns a new instance of {@code RemoteWebDriver} with the given sets of capabilities.
     * The required hub address is taken from the value assigned to the "grid.url" property key from the .properties file.
     *
     * @param capabilities an object of the class extending {@code MutableCapabilities}
     *                     that represents a set of requested capabilities.
     * @return a new instance of {@code RemoteWebDriver}.
     * @throws RuntimeException if an instance cannot be created due to malformed URL.
     */
    private static WebDriver getRemoteWebDriver(MutableCapabilities capabilities) {
        RemoteWebDriver remoteWebDriver;

        try {
            remoteWebDriver = new RemoteWebDriver(new URL(TestRunProperties.getGridUrl()), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Failed to create RemoteWebDriver due to: %s", e.getMessage()));
        }
        return remoteWebDriver;
    }
}
