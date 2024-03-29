package com.examination2.miura.application;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.examination2.miura.domain.Employee;
import com.examination2.miura.domain.EmployeeRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FindAllEmployeesUseCaseTest {

  @InjectMocks
  FindAllEmployeesUseCase sut;

  @Mock
  EmployeeRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 従業員の全件取得が正しく行える場合() {
    // setup
    when(repository.findAllEmployees())
            .thenReturn(List.of(
                    new Employee("1", "Taro", "Yamada"),
                    new Employee("2", "Jiro", "Yamada")
            ));

    List<Employee> expected = List.of(
            new Employee("1", "Taro", "Yamada"),
            new Employee("2", "Jiro", "Yamada")
    );

    // execute
    List<Employee> actual = sut.execute();

    // assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void データが存在しない場合() {
    // setup
    when(repository.findAllEmployees()).thenReturn(emptyList());

    List<Employee> expected = emptyList();

    // execute
    List<Employee> actual = sut.execute();

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
