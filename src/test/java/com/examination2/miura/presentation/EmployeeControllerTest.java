package com.examination2.miura.presentation;

import static com.examination2.miura.JsonUtils.marshalToJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import com.examination2.miura.application.CreateEmployeeUseCase;
import com.examination2.miura.application.FindAllEmployeesUseCase;
import com.examination2.miura.application.FindEmployeeByIdUseCase;
import com.examination2.miura.application.UpdateEmployeeUseCase;
import com.examination2.miura.application.dto.CreateEmployeeDto;
import com.examination2.miura.application.dto.UpdateEmployeeDto;
import com.examination2.miura.application.exception.EmployeeNotFoundException;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.presentation.request.UpdateEmployeeRequest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class EmployeeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  FindAllEmployeesUseCase findAllEmployeesUseCase;

  @MockBean
  FindEmployeeByIdUseCase findEmployeeByIdUseCase;

  @MockBean
  CreateEmployeeUseCase createEmployeeUseCase;

  @MockBean
  UpdateEmployeeUseCase updateEmployeeUseCase;

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

  @Nested
  class ID検索 {
    @ParameterizedTest
    @CsvSource(textBlock = """
            1,Taro,Yamada
            2,Jiro,Yamada
            """)
    void 指定したIDで従業員情報が正しく取得できる(String id, String firstName, String lastName) {
      // setup
      when(findEmployeeByIdUseCase.execute(id))
              .thenReturn(new Employee(id, firstName, lastName));

      // execute & assert
      given()
              .when()
              .get("/v1/employees/" + id)
              .then()
              .statusCode(200)
              .body("id", is(id))
              .body("firstName", is(firstName))
              .body("lastName", is(lastName));
    }

    @Test
    void 指定したIDで従業員情報が取得できない場合() {
      // setup
      when(findEmployeeByIdUseCase.execute("99"))
              .thenThrow(new EmployeeNotFoundException("99"));

      // execute & assert
      given()
              .when()
              .get("/v1/employees/99")
              .then()
              .statusCode(400)
              .body("code", is("0003"))
              .body("message", is("specified employee [id = 99] is not found."))
              .body("details", is(Collections.EMPTY_LIST));
    }
  }

  @Nested
  class 新規登録 {
    @Test
    void 従業員情報の新規登録が正しく行える場合() {
      // setup
      when(createEmployeeUseCase.execute(new CreateEmployeeDto("Saburo", "Yamada")))
              .thenReturn(new Employee("3", "Saburop", "Yamada"));

      // execute & assert
      given()
              .contentType(MediaType.APPLICATION_JSON_VALUE)
              .body(marshalToJson((new CreateEmployeeDto("Saburo", "Yamada"))))
              .when()
              .post("/v1/employees")
              .then()
              .statusCode(201)
              .header("Location", is("http://localhost/v1/employees/3"));
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
             %, Yamada, 1
            '', Yamada, 2
            '',     '', 4
            """)
    void 従業員情報の新規登録の際にバリデーションエラーが発生した場合(
            String firstName, String lastName, Integer sizeOfDetails
    ) {
      // execute & assert
      given()
              .contentType(MediaType.APPLICATION_JSON_VALUE)
              .body(marshalToJson((new CreateEmployeeDto(firstName, lastName))))
              .when()
              .post("/v1/employees")
              .then()
              .statusCode(400)
              .body("code", is("0002"))
              .body("message", is("request validation error is occurred."))
              .body("details", hasSize(sizeOfDetails));
    }
  }

  @Test
  void 従業員情報が正しく更新できる場合() {
    // execute & assert
    given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(marshalToJson(new UpdateEmployeeRequest("Ichiro", "Tanaka")))
            .when()
            .post("/v1/employees/1")
            .then()
            .statusCode(204)
            .body(is(""));
  }
}
