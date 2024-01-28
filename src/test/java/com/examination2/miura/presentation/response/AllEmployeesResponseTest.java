package com.examination2.miura.presentation.response;

import com.examination2.miura.domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AllEmployeesResponseTest {
  @Test
  void EmployeeのListからAllEmployeesResponseが生成できる場合() {
    // setup
    List<Employee> employees = List.of(
            new Employee("1", "Taro", "Yamada"),
            new Employee("2", "Jiro", "Yamada")
    );

    AllEmployeesResponse expected = new AllEmployeesResponse(
            List.of(
                    new EmployeeResponse("1", "Taro", "Yamada"),
                    new EmployeeResponse("2", "Jiro", "Yamada")
            )
    );

    // execute
    AllEmployeesResponse actual = AllEmployeesResponse.of(employees);

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
