package com.examination2.miura;

import static org.assertj.core.api.Assertions.assertThat;

import com.examination2.miura.presentation.EmployeeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Examination2ApplicationTests {
  @Autowired
  private EmployeeController employeeController;

  @Test
  void contextLoads() {
    assertThat(employeeController).isNotNull();
  }
}
