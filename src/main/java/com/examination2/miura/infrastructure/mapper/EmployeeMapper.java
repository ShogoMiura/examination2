package com.examination2.miura.infrastructure.mapper;

import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 従業員情報をデータベースから取得するためのマッパーインターフェースです。
 */
@Mapper
public interface EmployeeMapper {

  /**
   * データベースから次の従業員IDを取得します。
   *
   * @return 次の従業員ID
   */
  @Select("SELECT nextval('EMPLOYEE_ID_SEQ')")
  @Options(flushCache = Options.FlushCachePolicy.TRUE)
  Long getNextEmployeeId();

  /**
   * データベースから全ての従業員情報を取得します。
   *
   * @return 従業員テーブルから取得したEmployeeEntityオブジェクトのリスト
   */
  @Select("SELECT * FROM employees")
  List<EmployeeEntity> findAll();

  /**
   * データベースから指定したIDの従業員情報を取得します。
   *
   * @param id 取得する従業員のID
   * @return 従業員テーブルから取得したEmployeeオブジェクトのリスト
   */
  @Select("SELECT * FROM employees WHERE id = #{id}")
  EmployeeEntity findById(String id);

  /**
   * 新しい従業員をデータベースに登録します。
   *
   * @param employeeEntity データベースに登録する新しい従業員のエンティティ
   * @return 新規作成の件数
   */
  @Insert("INSERT INTO employees (id, first_name, last_name) "
          + "VALUES (#{id}, #{firstName}, #{lastName})")
  Integer insert(EmployeeEntity employeeEntity);

  /**
   * データベースの指定したIDの従業員情報を更新します。
   *
   * @param employeeEntity 更新する従業員の情報
   * @return 更新の件数
   */
  @Update("UPDATE employees SET first_name = #{firstName}, last_name = #{lastName} "
          + "WHERE id = #{id}")
  Integer update(EmployeeEntity employeeEntity);

  /**
   * データベースから指定したIDの従業員情報を削除します。
   *
   * @param id 削除する従業員のID
   * @return 削除の件数
   */
  @Delete("DELETE FROM employees WHERE id = #{id}")
  Integer delete(String id);
}
