package com.examination2.miura.application;

import com.examination2.miura.application.dto.CreateEmployeeDto;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CreateEmployeeUseCase は従業員の新規登録を行います。
 */
@RequiredArgsConstructor
@Service
public class CreateEmployeeUseCase {
  private final EmployeeRepository repository;

  /**
   * 従業員の新規登録を実行します。
   *
   * @param dto 新規登録する従業員情報。
   * @return 登録されたEmployee オブジェクト。
   */
  @Transactional
  public Employee execute(CreateEmployeeDto dto) {
    return repository.createEmployee(
            new Employee(
                    repository.getNextEmployeeId().toString(),
                    dto.firstName(),
                    dto.lastName()
            )
    );
  }
}
