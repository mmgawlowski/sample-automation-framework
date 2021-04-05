package request.objects.reqres.user;

import configuration.RequestConfigurationBuilder;
import org.apache.http.HttpStatus;
import pojo.reqres.user.get.UsersList;
import request.objects.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

/**
 * This class refers to the endpoint /users and the GET method from reqres.in.
 */
public class GetUsersListEndpoint extends BaseEndpoint<GetUsersListEndpoint, UsersList> {
    private UsersList pageWithUsers;
    private int pageNumber;

    @Override
    protected Type getModelType() {
        return UsersList.class;
    }

    @Override
    public GetUsersListEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .queryParam("page", pageNumber)
                .when().get("users");
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public GetUsersListEndpoint setPageWithListOfUsers(UsersList pageWithUsers) {
        this.pageWithUsers = pageWithUsers;
        return this;
    }
    public GetUsersListEndpoint setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }
}
