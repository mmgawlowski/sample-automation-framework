package tests.reqres.user;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import request.objects.reqres.user.DeleteUserEndpoint;
import tests.TestBase;

public class DeleteUserTests extends TestBase {

    @Test
    @TmsLink("ID-5")
    @Issue("Defect-5")
    @Severity(SeverityLevel.NORMAL)
    @Description("The purpose of this test is to delete an existent user and check if the returned status code matches the expected one.")
    public void givenUserWhenDeleteUserThenUserIsDeletedTest() {
        DeleteUserEndpoint deleteUserEndpoint = new DeleteUserEndpoint();
        deleteUserEndpoint.setUserId(12)
                .sendRequest()
                .assertRequestSuccess();
    }
}
