package booking.stepDefinitions;

import booking.utilities.ConfigurationReader;
import booking.utilities.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.util.Random;

import static io.restassured.RestAssured.*;

public class PartialUpdateBooking {

    @When("post request to CreateToken endpoint with username and password")
    public void postRequestToCreateTokenEndpointWithAnd() {
        Hooks.response = given()
                .contentType("application/json")
                .body(Utils.authBody(ConfigurationReader.get("username"), ConfigurationReader.get("password")))
                .log()
                .all()
                .when().post(ConfigurationReader.get("createToken")).prettyPeek();
    }

    @And("get {string} for new authentication")
    public void getForNewAuthentication(String token) {
        Hooks.token = Hooks.response.path(token);
    }

    @When("get request to GetBookingIds endpoint and get a existing id")
    public void getRequestToGetBookingIdsEndpointAndGetAExisting() {
        Hooks.response = given()
                .log()
                .all()
                .when().get();
        Hooks.idsList = Hooks.response.path("bookingid");
        Hooks.targetId = Hooks.idsList.get(new Random().nextInt(Hooks.idsList.size()));
    }

    @When("post request to UpdateBooking endpoint with {string} {string} and {string} body")
    public void postRequestToUpdateBookingEndpointWithAndBody(String token, String id, String bodyInfo) {
        Hooks.requestBody = Utils.postBody(bodyInfo);
        Hooks.response = given()
                .header("Accept", "application/json")
                .and().header("Content-Type", "application/json")
                .and().header("Cookie", token + "=" + Hooks.token)
                .pathParam(id, Hooks.targetId)
                .body(Hooks.requestBody)
                .log()
                .all()
                .when().patch(ConfigurationReader.get("pathParam")).prettyPeek();

    }

    @And("response body should contains requested body")
    public void responseBodyShouldContainsRequestedBody() {
        Assert.assertTrue(Hooks.requestBody.contains(Hooks.response.path("firstname")));
        Assert.assertTrue(Hooks.requestBody.contains(Hooks.response.path("lastname")));
    }

}
