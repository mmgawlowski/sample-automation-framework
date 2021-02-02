package page.objects.booking;

import custom.assertions.FluentAssertions;
import custom.assertions.WebElementAssert;
import custom.assertions.entry.points.SoftAssertions;
import driver.manager.DriverManager;
import driver.waits.Waits;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * An abstract class inherited by all assertion classes specific to page objects.
 * It provides several types of generic assertions that are easy to implement further and help to reduce code repetition.
 *
 * @param <A> a type parameter that is equal to an instantiated subclass of this class.
 *            It relates to the use of fluent interfaces.
 */
public abstract class BaseAssertions<A> implements FluentAssertions {
    private static final Logger log = LoggerFactory.getLogger(BaseAssertions.class);

    /**
     * An object of the AssertJ {@code SoftAssertion} class as soft assertion methods are not static.
     */
    protected final SoftAssertions softly = new SoftAssertions();

    /**
     * Asserts that current URL address contains the given text.
     *
     * @param text phrase to search for in the URL.
     * @param softAssertion if true, soft assertion will be used, otherwise - normal assertion.
     * @throws AssertionError if URL doesn't contain the requested phrase.
     *                        For soft assertions, the exception is only thrown after {@link #assertAll} is executed.
     */
    public void assertThatUrlContains(String text, Boolean softAssertion) {
        String failureMessage = String.format("Page URL doesn't contain the phrase: %s!", text);
        try {
            Waits.waitUntilUrlContains(text, 5);
            log.info("URL address contains: {}", text);
        } catch (TimeoutException e) {
            if (softAssertion) {
                softly.fail(failureMessage);
            } else {
                Assertions.fail(failureMessage);
            }
        }
    }

    /**
     * Asserts that current page title contains the given text.
     *
     * @param text phrase to search for in the page title.
     * @param softAssertion if true, soft assertion will be used, otherwise - normal assertion.
     * @throws AssertionError if the page title doesn't contain the requested phrase.
     *                        For soft assertions, the exception is only thrown after {@link #assertAll} is executed.
     */
    public void assertThatTitleContains(String text, Boolean softAssertion) {
        String failureMessage = String.format("Page title doesn't contain the given phrase: expected: %s, was: %s", text, DriverManager.getDriver().getTitle());
        try {
            Waits.waitUntilTitleContains(text, 10);
            log.info("Title contains: {}", text);
        } catch (TimeoutException e) {
            if (softAssertion) {
                softly.fail(failureMessage);
            } else {
                Assertions.fail(failureMessage);
            }
        }
    }

    /**
     * Asserts that the given WebElement is visible on the page.
     *
     * @param element the WebElement object.
     * @param name the string that is used to refer to the WebElement in the log message.
     * @param softAssertion if true, soft assertion will be used, otherwise - normal assertion.
     * @throws AssertionError if the WebElement is not visible or absent.
     *                        For soft assertions, the exception is only thrown after {@link #assertAll} is executed.
     */
    public void assertThatElementIsDisplayed(WebElement element, String name, Boolean softAssertion) {
        log.info("Verifying that {} is displayed", name.toLowerCase());
        String failureMessage = String.format("%s is not displayed!", StringUtils.capitalize(name));
        try {
            Waits.waitUntilElementIsVisible(element, 5);
            log.info("{} is displayed", name);
        } catch (TimeoutException e) {
            if (softAssertion) {
                softly.fail(failureMessage);
            } else {
                Assertions.fail(failureMessage);
            }
        }
    }

    /**
     * Asserts that the given WebElement is not visible on the page.
     *
     * @param element the WebElement object.
     * @param name the string that is used to refer to the WebElement in the log message.
     * @param softAssertion if true, soft assertion will be used, otherwise - normal assertion.
     * @throws AssertionError if the WebElement is visible.
     *                        For soft assertions, the exception is only thrown after {@link #assertAll} is executed.
     */
    public void assertThatElementIsNotDisplayed(WebElement element, String name, Boolean softAssertion) {
        log.info("Verifying that {} is NOT displayed", name);
        String failureMessage = String.format("%s is displayed!", StringUtils.capitalize(name));
        try {
            Waits.waitUntilElementIsVisible(element, 2);
            if (softAssertion) {
                softly.fail(failureMessage);
            } else {
                Assertions.fail(failureMessage);
            }
        } catch (TimeoutException e) {
            log.info("{} is not displayed", name);
        }
    }

    /**
     * Asserts that the given WebElement is visible on the page and interactive.
     *
     * @param element the WebElement object.
     * @param name the string that is used to refer to the WebElement in the log message.
     * @param softAssertion if true, soft assertion will be used, otherwise - normal assertion.
     * @throws AssertionError if the WebElement is not visible or disabled.
     *                        For soft assertions, the exception is only thrown after {@link #assertAll} is executed.
     */
    public void assertThatElementIsVisibleAndEnabled(WebElement element, String name, Boolean softAssertion) {
        log.info("Verifying that {} is available and enabled", name);
        String description = String.format("Verifying that %s is enabled", name);
        String failureMessage = String.format("%s is not available!", StringUtils.capitalize(name));
        try {
            Waits.waitUntilElementIsVisible(element, 10);
            if (softAssertion) {
                softly.assertThat(element).describedAs(description).isEnabled();
            } else {
                WebElementAssert.assertThat(element).describedAs(description).isEnabled();
            }
            log.info("{} is enabled", StringUtils.capitalize(name));
        } catch (TimeoutException e) {
            if (softAssertion) {
                softly.fail(failureMessage);
            } else {
                Assertions.fail(failureMessage);
            }
        }
    }

    /**
     * Asserts that the given WebElement is assigned a text containing the desired phrase.
     *
     * @param element the WebElement object.
     * @param name the string that is used to refer to the WebElement in the log message.
     * @param text phrase to search for in the WebElement assigned text.
     * @param softAssertion if true, soft assertion will be used, otherwise - normal assertion.
     * @throws AssertionError if the text assigned to the WebElement doesn't contain the requested phrase.
     *                        For soft assertions, the exception is only thrown after {@link #assertAll} is executed.
     */
    public void assertThatElementContainsText(WebElement element, String name, String text, Boolean softAssertion) {
        log.info("Verifying that {} contains text: {}", name, text);
        if (softAssertion) {
            softly.assertThat(element.getText()).containsIgnoringCase(text);
        } else {
            Assertions.assertThat(element.getText()).containsIgnoringCase(text);
        }
        log.info("{} contains text: {}", StringUtils.capitalize(name), text);
    }

    /**
     * Asserts that the requested file has been downloaded and saved to the given download dir.
     * When the assertion passes, the downloaded file is deleted.
     *
     * @param downloadDir the string that represents the path to the selected download directory.
     *                    Paths relative to the project directory are accepted.
     * @param fileName the name of the file expected to be downloaded.
     * @param softAssertion if true, soft assertion will be used, otherwise - normal assertion.
     * @throws AssertionError if a file with the specified name cannot be found in the specified folder.
     *                        For soft assertions, the exception is only thrown after {@link #assertAll} is executed.
     * @see Waits#waitForFileTobeDownloaded 
     */
    public void assertThatFileHasBeenDownloaded(String downloadDir, String fileName, Boolean softAssertion) {
        log.info("Verifying that file: {} has been downloaded", fileName);
        String failureMessage = String.format("File: %s could not be found in the specified folder: %s.", fileName, downloadDir);
        try{
            File downloadedFile = Waits.waitForFileTobeDownloaded(downloadDir, fileName, 15);
            if(downloadedFile.delete()) {
                log.info("File downloaded and deleted");
            }
        } catch (TimeoutException e) {
            if (softAssertion) {
                softly.fail(failureMessage);
            } else {
                Assertions.fail(failureMessage);
            }
        }

    }

    /**
     * Asserts all soft assertions assigned to an instance of a subclass of this class.
     *
     * @return an object of an instantiated subclass.
     */
    @SuppressWarnings("unchecked")
    public A assertAll() {
        softly.assertAll();
        return (A) this;
    }
}
