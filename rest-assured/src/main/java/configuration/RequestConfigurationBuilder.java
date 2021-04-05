package configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;

/**
 * {@code RequestConfigurationBuilder} class provides methods that allow to set the general configuration of the request
 * used when it is sent.
 */
public class RequestConfigurationBuilder {

    /**
     * @return an object of class {@code RequestSpecBuilder} with a basic configuration that can be further extended.
     */
    public RequestSpecBuilder getRequestSpecBuilder() {
        return new RequestSpecBuilder()
                .setConfig(RestAssuredConfig.config().objectMapperConfig(objectMapperConfig().defaultObjectMapperType(ObjectMapperType.GSON)))
                .setContentType(ContentType.JSON);
    }

    /**
     * @return a ready-to-use basic configuration that can be applied using the given().spec() method.
     */
    public static RequestSpecification getDefaultRequestSpecification() {
        return new RequestConfigurationBuilder().getRequestSpecBuilder().build();
    }
}
