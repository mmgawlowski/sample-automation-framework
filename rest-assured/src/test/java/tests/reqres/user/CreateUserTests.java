package tests.reqres.user;

import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pojo.reqres.user.UserCreateOrUpdate;
import pojo.reqres.user.UserCreateResponse;
import request.objects.reqres.user.CreateUserEndpoint;
import request.objects.reqres.user.DeleteUserEndpoint;
import test.data.UserGenerator;
import tests.TestBase;

public class CreateUserTests extends TestBase {

    private String userId;

    @Test
    @TmsLink("ID-4")
    @Issue("Defect-4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The purpose of this test is to create a new user and check if the returned object matches the sent one.")
    public void givenUserWhenPostUserThenUserIsCreatedTest() {
        UserGenerator userGenerator = new UserGenerator();
        UserCreateOrUpdate userToCreate = userGenerator.generateUser();

        CreateUserEndpoint createUserEndpoint = new CreateUserEndpoint();
        UserCreateResponse response = createUserEndpoint.setUser(userToCreate)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        userId = response.getId();

        Assertions.assertThat(response.getLastName()).isEqualTo(userToCreate.getLastName());
        Assertions.assertThat(response.getJob()).isEqualTo(userToCreate.getJob());
    }

    @AfterMethod
    public void cleanUp() {
        // No actual data is created when using Reqres, so there is no need to clean up after testing.
        // However, I created this method as an example of good practice.
        new DeleteUserEndpoint().setUserId(Integer.parseInt(userId))
                .sendRequest()
                .assertRequestSuccess();
    }
}
