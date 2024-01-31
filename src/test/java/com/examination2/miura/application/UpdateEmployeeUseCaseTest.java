package com.examination2.miura.application;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.when;

import com.examination2.miura.application.dto.UpdateEmployeeDto;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UpdateEmployeeUseCaseTest {
  @InjectMocks
  UpdateEmployeeUseCase sut;

  @Mock
  EmployeeRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 従業員を更新できる() {
    // setup
    when(repository.findEmployeeById("1"))
            .thenReturn(Optional.of(new Employee("1", "Taro", "Yamada")));

    UpdateEmployeeDto dto = new UpdateEmployeeDto("1", "Ichiro", null);

    // execute & assert
    assertThatCode(() -> sut.execute(dto))
            .doesNotThrowAnyException();
  }
}