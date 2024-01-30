package com.examination2.miura.infrastructure.mapper;

import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * 従業員情報をデータベースから取得するためのマッパーインターフェースです。
 */
@Mapper
public interface EmployeeMapper {

  /**
   * 従業員テーブルから全ての従業員情報を取得します。
   *
   * @return 従業員テーブルから取得したEmployeeEntity オブジェクトのリスト。
   */
  @Select("SELECT * FROM employees")
  List<EmployeeEntity> findAll();

  /**
   * 従業員テーブルから指定したIDの従業員情報を取得します。
   *
   * @param id 従業員ID。
   * @return 従業員テーブルから取得したEmployee オブジェクトのリスト。
   */
  @Select("SELECT * FROM employees WHERE id = #{id}")
  EmployeeEntity findById(String id);


  /**
   * 新しい従業員をデータベースに登録します。
   *
   * @param employeeEntity データベースに登録する新しい従業員のエンティティ。
   */
  @Insert("INSERT INTO employees (id, first_name, last_name) "
          + "VALUES (#{id}, #{firstName}, #{lastName})")
  void insert(EmployeeEntity employeeEntity);

  /**
   * データベースから次の従業員IDを取得するメソッドです。
   *
   * @return 次の従業員ID
   */
  @Select("SELECT nextval('EMPLOYEE_ID_SEQ')")
  @Options(flushCache = Options.FlushCachePolicy.TRUE)
  Long getNextEmployeeId();
}
