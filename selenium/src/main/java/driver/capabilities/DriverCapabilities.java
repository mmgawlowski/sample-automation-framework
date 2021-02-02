package driver.capabilities;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.logging.LogType;

import java.util.logging.Level;

/**
 * The purpose of this interface is to unify the capabilities management classes
 * for later simpler configuration of tests performed on multiple browsers.
 *
 * @param <T> a type extending {@code MutableCapabilities} e.g. {@code ChromeOptions}, {@code FirefoxOptions} and so on.
 */
public interface DriverCapabilities<T extends MutableCapabilities> {
    /**
     * Allows to compose and apply a list of default capabilities.
     */
    void addDefaultCapabilities();

    /**
     * Sets all the capabilities needed to run the browser in headless mode.
     * Only a few browsers support this mode!
     *
     * @param headless if true, headless mode will be enabled.
     */
    void setHeadless(Boolean headless);

    /**
     * Enables the selected type of logging at the selected level.
     * For now, applicable mostly to the Chrome browser.
     *
     * @param logType one of the available logging types e.g. Browser, Driver, Performance.
     *                Use the {@link LogType} constants.
     * @param level the level of the logging e.g. INFO, WARNING, SEVERE.
     */
    void setLogging(String logType, Level level);

    /**
     * This method will allow you to use your own set of capabilities when necessary.
     * You can still extend it using the other methods.
     *
     * @param options an object of a class extending {@code MutableCapabilities} matched to the browser.
     */
    void setCapabilities(T options);

    /**
     * Returns the set capabilities as an object of a class extending {@code MutableCapabilities} such as {@code ChromeOptions}.
     *
     * @return an object of a class extending {@code MutableCapabilities} matched to the browser.
     */
    T getCapabilities();
}
