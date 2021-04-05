package tests.reqres.register;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.reqres.register.RegisterData;
import request.objects.reqres.register.RegisterEndpoint;
import tests.TestBase;

import static org.hamcrest.core.IsEqual.equalTo;

public class RegisterTests extends TestBase {

    private RegisterEndpoint registerEndpoint;

    @BeforeMethod
    public void beforeTest() {
        registerEndpoint = new RegisterEndpoint();
    }

    @Test
    @TmsLink("ID-2")
    @Issue("Defect-2")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The test checks that the returned response body contains the expected data when a new user is registered.")
    public void givenCorrectDataWhenPostThenUserIsRegisteredTest() {
        RegisterData registerData = new RegisterData();
        registerData.setEmail("george.edwards@reqres.in");
        registerData.setPassword("peanut");

        Response response = registerEndpoint.setRegisterData(registerData)
                .sendRequest()
                .assertRequestSuccess()
                .getResponse();

        Assertions.assertThat(response.jsonPath().getInt("id")).isEqualTo(11);
        Assertions.assertThat(response.jsonPath().getString("token")).isEqualTo("QpwL5tke4Pnpja7X11");
    }

    @Test
    @TmsLink("ID-3")
    @Issue("Defect-3")
    @Severity(SeverityLevel.NORMAL)
    @Description("The test verifies that the expected error message is returned when an attempt is made to register without a password.")
    public void givenNoPasswordWhenPostThenErrorMessageIsReturnedTest() {
        RegisterData registerDataWithoutPassword = new RegisterData();
        registerDataWithoutPassword.setEmail("george.edwards@reqres.in");

        registerEndpoint.setRegisterData(registerDataWithoutPassword)
                .sendRequest()
                .assertStatusCode(HttpStatus.SC_BAD_REQUEST)
                .getResponse()
                .then().body("error", equalTo("Missing password"));
    }
}
