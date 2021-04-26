# helidonハンズオンコンテンツ

## Helidon MPとJPA/JTAを利用したRESTサービスの実装

### 事前準備

#### `src/main/resource/META-INF/microprofile-profile.properties`におけるデータソース設定

```
javax.sql.DataSource.test.dataSourceClassName=<データソースクラス名>
```

* 例：`javax.sql.DataSource.test.dataSourceClassName=oracle.jdbc.pool.OracleDataSource`

```
javax.sql.DataSource.test.dataSource.url=<データソースURL名>
```

* 例：`javax.sql.DataSource.test.dataSource.url=jdbc:oracle:thin:@sample_high?TNS_ADMIN=/path/Wallet_sample`

```
javax.sql.DataSource.test.dataSource.user=<データベースユーザ名>
```
* 例：`javax.sql.DataSource.test.dataSource.user=admin`

```
javax.sql.DataSource.test.dataSource.password=<データベースパスワード>
```

* 例：`javax.sql.DataSource.test.dataSource.password=password`

### ビルド方法

```sh
mvn package -DskipTests
```

### 実行方法

```sh
java -jar target/helidon-handson.jar 
```

### サービスへのアクセス例

* 全都道府県取得
```
curl 'http://localhost:8080/prefecture'
```

* IDによる都道府県取得
```
curl 'http://localhost:8080/prefecture/1'
```