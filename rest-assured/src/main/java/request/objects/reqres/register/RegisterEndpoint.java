package request.objects.reqres.register;

import configuration.RequestConfigurationBuilder;
import org.apache.http.HttpStatus;
import pojo.reqres.register.RegisterData;
import pojo.reqres.register.RegisterResponse;
import request.objects.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

/**
 * This class refers to the endpoint /register and the POST method from reqres.in.
 */
public class RegisterEndpoint extends BaseEndpoint<RegisterEndpoint, RegisterResponse> {
    RegisterData registerData;

    @Override
    protected Type getModelType() {
        return RegisterResponse.class;
    }

    @Override
    public RegisterEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification()).body(registerData)
                .when().post("register");
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public RegisterEndpoint setRegisterData(RegisterData registerData) {
        this.registerData = registerData;
        return this;
    }
}
