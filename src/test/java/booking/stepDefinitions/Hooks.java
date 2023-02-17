package booking.stepDefinitions;

import io.cucumber.java.Before;
import static io.restassured.RestAssured.baseURI;

import booking.utilities.ConfigurationReader;
import io.restassured.response.Response;

import java.util.List;

public class Hooks {
    public static Response response;
    public static List<Integer> idsList;
    public static List<String> responseKeyList;
    public static String requestBody;
    public static String token;
    public static Integer targetId;

    @Before
    public void setUp(){
        baseURI = ConfigurationReader.get("baseURL");
    }
}
