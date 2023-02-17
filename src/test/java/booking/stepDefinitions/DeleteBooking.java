package booking.stepDefinitions;

import booking.utilities.ConfigurationReader;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.given;

public class DeleteBooking {
    @When("post request to DeleteBooking endpoint with {string} {string}")
    public void postRequestToDeleteBookingEndpointWith(String token, String id) {
        Hooks.response = given()
//                .header("Accept", "application/json")
                .and().header("Content-Type", "application/json")
                .and().header("Cookie", token + "=" + Hooks.token)
                .pathParam(id, Hooks.targetId)
                .log()
                .all()
                .when().delete(ConfigurationReader.get("pathParam")).prettyPeek();
    }

    @When("get request to GetBooking endpoint with deleted booking {string}")
    public void getRequestToGetBookingEndpointWithDeletedBooking(String id) {
        Hooks.response = given()
                .accept("application/json")
                .and()
                .pathParam(id, Hooks.targetId)
                .log()
                .all()
                .when()
                .get(ConfigurationReader.get("pathParam"));
    }
}
