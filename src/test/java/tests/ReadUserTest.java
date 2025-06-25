package tests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class ReadUserTest extends BaseTest {
    @Test
    public void testReadUser() {
        String requestBody = "{ \"name\": \"Alice\", \"email\": \"alice@example.com\" }";
        Response createResp = given().header("Content-Type", "application/json").body(requestBody)
            .when().post("/users/").then().statusCode(200).extract().response();
        String userId = createResp.jsonPath().getString("id");

        Response readResp = given().header("Accept", "application/json")
            .when().get("/users/" + userId).then().statusCode(200).extract().response();

        String email = readResp.jsonPath().getString("email");
        Assert.assertEquals(email, "alice@example.com");
    }

    @Test
    public void testReadInvalidUser() {
        given().header("Accept", "application/json")
            .when().get("/users/invalid-id").then().statusCode(404);
    }
}
