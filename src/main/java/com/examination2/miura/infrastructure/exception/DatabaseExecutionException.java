package com.examination2.miura.infrastructure.exception;

/**
 * SQLの実行中にエラーが発生した場合にスローされる例外クラスです。
 */
public class DatabaseExecutionException extends RuntimeException {

  /**
   * 指定されたエラーメッセージで新しい DatabaseExecutionExceptionを構築します。
   *
   * @param message エラーメッセージ。
   */
  public DatabaseExecutionException(String message) {
    super(message);
  }
}
