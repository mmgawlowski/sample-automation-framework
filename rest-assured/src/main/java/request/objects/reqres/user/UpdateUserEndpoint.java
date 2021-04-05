package request.objects.reqres.user;

import configuration.RequestConfigurationBuilder;
import org.apache.http.HttpStatus;
import pojo.reqres.user.UserCreateOrUpdate;
import pojo.reqres.user.UserUpdateResponse;
import request.objects.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

/**
 * This class refers to the endpoint /users/{userId} and the PUT method from reqres.in.
 */
public class UpdateUserEndpoint extends BaseEndpoint<UpdateUserEndpoint, UserUpdateResponse> {
    private int userId;
    private UserCreateOrUpdate userToUpdate;

    @Override
    protected Type getModelType() {
        return UserUpdateResponse.class;
    }

    @Override
    public UpdateUserEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification()).body(userToUpdate)
                .when().put("users/{userId}", userId);
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public UpdateUserEndpoint setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public UpdateUserEndpoint setUser(UserCreateOrUpdate userToUpdate) {
        this.userToUpdate = userToUpdate;
        return this;
    }
}
