# 実技試験 1

一般的な RESTful API の主な設計原則を満たす、従業員管理 API サーバーを実装・公開してください。

# 実装の詳細
## 概要

下記のエンドポイントを実装してください。

- GET /v1/employees -> 全ての従業員情報の一覧を返します
- GET /v1/employees/{id} -> 指定した ID の従業員情報を返します
- POST /v1/employees -> 指定した従業員情報を登録します
- PATCH /v1/employees/{id} -> 指定した ID の従業員情報を更新します
- DELETE /v1/employees/{id} -> 指定した ID の従業員情報を削除します

その他明示していない詳細は
[Web API: The Good Parts](https://www.amazon.co.jp/exec/obidos/ASIN/4873116864/shohei0823-22)、および
[RESTful Web API の設計](https://learn.microsoft.com/ja-jp/azure/architecture/best-practices/api-design)
を参考にして実装してください。

データベースのスキーマについては、sql/create.sql を使用し、PostgreSQL にデータベースを作成してください。

[Render](https://render.com/) を使用して、アプリをサーバー上にデプロイしてください。  
アプリをデプロイした後、アプリの URL をプロジェクトに用意された test/resources/application.yml の `environment.baseUri` に保存してください。

```
environment:
  baseUri: http://localhost:8080
```

`mvn test -Dtest=RunCucumberTest` をテスト実行し、実装したものが要求を満たしているか確認ください。

# 基本仕様
## GET /v1/employees エンドポイント

データベースの全ての従業員情報を返します。

### 期待するリクエスト形式:

```
GET /v1/employees
```

### 期待するレスポンス形式:

- Header
```
HTTP/1.1 200
```

- Body
```
{
  "employees": [
    {
      "id": "1",
      "firstName": "Taro",
      "lastName": "Yamada"
    },
    {
      "id": "2",
      "firstName": "Jiro",
      "lastName": "Yamada"
    }
  ]
}
```

## GET /v1/employees/{id} エンドポイント

id で指定した従業員情報を返します。

### 期待するリクエスト形式:

```
GET /v1/employees/1
```

### 期待するレスポンス形式:

- Header
```
HTTP/1.1 200
```

- Body
```
{
  "id": "1",
  "firstName": "Taro",
  "lastName": "Yamada"
}
```

## POST /v1/employees エンドポイント

従業員情報を新規に登録します。

### 期待するリクエスト形式:

```
POST /v1/employees
```

- Body
```
{
  "firstName" : "Hanako",
  "lastName" : "Shirato"
}
```

### 期待するレスポンス形式:

** 成功レスポンス: **

- Header
```
HTTP/1.1 201
Location: http://host.domain:port/v1/employees/3
```

** 失敗レスポンス: **
- Header
```
HTTP/1.1 400
```

- Body
```
{
  "code": "0002",
  "message": "request validation error is occurred.",
  "details": [
    "firstName must not be blank"
  ]
}
```


## PATCH /v1/employees/{id} エンドポイント

id で指定した従業員情報を更新します。

### 期待するリクエスト形式:

```
PATCH /v1/employees/1
```

- Body
```
{
  "lastName" : "Yamada"
}
```


### 期待するレスポンス形式:

** 成功レスポンス: **
- Header
```
HTTP/1.1 204
```

** 失敗レスポンス (id で指定された従業員情報が存在しない場合): **
- Header
```
HTTP/1.1 400
```

- Body
```
{
  "code": "0003",
  "message": "specified employee [id = 4] is not found.",
  "details": []
}
```

## DELETE /v1/employees/{id} エンドポイント

id で指定した従業員情報を削除します。

### 期待するリクエスト形式:

```
DELETE /v1/employees/1
```

### 期待するレスポンス形式:

** 成功レスポンス: **
- Header
```
HTTP/1.1 204
```

** 失敗レスポンス (id で指定された従業員情報が存在しない場合): **
- Header
```
HTTP/1.1 400
```

- Body
```
{
  "code": "0003",
  "message": "specified employee [id = 4] is not found.",
  "details": []
}
```
