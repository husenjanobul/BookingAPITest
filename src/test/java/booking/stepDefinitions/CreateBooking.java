package booking.stepDefinitions;

import booking.utilities.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.*;

public class CreateBooking {
    @When("post request to CreateBooking endpoint wth client {string} info")
    public void postRequestToCreateBookingEndpointWthClientInfo(String bodyInfo) {
        Hooks.requestBody = Utils.postBody(bodyInfo);
        Hooks.response = given()
                .header("Accept", "application/json")
                .and().header("Content-Type", "application/json")
                .body(Hooks.requestBody)
                .log()
                .all()
                .when().post().prettyPeek();
    }

    @And("response body should be same the posted client info")
    public void responseBodyShouldBeSameThePostedClientInfo() {
        JSONObject jsonObject = new JSONObject(Hooks.response.asPrettyString());
        JSONObject body = new JSONObject(Hooks.requestBody);
        JSONObject booking =new JSONObject( jsonObject.getJSONObject("booking").toString());

        Assert.assertEquals(booking.toString(),body.toString());
    }
}
