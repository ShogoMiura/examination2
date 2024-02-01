package com.examination2.miura.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * エラーレスポンスを表すクラスです。
 *
 * @param code エラーコード
 * @param message エラーメッセージ
 * @param details エラーの詳細
 */
public record ErrorResponse(
        @JsonProperty("code")
        String code,
        @JsonProperty("message")
        String message,
        @JsonProperty("details")
        List<String> details
) {
}
