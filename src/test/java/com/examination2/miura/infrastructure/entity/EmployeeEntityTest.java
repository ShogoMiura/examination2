package com.examination2.miura.infrastructure.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.examination2.miura.domain.Employee;
import org.junit.jupiter.api.Test;

class EmployeeEntityTest {

  @Test
  void 正しくEmployeeに変換できる() {
    // setup
    EmployeeEntity sut = new EmployeeEntity("1", "Taro", "Yamada");
    Employee expected = new Employee("1", "Taro", "Yamada");

    // execute
    Employee actual = sut.convertToEmployee();

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
