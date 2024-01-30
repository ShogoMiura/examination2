package com.examination2.miura.infrastructure.repository;

import static java.util.Objects.nonNull;

import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import com.examination2.miura.infrastructure.mapper.EmployeeMapper;
import java.util.List;
import java.util.Optional;
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
    return mapper.findAll().stream()
            .map(EmployeeEntity::convertToEmployee)
            .toList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Employee> findEmployeeById(String id) {
    if (nonNull(mapper.findById(id))) {
      return Optional.of(mapper.findById(id).convertToEmployee());
    }
    return Optional.empty();
  }
}
