package com.examination2.miura.infrastructure.repository;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.examination2.miura.domain.Employee;
import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import com.examination2.miura.infrastructure.exception.DatabaseExecutionException;
import com.examination2.miura.infrastructure.mapper.EmployeeMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class EmployeeRepositoryImplTest {

  @InjectMocks
  EmployeeRepositoryImpl sut;

  @Mock
  EmployeeMapper mapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Nested
  class 全件検索 {
    @Test
    void 全件検索できる場合() {
      // setup
      when(mapper.findAll()).thenReturn(
              List.of(
                      new EmployeeEntity("1", "Taro", "Yamada"),
                      new EmployeeEntity("2", "Jiro", "Yamada")
              )
      );

      List<Employee> expected = List.of(
              new Employee("1", "Taro", "Yamada"),
              new Employee("2", "Jiro", "Yamada")
      );

      // execute
      List<Employee> actual = sut.findAllEmployees();

      // assert
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    void データがない場合() {
      // setup
      when(mapper.findAll()).thenReturn(emptyList());

      List<Employee> expected = emptyList();

      // execute
      List<Employee> actual = sut.findAllEmployees();

      // assert
      assertThat(actual).isEqualTo(expected);
    }
  }

  @Nested
  class ID検索 {
    @Test
    void ID検索できる場合() {
      // setup
      when(mapper.findById("1"))
              .thenReturn(new EmployeeEntity("1", "Taro", "Yamada"));

      Optional<Employee> expected = Optional.of(new Employee("1", "Taro", "Yamada"));

      // execute
      Optional<Employee> actual = sut.findEmployeeById("1");

      // assert
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 指定したIDが存在しない場合() {
      // setup
      when(mapper.findById("99")).thenReturn(null);

      Optional<Employee> expected = Optional.empty();

      // execute
      Optional<Employee> actual = sut.findEmployeeById("99");

      // assert
      assertThat(actual).isEqualTo(expected);
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        1
        2
        """)
  void 次のシーケンスを取得できる(Long expected) {
    // setup
    when(mapper.getNextEmployeeId()).thenReturn(expected);

    // execute
    Long actual = sut.getNextEmployeeId();

    // assert
    assertThat(actual).isEqualTo(expected);
  }

  @Nested
  class 新規登録 {
    @Test
    void 従業員の新規登録が正常に行える場合() {
      // setup
      when(mapper.insert(new EmployeeEntity("3", "Saburo", "Yamada")))
              .thenReturn(1);

      Employee expected = new Employee("3", "Saburo", "Yamada");

      // execute
      Employee actual = sut.create(new Employee("3", "Saburo", "Yamada"));

      // assert
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 従業員の新規登録が正常に行えない場合() {
      // setup
      when(mapper.insert(new EmployeeEntity("3", "Saburo", "Yamada")))
              .thenReturn(0);

      // execute & assert
      assertThatThrownBy(() -> sut.create(new Employee("3", "Saburo", "Yamada")))
              .isInstanceOf(DatabaseExecutionException.class)
              .hasMessage("SQLの実行に失敗しました。");

    }
  }
}
