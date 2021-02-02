package driver;

/**
 * This enum contains constants representing the browsers available for testing.
 */
public enum BrowserType {

    FIREFOX ("Firefox"),
    CHROME ("Chrome"),
    IE ("Internet Explorer");

    private final String browserName;

    BrowserType(String browserName) {
        this.browserName = browserName;
    }

    @Override
    public String toString() {
        return browserName;
    }
}
