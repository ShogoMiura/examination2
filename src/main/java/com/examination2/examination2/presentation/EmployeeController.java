package com.examination2.examination2.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTPリクエストを処理するコントローラークラスです。
 */
@RestController
@RequestMapping("/")
public class EmployeeController {

    /**
     * ルートエンドポイントへのHTTP GETリクエストを処理します。
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getRoot() {

    }
}
