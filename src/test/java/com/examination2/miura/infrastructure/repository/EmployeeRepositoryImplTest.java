package com.examination2.miura.infrastructure.repository;

import com.examination2.miura.domain.Employee;
import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import com.examination2.miura.infrastructure.mapper.EmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class EmployeeRepositoryImplTest {

  @InjectMocks
  EmployeeRepositoryImpl sut;

  @Mock
  EmployeeMapper mapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 全件検索が正しく行える() {
    // setup
    when(mapper.findAllEmployees()).thenReturn(
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

}