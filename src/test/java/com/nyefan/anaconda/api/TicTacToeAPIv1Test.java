package com.nyefan.anaconda.api;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.web.bind.annotation.RequestMapping;

import static io.restassured.RestAssured.given;

@IfProfileValue(name="test-groups", value="integration")
public class TicTacToeAPIv1Test {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(System.getProperty("server.port", "8080"));
        RestAssured.basePath = System.getProperty("server.servlet.context-path", "/anaconda/tictactoe")
                + TicTacToeAPIv1.class.getAnnotation(RequestMapping.class).value()[0];
        RestAssured.baseURI = System.getProperty("server.host", "http://localhost");
    }

    @Test
    public void healthCheckTest() {
        given().when().get("/").then().statusCode(200);
    }

    //TODO: run full set of integration tests
}
