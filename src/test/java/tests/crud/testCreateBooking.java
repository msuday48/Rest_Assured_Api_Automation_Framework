package tests.crud;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.Api_Testing.base.BaseTest;
import org.Api_Testing.endpoints.APIConstants;
import org.Api_Testing.pojos.BookingRespons;
import org.testng.annotations.Test;

public class testCreateBooking extends BaseTest {

    @Owner("Promode")
    @TmsLink("https://google.com")
    @Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
    @Issue("JIRA_RBT-4")
    @Description("Verify that POST request is working fine.")
    @Test(groups = "qa")

    public void testVerifyCreateBookingPOST01(){

        requestSpecification.baseUri(APIConstants.BASE_URL);
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response =RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

  validatableResponse=response.then().log().all();
  validatableResponse.statusCode(200);

  BookingRespons bookingResponse=payloadManager.bookingResponseJava(response.asString());
  assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"James");
    }
}