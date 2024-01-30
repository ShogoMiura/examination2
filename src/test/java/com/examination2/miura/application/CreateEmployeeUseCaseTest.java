package com.examination2.miura.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.examination2.miura.application.dto.CreateEmployeeDto;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CreateEmployeeUseCaseTest {
  @InjectMocks
  CreateEmployeeUseCase sut;

  @Mock
  EmployeeRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 従業員の新規登録ができる() {
    // setup
    when(repository.getNextEmployeeId())
            .thenReturn(3L);

    when(repository.createEmployee(new Employee("3", "Saburo", "Yamada")))
            .thenReturn(new Employee("3", "Saburo", "Yamada"));

    Employee expected = new Employee("3", "Saburo", "Yamada");

    CreateEmployeeDto dto = new CreateEmployeeDto("Saburo", "Yamada");

    // execute
    Employee actual = sut.execute(dto);

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}