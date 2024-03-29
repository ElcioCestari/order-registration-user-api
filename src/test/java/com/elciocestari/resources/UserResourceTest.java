package com.elciocestari.resources;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.*;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserResourceTest {

    private static UserResponseDTO userResponseDTO;

    @Test
    @Order(1)
    void users() {
        given()
                .when().get("/users")
                .then()
                .statusCode(OK)
                .body("$", everyItem(hasKey("username")))
                .body("$", everyItem(not(hasKey("password"))));
    }

    @Test
    @Order(2)
    @SneakyThrows
    void save() {
        var dto = new UserRequestDTO(random(12), random(12));
        userResponseDTO = given()
                .contentType(JSON)
                .body(new ObjectMapper().writeValueAsString(dto))
                .when().post("/users")
                .then()
                .statusCode(CREATED)
                .body("$", not(hasKey("password")))
                .body("$", not(hasValue(dto.getPassword())))
                .body("username", is(dto.getUsername())).extract().body().as(UserResponseDTO.class);
        assertNotNull(userResponseDTO);
    }

    @Test
    @Order(3)
    @SneakyThrows
    void findOne() {
        given()
                .contentType(JSON)
                .when().get("/users/" + userResponseDTO.getUsername())
                .then()
                .statusCode(OK)
                .body("$", not(hasKey("password")));
    }


    @Test
    @Order(4)
    @SneakyThrows
    void update() {
        var dto = new UserRequestDTO(userResponseDTO.getUsername(), "new_pass");
        given()
                .contentType(JSON)
                .body(new ObjectMapper().writeValueAsString(dto))
                .when().put("/users/" + userResponseDTO.getUsername())
                .then()
                .statusCode(OK)
                .body("$", not(hasKey("password")))
                .body("$", not(hasValue(dto.getPassword())));
    }

    @Test
    @Order(5)
    @SneakyThrows
    void delete() {
        given()
                .contentType(JSON)
                .when().delete("/users/" + userResponseDTO.getUsername())
                .then()
                .statusCode(NO_CONTENT);
    }

    @Test
    @Order(6)
    @SneakyThrows
    void save_whenAlreadyExistsAnUser_thenThrowException() {
        var dto = new UserRequestDTO(random(12), random(12));
        userResponseDTO = given()
                .contentType(JSON)
                .body(new ObjectMapper().writeValueAsString(dto))
                .when().post("/users")
                .then()
                .extract().body().as(UserResponseDTO.class);
        assertNotNull(userResponseDTO);

        given()
                .contentType(JSON)
                .body(new ObjectMapper().writeValueAsString(new UserRequestDTO(dto.getUsername(), dto.getPassword())))
                .when().post("/users")
                .then()
                .statusCode(BAD_REQUEST);

        given()
                .contentType(JSON)
                .when().delete("/users/" + userResponseDTO.getUsername())
                .then()
                .statusCode(NO_CONTENT);
    }

}