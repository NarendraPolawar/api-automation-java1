package tests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class CreateUserTest extends BaseTest {
    @Test
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"John\", \"email\": \"john@example.com\" }";
        Response response = given().header("Content-Type", "application/json").body(requestBody)
            .when().post("/users/").then().statusCode(200).extract().response();
        String id = response.jsonPath().getString("id");
        Assert.assertNotNull(id);
    }

    @Test
    public void testCreateUserInvalidEmail() {
        String requestBody = "{ \"name\": \"John\", \"email\": \"not-an-email\" }";
        given().header("Content-Type", "application/json").body(requestBody)
            .when().post("/users/").then().statusCode(422);
    }
}
