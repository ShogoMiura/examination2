package com.examination2.miura.domain;

import java.util.List;
import java.util.Optional;

/**
 * 従業員情報に関するクエリを実行するためのリポジトリインターフェースです。
 * このインターフェースは、従業員に関するデータを取得するためのメソッドを提供します。
 */
public interface EmployeeRepository {

  /**
   * すべての従業員のリストを取得します。
   *
   * @return すべての従業員を表す Employee オブジェクトのリスト。
   */
  List<Employee> findAllEmployees();

  Optional<Employee> findEmployeeById(String id);
}
