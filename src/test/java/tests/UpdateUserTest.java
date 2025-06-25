package tests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class UpdateUserTest extends BaseTest {
    @Test
    public void testUpdateUser() {
        String requestBody = "{ \"name\": \"Bob\", \"email\": \"bob@example.com\" }";
        Response createResp = given().header("Content-Type", "application/json").body(requestBody)
            .when().post("/users/").then().statusCode(200).extract().response();
        String userId = createResp.jsonPath().getString("id");

        String updateBody = "{ \"name\": \"Bobby Updated\", \"email\": \"bobby.updated@example.com\" }";
        Response updateResp = given().header("Content-Type", "application/json").body(updateBody)
            .when().put("/users/" + userId).then().statusCode(200).extract().response();

        String updatedName = updateResp.jsonPath().getString("name");
        Assert.assertEquals(updatedName, "Bobby Updated");
    }

    @Test
    public void testUpdateInvalidUser() {
        String updateBody = "{ \"name\": \"NoUser\", \"email\": \"nouser@example.com\" }";
        given().header("Content-Type", "application/json").body(updateBody)
            .when().put("/users/invalid-id").then().statusCode(404);
    }
}
