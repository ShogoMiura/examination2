package com.examination2.miura.application.dto;

/**
 * 従業員を新規作成するためのDTO を表現するレコードクラスです。
 * このクラスは、従業員の名前と姓を保持します。
 */
public record CreateEmployeeDto(String firstName, String lastName) {
}
