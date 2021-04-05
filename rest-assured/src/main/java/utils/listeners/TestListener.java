package utils.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * A standard implementation of one of the basic TestNG listeners.
 *
 * @see <a href="https://www.javadoc.io/doc/org.testng/testng/latest/org/testng/ITestListener.html">ITestListener</a>
 */
public class TestListener implements ITestListener {
    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test {} passed successfully", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test {} failed!", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test {} skipped!", result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info("Test {} failed!", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Running tests");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test run finished");
    }

}