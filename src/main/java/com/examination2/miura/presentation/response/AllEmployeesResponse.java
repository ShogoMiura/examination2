package com.examination2.miura.presentation.response;

import com.examination2.miura.domain.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 全ての従業員情報のレスポンスを表すクラスです。
 *
 * @param allEmployeesResponse EmployeeResponseオブジェクトのリスト
 */
public record AllEmployeesResponse(
        @JsonProperty("employees")
        List<EmployeeResponse> allEmployeesResponse
) {
  /**
   * EmployeeオブジェクトのリストからAllEmployeesResponseオブジェクトを生成するためのファクトリーメソッドです。
   * リスト内の各Employeeオブジェクトは、EmployeeResponse.ofメソッドを使用して対応するEmployeeResponseオブジェクトに変換されます。
   *
   * @param employees 変換元のEmployeeオブジェクトのリスト
   * @return 生成されたAllEmployeesResponseオブジェクト
   */
  public static AllEmployeesResponse of(List<Employee> employees) {
    return new AllEmployeesResponse(employees.stream().map(EmployeeResponse::of).toList());
  }
}
