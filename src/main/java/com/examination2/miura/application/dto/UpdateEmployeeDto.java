package com.examination2.miura.application.dto;

/**
 * 従業員情報を更新するためのDTO を表現するレコードクラスです。
 * このクラスは、従業員のidと名前、姓を保持します。
 *
 * @param id 従業員ID。
 * @param firstName 従業員の名前。
 * @param lastName 従業員の姓。
 */
public record UpdateEmployeeDto(String id, String firstName, String lastName) {
}
