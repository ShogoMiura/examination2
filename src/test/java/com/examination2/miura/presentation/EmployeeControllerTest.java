package com.examination2.miura.presentation;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;


@WebMvcTest
class EmployeeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    RestAssuredMockMvc.mockMvc(mockMvc);
  }

  @Test
  void ルートURLへのアクセスを受け付ける() {
    given()
            .when()
            .get("/")
            .then()
            .statusCode(200);
  }
}
