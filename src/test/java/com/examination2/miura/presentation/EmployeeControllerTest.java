package com.examination2.miura.presentation;

import com.examination2.miura.application.FindAllEmployeesUseCase;
import com.examination2.miura.domain.Employee;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;


@WebMvcTest
class EmployeeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  FindAllEmployeesUseCase findAllEmployeesUseCase;

  @BeforeEach
  void setUp() {
    RestAssuredMockMvc.mockMvc(mockMvc);
  }

  @Test
  void ルートURLへのアクセスを受け付ける() {
    // execute & assert
    given()
            .when()
            .get("/")
            .then()
            .statusCode(200);
  }

  @Test
  void 全従業員の情報が正常に取得できる場合() {
    // setup
    when(findAllEmployeesUseCase.execute())
            .thenReturn(
                    List.of(
                            new Employee("1", "Taro", "Yamada"),
                            new Employee("2", "Jiro", "Yamada")
                    )
            );

    // execute & assert
    given()
            .when()
            .get("/v1/employees")
            .then()
            .statusCode(200)
            .assertThat()
            .body("employees.size()", is(2))
            .body("employees[0].id", is("1"))
            .body("employees[0].firstName", is("Taro"))
            .body("employees[0].lastName", is("Yamada"))
            .body("employees[1].id", is("2"))
            .body("employees[1].firstName", is("Jiro"))
            .body("employees[1].lastName", is("Yamada"));
  }
}
