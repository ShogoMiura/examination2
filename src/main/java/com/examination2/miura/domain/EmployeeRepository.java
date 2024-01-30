package com.examination2.miura.domain;

import com.examination2.miura.infrastructure.entity.EmployeeEntity;

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
   * @return すべての従業員を表すEmployee オブジェクトのリスト。
   */
  List<Employee> findAllEmployees();

  /**
   * データベースから指定した従業員IDの従業員情報を取得します。
   *
   * @param id 従業員ID
   * @return データベースから取得したOptionalのEmployee オブジェクト。従業員が見つからなかった場合は空のOptional。
   */
  Optional<Employee> findEmployeeById(String id);

  /**
   * データベースから次の従業員IDの値を取得します。
   *
   * @return 次の従業員IDの値。
   */
  Long getNextEmployeeId();

  Employee create(EmployeeEntity employeeEntity);
}
