package com.example.MPR;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class MyRestAssureControllerTest {

    private static final String URI = "http://localhost:8080";

    @Test
    public void testGetUserById(){
        when()
                .get(URI + "/users/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1))
                .body("username", equalTo("username1"))
                .log()
                .body();
    }

    @Test
    public void testAddUser(){
        with()
                .body(new UserDTO("usernameTest4", "email@test",54))
                .contentType("application/json")
                .when()
                .post("/users/add")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void testDeleteUser() {
        given()
                .pathParam("id", 5)
                .when()
                .delete("/users/delete/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdateUser() {
        UserDTO updatedUserData = new UserDTO("updatedUsername", "updatedEmail@test", 30);

        given()
                .contentType("application/json")
                .body(updatedUserData)
                .pathParam("id", 3)
                .when()
                .put("/users/update/{id}")
                .then()
                .statusCode(200);
    }
}
