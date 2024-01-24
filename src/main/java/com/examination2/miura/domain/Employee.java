package com.examination2.miura.domain;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * 従業員情報を表すクラスです。
 * このクラスは、従業員のID、名前、姓を保持します。
 *
 * @param id 従業員のID。1 から9999999999 の範囲の数字で、nullであってはいけません。
 * @param firstName 従業員の名前。100 文字以内で、nullであってはいけません。
 * @param lastName 従業員の姓。100 文字以内で、nullであってはいけません。
 */
public record Employee(String id, String firstName, String lastName) {

  /**
   * Employeeクラスのインスタンスを生成します。
   *
   * @param id 従業員のID。1 から9999999999 の範囲の数字で、nullであってはいけません。
   * @param firstName 従業員の名前。100 文字以内で、nullであってはいけません。
   * @param lastName 従業員の姓。100 文字以内で、nullであってはいけません。
   */
  public Employee {
    if (isNull(id)) throw new IllegalArgumentException("従業員IDがnullです。");
    if (!isNumeric(id)) throw new IllegalArgumentException("従業員IDが数字ではありません。");
    if (id.length() > 10 || id.equals("0")) {
      throw new IllegalArgumentException("従業員IDは1 から9999999999 までの数字です。");
    }

    if (isNull(firstName)) throw new IllegalArgumentException("従業員の名前がnullです。");
    if (firstName.length() > 100) throw new IllegalArgumentException("従業員の名前が100文字より大きいです。");

    if (isNull(lastName)) throw new IllegalArgumentException("従業員の姓がnullです。");
    if (lastName.length() > 100) throw new IllegalArgumentException("従業員の姓が100文字より大きいです。");
  }
}
