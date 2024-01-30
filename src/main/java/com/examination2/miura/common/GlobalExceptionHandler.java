package com.examination2.miura.common;

import static java.util.Collections.emptyList;

import com.examination2.miura.application.exception.EmployeeNotFoundException;
import com.examination2.miura.presentation.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * アプリケーション全体で発生する例外をハンドリングするための例外処理クラスです。
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  /**
   * EmployeeNotFoundException が発生した際に処理するための例外ハンドラメソッドです。
   * レスポンスとしてはHTTPステータスコード 400 (Bad Request) が設定され、エラーレスポンスが返されます。
   *
   * @param e 発生したEmployeeNotFoundException インスタンス。
   * @return エラーレスポンス。
   */
  @ExceptionHandler(EmployeeNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException e) {
    log.warn("指定されたIDの従業員情報が見つかりません。id = {}", e.getId(), e);
    return new ErrorResponse(
            "0003",
            String.format("specified employee [id = %s] is not found.", e.getId()),
            emptyList()
    );
  }
}
