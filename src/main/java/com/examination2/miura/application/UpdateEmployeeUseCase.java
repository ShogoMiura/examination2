package com.examination2.miura.application;

import com.examination2.miura.application.dto.UpdateEmployeeDto;
import com.examination2.miura.application.exception.EmployeeNotFoundException;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 従業員情報を更新するユースケースを表すサービスクラスです。
 */
@RequiredArgsConstructor
@Service
public class UpdateEmployeeUseCase {
  private final EmployeeRepository repository;


  /**
   * 指定された従業員IDに基づいて従業員情報を更新します。
   *
   * @param dto 更新する従業員の情報を含むDTOオブジェクト
   * @throws EmployeeNotFoundException 指定された従業員IDに対するデータが見つからない場合にスローされます。
   */
  @Transactional
  public void execute(UpdateEmployeeDto dto) {
    Employee employee = repository.findEmployeeById(dto.id())
            .orElseThrow(() -> new EmployeeNotFoundException(dto.id()));

    repository.updateEmployee(dto.convertToEmployee(employee));
  }
}
