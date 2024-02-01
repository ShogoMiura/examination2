package com.examination2.miura.application;

import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 従業員情報を全件取得するためのユースケースクラスです。
 */
@RequiredArgsConstructor
@Service
public class FindAllEmployeesUseCase {
  private final EmployeeRepository repository;

  /**
   * 全ての従業員情報を取得します。
   *
   * @return Employeeオブジェクトのリスト
   */
  public List<Employee> execute() {
    return repository.findAllEmployees();
  }
}
