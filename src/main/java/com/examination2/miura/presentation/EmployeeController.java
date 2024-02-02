package com.examination2.miura.presentation;

import com.examination2.miura.application.CreateEmployeeUseCase;
import com.examination2.miura.application.DeleteEmployeeUseCase;
import com.examination2.miura.application.FindAllEmployeesUseCase;
import com.examination2.miura.application.FindEmployeeByIdUseCase;
import com.examination2.miura.application.UpdateEmployeeUseCase;
import com.examination2.miura.application.dto.CreateEmployeeDto;
import com.examination2.miura.application.dto.UpdateEmployeeDto;
import com.examination2.miura.domain.Employee;
import com.examination2.miura.presentation.request.CreateEmployeeRequest;
import com.examination2.miura.presentation.request.UpdateEmployeeRequest;
import com.examination2.miura.presentation.response.AllEmployeesResponse;
import com.examination2.miura.presentation.response.EmployeeResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * HTTPリクエストを処理するコントローラークラスです。
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class EmployeeController {
  private final FindAllEmployeesUseCase findAllEmployeesUseCase;
  private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;
  private final CreateEmployeeUseCase createEmployeeUseCase;
  private final UpdateEmployeeUseCase updateEmployeeUseCase;
  private final DeleteEmployeeUseCase deleteEmployeeUseCase;

  /**
   * ルートエンドポイントへのHTTP GETリクエストを処理します。
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public void getRoot() {
    // do nothing
  }

  /**
   * すべての従業員情報を返します。
   *
   * @return すべての従業員情報を含むAllEmployeesResponseオブジェクト
   */
  @GetMapping("v1/employees")
  @ResponseStatus(HttpStatus.OK)
  public AllEmployeesResponse findAllEmployees() {
    return AllEmployeesResponse.of(findAllEmployeesUseCase.execute());
  }

  /**
   * 指定されたIDの従業員情報を返します。
   *
   * @param id 取得する従業員のID
   * @return 指定されたIDの従業員情報を含むEmployeeResponseオブジェクト
   */
  @GetMapping("v1/employees/{id}")
  @ResponseStatus(HttpStatus.OK)
  public EmployeeResponse findEmployeeById(@PathVariable String id) {
    Employee employee = findEmployeeByIdUseCase.execute(id);
    return EmployeeResponse.of(employee);
  }

  /**
   * 従業員を新規登録します。
   *
   * @param request 作成する従業員の情報を含むリクエストデータ
   * @return 作成された従業員のリソースへのURIとともに、HTTPステータス201を返します
   */
  @PostMapping("v1/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> createEmployee(
          @RequestBody @Validated CreateEmployeeRequest request
  ) {
    Employee employee = createEmployeeUseCase.execute(
            new CreateEmployeeDto(request.firstName(), request.lastName())
    );

    URI uri = UriComponentsBuilder
            .fromUriString(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString())
            .path("/" + employee.id())
            .build()
            .toUri();

    return ResponseEntity.created(uri).build();
  }

  /**
   * 従業員情報を更新します。
   *
   * @param id 更新する従業員のID
   * @param request 更新する従業員の情報を含むリクエストデータ
   */
  @PatchMapping("v1/employees/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateEmployee(
          @PathVariable("id") String id,
          @RequestBody UpdateEmployeeRequest request
  ) {
    updateEmployeeUseCase.execute(
            new UpdateEmployeeDto(id, request.firstName(), request.lastName())
    );
  }

  /**
   * 指定したIDの従業員情報を削除します。
   *
   * @param id 削除する従業員のID
   */
  @DeleteMapping("v1/employees/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteEmployee(@PathVariable("id") String id) {
    deleteEmployeeUseCase.execute(id);
  }
}
