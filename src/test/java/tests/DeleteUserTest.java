package tests;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseTest {
    @Test
    public void testDeleteUser() {
        String requestBody = "{ \"name\": \"DeleteMe\", \"email\": \"deleteme@example.com\" }";
        Response createResp = given().header("Content-Type", "application/json").body(requestBody)
            .when().post("/users/").then().statusCode(200).extract().response();
        String userId = createResp.jsonPath().getString("id");

        given().header("Accept", "application/json")
            .when().delete("/users/" + userId).then().statusCode(200);

        given().header("Accept", "application/json")
            .when().get("/users/" + userId).then().statusCode(404);
    }

    @Test
    public void testDeleteInvalidUser() {
        given().header("Accept", "application/json")
            .when().delete("/users/invalid-id").then().statusCode(404);
    }
}
