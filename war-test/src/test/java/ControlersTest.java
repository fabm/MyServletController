import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class ControlersTest {
    @BeforeClass
    public static void setupUrl() {
        RestAssured.baseURI = "http://localhost:8081/war-test";
    }

    @Test
    public void noneTest() {
        RestAssured.get("teste")
                .then()
                .body(equalTo("none"));
    }

    @Test
    public void olaMundoTest() {
        RestAssured.get("/ola/mundo/1/2/teste")
                .then()
                .body(equalTo("ola:1:2teste"));
    }

    @Test
    public void olaTest() {
        RestAssured.get("/ola/test")
                .then()
                .body(equalTo("teste"));
    }

}
