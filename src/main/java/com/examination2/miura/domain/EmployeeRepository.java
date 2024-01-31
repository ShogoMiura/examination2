package com.examination2.miura.domain;

import com.examination2.miura.infrastructure.exception.DatabaseExecutionException;
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

  /**
   * データベースに従業員情報を登録します。
   *
   * @param employee 登録する従業員の情報を含むEmployee オブジェクト。
   * @return 登録された従業員の情報を含むEmployee オブジェクト。
   * @throws DatabaseExecutionException SQLの実行に失敗した場合に投げられます。
   */
  Employee createEmployee(Employee employee);

  /**
   * 従業員テーブルから指定したIDの従業員情報を更新します。
   *
   * @param employee 更新する従業員の情報。
   * @throws DatabaseExecutionException SQLの実行に失敗した場合に投げられます。
   */
  void updateEmployee(Employee employee);

  /**
   * 従業員テーブルから指定したIDの従業員情報を削除します。
   *
   * @param id 削除する従業員ID。
   * @throws DatabaseExecutionException SQLの実行に失敗した場合に投げられます。
   */
  void deleteEmployee(String id);
}
