# language: ja

機能: 従業員情報管理

  シナリオ:ルートURLにアクセスできる
    前提適切なBaseURIが指定されている
    もしルートURLにアクセスする
    ならばHTTPステータスコードとして200が返却される

  シナリオ:従業員情報を一覧できる
    前提適切なBaseURIが指定されている
    もしすべての従業員情報を取得する
    ならばHTTPステータスコードとして200が返却される
    かつContentTypeとして"application/json"が返却される
    かつ[ID、名字、名前]がリストで返却される

  シナリオ:指定したIDの従業員情報が取得できる
     前提適切なBaseURIが指定されている
     もしIDを指定して従業員情報を取得する
     ならばHTTPステータスコードとして200が返却される
     かつContentTypeとして"application/json"が返却される
     かつ[ID、名字、名前]がオブジェクトで返却される

   シナリオ:存在しないIDで従業員情報を取得すると、エラーが返却される
     前提適切なBaseURIが指定されている
     もし存在しないIDで従業員情報を取得する
     ならばHTTPステータスコードとして400が返却される
     かつcodeとして"0003"が返却される
     かつmessageとして"specified employee [id = %s] is not found."が返却される
     かつdetailsとして空のリストが返却される