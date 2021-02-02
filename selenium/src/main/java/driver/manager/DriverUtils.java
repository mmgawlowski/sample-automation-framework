package driver.manager;

import driver.BrowserType;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import utils.AllureUtils;
import utils.helper.methods.generic.FilesManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static driver.manager.DriverManager.getCurrentBrowser;


public class DriverUtils {

    private static final Logger log = LoggerFactory.getLogger(DriverUtils.class);

    /**
     * Sets the initial configuration of the browser before testing.
     * At the moment it only maximises the browser window, but there may be also Implicit Wait settings etc.
     */
    @Step("Maximizing browser window")
    public static void setInitialConfiguration() {
        log.info("Maximizing browser window");
        DriverManager.getDriver().manage().window().maximize();
    }

    /**
     * Creates a text file in /tmp dir that contains log messages of the selected type.
     * If a file for the given type already exists, the new data is appended to it.
     * <p>
     * Eventually every browser should have its own files, but for now only Chrome is supported
     * until support for setting and retrieving logs through Selenium for other browsers is improved.
     * <p>
     * In most cases, log messages are only available if logging of the selected type has been previously enabled.
     * <p>
     * This method is an alternative to {@link AllureUtils#getLogsAsAttachment}
     * if direct access to the log file is preferred or you have decided not to use Allure Framework for reporting.
     *
     * @param result the result of the test method that just ran.
     *               It allows to get the name of that method, which is used to organise log messages.
     * @param logType one of the available logging types e.g. Browser, Driver, Performance.
     *                Use the {@link LogType} constants.
     */
    public static synchronized void saveLogsToFile(ITestResult result, String logType) {
        BrowserType browserType = getCurrentBrowser();

        if (BrowserType.CHROME.equals(browserType)) {
            String path = "tmp\\";
            String fileName = String.format("chrome-%s.log", logType);
            FilesManagement.createDestinationFolder(path);

            try (FileWriter outputFile = new FileWriter(path + fileName , StandardCharsets.UTF_8, true);
                 BufferedWriter bw = new BufferedWriter(outputFile)) {

                List<LogEntry> logs = DriverManager.getDriver().manage().logs().get(logType).getAll();
                String testName = result.getParameters().length > 0 ? String.format("Test case: %s %s", result.getName(), Arrays.toString(result.getParameters())) : String.format("Test case: %s", result.getName());

                bw.write(testName);
                bw.write(System.lineSeparator().repeat(2));

                for (LogEntry log : logs) {
                    bw.write(log.toString());
                    bw.newLine();
                }

                bw.newLine();
                log.info("{} logs saved to {}", StringUtils.capitalize(logType), path + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.warn("Currently getting logs is available only for Chrome");
        }

    }

    @Step("Navigating to URL: {pageUrl}")
    public static void navigateToPage(String pageUrl) {
        log.info("Navigating to {}", pageUrl);
        DriverManager.getDriver().navigate().to(pageUrl);
    }
}
