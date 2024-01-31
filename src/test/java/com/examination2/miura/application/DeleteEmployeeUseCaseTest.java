package com.examination2.miura.application;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.examination2.miura.application.exception.EmployeeNotFoundException;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteEmployeeUseCaseTest {
  @InjectMocks
  DeleteEmployeeUseCase sut;

  @Mock
  EmployeeRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 従業員を正しく削除できる場合() {
    // setup
    when(repository.findEmployeeById("1"))
            .thenReturn(Optional.of(new Employee("1", "Taro", "Yamada")));

    // execute & assert
    assertThatCode(() -> sut.execute("1"))
            .doesNotThrowAnyException();
  }

  @Test
  void 削除したい従業員が存在しない場合() {
    // setup
    when(repository.findEmployeeById("99"))
            .thenReturn(Optional.empty());

    // execute & assert
    assertThatThrownBy(() -> sut.execute("99"))
            .isInstanceOf(EmployeeNotFoundException.class);
  }
}