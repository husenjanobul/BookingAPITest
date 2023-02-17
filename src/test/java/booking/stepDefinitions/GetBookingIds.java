package booking.stepDefinitions;

import booking.utilities.ConfigurationReader;
import booking.utilities.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;

public class GetBookingIds {

    @Given("get request to health check endpoint")
    public void getRequestToHealthCheckEndpoint() {
        Hooks.response = given()
                .log()
                .all()
                .when().get(ConfigurationReader.get("ping"));
    }
    @Then("response status code should be {int}")
    public void responseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals("assert status code",statusCode,Hooks.response.statusCode());
    }

    @When("get request to GetBookingIds endpoint")
    public void getRequestToGetBookingIdsEndpoint() {
        Hooks.response = given()
                .log()
                .all()
                .when().get();
    }

    @And("response body should be array and contains uniq Ids")
    public void responseBodyShouldBeArrayAndContainsUniqIds() {
        Hooks.idsList = Hooks.response.path("bookingid");
        Assert.assertTrue(Utils.containsOnlyUniqueValues(Hooks.idsList));
    }

    @When("get request to GetBookingIds endpoint with {string} date")
    public void getRequestToGetBookingIdsEndpointWithDate(String date) {
        LocalDate checkInDate = LocalDate.now();
        Hooks.response = given()
                .queryParam(date,checkInDate.toString())
                .log()
                .all()
                .when().get();
    }
}
