package com.examination2.miura.infrastructure.mapper;

import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface EmployeeMapper {
  List<EmployeeEntity> findAllEmployees();
}
