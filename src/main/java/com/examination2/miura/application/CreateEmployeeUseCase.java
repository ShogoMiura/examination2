package com.examination2.miura.application;

import com.examination2.miura.application.dto.CreateEmployeeDto;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 従業員の新規登録を行うためのユースケースクラスです。
 */
@RequiredArgsConstructor
@Service
public class CreateEmployeeUseCase {
  private final EmployeeRepository repository;

  /**
   * 従業員を新規登録します。
   *
   * @param dto 新規登録する従業員情報を含むDTOオブジェクト
   * @return 登録されたEmployeeオブジェクト
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
