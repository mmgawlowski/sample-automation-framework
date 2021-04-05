package tests.reqres.user;

import io.qameta.allure.*;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.reqres.user.get.Data;
import pojo.reqres.user.get.SingleUser;
import pojo.reqres.user.get.Support;
import request.objects.reqres.user.GetSingleUserEndpoint;
import tests.TestBase;

public class GetSingleUserTests extends TestBase {

    private GetSingleUserEndpoint endpoint;

    @BeforeMethod
    public void beforeTest() {
        endpoint = new GetSingleUserEndpoint();
    }

    @Test
    @TmsLink("ID-6")
    @Issue("Defect-6")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The test checks if, given an ID of an existing user, the returned SingleUser object is the same as the expected one.")
    public void givenExistingUserIdWhenGetUserThenUserIsReturnedTest() {
        Data data = new Data();
        data.setId(5);
        data.setEmail("charles.morris@reqres.in");
        data.setFirstName("Charles");
        data.setLastName("Morris");
        data.setAvatar("https://reqres.in/img/faces/5-image.jpg");

        Support support = new Support();
        support.setUrl("https://reqres.in/#support-heading");
        support.setText("To keep ReqRes free, contributions towards server costs are appreciated!");

        SingleUser expectedUser = new SingleUser();
        expectedUser.setData(data);
        expectedUser.setSupport(support);

        SingleUser actualUser = endpoint.setUserId(Integer.parseInt(expectedUser.getData().getId().toString()))
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        Assertions.assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);
    }

    @Test
    @TmsLink("ID-7")
    @Issue("Defect-7")
    @Severity(SeverityLevel.NORMAL)
    @Description("The test checks that no user will be found when a non-existent user ID is given.")
    public void givenNonExistingUserIdWhenGetUserThenUserIsNotFoundTest() {
        int nonExistingUserId = 36;
        endpoint.setUserId(nonExistingUserId).sendRequest().assertStatusCode(HttpStatus.SC_NOT_FOUND);
    }
}
