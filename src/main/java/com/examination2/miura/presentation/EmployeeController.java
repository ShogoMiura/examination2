package com.examination2.miura.presentation;

import com.examination2.miura.application.FindAllEmployeesUseCase;
import com.examination2.miura.application.FindEmployeeByIdUseCase;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.presentation.response.AllEmployeesResponse;
import com.examination2.miura.presentation.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTPリクエストを処理するコントローラークラスです。
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class EmployeeController {
  private final FindAllEmployeesUseCase findAllEmployeesUseCase;
  private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;

  /**
   * ルートエンドポイントへのHTTP GETリクエストを処理します。
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public void getRoot() {
    // do nothing
  }

  /**
   * すべての従業員情報を返します。
   *
   * @return すべての従業員情報を含む AllEmployeesResponseオブジェクト。
   */
  @GetMapping("/v1/employees")
  @ResponseStatus(HttpStatus.OK)
  public AllEmployeesResponse findALlEmployees() {
    return AllEmployeesResponse.of(findAllEmployeesUseCase.execute());
  }

  /**
   * 指定された従業員情報を取得し、EmployeeResponse として返します。
   *
   * @return 指定されたIDの従業員情報を含む EmployeeResponseオブジェクト。
   */
  @GetMapping("v1/employees/{id}")
  @ResponseStatus(HttpStatus.OK)
  public EmployeeResponse findEmployeeById(@PathVariable String id) {
    Employee employee = findEmployeeByIdUseCase.execute(id);
    return EmployeeResponse.of(employee);
  }
}
