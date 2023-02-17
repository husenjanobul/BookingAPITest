package booking.stepDefinitions;

import booking.utilities.ConfigurationReader;
import booking.utilities.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.*;


public class GetBooking {
    @When("get request to GetBooking endpoint with path param {string}")
    public void getRequestToGetBookingEndpointWithPathParamId(String id) {
        Random random = new Random();
        String existId = Hooks.idsList.get(random.nextInt(Hooks.idsList.size())).toString();

        Hooks.response = given()
                .accept("application/json")
                .and()
                .pathParam(id, existId)
                .log()
                .all()
                .when()
                .get(ConfigurationReader.get("pathParam"));

        Hooks.responseKeyList = Utils.getKeys(Utils.responseToJsonObj(Hooks.response));
    }

    @And("response should be contains following information")
    public void responseShouldBeContainsFollowingInformation(List<String> info) {
        Assert.assertTrue(Hooks.responseKeyList.containsAll(info));
    }

}
