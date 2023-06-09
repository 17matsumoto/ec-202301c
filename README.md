# デリバリーピザECサイト
このプロジェクトはJavaで開発されたECサイトです。ユーザーは商品を閲覧し、カートに追加し、注文を確定することができます。


## システム概要
* ユーザー認証: ユーザーはアカウントを作成し、ログインすることができます。
* 商品閲覧: ユーザーは商品を閲覧することができます。
* 商品詳細: ユーザは商品の詳細からトッピングの追加、個数を選択できます。
* カート管理: ユーザーはカートに商品を追加、削除することができます。
* 注文処理: ユーザーはカートの中身を確認し、注文を確定することができます。
## 必要なソフトウェアとライブラリー

# <img width="461" alt="image" src="https://github.com/17matsumoto/ec-202301c/assets/115763759/7deeb6c3-1700-467c-b295-f423ba7ab6b7">

## インストールとセットアップ
1. プロジェクトをクローンする :　git clone https://github.com/17matsumoto/ec-202301c.git
2. データベースを作成する。: Postgresqlで新しいデータベースを作成し、接続情報を設定します。
3. データベースの設定を更新する: src/main/resources/application.yml ファイル内のデータベース接続情報を更新します。
4. プロジェクトを更新する: gradleを更新します。
5. アプリケーションを実行する: spring boot　Appを実行します。
6. ブラウザでアプリケーションにアクセスします: http://localhost:8080

## 使用方法
1. アカウントの作成: サイトにアクセスし、新しいアカウントを作成します。
2. ログイン: 作成したアカウントでログインします。
3. 商品一覧: 商品を選択して閲覧します。
4. カートへの追加: 商品の詳細ページで「トッピングの追加」→「数量」→「カートに入れる」ボタンをクリックします。
5. カートの管理: ヘッダーのカートアイコンからカートの中身を確認し、必要に応じて商品を削除します。
6. 注文処理: カートの中身を確認し、必要情報を入力し、注文を確定します。

