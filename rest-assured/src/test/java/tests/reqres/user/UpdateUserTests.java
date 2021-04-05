package tests.reqres.user;

import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pojo.reqres.user.UserCreateOrUpdate;
import pojo.reqres.user.UserUpdateResponse;
import request.objects.reqres.user.UpdateUserEndpoint;
import test.data.UserGenerator;
import tests.TestBase;

public class UpdateUserTests extends TestBase {

    @Test
    @TmsLink("ID-9")
    @Issue("Defect-9")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The purpose of this test is to update an existent user and check if the returned object matches the sent one.")
    public void givenUserWhenPutUserThenUserIsUpdatedTest() {
        UserGenerator userGenerator = new UserGenerator();
        UserCreateOrUpdate userToUpdate = userGenerator.generateUser();

        UpdateUserEndpoint updateUserEndpoint = new UpdateUserEndpoint();
        UserUpdateResponse response = updateUserEndpoint.setUser(userToUpdate)
                .setUserId(53)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        Assertions.assertThat(response).usingRecursiveComparison()
                .ignoringFields("updatedAt")
                .isEqualTo(userToUpdate);
    }
}
