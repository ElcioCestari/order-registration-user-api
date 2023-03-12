package com.elciocestari.resources;

import com.elciocestari.dtos.UserRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.*;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.CREATED;

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

    @Test
    @SneakyThrows
    void save() {
        var dto = new UserRequestDTO("elcio", "elcio_pass");
        given()
                .contentType(JSON)
                .body(new ObjectMapper().writeValueAsString(dto))
                .when().post("/users")
                .then()
                .statusCode(CREATED)
                .body("$", not(hasKey("password")))
                .body("$", not(hasValue(dto.getPassword())))
                .body("username", is(dto.getUsername()));
    }

}