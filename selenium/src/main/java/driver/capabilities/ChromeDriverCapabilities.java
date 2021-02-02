package driver.capabilities;

import io.qameta.allure.Step;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.helper.methods.generic.FilesManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * The {@code ChromeDriverCapabilities} class is responsible for providing methods for advanced capabilities management
 * for the Chrome browser.
 */
public class ChromeDriverCapabilities implements DriverCapabilities<ChromeOptions> {
    private static final Logger log = LoggerFactory.getLogger(ChromeDriverCapabilities.class);
    private ChromeOptions chromeOptions = new ChromeOptions();
    private final LoggingPreferences logPrefs = new LoggingPreferences();
    private static final String DOWNLOAD_PATH = System.getProperty("user.dir") + "\\download\\chrome";

    @Override
    @Step("Adding default capabilities")
    public void addDefaultCapabilities() {
        log.info("Adding default capabilities for Chrome");
        FilesManagement.createDestinationFolder(DOWNLOAD_PATH);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", "false");
        prefs.put("download.default_directory", DOWNLOAD_PATH);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("--disable-popup-blocking");
    }

    @Override
    @Step("Headless mode enabled: {headless}")
    public void setHeadless(Boolean headless) {
        if (headless){
            chromeOptions.setHeadless(true);
            chromeOptions.addArguments("--window-size=1920x1080");
            chromeOptions.addArguments("--proxy-server='direct://'");
            chromeOptions.addArguments("--proxy-bypass-list=*");
            log.warn("Chrome will run in headless mode.");
        }
    }

    @Override
    public void setLogging(String logType, Level level) {
        log.info("Setting logging for Chrome: type: {}, level: {}", logType, level.toString().toLowerCase());
        logPrefs.enable(logType, level);
        chromeOptions.setCapability("goog:loggingPrefs", logPrefs);
    }

    @Override
    @Step("Setting custom capabilities")
    public void setCapabilities(ChromeOptions options) {
        this.chromeOptions = options;
    }

    @Override
    public ChromeOptions getCapabilities() {
        return chromeOptions;
    }

}
