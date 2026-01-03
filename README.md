# 🎮 セーブ・ロードシステム

高校生でもわかる！Javaでゲームのセーブ・ロード機能を作ろう！

## 📖 このプロジェクトについて

このプロジェクトは、**ファイル入出力**を使ってゲームのセーブ・ロード機能を学ぶためのものです。
RPGゲームのプレイヤーデータを**ファイルに保存**して、**後で読み込む**ことができます。

### 🎯 学べること
- ✅ ファイルの読み書き（FileWriter, FileReader）
- ✅ try-with-resources構文
- ✅ 例外処理（IOException）
- ✅ データの永続化（プログラム終了後もデータが残る）

## 📁 プロジェクト構成

```
save_load_system/
├── src/
│   ├── com/javaquest/
│   │   ├── model/
│   │   │   └── Player.java          # プレイヤークラス
│   │   └── manager/
│   │       └── SaveManager.java     # セーブ・ロード管理
│   └── practice/
│       ├── NameSaver.java           # 練習1：名前だけ保存
│       └── StatusSaver.java         # 練習2：ステータス保存
├── data/                            # セーブデータ保存先
│   ├── player_name.txt             # プレイヤー名
│   └── status.txt                  # ステータスデータ
└── README.md                       # このファイル
```

## 🚀 実行方法

### 1. コンパイル
```bash
# Windowsの場合
javac -d bin src/practice/*.java src/com/javaquest/*/*.java

# macOS/Linuxの場合
javac -d bin src/practice/*.java src/com/javaquest/*/*.java
```

### 2. 実行

#### 練習1：名前の保存・読み込み
```bash
java -cp bin practice.NameSaver
```

#### 練習2：ステータスの保存・読み込み
```bash
java -cp bin practice.StatusSaver
```

#### メインシステム（プレイヤー全体の保存・読み込み）
```bash
java -cp bin com.javaquest.manager.SaveManager
```

## 💡 各クラスの説明

### 🏃‍♂️ Player.java
RPGのプレイヤーを表すクラスです。

**持っているデータ：**
- 名前（例：「勇者」）
- HP（体力）
- 最大HP
- レベル
- 武器（例：「鉄の剣」）
- 防具（例：「革の防具」）

**できること：**
```java
Player player = new Player("勇者", 100, 1);
player.setWeapon("鉄の剣");
player.displayStatus(); // ステータス表示
```

### 💾 SaveManager.java
プレイヤーデータの保存・読み込みを管理するクラスです。

**できること：**
```java
// セーブ
SaveManager.save(player, "save_data.txt");

// ロード
Player loadedPlayer = SaveManager.load("save_data.txt");
```

**保存されるファイルの中身：**
```
勇者
80
100
5
鉄の剣
革の防具
```

### 📝 NameSaver.java（練習用）
名前だけを保存・読み込みする簡単なクラスです。

**使い方：**
```java
NameSaver.saveName("勇者アレン", "data/player_name.txt");
String name = NameSaver.loadName("data/player_name.txt");
```

### 📊 StatusSaver.java（練習用）
HP、MP、レベルをCSV形式で保存・読み込みするクラスです。

**使い方：**
```java
StatusSaver.save(100, 50, 5, "data/status.txt");
int[] status = StatusSaver.load("data/status.txt");
```

**保存されるファイルの中身：**
```
100,50,5
```

## 🔧 重要なポイント

### 1. try-with-resources構文
ファイルを扱うときは必ず使いましょう！

```java
// ✅ 正しい書き方
try (FileWriter writer = new FileWriter("file.txt")) {
    writer.write("データ");
    // 自動でファイルが閉じられる
} catch (IOException e) {
    System.out.println("エラー: " + e.getMessage());
}
```

### 2. 例外処理
ファイル操作は失敗する可能性があるので、必ずtry-catchで囲みます。

### 3. データの形式
- **1行1データ**：名前、HP、レベルなどを1行ずつ保存
- **CSV形式**：カンマ区切りで複数データを1行に保存

## 🎮 実行例

### NameSaverの実行結果
```
========== 名前保存テスト ==========

--- 名前保存 ---
名前を保存: 勇者アレン -> data/player_name.txt

--- 名前読み込み ---
名前を読み込み: 勇者アレン <- data/player_name.txt

--- 結果 ---
プレイヤー名: 勇者アレン
テスト成功！名前の保存と読み込みが正常に動作しました。
```

### StatusSaverの実行結果
```
========== ステータス保存テスト ==========

--- ステータス保存 ---
ステータス保存完了: HP=100, MP=50, Level=5

--- ステータス読み込み ---
ステータス読み込み完了: HP=100, MP=50, Level=5

--- 結果 ---
HP: 100 (体力)
MP: 50 (魔法力)
Level: 5 (レベル)

テスト成功！CSV形式でのステータス保存と読み込みが正常に動作しました。
```

## 🛠️ トラブルシューティング

### Q: 「ファイルが見つかりません」エラーが出る
**A:** `data`フォルダが存在するか確認してください。存在しない場合は作成してください。

### Q: 文字化けする
**A:** Windowsの場合、ファイルの文字コードがShift_JISになっている可能性があります。UTF-8で保存し直してください。

### Q: コンパイルエラーが出る
**A:** パッケージ構造が正しいか確認してください。`src`フォルダ内に`com/javaquest/`フォルダがあるか確認してください。

## 📚 次のステップ

このプロジェクトをマスターしたら、以下にチャレンジしてみましょう！

1.**複数のセーブスロット**：save_slot1.txt, save_slot2.txt など
2.**JSON形式での保存**：より構造化されたデータ形式
3.**暗号化**：セーブデータを暗号化して改ざんを防ぐ
4.**データベース**：SQLiteなどのデータベースを使用

---

**頑張って学習しましょう！わからないことがあれば、コードのコメントを読み返してみてください。** 