package com.examination2.miura.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
class EmployeeTest {
  private static final String TOO_LONG_NAME
          = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
          "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

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

  @Test
  void 従業員のfirstNameがnullの場合例外が発生する() {
    // execute & assert
    assertThatThrownBy(() -> new Employee("1", null, "Tanaka"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("従業員の名前がnullです。");
  }

  @Test
  void 従業員のfirstNameが100文字を超える場合例外が発生する() {
    // execute & assert
    assertThatThrownBy(() -> new Employee("1", TOO_LONG_NAME, "Tanaka"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("従業員の名前が100文字より大きいですです。");
  }

  @Test
  void 従業員のlastNameがnullの場合例外が発生する() {
    // execute & assert
    assertThatThrownBy(() -> new Employee("1", "Taro", null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("従業員の姓がnullです。");
  }

  @Test
  void 従業員のlastNameが100文字を超える場合例外が発生する() {
    // execute & assert
    assertThatThrownBy(() -> new Employee("1", "Taaro", TOO_LONG_NAME))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("従業員の姓が100文字より大きいです。");
  }
}
