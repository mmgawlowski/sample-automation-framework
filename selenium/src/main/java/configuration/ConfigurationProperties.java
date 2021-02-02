package configuration;

import java.util.Properties;

/**
 * {@code ConfigurationProperties} is the class responsible for storing and sharing properties and follows the Singleton pattern.
 * <p>
 * First, properties are loaded from a file involving the {@link PropertiesLoader} class,
 * and second, they are accessed using the {@link TestRunProperties} class.
 */
public class ConfigurationProperties {
    private static Properties properties;

    private ConfigurationProperties() {
    }

    public static void setProperties(Properties properties) {
        ConfigurationProperties.properties = properties;
    }

    public static Properties getProperties() {
        if (properties == null) {
            throw new IllegalStateException("Please set properties using setProperties() before calling getProperties()");
        }
        return properties;
    }

}