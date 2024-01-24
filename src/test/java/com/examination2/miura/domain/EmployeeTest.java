package com.examination2.miura.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
class EmployeeTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
    #     value, message
               , 従業員IDがnullです。
          あいう, 従業員IDが数字ではありません。
            abc, 従業員IDが数字ではありません。
    11111111111, 従業員IDは1 から9999999999 までの数字です。
              0, 従業員IDは1 から9999999999 までの数字です。
    """)
  void 従業員IDのガード条件に違反する場合(String value, String message) {
    // execute & assert
    assertThatThrownBy(() -> new Employee(value, "Taro", "Tanaka"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(message);
  }
}
