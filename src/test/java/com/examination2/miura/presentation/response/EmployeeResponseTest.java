package com.examination2.miura.presentation.response;

import com.examination2.miura.domain.Employee;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeResponseTest {
  @Test
  void EmployeeからEmployeeResponseが作成できる() {
    // setup
    Employee employee = new Employee("1", "Taro", "Yamada");

    EmployeeResponse expected = new EmployeeResponse("1", "Taro", "Yamada");

    // execute
    EmployeeResponse actual = EmployeeResponse.of(employee);

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}