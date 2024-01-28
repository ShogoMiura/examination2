package com.examination2.miura.presentation;

import com.examination2.miura.application.FindAllEmployeesUseCase;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.presentation.response.AllEmployeesResponse;
import com.examination2.miura.presentation.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * HTTPリクエストを処理するコントローラークラスです。
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class EmployeeController {
  private final FindAllEmployeesUseCase findAll;

  /**
   * ルートエンドポイントへのHTTP GETリクエストを処理します。
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public void getRoot() {
    // do nothing
  }

  @GetMapping("/v1/employees")
  @ResponseStatus(HttpStatus.OK)
  public AllEmployeesResponse findALlEmployees() {
    List<Employee> employeeList = findAll.execute();
    List<EmployeeResponse> result = new ArrayList<>();
    for (Employee employee : employeeList) {
      result.add(new EmployeeResponse(employee.id(), employee.firstName(), employee.lastName()));
    }

    AllEmployeesResponse response = new AllEmployeesResponse(result);
    return response;
  }
}
