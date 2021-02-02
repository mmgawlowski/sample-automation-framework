package configuration;


import driver.BrowserType;

/**
 * {@code TestRunProperties} is the class responsible for providing access to properties read from .properties files
 * and stored as a Properties object.
 */
public class TestRunProperties {

    /**
     * Returns the URL of the application under test.
     *
     * @return a string that represents value assigned to the "app.url" property in the .properties file.
     */
    public static String getAppUrl() {
        return ConfigurationProperties.getProperties().getProperty("app.url");
    }

    /**
     * Returns the URL of the Selenium Grid Hub.
     * This property is important during remote test execution.
     *
     * @return a string that represents value assigned to the property key "grid.url"  in the .properties file.
     */
    public static String getGridUrl() {
        return ConfigurationProperties.getProperties().getProperty("grid.url");
    }

    /**
     * Returns the browser on which the tests should be executed.
     *
     * @return a {@link BrowserType} enum constant that represents value assigned to the property key "browser"
     *         in the .properties file.
     */
    public static BrowserType getBrowserToRun() {
        return BrowserType.valueOf(ConfigurationProperties.getProperties().getProperty("browser").toUpperCase());
    }

    /**
     * Returns a boolean that determines whether tests should be executed remotely.
     *
     * @return a boolean that represents value assigned to the property key "is.remote.run" in the .properties file.
     */
    public static boolean getIsRemoteRun(){
        return Boolean.parseBoolean(ConfigurationProperties.getProperties().getProperty("is.remote.run"));
    }

    /**
     * Returns a boolean that determines whether tests should be executed on the browser running in the headless mode.
     *
     * @return a boolean that represents value assigned to the property key "is.headless.run" in the .properties file.
     */
    public static boolean getIsHeadlessRun(){
        return Boolean.parseBoolean(ConfigurationProperties.getProperties().getProperty("is.headless.run"));
    }
}
