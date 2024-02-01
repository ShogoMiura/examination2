package com.examination2.miura;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * TestUtils はテストで使用するユーティルクラスです。
 */
@Slf4j
@UtilityClass
public class TestUtils {

  /**
   * 指定されたオブジェクトをJSON文字列に変換します。
   *
   * @param obj JSONに変換するオブジェクト。
   * @return 対応するJSON文字列。変換に失敗したら空文字を返します。
   */
  public static String marshalToJson(Object obj) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.warn("JSONへの変換に失敗しました。", e);
    }
    return "";
  }
}