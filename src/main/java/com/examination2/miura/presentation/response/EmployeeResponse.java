package com.examination2.miura.presentation.response;

import com.examination2.miura.domain.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 従業員情報のレスポンスを表すクラスです。
 *
 * @param id        従業員のID。
 * @param firstName 従業員の名前。
 * @param lastName  従業員の姓。
 */
public record EmployeeResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("firstName")
        String firstName,
        @JsonProperty("lastName")
        String lastName
) {
  public static EmployeeResponse of(Employee employee) {
    return null;
  }
}
