package RestAssuredAPI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class RestAssuredTest {

    private Response response;
    private RequestSpecification httpRequest;

    @BeforeClass
    public void setUp() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        httpRequest = given()
                .header("Authorization", " 59df17d6fa9e38e2c5fc8af0dab338c89809c2eaca32cc01b882822f17033371")
                .contentType(ContentType.JSON);
    }

    @Test
    public void verifyStatusCode() {
        Response response = httpRequest.get("/users");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void getAllUserTest() {
        response = given()
                .contentType(ContentType.JSON)
                .get("/users");

        // Verify the status code is 200
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        // Get the Response Body--Deserialize the response data
        JsonArray users = new Gson().fromJson(response.getBody().asString(), JsonArray.class);

        // Verify the response body contains the expected user details
        Assert.assertEquals(users.size(), 10);

        // Print each user's data
        for (JsonElement user : users) {
            JsonObject userData = user.getAsJsonObject();

            String id = userData.get("id").getAsString();
            String name = userData.get("name").getAsString();
            String email = userData.get("email").getAsString();
            String gender = userData.get("gender").getAsString();
            String status = userData.get("status").getAsString();

            System.out.println("User ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Gender: " + gender);
            System.out.println("Status: " + status);
            System.out.println("--------------------------------");
        }
    }
}




