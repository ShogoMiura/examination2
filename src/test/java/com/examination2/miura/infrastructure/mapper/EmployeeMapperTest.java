package com.examination2.miura.infrastructure.mapper;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

import com.examination2.miura.infrastructure.entity.EmployeeEntity;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import java.sql.DriverManager;
import java.util.List;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

  @Nested
  class 全件検索 {
    @Test
    @DataSet(value = "datasets/setup/employees.yml")
    @ExpectedDataSet(value = "datasets/expected/employees.yml")
    void 従業員の全件検索が正常に行える場合() {
      // setup
      List<EmployeeEntity> expected = List.of(
              new EmployeeEntity("1", "Taro", "Yamada"),
              new EmployeeEntity("2", "Jiro", "Yamada")
      );

      // execute
      List<EmployeeEntity> actual = sut.findAll();

      // assert
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DataSet(value = "datasets/setup/empty-employees.yml")
    @ExpectedDataSet(value = "datasets/expected/empty-employees.yml")
    void 全件検索の結果従業員が存在しない場合() {
      // setup
      List<EmployeeEntity> expected = emptyList();

      // execute
      List<EmployeeEntity> actual = sut.findAll();

      // assert
      assertThat(actual).isEqualTo(expected);
    }
  }

  @Nested
  class ID検索 {
    @Test
    @DataSet(value = "datasets/setup/employees.yml")
    @ExpectedDataSet(value = "datasets/expected/employees.yml")
    void 従業員のID検索が正常に行える場合() {
      // setup
      EmployeeEntity expected = new EmployeeEntity("1", "Taro", "Yamada");

      // execute
      EmployeeEntity actual = sut.findById("1");

      // assert
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DataSet(value = "datasets/setup/employees.yml")
    @ExpectedDataSet(value = "datasets/expected/employees.yml")
    void 指定したIDの従業員が存在しない場合() {
      // execute
      EmployeeEntity actual = sut.findById("99");

      // assert
      assertThat(actual).isNull();
    }
  }

  @DataSet(value = "datasets/setup/employees.yml")
  @ExpectedDataSet(value = "datasets/expected/after-insert-employees.yml")
  @Test
  void 従業員の新規登録が正常に行える場合() {
    // setup
    Integer expected = 1;

    // execute
    Integer actual = sut.insert(new EmployeeEntity("3", "Saburo", "Yamada"));

    // assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DataSet(value = "datasets/setup/employees.yml")
  @ExpectedDataSet(value = "datasets/expected/employees.yml")
  void 次の従業員IDのシーケンスが取得できる場合() {
    // setup
    Long expected = 3L;

    // execute
    Long actual = sut.getNextEmployeeId();

    // assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DataSet(value = "datasets/setup/employees.yml")
  @ExpectedDataSet(value = "datasets/expected/after-update-employees.yml")
  void 従業員情報を正しく更新できる場合() {
    // setup
    EmployeeEntity entity = new EmployeeEntity("1", "Ichiro", "Yamada");
    Integer expected = 1;

    // execute
    Integer actual = sut.update(entity);

    // assert
    assertThat(actual).isEqualTo(expected);
  }
}
