package com.example.demo;


import com.example.demo.support.AbstractIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.junit.Test;

public class DemoApplicationTest extends AbstractIntegrationTest {
    @Test
    public void contextLoads() {
    }

    @Test
    public void healthy() {
        RestAssured.given(requestSpecification)
                .when()
                .get("/actuator/health")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.ALL);
    }
}
