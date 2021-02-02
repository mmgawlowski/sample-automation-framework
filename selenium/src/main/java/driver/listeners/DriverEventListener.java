package driver.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * This class implements the {@code WebDriverEventListener} interface allowing to intercept events thrown by the WebDriver
 * and select the actions to follow them.
 * <p>
 * In this case, the following methods are used for extended logging to aid in debugging.
 */
public class DriverEventListener implements WebDriverEventListener {

    private static final Logger log = LoggerFactory.getLogger(DriverEventListener.class);

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        log.trace("Trying to accept alert");
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        log.trace("Accepted alert");
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        log.trace("Dismissed alert ");
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        log.trace("Trying to dismiss alert");
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        log.trace("Trying to navigate to {}", url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        log.trace("Navigated to {}", url);
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        log.trace("Trying to navigate back for page with URL: {}", driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        log.trace("Navigated back for page with URL: {}", driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        log.trace("Trying to navigate forward for page with URL: {}", driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        log.trace("Navigated forward for page with URL: {}", driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        log.trace("Trying to refresh page with URL: {}", driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        log.trace("Refreshed page with URL: {}", driver.getCurrentUrl());
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        log.trace("Trying to find element with locator {}", by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        log.trace("Found element with locator {}", by.toString());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        log.trace("Trying to click on element with tag name: {}", element.getTagName());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        log.trace("Clicked on element");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        if (keysToSend == null) {
            log.trace("Trying to enter empty string or clear element: {}", element.getTagName());
        } else {
            log.trace("Trying to type into element: {} text {}", element.getTagName(), Arrays.toString(keysToSend));
        }
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        if (keysToSend == null) {
            log.trace("Entered empty string or cleared element: {}", element.getTagName());
        } else {
            log.trace("Typed into element: {} text {}", element.getTagName(), Arrays.toString(keysToSend));
        }
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        log.trace("Trying to execute JS script: {}", script);
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        log.trace("Executed JS script: {}", script);
    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {
        log.trace("Trying to switch to window: " + s);
    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {
        log.trace("Switched to window: {}", s);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        log.debug("There was an exception with message: {}", throwable.getMessage());
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {
        log.trace("Trying to get screenshot");
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {
        log.trace("Done screenshot");
    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        log.trace("Trying to get text for WebElement");
    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
        log.trace("Taken text of WebElement. Text was {}", s);
    }
}
