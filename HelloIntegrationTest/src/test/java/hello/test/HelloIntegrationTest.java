//package hello.test;
//
//import hello.test.core.AbstractIntegrationTest;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.Test;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.*;
//
//public class HelloIntegrationTest extends AbstractIntegrationTest {
//
//    private static final Logger LOG = LogManager.getLogger();
//
//    @Test
//    public void givenGreeting_whenHittingEndpoint_should200() throws Exception {
//        when()
//            .get("/greeting")
//        .then()
//            .statusCode(200);
//    }
//
//    @Test
//    public void givenGreeting_whenHittingEndpoint_shouldContainGreeting() throws Exception {
//        when()
//            .get("/greeting")
//        .then()
//            .body("content", equalToIgnoringCase("Hello, World!"));
//    }
//
//    @Test
//    public void givenGreeting_whenSupplyingName_shouldGreetPersonName() throws Exception {
//        given()
//            .queryParam("name", "Patrick")
//        .when()
//            .get("/greeting")
//        .then()
//            .body("content", containsString("Patrick"));
//    }
//
//
//    @Test
//    public void givenGreeting_whenMultipleCalls_shouldIncreaseCounter() throws Exception {
//
//        int first =
//        when()
//            .get("/greeting")
//        .then()
//            .body("id", notNullValue())
//        .and()
//            .extract().body().jsonPath().getInt("id");
//
//        int second =
//        when()
//            .get("/greeting")
//        .then()
//            .body("id", notNullValue())
//        .and()
//            .extract().body().jsonPath().getInt("id");
//
//        assertTrue("Counter should increase", second > first);
//    }
//}
//
