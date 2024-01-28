package com.examination2.miura.presentation.response;

import com.examination2.miura.domain.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 全ての従業員のレスポンスを表すクラスです。
 *
 * @param allEmployeesResponse EmployeeResponse オブジェクトのリスト。
 */
public record AllEmployeesResponse(
        @JsonProperty("employees")
        List<EmployeeResponse> allEmployeesResponse
) {
  public static AllEmployeesResponse of(List<Employee> employees) {
    return null;
  }
}
