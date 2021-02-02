package driver.listeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverEventListenerRegister {

    /**
     * Returns the given {@code WebDriver} instance wrapped in an object of the {@code EventFiringWebDriver} class
     * with registered {@link DriverEventListener}.
     * <p>
     * This allows WebDriver events to be thrown, which are then caught by the listener
     * and specific actions are performed for each of them.
     *
     * @param driver the given {@code WebDriver} instance.
     * @return a wrapped {@code WebDriver} instance throwing events that are caught by {@code DriverEventListener}.
     * @see <a href="https://www.toolsqa.com/selenium-webdriver/event-listener/">Event Listener</a>
     */
    public synchronized static WebDriver registerWebDriverEventListener(WebDriver driver) {
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        DriverEventListener driverEventListener = new DriverEventListener();
        return eventFiringWebDriver.register(driverEventListener);
    }

}
