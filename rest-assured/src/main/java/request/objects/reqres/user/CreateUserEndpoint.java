package request.objects.reqres.user;

import configuration.RequestConfigurationBuilder;
import org.apache.http.HttpStatus;
import pojo.reqres.user.UserCreateOrUpdate;
import pojo.reqres.user.UserCreateResponse;
import request.objects.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

/**
 * This class refers to the endpoint /users and the POST method from reqres.in.
 */
public class CreateUserEndpoint extends BaseEndpoint<CreateUserEndpoint, UserCreateResponse> {
    private UserCreateOrUpdate userToCreate;

    @Override
    protected Type getModelType() {
       return UserCreateResponse.class;
    }

    @Override
    public CreateUserEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification()).body(userToCreate)
        .when().post("users");
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_CREATED;
    }

    public CreateUserEndpoint setUser(UserCreateOrUpdate userToCreate) {
        this.userToCreate = userToCreate;
        return this;
    }
}
