package com.examination2.miura.infrastructure.mapper;

import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 従業員情報をデータベースから取得するためのマッパーインターフェースです。
 */
@Mapper
public interface EmployeeMapper {

  /**
   * 従業員テーブルから全ての従業員情報を取得します。
   *
   * @return 従業員テーブルから取得したEmployeeEntity オブジェクトのリスト
   */
  @Select("SELECT * FROM employees")
  List<EmployeeEntity> findAllEmployees();
}