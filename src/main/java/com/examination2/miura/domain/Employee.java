package com.examination2.miura.domain;

import static java.util.Objects.isNull;

/**
 * 従業員情報を表すレコードクラスです。
 * このクラスは、従業員のID、名前、姓を保持します。
 *
 * @param id 従業員のID。1 から9999999999 までの数字で、nullであってはいけません。
 * @param firstName 従業員の名前。100 文字以内で、nullであってはいけません。
 * @param lastName 従業員の姓。100 文字以内で、nullであってはいけません。
 */
public record Employee(String id, String firstName, String lastName) {

  public Employee(String id, String firstName, String lastName) {
    if (isNull(id)) throw new IllegalArgumentException("従業員IDがnullです。");

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
