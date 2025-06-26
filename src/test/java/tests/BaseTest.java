package tests;

import io.qameta.allure.Step;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected static String BASE_URL;

    @BeforeClass
    @Step("Setup base URL for API testing")
    @Description("Initializes the RestAssured base URI from system property or defaults to http://localhost:8000")
    public void setup() {
        BASE_URL = System.getProperty("baseUrl", "http://localhost:8000");
        RestAssured.baseURI = BASE_URL;
        logBaseUrl(BASE_URL);
    }

    @Step("Base URL set to: {baseUrl}")
    public void logBaseUrl(String baseUrl) {
        // This helps make the value visible in Allure steps
    }
}

