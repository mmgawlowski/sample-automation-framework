package tests.simple.examples;

import driver.manager.DriverManager;
import driver.manager.DriverUtils;
import driver.waits.Waits;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import tests.TestBase;

import java.io.File;

import static driver.manager.DriverManager.getCurrentBrowser;

public class FileDownloadingTests extends TestBase {
    private static final Logger log = LoggerFactory.getLogger(FileDownloadingTests.class);

    @Test
    public void asUserTryToDownloadAFile() {
        DriverUtils.navigateToPage("http://theinternet.przyklady.javastart.pl/download");
        WebElement linkToFile = DriverManager.getDriver().findElement(By.linkText("ISTQB Fundation Level.pdf"));
        linkToFile.click();

        String downloadDir = "download/" + getCurrentBrowser().name().toLowerCase();
        String fileName = "ISTQB Fundation Level.pdf";

        try {
            File downloadedFile = Waits.waitForFileTobeDownloaded(downloadDir, fileName, 10);
            if (downloadedFile.delete()) {
                log.info("The file successfully downloaded and deleted");
            }
        } catch (TimeoutException e) {
            Assertions.fail("File: %s could not be found in the specified folder: %s.", fileName, downloadDir);
        }
    }
}