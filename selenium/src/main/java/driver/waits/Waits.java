package driver.waits;

import driver.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * {@code Waits} provides ready-to-use Explicit Wait methods.
 *
 * @see <a href="https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver">Selenium Wait Commands</a>
 */
public class Waits {

    /**
     * Provides a new instance of {@link WebDriverWait} to other methods.
     *
     * @param secondsToWait the time, expressed in seconds,
     *                      during which the fulfilment of the specified condition is to be checked.
     * @return a new instance of {@code WebDriverWait}.
     */
    private static WebDriverWait getWebDriverWait(int secondsToWait) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(secondsToWait));
    }

    /**
     * Waits until the given WebElement is visible on the page.
     *
     * @param element WebElement against which the condition is checked.
     * @param secondsToWait the time, expressed in seconds,
     *                      during which the fulfilment of the specified condition is to be checked.
     * @return the given WebElement if the condition is met.
     * @throws TimeoutException if condition is not met after the specified time.
     */
    public static WebElement waitUntilElementIsVisible(WebElement element, int secondsToWait){
       return getWebDriverWait(secondsToWait).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until an WebElement that can be identified by the given locator is visible on the page.
     *
     * @param locator the {@code By} type locator of the WebElement against which the condition is checked.
     * @param secondsToWait the time, expressed in seconds,
     *                      during which the fulfilment of the specified condition is to be checked.
     * @return the WebElement identified by the given locator if the condition is met.
     * @throws TimeoutException if condition is not met after the specified time.
     */
    public static WebElement waitUntilLocatedElementIsVisible(By locator, int secondsToWait){
       return getWebDriverWait(secondsToWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the given WebElement is clickable.
     *
     * @param element WebElement against which the condition is checked.
     * @param secondsToWait the time, expressed in seconds,
     *                      during which the fulfilment of the specified condition is to be checked.
     * @return the given WebElement if the condition is met.
     * @throws TimeoutException if condition is not met after the specified time.
     */
    public static WebElement waitUntilElementIsClickable(WebElement element, int secondsToWait){
        return getWebDriverWait(secondsToWait).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the title of the page contains the given text.
     *
     * @param text the string to be checked.
     * @param secondsToWait the time, expressed in seconds,
     *                      during which the fulfilment of the specified condition is to be checked.
     * @throws TimeoutException if condition is not met after the specified time.
     */
    public static void waitUntilTitleContains(String text, int secondsToWait){
        getWebDriverWait(secondsToWait).until(ExpectedConditions.titleContains(text));
    }

    /**
     * Waits until the URL address of the page contains the given text.
     *
     * @param text the string to be checked.
     * @param secondsToWait the time, expressed in seconds,
     *                      during which the fulfilment of the specified condition is to be checked.
     * @throws TimeoutException if condition is not met after the specified time.
     */
    public static void waitUntilUrlContains(String text, int secondsToWait){
        getWebDriverWait(secondsToWait).until(ExpectedConditions.urlContains(text));
    }

    /**
     * Waits until a file with the specified name can be found in the specified destination directory.
     *
     * @param downloadDir the string that represents the path to the destination dir.
     *                    Paths relative to the project directory are accepted.
     * @param fileName the name of the file expected to be downloaded.
     * @param secondsToWait the time, expressed in seconds,
     *                      during which the fulfilment of the specified condition is to be checked.
     * @return the downloaded file.
     * @throws TimeoutException if condition is not met after the specified time.
     */
    public static File waitForFileTobeDownloaded(String downloadDir, String fileName, int secondsToWait) {
        getWebDriverWait(secondsToWait).until((file) -> Paths.get(downloadDir, fileName).toFile().exists());
        return Paths.get(downloadDir, fileName).toFile();
    }
}
