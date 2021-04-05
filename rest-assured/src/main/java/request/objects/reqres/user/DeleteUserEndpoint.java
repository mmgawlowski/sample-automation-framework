package request.objects.reqres.user;

import configuration.RequestConfigurationBuilder;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpStatus;
import request.objects.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

/**
 * This class refers to the endpoint /users/{userId} and the DELETE method from reqres.in.
 */
public class DeleteUserEndpoint extends BaseEndpoint<DeleteUserEndpoint, ObjectUtils.Null> {
    private int userId;

    @Override
    protected Type getModelType() {
        return null;
    }

    @Override
    public DeleteUserEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().delete("users/{userId}", userId);
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_NO_CONTENT;
    }

    public DeleteUserEndpoint setUserId(int userId) {
        this.userId = userId;
        return this;
    }
}
