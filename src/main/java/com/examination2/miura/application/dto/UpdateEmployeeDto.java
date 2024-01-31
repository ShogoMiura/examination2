package com.examination2.miura.application.dto;

import static java.util.Objects.nonNull;

import com.examination2.miura.domain.Employee;

/**
 * 従業員情報を更新するためのDTO を表現するレコードクラスです。
 * このクラスは、従業員のidと名前、姓を保持します。
 *
 * @param id        従業員ID。
 * @param firstName 従業員の名前。
 * @param lastName  従業員の姓。
 */
public record UpdateEmployeeDto(String id, String firstName, String lastName) {

  /**
   * 指定されたEmployee オブジェクトを使用して、従業員情報を更新します。
   * 更新する際に、新しい情報が提供されている場合はそれを使用し、提供されていない場合は元の情報を保持します。
   *
   * @param employee 更新対象の従業員情報を含むEmployee オブジェクト
   * @return 更新された従業員情報を反映した新しいEmployee オブジェクト
   */
  public Employee convertToEmployee(Employee employee) {
    String updateFirstName;
    String updateLastName;

    if (nonNull(this.firstName)) {
      updateFirstName = this.firstName;
    } else {
      updateFirstName = employee.firstName();
    }

    if (nonNull(this.lastName)) {
      updateLastName = this.lastName;
    } else {
      updateLastName = employee.lastName();
    }

    return new Employee(id, updateFirstName, updateLastName);
  }
}
