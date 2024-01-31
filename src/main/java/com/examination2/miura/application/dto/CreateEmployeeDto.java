package com.examination2.miura.application.dto;

/**
 * 従業員を新規登録するためのDTO を表現するレコードクラスです。
 * このクラスは、従業員の名前と姓を保持します。
 *
 * @param firstName 従業員の名前。
 * @param lastName 従業員の姓。
 */
public record CreateEmployeeDto(String firstName, String lastName) {
}
