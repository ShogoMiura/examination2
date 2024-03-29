package com.examination2.miura.infrastructure.repository;

import static java.util.Objects.nonNull;

import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import com.examination2.miura.infrastructure.exception.DatabaseExecutionException;
import com.examination2.miura.infrastructure.mapper.EmployeeMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 従業員リポジトリの実装クラスです。
 */
@Slf4j
@RequiredArgsConstructor
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
  private final EmployeeMapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Employee> findAllEmployees() {
    return mapper.findAll().stream().map(EmployeeEntity::convertToEmployee).toList();
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

  /**
   * {@inheritDoc}
   */
  @Override
  public Long getNextEmployeeId() {
    return mapper.getNextEmployeeId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Employee createEmployee(Employee employee) {
    Integer num = mapper.insert(
            new EmployeeEntity(employee.id(), employee.firstName(), employee.lastName())
    );
    isFailedSqlExecution(num);

    return employee;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateEmployee(Employee employee) {
    Integer num = mapper.update(
            new EmployeeEntity(employee.id(), employee.firstName(), employee.lastName())
    );
    isFailedSqlExecution(num);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteEmployee(String id) {
    Integer num = mapper.delete(id);
    isFailedSqlExecution(num);
  }

  private void isFailedSqlExecution(Integer num) {
    if (num != 1) {
      log.error("SQLの実行に失敗しました。");
      throw new DatabaseExecutionException("SQLの実行に失敗しました。");
    }
  }
}
