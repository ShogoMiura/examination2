package com.examination2.miura.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * 従業員を作成するためのリクエストデータを表すレコードクラスです。
 */
public record CreateEmployeeRequest(
        @JsonProperty("firstName")
        @NotBlank
        @Length(max = 100)
        @Pattern(regexp = "^[a-zA-Z]+$")
        String firstName,

        @JsonProperty("lastName")
        @NotBlank
        @Length(max = 100)
        @Pattern(regexp = "^[a-zA-Z]+$")
        String lastName
) {
}
