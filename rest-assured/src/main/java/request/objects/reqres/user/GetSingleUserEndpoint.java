package request.objects.reqres.user;

import configuration.RequestConfigurationBuilder;
import org.apache.http.HttpStatus;
import pojo.reqres.user.get.SingleUser;
import request.objects.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

/**
 * This class refers to the endpoint /users/{userId} and the GET method from reqres.in.
 */
public class GetSingleUserEndpoint extends BaseEndpoint<GetSingleUserEndpoint, SingleUser> {
    private int userId;

    @Override
    protected Type getModelType() {
       return SingleUser.class;
    }

    @Override
    public GetSingleUserEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .pathParam("userId", userId)
                .when().get("users/{userId}");
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public GetSingleUserEndpoint setUserId(int userId) {
        this.userId = userId;
        return this;
    }
}
