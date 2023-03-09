package com.elciocestari.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class UserResourceTest {

    @Test
    void users() {
        given()
          .when().get("/users")
          .then()
             .statusCode(200)
             .body(is("users"));
    }

}