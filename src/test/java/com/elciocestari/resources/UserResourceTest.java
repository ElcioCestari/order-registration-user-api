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
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.OK;

@QuarkusTest
class UserResourceTest {

    @Test
    void users() {
        given()
                .when().get("/users")
                .then()
                .statusCode(OK)
                .body("$", everyItem(hasKey("username")))
                .body("$", everyItem(not(hasKey("password"))));
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