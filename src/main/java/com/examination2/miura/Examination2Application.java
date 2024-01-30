package com.examination2.miura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot アプリケーションの起動クラスです。
 * このクラスは、SpringBootアプリケーションのエントリーポイントとして使用されます。
 */
@SpringBootApplication
public class Examination2Application {

  /**
   * アプリケーションのエントリーポイントを提供するメソッドです。
   * このメソッドは、Springアプリケーションの実行を開始し、指定されたクラスをコンテキストにロードします。
   *
   * @param args アプリケーションの起動時に渡されるコマンドライン引数。
   */
  public static void main(String[] args) {
    SpringApplication.run(Examination2Application.class, args);
  }
}
