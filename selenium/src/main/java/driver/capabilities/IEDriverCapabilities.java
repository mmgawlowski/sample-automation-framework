package driver.capabilities;

import io.qameta.allure.Step;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

/**
 * The {@code IEDriverCapabilities} class is responsible for providing methods for advanced capabilities management
 * for the Internet Explorer.
 */
public class IEDriverCapabilities implements DriverCapabilities<InternetExplorerOptions> {
    private static final Logger log = LoggerFactory.getLogger(IEDriverCapabilities.class);
    private InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();

    @Override
    @Step("Adding default capabilities")
    public void addDefaultCapabilities() {
        log.info("Adding default capabilities for Internet Explorer");
        internetExplorerOptions.ignoreZoomSettings();
        internetExplorerOptions.enablePersistentHovering();
        internetExplorerOptions.introduceFlakinessByIgnoringSecurityDomains();
        internetExplorerOptions.requireWindowFocus();
        internetExplorerOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        internetExplorerOptions.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, UnexpectedAlertBehaviour.IGNORE);
    }

    @Override
    public void setHeadless(Boolean headless) {
        if (headless) {
            log.warn("Headless mode is unavailable for Internet Explorer");
        }
    }

    @Override
    public void setLogging(String logType, Level level) {

    }

    @Override
    @Step("Setting custom capabilities")
    public void setCapabilities(InternetExplorerOptions options) {
        this.internetExplorerOptions = options;
    }

    @Override
    public InternetExplorerOptions getCapabilities() {
        return internetExplorerOptions;
    }
}
