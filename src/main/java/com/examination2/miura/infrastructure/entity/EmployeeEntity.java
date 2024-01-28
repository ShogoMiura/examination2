package com.examination2.miura.infrastructure.entity;

import com.examination2.miura.domain.Employee;

/**
 * 従業員情報を表すエンティティクラスです。
 * このクラスは、従業員のID、名前、姓を保持します。
 *
 * @param id 従業員のID
 * @param firstName 従業員の名前
 * @param lastName 従業員の姓
 */
public record EmployeeEntity(String id, String firstName, String lastName) {

  public Employee convertToEmployee() {
    return null;
  }
}
