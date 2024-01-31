package com.examination2.miura.application.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.examination2.miura.domain.Employee;
import org.junit.jupiter.api.Test;

class UpdateEmployeeDtoTest {
  private static final Employee EMPLOYEE = new Employee("1", "Taro", "Yamada");
  @Test
  void 抜けのないdtoを正しくEmployeeに変換できる() {
    // setup
    UpdateEmployeeDto sut = new UpdateEmployeeDto("1", "Ichiro", "Tanaka");
    Employee expected = new Employee("1", "Ichiro", "Tanaka");

    // execute
    Employee actual = sut.convertToEmployee(EMPLOYEE);

    // assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void 抜けのあるdtoを正しくEmployeeに変換できる() {
    // setup
    UpdateEmployeeDto sut = new UpdateEmployeeDto("1", "Ichiro", null);
    Employee expected = new Employee("1", "Ichiro", "Yamada");

    // execute
    Employee actual = sut.convertToEmployee(EMPLOYEE);

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
