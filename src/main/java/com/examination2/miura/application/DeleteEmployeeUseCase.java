package com.examination2.miura.application;

import com.examination2.miura.application.exception.EmployeeNotFoundException;
import com.examination2.miura.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 指定したIDの従業員情報を削除するためのユースケースクラスです。
 */
@RequiredArgsConstructor
@Service
public class DeleteEmployeeUseCase {
  private final EmployeeRepository repository;

  /**
   * 指定したIDの従業員情報を削除します。
   *
   * @param id 削除する従業員のID
   * @throws EmployeeNotFoundException 指定された従業員IDに対するデータが見つからない場合にスローされます
   */
  @Transactional
  public void execute(String id) {
    if (repository.findEmployeeById(id).isEmpty()) throw new EmployeeNotFoundException(id);

    repository.deleteEmployee(id);
  }
}
