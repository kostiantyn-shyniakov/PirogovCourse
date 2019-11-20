import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.UserPayload;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class UserApiTests {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "";
    }

    @Test
    void testCanRegisterUser() {
        UserPayload user = new UserPayload()
                .setUsername(RandomStringUtils.randomAlphanumeric(6))
                .setPassword("12345")
                .setEmail("user@gmail.com");

        RestAssured.given().contentType(ContentType.JSON).log().all()
                .body(user)
                .when()
                .post("register")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("id", not(isEmptyString()));
    }
}
