package com.examination2.miura.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
class EmployeeTest {

  @Test
  void 従業員IDがnullの場合に例外が発生する() {
    // execute & assert
      assertThatThrownBy(() -> new Employee(null, "Taro", "Tanaka"))
              .isInstanceOf(IllegalArgumentException.class)
              .hasMessage("従業員IDがnullです。");
  }
}
