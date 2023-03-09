package com.elciocestari.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
class UserResourceTest {

    @Test
    void users() {
        given()
                .when().get("/users")
                .then()
                .statusCode(200)
                .body("", hasSize(2))
                .body("[0].username", is("username_fake_1"))
                .body("[0].password", is("password_fake_1"))
                .body("[1].username", is("username_fake_2"))
                .body("[1].password", is("password_fake_2"));

    }

}