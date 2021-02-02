package driver.capabilities;

import driver.BrowserType;
import org.openqa.selenium.MutableCapabilities;

/**
 * A simple factory class that provides a new instance of a class implementing the {@link DriverCapabilities}
 * interface compatible with the given browser.
 */
public class DriverCapabilitiesProvider {

    /**
     * Returns a new instance of a class responsible for managing capabilities corresponding to the given browser.
     *
     * @param browserType the {@link BrowserType} enum constant that corresponds to the requested browser.
     * @return an object of a class implementing {@code DriverCapabilities} that corresponds to the requested browser.
     * @throws IllegalStateException if the requested browser is not in the list of possible choices.
     */
    public static DriverCapabilities<? extends MutableCapabilities> getDriverCapabilities(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                return new ChromeDriverCapabilities();
            case FIREFOX:
                return new FirefoxDriverCapabilities();
            case IE:
                return new IEDriverCapabilities();
            default:
                throw new IllegalStateException("Unknown browser type! Please check your configuration");
        }
    }
}
