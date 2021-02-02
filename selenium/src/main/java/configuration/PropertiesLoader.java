package configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * {@code PropertiesLoader} is the class responsible for reading properties from the .properties file.
 */
public class PropertiesLoader {

    private static final Logger log = LoggerFactory.getLogger(PropertiesLoader.class);

    /**
     * Reads properties from the specified .properties file,
     * then loads them into an object of the {@link Properties} class.
     * <p>
     * The files are stored in the main/resources directory.
     *
     * @param propertiesFileName the name of the .properties file, e.g. "booking_com.properties"
     * @return the loaded properties as an object of the {@code Properties} class.
     * @throws RuntimeException if the properties cannot be loaded.
     */
    public Properties getPropertiesFromFile(String propertiesFileName) {

        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            log.info("Trying to load properties with file name: {}", propertiesFileName);

            if (inputStream != null) {
                properties.load(inputStream);
                log.info("Successfully loaded properties for file: {}", propertiesFileName);
            } else {
                throw new FileNotFoundException(String.format("Property file '%s' not found in the classpath", propertiesFileName));
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot load properties due to IOException!");
        }

        return properties;
    }

}
