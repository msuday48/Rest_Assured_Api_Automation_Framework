package org.Api_Testing.base;
import org.Api_Testing.asserts.AssertActions;
import  org.Api_Testing.endpoints.APIConstants;
import  org.Api_Testing.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    // CommonToAll Testcase
    // Base URL, Content Type - json - common

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setUp() {
        //BASE URL,Content Type JSON

        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

    /*    requestSpecification=RestAssured
                .given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(ContentType.JSON)
                .log().all();
    }*/

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();
    }

    public String getToken() {
        requestSpecification = RestAssured
                .given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);

        // Setting the payload
        String payload = payloadManager.setAuthPayload();

        // Get the Token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();
        // String Extraction
        String token = payloadManager.getTokenFromJSON(response.asString());

        return token;
    }
}