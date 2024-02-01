package com.examination2.miura.application.exception;

import lombok.Getter;

/**
 * 従業員が見つからない場合にスローされる例外クラスです。
 */
@Getter
public class EmployeeNotFoundException extends RuntimeException {

  /**
   * 従業員ID。
   */
  private final String id;

  /**
   * 指定された従業員IDで新しいEmployeeNotFoundExceptionを構築します。
   *
   * @param id 例外が発生した従業員のID
   */
  public EmployeeNotFoundException(String id) {
    super();
    this.id = id;
  }
}
