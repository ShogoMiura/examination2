package com.examination2.miura.presentation.response;

import static org.assertj.core.api.Assertions.assertThat;

import com.examination2.miura.domain.Employee;
import org.junit.jupiter.api.Test;

class EmployeeResponseTest {
  @Test
  void EmployeeからEmployeeResponseが生成できる場合() {
    // setup
    Employee employee = new Employee("1", "Taro", "Yamada");

    EmployeeResponse expected = new EmployeeResponse("1", "Taro", "Yamada");

    // execute
    EmployeeResponse actual = EmployeeResponse.of(employee);

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
