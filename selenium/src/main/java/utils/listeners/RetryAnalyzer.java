package utils.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class implements one of the basic TestNG listeners,
 * which allows the test to be repeated under certain conditions.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int MAX_NUMBER_OF_RETRIES = 1;
    private static final Logger log = LoggerFactory.getLogger(RetryAnalyzer.class);

    /**
     * Repeats the test if it ended with a status other than success
     * until the set maximum number of retries is reached.
     * <p>
     * Failures before reaching the limit will be marked as skipped.
     *
     * @param iTestResult the result of the test method that just ran.
     * @return true if the test method has to be retried, false otherwise.
     */
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (count < MAX_NUMBER_OF_RETRIES) {
                count++;
                log.info("Retrying test method {}!", iTestResult.getName());
                return true;
            }
        }
        log.info("Test method {} will be not retried!", iTestResult.getName());
        return false;
    }
}
