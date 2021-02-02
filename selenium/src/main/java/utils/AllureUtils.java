package utils;

import driver.manager.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

/**
 * {@code AllureUtils} contains methods related to the Allure Framework - a powerful tool for creating reports.
 */
public class AllureUtils {

    /**
     * Creates a screenshot that is later attached to the generated test report.
     *
     * @return the taken screenshot as bytes.
     */
    @Attachment(value = "Page screenshot test failure", type = "image/png")
    public static byte[] TakeScreenShot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Creates a text file that contains log messages of the selected type and attach it to the generated test report.
     * In most cases, log messages are only available if logging of the selected type has been previously enabled.
     * <p>
     * It should be used for the Chrome browser only
     * until support for setting and retrieving logs through Selenium for other browsers is improved.
     *
     * @param logType one of the available logging types e.g. Browser, Driver, Performance.
     *                Use the {@link LogType} constants.
     * @return a string which will later be converted into a text file.
     */
    @Attachment(value = "{0}", type = "text/plain")
    public static String getLogsAsAttachment(String logType){
        List<LogEntry> logs = DriverManager.getDriver().manage().logs().get(logType).getAll();
        StringBuilder sb = new StringBuilder();
        for (LogEntry log : logs) {
            sb.append(log.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
