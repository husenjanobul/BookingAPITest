package booking.utilities;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.*;

public class Utils {

    public static boolean containsOnlyUniqueValues(List<Integer> list) {
        Set<Integer> uniqueValues = new HashSet<>();
        for (Integer i : list) {
            if (uniqueValues.contains(i)) {
                return false;
            } else {
                uniqueValues.add(i);
            }
        }
        return true;
    }

    public static JSONObject responseToJsonObj(Response response){
        return new JSONObject(response.asPrettyString());
    }

    public static List<String> getKeys(JSONObject json) {
        List<String> keys = new ArrayList<>();
        for (String key : json.keySet()) {
            keys.add(key);
            Object obj = json.get(key);
            if (obj instanceof JSONObject) {
                keys.addAll(getKeys((JSONObject) obj));
            }
        }
        return keys;
    }

    public static String postBody(String bodyInfo){
        Faker faker = new Faker();
        Random random = new Random();
        if(bodyInfo.equals( "partial")) {
            return "{\n" +
                    "    \"firstname\" : \"" + faker.name().firstName() + "\",\n" +
                    "    \"lastname\" : \"" + faker.name().lastName() + "\"\n" +
                    "}";
        }else {
            return "{\n" +
                    "    \"firstname\" : \"" + faker.name().firstName() + "\",\n" +
                    "    \"lastname\" : \"" + faker.name().lastName() + "\",\n" +
                    "    \"totalprice\" : " + random.nextInt(1000) + ",\n" +
                    "    \"depositpaid\" : " + random.nextBoolean() + ",\n" +
                    "    \"bookingdates\" : {\n" +
                    "        \"checkin\" : \"" + LocalDate.now().minusDays(15) + "\",\n" +
                    "        \"checkout\" : \"" + LocalDate.now().minusDays(5) + "\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\" : \"" + faker.options().option("Breakfast", "Lunch", "Dinner") + "\"" +
                    "}";
        }
    }

    public static String authBody(String user,String password){
        return "{\n" +
                "    \"username\" : \""+user+"\",\n" +
                "    \"password\" : \""+password+"\"" +
                "}";
    }

}
