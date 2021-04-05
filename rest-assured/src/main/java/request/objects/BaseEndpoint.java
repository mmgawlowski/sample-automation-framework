package request.objects;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * An abstract class providing basic methods to create and configure subclasses,
 * each responsible for sending one specific type of HTTP request
 * (combination of the specific endpoint and HTTP method).
 *
 * @param <E> generic parameter corresponding to the given endpoint-HTTP method subclass.
 * @param <M> generic parameter corresponding to the response model returned by a given HTTP request.
 */
@SuppressWarnings("unchecked")
public abstract class BaseEndpoint<E, M> {
    private static final Logger log = LoggerFactory.getLogger(BaseEndpoint.class);

    protected Response response;

    /**
     *
     * @return the response model type, which is represented by the corresponding POJO class.
     */
    protected abstract Type getModelType();

    /**
     * Executes HTTP request.
     * Each type of HTTP request has its own configuration implemented in this method.
     *
     * @return an object of the given endpoint subclass.
     */
    public abstract E sendRequest();

    /**
     * Returns an HTTP status code indicating that the request was processed correctly.
     *
     * @return HTTP status code as integer.
     */
    protected abstract int getSuccessStatusCode();

    /**
     * Returns the deserialised response as an object of the appropriate POJO class.
     *
     * @return a POJO class object (the response model).
     */
    public M getResponseModel() {
        return response.as(getModelType());
    }

    /**
     * Asserts that the request was processed correctly by comparing the received HTTP status code
     * with the one indicating success.
     *
     * @return an object of the given endpoint subclass.
     */
    public E assertRequestSuccess() {
        return assertStatusCode(getSuccessStatusCode());
    }

    /**
     * Asserts that received HTTP status code equals the expected one.
     *
     * @param statusCode the expected HTTP status code as an integer.
     * @return an object of the given endpoint subclass.
     */
    public E assertStatusCode(int statusCode) {
        Assertions.assertThat(response.getStatusCode()).as("Status code").isEqualTo(statusCode);
        return (E) this;
    }

    /**
     * Saves the received response as a JSON file in the target/responses directory.
     *
     * @param fileName the chosen file name.
     * @return an object of the given endpoint subclass.
     */
    public E saveResponseAsFile(String fileName) {
        File destFolder = new File("target/responses");
        String path = String.format("%s/%s", destFolder, fileName);
        if (!destFolder.exists()) {
            if (destFolder.mkdirs()) {
                log.info("Folders: {} created", destFolder);
            } else {
                log.warn("Folders: {} couldn't be created", destFolder);
            }
        }

        try (FileWriter file = new FileWriter(path)) {
            file.write(response.jsonPath().prettify());
            file.flush();
            log.info("Response has been saved as {}", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } return (E) this;
    }

    public Response getResponse() {
        return response;
    }
}
