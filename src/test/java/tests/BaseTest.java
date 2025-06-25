package tests;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static String BASE_URL;

    @BeforeClass
    public void setup() {
        BASE_URL = System.getProperty("baseUrl", "http://localhost:8000");
        RestAssured.baseURI = BASE_URL;
    }
}
