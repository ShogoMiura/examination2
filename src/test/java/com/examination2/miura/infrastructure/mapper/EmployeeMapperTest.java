package com.examination2.miura.infrastructure.mapper;


import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.database.rider.core.api.connection.ConnectionHolder;

import java.sql.DriverManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DBUnit
@DBRider
class EmployeeMapperTest {
  private static final String DB_URL = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false";
  private static final String DB_USER = "user";
  private static final String DB_PASSWORD = "password";

  private static final ConnectionHolder connectionHolder =
          () -> DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

  @Autowired
  private EmployeeMapper sut;

  @BeforeAll
  static void setUpAll() {
    Flyway.configure().dataSource(DB_URL, DB_USER, DB_PASSWORD).load().migrate();
  }

  @Test
  @DataSet(value = "datasets/setup/employees.yml")
  void 従業員の全件検索が正常に行える場合() {
    // setup
    List<EmployeeEntity> expected = List.of(
            new EmployeeEntity("1", "Taro", "Yamada"),
            new EmployeeEntity("2", "Jiro", "Yamada")
    );

    // execute
    List<EmployeeEntity> actual = sut.findAllEmployees();

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}