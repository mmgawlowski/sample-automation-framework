package request.objects.reqres.login;

import configuration.RequestConfigurationBuilder;
import org.apache.http.HttpStatus;
import pojo.reqres.login.LoginData;
import pojo.reqres.login.LoginResponse;
import request.objects.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

/**
 * This class refers to the endpoint /login and the POST method from reqres.in.
 */
public class LoginEndpoint extends BaseEndpoint<LoginEndpoint, LoginResponse> {
    LoginData loginData;

    @Override
    protected Type getModelType() {
        return LoginResponse.class;
    }

    @Override
    public LoginEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification()).body(loginData)
                .when().post("login");
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public LoginEndpoint setLoginData(LoginData loginData) {
        this.loginData = loginData;
        return this;
    }
}
