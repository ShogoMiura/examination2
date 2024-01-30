package com.examination2.miura.application;

import com.examination2.miura.application.exception.EmployeeNotFoundException;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 指定したIDの従業員情報を取得するためのユースケースクラスです。
 */
@RequiredArgsConstructor
@Service
public class FindEmployeeByIdUseCase {
  private final EmployeeRepository repository;

  /**
   * 指定したIDの従業員情報を取得します。
   *
   * @param id 従業員ID。
   * @return 指定したIDに対応するEmployee オブジェクト。
   * @throws RuntimeException 指定したIDの従業員が存在しない場合に投げられます。
   */
  public Employee execute(String id) {
    return repository.findEmployeeById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
  }
}
