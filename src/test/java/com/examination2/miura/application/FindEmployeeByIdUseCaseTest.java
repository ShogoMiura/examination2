package com.examination2.miura.application;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


class FindEmployeeByIdUseCaseTest {
  @InjectMocks
  FindEmployeeByIdUseCase sut;

  @Mock
  EmployeeRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 指定したIDの従業員が正常に取得できる場合() {
    // setup
    when(repository.findEmployeeById("1"))
            .thenReturn(Optional.of(new Employee("1", "Taro", "Yamada")));

    Employee expected = new Employee("1", "Taro", "Yamada");

    //execute
    Employee actual = sut.execute("1");

    // assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void 指定したIDの従業員が取得できない場合() {
    // setup
    when(repository.findEmployeeById("99")).thenReturn(Optional.empty());

    //execute & assert
    assertThatThrownBy(() -> sut.execute("99"))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("指定したIDの従業員は見つかりません。");
  }
}