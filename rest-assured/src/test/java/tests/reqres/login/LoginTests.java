package tests.reqres.login;

import io.qameta.allure.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import pojo.reqres.login.LoginData;
import request.objects.reqres.login.LoginEndpoint;
import tests.TestBase;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;

public class LoginTests extends TestBase {

    @Test
    @TmsLink("ID-1")
    @Issue("Defect-1")
    @Severity(SeverityLevel.NORMAL)
    @Description("The test checks that the expected error message is returned when a login attempt is made for a non-existent user.")
    public void givenNonRegisteredEmailWhenPostThenUserNotFoundMessageIsReturnedTest() {
        LoginData nonexistentLoginData = new LoginData();
        nonexistentLoginData.setEmail("gall.anonim@gmail.com");
        nonexistentLoginData.setPassword("password");

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectContentType(ContentType.JSON)
                .expectBody("error", equalTo("user not found"))
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .expectHeader("Server", "cloudflare")
                .expectResponseTime(Matchers.lessThan(1000L), TimeUnit.MILLISECONDS);
        ResponseSpecification expectedResponseSpecification = responseSpecBuilder.build();

        LoginEndpoint loginEndpoint = new LoginEndpoint();
        loginEndpoint.setLoginData(nonexistentLoginData)
                .sendRequest()
                .getResponse()
                .then().spec(expectedResponseSpecification);
    }
}
