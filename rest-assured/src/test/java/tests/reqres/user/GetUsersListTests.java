package tests.reqres.user;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import request.objects.reqres.user.GetUsersListEndpoint;
import tests.TestBase;

import java.io.InputStream;

public class GetUsersListTests extends TestBase {

    @Test
    @TmsLink("ID-8")
    @Issue("Defect-8")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The test checks if, given a number of a page, the returned UsersList object is the same as the expected one.")
    public void givenExistingUsersWhenGetWithNumberOfThePageThenPageWithListOfUsersIsReturnedTest() {
        InputStream expectedResponse = getClass().getClassLoader()
                .getResourceAsStream("test-data/reqres_get_page_response.json");

        Response actualResponse = new GetUsersListEndpoint().setPageNumber(2)
                .sendRequest()
                .assertRequestSuccess()
                .saveResponseAsFile("reqres_get_page_actual_response.json")
                .getResponse();
        // Creating an expected response using a POJO class object when there are a large number of fields to compare is inefficient.
        // For this reason I have prepared the expected response as a JSON file.
        Assertions.assertThat(actualResponse.asInputStream()).hasSameContentAs(expectedResponse);
    }
}
