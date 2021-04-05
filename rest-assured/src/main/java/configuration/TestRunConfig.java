package configuration;

import org.aeonbits.owner.Config;

/**
 * This interface is a part of the OWNER library - an interesting alternative for usually used {@code Properties}.
 * It was mentioned in the JavaStart course and I think it is worth sharing further.
 * <p>
 * In this case properties are read from reqres.properties file placed in src/main/resources.
 *
 * @see <a href="http://owner.aeonbits.org/">OWNER Homepage</a>
 */
@Config.Sources("classpath:regres.properties")
public interface TestRunConfig extends Config {

    @Key("BASE_URI")
    String baseUri();

    @Key("BASE_PATH")
    String basePath();
}
