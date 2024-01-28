package com.examination2.miura.infrastructure.repository;

import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import com.examination2.miura.infrastructure.mapper.EmployeeMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 従業員リポジトリの実装クラスです。
 */
@RequiredArgsConstructor
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

  private final EmployeeMapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Employee> findAllEmployees() {
    List<EmployeeEntity> employeeEntities = mapper.findAllEmployees();

    List<Employee> result = new ArrayList<>();
    for (EmployeeEntity entity : employeeEntities) {
      result.add(new Employee(entity.id(), entity.firstName(), entity.lastName()));
    }

    return result;
  }

}
