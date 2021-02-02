package driver.capabilities;

import io.qameta.allure.Step;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.helper.methods.generic.FilesManagement;

import java.util.logging.Level;

/**
 * The {@code FirefoxDriverCapabilities} class is responsible for providing methods for advanced capabilities management
 * for the Firefox browser.
 */
public class FirefoxDriverCapabilities implements DriverCapabilities<FirefoxOptions> {
    private static final Logger log = LoggerFactory.getLogger(FirefoxDriverCapabilities.class);
    private FirefoxOptions firefoxOptions = new FirefoxOptions();
    private static final String DOWNLOAD_PATH = System.getProperty("user.dir") + "\\download\\firefox";

    @Override
    @Step("Adding default capabilities")
    public void addDefaultCapabilities() {
        log.info("Adding default capabilities for Firefox");
        FilesManagement.createDestinationFolder(DOWNLOAD_PATH);
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", DOWNLOAD_PATH);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/pdf, image/jpeg, application/octet-stream");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.helperApps.neverAsk.openFile", "application/pdf");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.closeWhenDone", true);
        profile.setAcceptUntrustedCertificates(true);
        firefoxOptions.setProfile(profile);
        firefoxOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
    }

    @Override
    @Step("Headless mode enabled: {headless}")
    public void setHeadless(Boolean headless) {
        if (headless){
            firefoxOptions.setHeadless(true);
            firefoxOptions.addArguments("-width=1920");
            firefoxOptions.addArguments("-height=1080");
            log.warn("Firefox will run in headless mode.");
        }
    }

    @Override
    public void setLogging(String logType, Level level) {
        log.warn("Logging settings are currently not available for GeckoDriver");
    }

    @Override
    @Step("Setting custom capabilities")
    public void setCapabilities(FirefoxOptions options) {
        this.firefoxOptions = options;
    }

    @Override
    public FirefoxOptions getCapabilities() {
        return firefoxOptions;
    }
}
