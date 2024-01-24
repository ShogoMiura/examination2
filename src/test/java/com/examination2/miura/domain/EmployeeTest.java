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

  @Test
  void 従業員IDが数字以外の場合に例外が発生する() {
    // execute & assert
    assertThatThrownBy(() -> new Employee("a", "Taro", "Tanaka"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("従業員IDが数字ではありません。");
  }

  @Test
  void 従業員IDが11桁以上の場合に例外が発生する() {
    // execute & assert
    assertThatThrownBy(() -> new Employee("11111111111", "Taro", "Tanaka"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("従業員IDが大きすぎます。");
  }
}
