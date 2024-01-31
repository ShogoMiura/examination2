package com.examination2.miura.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * 従業員情報を更新するためのリクエストデータを表すレコードクラスです。
 *
 * @param firstName 更新する従業員の名前。
 * @param lastName 更新する従業員の姓。
 */
public record UpdateEmployeeRequest(
        @JsonProperty("firstName")
        @Length(max = 100)
        @Pattern(regexp = "^[a-zA-Z]+$")
        String firstName,

        @JsonProperty("lastName")
        @Length(max = 100)
        @Pattern(regexp = "^[a-zA-Z]+$")
        String lastName
) {
}
